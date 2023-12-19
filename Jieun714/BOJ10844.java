package Jieun714;
/**
 * 문제: 45656이란 수를 보자. 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 *      N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.
 * 입력: 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 * 출력: 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 * 해결: DP 사용
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844 {
    public static int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //자연수

        long[][] dp = new long[N + 1][10]; //N개의 자릿값을 위해 2차원배열 사용
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        //Botton Up 방식의 DP
        //모든 분기에서 모듈러 연산 실행
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1] % MOD;
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][8] % MOD;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        long result = 0;
        for (int i = 0; i <= 9; i++) { //각 자릿값마다의 경우의 수를 모두 더함
            result = (result + dp[N][i]) % MOD;
        }

        System.out.println(result); //결과 출력
    }
}