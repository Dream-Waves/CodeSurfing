/*
7490. Gold 5 - 0 만들기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           9529	    4756      3561	         48.834%


    문제
        1부터 N까지의 수를 오름차순으로 쓴 수열 1 2 3 ... N을 생각하자.
        그리고 '+'나 '-', 또는 ' '(공백)을 숫자 사이에 삽입하자(+는 더하기, -는 빼기, 공백은 숫자를 이어 붙이는 것을 뜻한다). 이렇게 만든 수식의 값을 계산하고 그 결과가 0이 될 수 있는지를 살피자.
        N이 주어졌을 때 수식의 결과가 0이 되는 모든 수식을 찾는 프로그램을 작성하라.


    입력
        첫 번째 줄에 테스트 케이스의 개수가 주어진다(<10).
        각 테스트 케이스엔 자연수 N이 주어진다(3 <= N <= 9).


    출력
        각 테스트 케이스에 대해 ASCII 순서에 따라 결과가 0이 되는 모든 수식을 출력한다. 각 테스트 케이스의 결과는 한 줄을 띄워 구분한다.


    예제 입력 1
        2
        3
        7
    예제 출력 1
        1+2-3

        1+2-3+4-5-6+7
        1+2-3-4+5+6-7
        1-2 3+4+5+6+7
        1-2 3-4 5+6 7
        1-2+3+4-5+6-7
        1-2-3-4-5+6+7


    알고리즘 분류
        구현
        문자열
        브루트포스 알고리즘
        백트래킹
*/


// 메모리 : 32704KB
// 시간 : 364ms
// 코드 길이 : 2851B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ7490 {
    static int N; // 자연수 (3 ≤ N ≤ 9)
    static String operator[] = {"+", "-", " "}; // 수식에 사용되는 연산자를 저장한 배열
    static ArrayList<String> answerFormularList; // 수식의 결과가 0이 되는 수식을 저장한 리스트
    static StringBuilder sb;

    public static void calculate(String formular, String expression) { // 수식의 결과를 계산하는 메서드
        String numberList[] = expression.split("\\+|-"); // 수식의 결과를 계산할 때 사용되는 수를 저장한 배열

        int result = Integer.parseInt(numberList[0]); // 수식의 결과
        int index = numberList[0].length(); // 연산자가 수식에 위치하는 인덱스
        for (int m = 1, num = numberList.length; m < num; m++) {
            if (expression.charAt(index) == '+') {
                result += Integer.parseInt(numberList[m]);
            }
            else if (expression.charAt(index) == '-') {
                result -= Integer.parseInt(numberList[m]);
            }

            index += (1 + numberList[m].length());
        }

        if (result == 0) {
            answerFormularList.add(formular);
        }
    }

    public static void create(String formular, String expression, int number) { // '+', '-', ' ' 중 연산자를 골라 수식을 만들고, 만든 수식의 값을 계산하는 메서드  // create(연산자를 골라 만든 수식, ' ' 연산자를 처리한 수식, 수식에 사용할 수)
        if (number > N) {
            calculate(formular, expression);

            return;
        }

        for (int o = 0; o < 3; o++) {
            if (operator[o].equals(" ")) {
                create(formular + operator[o] + number, expression + number, number + 1);
            }
            else {
                create(formular + operator[o] + number, expression + operator[o] + number, number + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 수 (T < 10)

        sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(bf.readLine());

            answerFormularList = new ArrayList<>();
            create("1", "1", 2);

            Collections.sort(answerFormularList); // ASCII 순서에 따라 정렬
            for (String answerFormular : answerFormularList) {
                sb.append(answerFormular);
                sb.append("\n");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
