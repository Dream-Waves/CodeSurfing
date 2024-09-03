package Jieun714;
/**
 * 문제: 오로지 <br>, <hr> 태그와 그 외 평문으로만 주어지는 HTML 문서가 있을 때, 그 결과를 보여주는 코드를 작성하시오.
 *      한 줄에는 80자보다 많은 글자가 출력되어서는 안 된다.
 * 입력: 원래의 HTML 문서가 입력으로 주어진다. 이 텍스트는 단어와 HTML 태그들로 이루어져 있으며, 태그는 한 개 이상의 공백문자나 탭, 개행 문자 등으로 구분된다.
 *      단어는 연속된 알파벳, 숫자, 또는 문장 부호들이다. 예를 들어, "abc,123"은 하나의 단어이지만, "abc, 123"은 "abc,", "123" 두 단어이다.
 *      단어는 항상 80글자 이하이며, '<'나 '>'를 포함하지 않는다. 입력에 등장하는 태그는 <br>과 <hr> 외에는 없다.
 * 출력: 다음과 같은 규칙에 맞게 출력해야 한다.
 *       - 이번에 출력할 단어를 출력하고 나서도 현재 줄이 80글자를 넘지 않으면 현재 줄에 출력해도 좋다. 단, 80글자를 넘어가게 된다면 새로운 줄에 출력해야 한다.
 *       - <br> 태그를 읽게 되면, 새 줄을 시작한다.
 *       - <hr> 태그를 읽게 되면, 이미 줄의 첫 부분이 아니라면 새 줄을 시작한 뒤, '-'를 한 줄에 80글자를 입력한다. 그 후 다시 새 줄을 시작한다.
 *      마지막 줄은 개행 문자로 끝난다.
 *      여러 개의 연속된 개행 문자, 공백 문자, 탭 문자는 하나의 공백문자로 출력한다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6581 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        StringBuilder sb = new StringBuilder();
        int cnt = 0; //현재 줄에 있는 글자수

        while((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                if(s.equals("<br>")){ //새 줄 시작
                    sb.append("\n");
                    cnt = 0;
                } else if (s.equals("<hr>")) { //'-' 80글자 입력
                    if(cnt != 0) sb.append("\n"); //줄의 첫 부분이 아니라면 새 줄 시작
                    sb.append("-".repeat(80)).append("\n"); //'-' 80글자 입력
                    cnt = 0;
                } else{
                    int now = cnt + s.length() + 1; //한 줄에 채워지는 글자수
                    if(80 < now) { //80글자가 넘어갈 때
                        sb.append("\n").append(s); //새 줄에 글자 추가
                        cnt = s.length();
                    } else {
                        if(cnt != 0) sb.append(" "); //줄의 첫 부분이 아니라면 공백 추가
                        sb.append(s);
                        cnt = now;
                    }
                }
            }
        }
        System.out.println(sb); //결과 출력
    }
}