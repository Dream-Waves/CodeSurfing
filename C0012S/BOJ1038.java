/*
1038. Gold 5 - 감소하는 수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           30080	    9833      7758	         34.875%


    문제
        음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면, 그 수를 감소하는 수라고 한다. 예를 들어, 321과 950은 감소하는 수지만, 322와 958은 아니다. N번째 감소하는 수를 출력하는 프로그램을 작성하시오. 0은 0번째 감소하는 수이고, 1은 1번째 감소하는 수이다. 만약 N번째 감소하는 수가 없다면 -1을 출력한다.


    입력
        첫째 줄에 N이 주어진다. N은 1,000,000보다 작거나 같은 자연수 또는 0이다.


    출력
        첫째 줄에 N번째 감소하는 수를 출력한다.


    예제 입력 1
        18
    예제 출력 1
        42

    예제 입력 2
        0
    예제 출력 2
        0

    예제 입력 3
        500000
    예제 출력 3
        -1


    알고리즘 분류
        브루트포스 알고리즘
        백트래킹
*/


// 메모리 : 14320KB
// 시간 : 128ms
// 코드 길이 : 1585B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ1038 {
    static int N; // 감소하는 수의 순서
    static ArrayList<Long> numberList; // 감소하는 수를 저장하는 리스트
    static StringBuilder sb;

    public static void make(int index, long number) { // 감소하는 수를 만드는 메서드
        if (index > 10) { // 자릿수가 10보다 클 경우
            return;
        }

        numberList.add(number);
        for (long i = 0, can = number % 10; i < can; i++) { // number의 뒤에는 number를 10으로 나눈 나머지 can보다 작은 수만 올 수 있다.
            make(index + 1, (number * 10) + i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        numberList = new ArrayList<>();
        sb = new StringBuilder();
        if (N > 1022) { // X의 최댓값 9876543210, X의 최댓값의 자리 수 1022
            sb.append(-1);
        }
        else if (N == 1022) {
            sb.append("9876543210");
        }
        else {
            for (int n = 0; n < 10; n++) { // 0부터 9까지의 각 숫자로 시작하는 감소하는 수 만들기
                make(1, n);
            }

            Collections.sort(numberList); // 오름차순 정렬

            sb.append(numberList.get(N));
        }

        System.out.println(sb);
    }
}
