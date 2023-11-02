package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 23-11-02
 * BOJ 1245: 농장 관리
 * */

public class BOJ1245 {
    static int N, M;
    static int[][] farm;
    static boolean[][] isVisited;
    static int dx[] = {1, 1, 1, 0, 0, -1, -1, -1};
    static int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    static boolean a;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        farm = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i=0; i<N; i++){
            token = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; j++){
                farm[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        int ans = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!isVisited[i][j]){
                    a = true;
                    search(i, j);
                    if(a==true) ans++;
                    // (i, j) 주위에 해당 점보다 높이가 높은 점이 하나도 없다면 ans +1 처리
                }
            }
        }

        System.out.println(ans);
    }

    static void search(int x, int y){
        isVisited[x][y] = true;
        for(int i=0; i<8; i++){ // (x, y)를 기준으로 8방향 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || nx>=N || ny<0 || ny>=M) continue; // 범위 벗어나는 점이라면 패스

            if(farm[nx][ny]>farm[x][y]) a = false; // (x, y) 주위에 있는 점 중 한 점이라도 높은 곳이 있다면 false 체크
            if(!isVisited[nx][ny] && farm[nx][ny]==farm[x][y]) search(nx, ny); // 다음 점이 방문하지 않은 점이고 현재 점과 높이가 같다면 DFS로 다음 점 탐색
        }
    }
}
