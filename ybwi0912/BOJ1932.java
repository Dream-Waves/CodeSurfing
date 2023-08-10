package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 2023-08-10
* BOJ 1932번: 정수 삼각형
* 삼각형의 맨 아래쪽부터 위로 한 칸씩 올라가며 인접한 값 중 최댓값을 찾아 누적
* 삼각형의 맨 꼭대기까지 돈 뒤, 최종적으로 누적합을 출력
* */

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(token.nextToken()); // 삼각형의 크기
        int[][] triangle = new int[n][n+1]; // 크기가 n인 삼각형. 편한 값 비교를 위해 n+1으로 지정

        for(int i=0; i<n; i++){
            token = new StringTokenizer(bf.readLine());

            for(int j=0; j<i+1; j++) triangle[i][j] = Integer.parseInt(token.nextToken());
        }
        // input

        for(int i=n-1; i>0; i--){
            for(int j=0; j<i; j++){
                triangle[i-1][j] += Math.max(triangle[i][j], triangle[i][j+1]);
                // 바로 위의 두 칸 중 최댓값을 누적
            }
        }
        // operation

        System.out.println(triangle[0][0]); // 최종 누적합(삼각형의 꼭대기 값)을 출력
        // output
    }
}
