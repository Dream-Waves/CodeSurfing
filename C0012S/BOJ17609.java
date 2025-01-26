/*
17609. Gold 5 - 회문

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	        512 MB           32070	    9129      6741	         29.080%


    문제
        회문(回文) 또는 팰린드롬(palindrome)은 앞 뒤 방향으로 볼 때 같은 순서의 문자로 구성된 문자열을 말한다. 예를 들어 ‘abba’ ‘kayak’, ‘reviver’, ‘madam’은 모두 회문이다. 만일 그 자체는 회문이 아니지만 한 문자를 삭제하여 회문으로 만들 수 있는 문자열이라면 우리는 이런 문자열을 “유사회문”(pseudo palindrome)이라고 부른다. 예를 들어 ‘summuus’는 5번째나 혹은 6번째 문자 ‘u’를 제거하여 ‘summus’인 회문이 되므로 유사회문이다.
        여러분은 제시된 문자열을 분석하여 그것이 그 자체로 회문인지, 또는 한 문자를 삭제하면 회문이 되는 “유사회문”인지, 아니면 회문이나 유사회문도 아닌 일반 문자열인지를 판단해야 한다. 만일 문자열 그 자체로 회문이면 0, 유사회문이면 1, 그 외는 2를 출력해야 한다.


    입력
        입력의 첫 줄에는 주어지는 문자열의 개수를 나타내는 정수 T(1 ≤ T ≤ 30)가 주어진다. 다음 줄부터 T개의 줄에 걸쳐 한 줄에 하나의 문자열이 입력으로 주어진다. 주어지는 문자열의 길이는 3 이상 100,000 이하이고, 영문 알파벳 소문자로만 이루어져 있다.


    출력
        각 문자열이 회문인지, 유사 회문인지, 둘 모두 해당되지 않는지를 판단하여 회문이면 0, 유사 회문이면 1, 둘 모두 아니면 2를 순서대로 한 줄에 하나씩 출력한다.


    예제 입력 1
        7
        abba
        summuus
        xabba
        xabbay
        comcom
        comwwmoc
        comwwtmoc
    예제 출력 1
        0
        1
        1
        2
        2
        0
        1


    알고리즘 분류
        문자열
        두 포인터
*/


// 메모리 : 30268KB
// 시간 : 288ms
// 코드 길이 : 3335B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {
    static int T; // 주어지는 문자열의 개수 (1 ≤ T ≤ 30)
    static boolean flag; // 한 문자열의 앞 뒤 방향으로 볼 때 같은 순서의 문자가 서로 다른 문자의 포함 여부
    static StringBuilder sb;

    public static int distinguish(String sentence) { // 문자열이 회문인지, 유사회문인지 또는 일반 문자열인지를 구별하는 메서드
        int rightIndex = sentence.length() - 1; // 뒤 방향으로 볼 때의 순서를 나타내는 인덱스
        for (int s = 0, size = sentence.length() / 2; s <= size; s++) {
            if (sentence.charAt(s) != sentence.charAt(rightIndex)) { // 앞 방향으로 볼 때의 순서의 문자와 뒤 방향으로 볼 때의 순서의 문자가 다를 경우
                if (flag) { // 이미 문자열에 앞 뒤 방향으로 볼 때 같은 순서의 문자가 서로 다른 문자가 포함되어 있을 경우
                    return 2; // 일반 문자열
                }

                flag = true; // 문자열에 앞 뒤 방향으로 볼 때의 순서의 문자가 서로 다른 문자가 포함되어 있음을 체크

                // 한 문자를 삭제하면 회문이 되는지 검사
                if (sentence.charAt(s + 1) == sentence.charAt(rightIndex)) { // 앞 방향으로 볼 때의 순서의 문자를 삭제했다고 가정 후, 앞 방향으로 볼때의 순서의 문자와 뒤 방향으로 볼 때의 순서의 문자가 같을 경우
                    if (distinguish(sentence.substring(s + 1, rightIndex + 1)) == 1) { // 회문일 경우
                        return 1; // 유사회문
                    }
                }

                if (sentence.charAt(s) == sentence.charAt(rightIndex - 1)) { // 뒤 방향으로 볼 때의 순서의 문자를 삭제했다고 가정 후, 앞 방향으로 볼때의 순서의 문자와 뒤 방향으로 볼 때의 순서의 문자가 같을 경우
                    if (distinguish(sentence.substring(s, rightIndex)) == 1) { // 회문일 경우
                        return 1; // 유사회문
                    }
                    else { // 회문이 아닐 경우
                        return 2; // 일반 문자열
                    }
                }
                else { // 한 문자를 삭제하더라도 앞 방향으로 볼때의 순서의 문자와 뒤 방향으로 볼 때의 순서의 문자가 같지 않을 경우
                    return 2; // 일반 문자열
                }
            }

            rightIndex -= 1;
        }

        if (flag) { // 한 문자를 삭제하면 회문일 경우
            return 1; // 유사회문
        }
        else { // 한 문자를 삭제하지 않더라도 회문일 경우
            return 0; // 회문
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());

        sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            flag = false;
            sb.append(distinguish(bf.readLine()));
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
