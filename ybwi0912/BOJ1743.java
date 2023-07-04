package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023-07-04
 * DFS
 * */
public class BOJ1743 {
    public static int N, M, K;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int[][] arr;
    public static boolean[][] isvisit;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken()); // 세로
        M = Integer.parseInt(token.nextToken()); // 가로
        K = Integer.parseInt(token.nextToken()); // 음식물 쓰레기의 개수

        arr = new int[N+1][M+1]; // padding 추가
        isvisit = new boolean[N+1][M+1];

        for(int i=0; i<K; i++) {
            token = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());
            arr[r][c] = 1; // 음식물이 떨어진 좌표
        }
        // input

        int max = 0;

        for(int i=1; i<N+1; i++){
            for(int j=1; j<M+1; j++){
                if(isvisit[i][j]==false && arr[i][j]==1){ // 방문되지 않은 노드이고, 해당 노드에 쓰레기가 떨어져 있을 경우
                    count = 1; // 쓰레기 크기
                    isvisit[i][j] = true;
                    dfs(i, j);

                    max = Math.max(max, count);
                }
            }
        }
        // operation

        System.out.println(max);
        // output
    }

    public static void dfs(int r, int c){
        for(int i=0; i<4; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];

            if(nx < 1 || nx > N || ny < 1 || ny > M || isvisit[nx][ny] == true || arr[nx][ny] == 0) continue;
            // 유효한 좌표가 아닐 경우, 방문했던 노드일 경우, 해당 노드에 쓰레기가 없을 경우 탐색 종료

            isvisit[nx][ny] = true; // 방문 처리
            count++;

            dfs(nx, ny);
        }
    }
}
