/*
9935. Gold 4 - 문자열 폭발

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초 (추가 시간 없음)	        128 MB           80206	    21312     15114	         26.150%


    문제
        상근이는 문자열에 폭발 문자열을 심어 놓았다. 폭발 문자열이 폭발하면 그 문자는 문자열에서 사라지며, 남은 문자열은 합쳐지게 된다.

        폭발은 다음과 같은 과정으로 진행된다.
            · 문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
            · 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
            · 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.

        상근이는 모든 폭발이 끝난 후에 어떤 문자열이 남는지 구해보려고 한다. 남아있는 문자가 없는 경우가 있다. 이때는 "FRULA"를 출력한다.

        폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.


    입력
        첫째 줄에 문자열이 주어진다. 문자열의 길이는 1보다 크거나 같고, 1,000,000보다 작거나 같다.
        둘째 줄에 폭발 문자열이 주어진다. 길이는 1보다 크거나 같고, 36보다 작거나 같다.
        두 문자열은 모두 알파벳 소문자와 대문자, 숫자 0, 1, ..., 9로만 이루어져 있다.


    출력
        첫째 줄에 모든 폭발이 끝난 후 남은 문자열을 출력한다.


    예제 입력 1
        mirkovC4nizCC44
        C4
    예제 출력 1
        mirkovniz

    예제 입력 2
        12ab112ab2ab
        12ab
    예제 출력 2
        FRULA


    알고리즘 분류
        자료 구조
        문자열
        스택
*/


// 메모리 : 63016KB
// 시간 : 620ms
// 코드 길이 : 2338B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {
    static String str; // 문자열 (1 ≤ 문자열의 길이 ≤ 1000000)
    static String explosion; // 폭발 문자열 (1 ≤ 폭발 문자열의 길이 ≤ 36)
    static Stack<Character> stack; // 폭발한 문자열의 문자들을 담는 스택

    public static void bomb() { // 문자열 폭발을 수행하는 메서드
        int explosionLen = explosion.length(); // 폭발 문자열의 길이

        stack = new Stack<>();
        for (int s = 0, strLen = str.length(); s < strLen; s++) {
            Character now = str.charAt(s);

            stack.push(now);

            if (stack.size() >= explosionLen) { // 스택에 있는 문자의 개수가 폭발 문자열의 길이 이상일 경우
                boolean flag = true; // 스택에 폭발 문자열과 같은 문자열의 존재 여부

                // 스택에 폭발 문자열과 같은 문자열이 존재하는지 검사
                for (int c = 0; c < explosionLen; c++) {
                    if (stack.get(stack.size() - explosionLen + c) != explosion.charAt(c)) {
                        flag = false;
                        break;
                    }
                }

                // 스택에 폭발 문자열과 같은 문자열이 존재할 경우, 스택에서 제거
                if (flag) {
                    for (int p = 0; p < explosionLen; p++) {
                        stack.pop();
                    }
                }
            }
        }
    }

    public static void print() { // 모든 폭발이 끝난 후 남은 문자열을 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        if (stack.isEmpty()) { // 문자열 폭발 후 남아 있는 문자가 없을 경우
            System.out.println("FRULA");
        }
        else {
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            System.out.println(sb.reverse());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        str = bf.readLine();
        explosion = bf.readLine();

        bomb();
        print();
    }
}
