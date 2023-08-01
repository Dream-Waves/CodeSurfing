package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2023-08-01
 * BOJ 1149: RGB거리
 * 동적 계획법 -두 번째 집부터 차례대로 이전 집들 중 최솟값을 고르는 경우의 수를 더해나가면서 구하는 방식
 * */

public class BOJ1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken()); // 집의 개수

        int[][] input = new int[N][3]; // 1번 ~ N번 집을 세 가지 색상으로 칠하는 비용

        for(int i=0; i<N; i++){
            token = new StringTokenizer(bf.readLine());
            input[i][0] = Integer.parseInt(token.nextToken()); // Red
            input[i][1] = Integer.parseInt(token.nextToken()); // Green
            input[i][2] = Integer.parseInt(token.nextToken()); // Blue
        }
        // input

        for(int i=1; i<N; i++){
            input[i][0] += Math.min(input[i-1][1], input[i-1][2]);
            input[i][1] += Math.min(input[i-1][0], input[i-1][2]);
            input[i][2] += Math.min(input[i-1][1], input[i-1][0]);
        }
        // operation

        System.out.println(Math.min(Math.min(input[N-1][0], input[N-1][1]), input[N-1][2]));
        // output
    }
}
