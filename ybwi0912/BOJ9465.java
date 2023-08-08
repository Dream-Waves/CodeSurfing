package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2023-08-08
 * BOJ 9465: 스티커
 * DP - 점화식을 세워서 해결
 * */

public class BOJ9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int i=0; i<T; i++){
            int n = Integer.parseInt(bf.readLine());

            int[][] stickers = new int[2][n+1]; // 노드의 이전 노드와 그 이전 노드를 고려하기 위해 n+1으로 크기 설정
            int[][] dp = new int[2][n+1];

            for(int j=0; j<2; j++){
                String[] input = bf.readLine().split(" ");
                // StringTokenizer -> String.split()으로 고치면서 시간은 줄고 메모리는 늘었다

                for(int k=1; k<n+1; k++) stickers[j][k] = Integer.parseInt(input[k-1]);
            }
            // input

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];
            // 초기값 설정

            for(int j=2; j<n+1; j++){
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + stickers[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + stickers[1][j];
            } // 대각선 방향의 한 칸 앞, 두 칸 앞의 스티커 고려
            // operation

            System.out.println(Math.max(dp[0][n], dp[1][n]));
            // 각 열의 마지막 스티커까지 확인한 후 최댓값을 출력
            // output
        }
    }
}
