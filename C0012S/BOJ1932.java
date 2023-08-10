/*
1932. Silver 1 - 정수 삼각형

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           82612	    47657     35958	         59.297%


    문제
                    7
                  3   8
                8   1   0
              2   7   4   4
            4   5   2   6   5

        위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
        맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라. 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
        삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.


    입력
        첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.


    출력
        첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.


    예제 입력 1
        5
        7
        3 8
        8 1 0
        2 7 4 4
        4 5 2 6 5
    예제 출력 1
        30


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 27760KB
// 시간 : 316ms
// 코드 길이 : 1686B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;
        int n = Integer.parseInt(bf.readLine()); // 삼각형의 크기 n (1 ≤ n ≤ 500)

        int triangle[][] = new int[n][n]; // 크기가 n인 정수 삼각형의 수들을 저장한 배열
        int dp[][] = new int[n][n]; // dp[x][y] : (x, y) 위치까지 올 수 있는 경로들의 수의 합 중 최댓값
        for (int r = 0; r < n; r++) {
            token = new StringTokenizer(bf.readLine());

            for (int c = 0; c <= r; c++) {
                triangle[r][c] = Integer.parseInt(token.nextToken());

                if (r == 0) { // 초깃값(dp[0][0])에 저장될 값은 삼각형의 첫 번째 수
                    dp[r][c] = triangle[r][c];
                }
                else {
                    if (c == 0) { // 해당 행의 첫 번째 원소일 경우
                        dp[r][c] = dp[r - 1][c] + triangle[r][c];
                    }
                    else {
                        dp[r][c] = Math.max(dp[r - 1][c - 1], dp[r - 1][c]) + triangle[r][c];
                    }
                }
            }
        }

        int maxSum = 0; // 합이 최대가 되는 경로에 있는 수의 합
        for (int m = 0; m < n; m++) { // 2 차원 배열 dp의 마지막 행에서 최댓값 찾기
            maxSum = Math.max(maxSum, dp[n - 1][m]);
        }

        System.out.println(maxSum);
    }
}
