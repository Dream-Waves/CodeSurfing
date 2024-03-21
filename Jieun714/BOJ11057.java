package Jieun714;
/**
 * 문제: 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
 *      예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 *      수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 * 입력: 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 출력: 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 * 해결: 다이나믹 프로그래밍
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //수의 길이
        int[][] dp = new int[N + 1][10]; //오르막 수의 개수를 저장하는 배열

        // 길이가 1일때 dp 초기화
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 10; j++) { // 현재 자릿수가 j일 때
                for (int k = j; k < 10; k++) { // 이전 자릿수가 k 이상일 때
                    dp[i][j] += dp[i - 1][k]%10007; //모듈러 연산을 수행하면서 값을 누적
                }
            }
        }
        System.out.println(dp[N][0]%10007); // 결과 출력
    }
}