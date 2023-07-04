package Jieun714;
/**
 * 문제: 제일 큰 음식물의 크기 구하는 프로그램 작성
 * 입력: 첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다. 그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
 *      좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.
 * 출력: 첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.
 * 해결: BFS 사용.(Queue)
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1743 {
    public static int N, M, K;
    public static int [][] map;
    public static boolean [][] isVisited;
    public static int max = 0;
    public static int [] dx = {-1, 1, 0, 0};
    public static int [] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //통로의 세로 길이
        M = Integer.parseInt(st.nextToken()); //통로의 가로 길이
        K = Integer.parseInt(st.nextToken()); //음식물 쓰레기 갯수
        map = new int[N][M]; //map 초기화

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine()); //좌표(r,c)
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = 1;
        }

        isVisited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(max); //결과 출력
    }
    //bfs 함수
    public static void bfs(int r, int c){
        Queue<int []> que = new ArrayDeque<>();
        que.offer(new int []{r, c});
        isVisited[r][c] = true;
        int cnt = 1; //음식물 쓰레기 갯수

        while(!que.isEmpty()){
            int [] now = que.poll();

            for(int i=0; i<4; i++){
                int nx = now[1] +dx[i];
                int ny = now[0] +dy[i];
                if(nx>=0 && nx<M && ny>=0 && ny<N && !isVisited[ny][nx] && map[ny][nx]==1){ //범위 만족. 방문하지 않고, map이 1일 떄
                    isVisited[ny][nx] = true;
                    que.add(new int [] {ny, nx}); //쿠
                    cnt++;
                }
            } //for end
        }
        max = Math.max(max, cnt); //최댓값
    }
}