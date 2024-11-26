/*
15922. Gold 5 - 아우으 우아으이야!!

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           2914	    1859      1496	         65.672%


    문제
        아우으 우아으이야!! 으어아아아아아아아ㅏㅏㅏ아아앙ㅇ아아ㅏ
        수직선 위에 선분을 여러 개 그릴 거 야아아앙ㅇ아아아ㅏㅏ아아ㅏㅏ!!
        선분을 겹치게 그리는 것도 가능하다아어으우어우으아아아아아아아아아이야!!!!1
        선분을 모두 그렸을 때, 수직선 위에 그려진 선분 길이의 총합은 얼마아아으으우어으이으야이야!!!!


    입력
        첫째 줄에 수직선 위에 그릴 선분의 개수 N이 주어진다아우으 우아으이야!!. (1 ≤ N ≤ 100,000)
        둘째 줄 부터 N개의 줄에 좌표를 나타내는 정수쌍 (x, y)가 주어진다으어아아아아아아아ㅏㅏㅏ아아앙ㅇ아아.
        이는 [x, y] 구간 (x와 y를 포함하는 구간)에 선분을 그린다는 의미이다유아아우응아이양.
        좌표는 x가 증가하는 순으로, x가 같다면 y가 증가하는 순으로 주어진다으우오아앙아ㅓㅇ아ㅡㅇ. (-1,000,000,000 ≤ x < y ≤ 1,000,000,000)


    출력
        N개의 선분을 모두 그렸을 때, 수직선 위에 그어진 선분 길이의 총합을 출력한다아아어으잉에애야우아으아이아야아아아아아아이야!!!


    예제 입력 1
        5
        -5 -2
        -3 0
        2 5
        6 10
        8 12
    예제 출력 1
        14

    예제 입력 2
        2
        -1000000000 1000000000
        -1 1
    예제 출력 2
        2000000000


    알고리즘 분류
        스위핑
*/


// 메모리 : 39956KB
// 시간 : 344ms
// 코드 길이 : 1500B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15922 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine()); // 수직선 위에 그릴 선분의 개수 (1 ≤ N ≤ 100000)

        StringTokenizer token;
        int maxNext = Integer.MIN_VALUE; // n 번째 입력 전까지의 y 좌표의 최댓값
        int lengthSum = 0; // N 개의 선분을 모두 그렸을 때, 수직선 위에 그어진 선분 길이의 총합
        for (int n = 0; n < N; n++) {
            token = new StringTokenizer(bf.readLine());
            int now = Integer.parseInt(token.nextToken()); // x 좌표
            int next = Integer.parseInt(token.nextToken()); // y 좌표

            if (now >= maxNext) { // x 좌표가 직전 입력 좌표까지의 y 좌표의 최댓값보다 크거나 같을 경우
                lengthSum += (next - now);
            }
            else if (next > maxNext) { // x 좌표가 직전 입력 좌표까지의 y 좌표의 최댓값보다 크거나 같지 않고, y 좌표가 직전 입력 좌표까지의 y 좌표의 최댓값보다 클 경우
                lengthSum += (next - maxNext);
            }

            maxNext = Math.max(maxNext, next); // y 좌표의 최댓값 갱신
        }

        System.out.println(lengthSum);
    }
}
