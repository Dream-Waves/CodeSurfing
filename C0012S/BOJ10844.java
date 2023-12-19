/*
10844. Silver 1 - 쉬운 계단 수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           139058	    44678     32520	         30.486%


    문제
        45656이란 수를 보자.
        이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
        N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.


    입력
        첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.


    예제 입력 1
        1
    예제 출력 1
        9

    예제 입력 2
        2
    예제 출력 2
        17


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14216KB
// 시간 : 124ms
// 코드 길이 : 2769B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844 {
    static int N; // 계단의 길이 (1 ≤ N ≤ 100)
    static long stair[][]; // stair[계단의 길이][해당 인덱스로 시작하는 계단 수]

    // 0으로 시작하는 계단 수는 없지만, 점화식 계산을 위해 0으로 시작하는 계단 수의 개수도 구한다.
    // stair[2][0] : 01 → stair[2][0] = stair[1][1] = 1
    // stair[2][1] : 10, 12 → stair[2][1] = stair[1][0] + stair[1][2] = 2
    // stair[2][2] : 21, 23 → stair[2][2] = stair[1][1] + stair[1][3] = 2
    // stair[3][0] : 010, 012 → stair[3][0] = stair[2][1]
    // stair[3][1] : 101, 121, 123 → stair[3][1] = stair[2][0] + stair[2][2] = 3
    // 계단의 길이가 n이고 0으로 시작하는 계단 수의 개수는, 계단의 길이가 n - 1이고 1로 시작하는 계단 수의 개수와 같다.  // stair[n][0] = stair[n - 1][1]
    // 계단의 길이가 n이고 1부터 8까지의 수인 s로 시작하는 계단 수의 개수는, 계단의 길이가 n - 1이고 s - 1로 시작하는 계단 수의 개수와 계단의 길이가 n - 1이고 s + 1로 시작하는 계단 수의 개수의 합과 같다.  // stair[n][s] = stair[n - 1][s - 1] + stair[n - 1][s + 1]
    // 계단의 길이가 n이고 9로 시작하는 계단 수의 개수는, 계단의 길이가 n - 1이고 8로 시작하는 계단 수의 개수와 같다.  // stair[n][9] = stair[n - 1][8]
    public static void makeStair() { // 길이가 N인 계단 수의 개수를 구하는 메서드
        // 계단의 길이가 1인 경우의 계단 수의 개수
        for (int i = 0; i < 10; i++) {
            stair[1][i] = 1L;
        }

        // 계단의 길이가 n인 경우의 계단 수의 개수
        for (int n = 2; n <= N; n++) {
            // 계단의 길이가 n이고, 0으로 시작하는 계단 수의 개수
            stair[n][0] = stair[n - 1][1];

            // 계단의 길이가 n이고, s로 시작하는 계단 수의 개수
            for (int s = 1; s < 9; s++) {
                stair[n][s] = (stair[n - 1][s - 1] + stair[n - 1][s + 1]) % 1000000000;
            }

            // 계단의 길이가 n이고, 9로 시작하는 계단 수의 개수
            stair[n][9] = stair[n - 1][8] % 1000000000;
        }

        long result = 0;
        for (int r = 1; r < 10; r++) {
            result += stair[N][r];
        }

        System.out.println(result % 1000000000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        stair = new long[N + 1][10];
        makeStair();
    }
}
