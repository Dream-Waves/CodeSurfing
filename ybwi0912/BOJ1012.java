package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 2023-08-15
* BOJ 1012: 유기농 배추
* DFS 사용 - 배추밭 배열을 처음부터 쭉 돌면서 배추가 있는 곳을 만날 때마다 DFS를 돌려 인접한 배추가 존재하는 위치를 방문 배열에 체크하고, cnt를 1 더해준다.
* 배추밭 배열을 끝까지 돌았을 때 최종적인 cnt 값을 답으로 출력.
* */

public class BOJ1012 {
    static int M, N, K; // 배추밭의 가로 길이, 세로 길이, 배추가 있는 위치의 개수
    static int[][] farm; // 배추밭 배열
    static boolean[][] isvisited; // 방문 여부를 체크하기 위한 배열
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int cnt; // 필요한 배추흰지렁이의 수

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int i=0; i<T; i++) {
            StringTokenizer token = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());

            farm = new int[M][N];
            isvisited = new boolean[M][N];
            cnt = 0;
            // 매 테스트 케이스마다 배추밭 배열, 방문 배열, cnt를 초기화해줘야 한다

            for(int j=0; j<K; j++){
                token = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(token.nextToken());
                int y = Integer.parseInt(token.nextToken());
                farm[x][y] = 1;
            }
            // input

            for(int x=0; x<M; x++){
                for(int y=0; y<N; y++){
                    if(farm[x][y] == 1 && !isvisited[x][y]){ // 배추가 위치해 있고 해당 위치를 방문하지 않았다면
                        search(x, y); // dfs 메소드를 돌려 (x, y)와 인접해 있는 모든 배추의 위치를 방문 배열에 체크한다.
                        cnt++; // 배추흰지렁이 카운트를 1 더해준다
                    }
                }
            }
            // operation

            System.out.println(cnt);
            // output
        }
    }

    static void search(int x, int y){
        isvisited[x][y] = true; // 해당 좌표를 방문했음을 체크한다

        for(int i=0; i<4; i++){ // 해당 좌표의 상하좌우 체크
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < M && ny >= 0 && ny < N){
                if(farm[nx][ny] == 1 && !isvisited[nx][ny]){
                    search(nx, ny); // 해당 좌표의 인접한 곳에 배추가 존재한다면 dfs 함수를 돌려 체크한다
                }
            }
        }
    }
}
