/*
6581. Gold 5 - HTML

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           3280	    517       384	         21.658%


    문제
        오로지 <br>, <hr> 태그와 그 외 평문으로만 주어지는 HTML 문서가 있을 때, 그 결과를 보여주는 코드를 작성하시오.
        한 줄에는 80자보다 많은 글자가 출력되어서는 안 된다.


    입력
        원래의 HTML 문서가 입력으로 주어진다. 이 텍스트는 단어와 HTML 태그들로 이루어져 있으며, 태그는 한 개 이상의 공백문자나 탭, 개행 문자 등으로 구분된다.
        단어는 연속된 알파벳, 숫자, 또는 문장 부호들이다. 예를 들어, "abc,123"은 하나의 단어이지만, "abc, 123"은 "abc,", "123" 두 단어이다. 단어는 항상 80글자 이하이며, '<'나 '>'를 포함하지 않는다. 입력에 등장하는 태그는 <br>과 <hr> 외에는 없다.


    출력
        다음과 같은 규칙에 맞게 출력해야 한다.
            · 이번에 출력할 단어를 출력하고 나서도 현재 줄이 80글자를 넘지 않으면 현재 줄에 출력해도 좋다. 단, 80글자를 넘어가게 된다면 새로운 줄에 출력해야 한다.
            · <br> 태그를 읽게 되면, 새 줄을 시작한다.
            · <hr> 태그를 읽게 되면, 이미 줄의 첫 부분이 아니라면 새 줄을 시작한 뒤, '-'를 한 줄에 80글자를 입력한다. 그 후 다시 새 줄을 시작한다.

        마지막 줄은 개행 문자로 끝난다.
        여러 개의 연속된 개행 문자, 공백 문자, 탭 문자는 하나의 공백문자로 출력한다.


    예제 입력 1
        Hallo, dies ist eine
        ziemlich lange Zeile, die in Html
        aber nicht umgebrochen wird.
        <br>
        Zwei <br> <br> produzieren zwei Newlines.
        Es gibt auch noch das tag <hr> was einen Trenner darstellt.
        Zwei <hr> <hr> produzieren zwei Horizontal Rulers.
        Achtung       mehrere Leerzeichen irritieren

        Html genauso wenig wie


        mehrere Leerzeilen.
    예제 출력 1
        Hallo, dies ist eine ziemlich lange Zeile, die in Html aber nicht umgebrochen
        wird.
        Zwei

        produzieren zwei Newlines. Es gibt auch noch das tag
        --------------------------------------------------------------------------------
        was einen Trenner darstellt. Zwei
        --------------------------------------------------------------------------------
        --------------------------------------------------------------------------------
        produzieren zwei Horizontal Rulers. Achtung mehrere Leerzeichen irritieren Html
        genauso wenig wie mehrere Leerzeilen.


    알고리즘 분류
        문자열
        파싱
*/


// 메모리 : 14280KB
// 시간 : 100ms
// 코드 길이 : 2461B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6581 {
    static int nowLength; // 현재 줄의 글자수
    static StringBuilder sb;

    public static boolean changeBR(String str) { // <br> 태그를 새 줄로 변환하는 메서드
        if (str.equals("<br>")) {
            sb.append("\n");
            nowLength = 0;

            return true;
        }

        return false;
    }

    public static boolean changeHR(String str) { // <hr> 태그를 줄의 첫 부분이 아닐 경우 새 줄을 시작한 뒤 '-'를 한 줄에 80 글자를 입력한 후 다시 새 줄을 시작하도록 변환하는 메서드
        if (str.equals("<hr>")) {
            if (nowLength != 0) { // 줄의 첫 부분이 아닐 경우
                sb.append("\n"); // 새 줄 시작
            }

            sb.append("-".repeat(80));
            sb.append("\n");
            nowLength = 0;

            return true;
        }

        return false;
    }

    public static void changeText(String str) { // 입력받은 HTML 문서의 한 줄을 변환하는 메서드
        for (String word : str.split("[ \n\t]+")) { // 공백 문자, 개행 문자, 탭 문자 기준으로 구분
            if (word.equals("")) { // 여러 개의 연속된 개행 문자, 공백 문자, 탭 문자는 하나의 공백 문자로 출력해야 하므로 해당 조건문으로 제어
                continue;
            }

            if (!changeBR(word) && !changeHR(word)) {
                if ((nowLength + word.length() + 1) > 80) { // 현재 줄의 글자수가 80 글자를 넘어갈 경우
                    sb.append("\n");
                    nowLength = 0;
                }

                if (nowLength != 0) { // 각 줄의 끝에는 공백이 없어야 하므로 해당 조건문을 통해 단어 사이에 필요한 공백 문자 제어
                    sb.append(" ");
                    nowLength += 1;
                }

                sb.append(word);
                nowLength += word.length();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String text; // HTML 문서의 한 줄
        sb = new StringBuilder();
        while ((text = bf.readLine()) != null) {
            changeText(text);
        }

        System.out.println(sb);
    }
}
