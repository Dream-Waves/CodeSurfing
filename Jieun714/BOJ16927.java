package Jieun714;
/**
 * 문제: 크기가 N×M인 배열이 있을 때, 배열을 돌려보려고 한다. 배열은 다음과 같이 반시계 방향으로 돌려야 한다.
 *      배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.
 * 입력: 첫째 줄에 배열의 크기 N, M과 수행해야 하는 회전의 수 R이 주어진다.
 *      둘째 줄부터 N개의 줄에 배열 A의 원소 Aij가 주어진다.
 * 출력: 입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.
 * 해결: 구현
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //배열의 세로 크기
        int M = Integer.parseInt(st.nextToken()); //배열의 가로 크기
        int R = Integer.parseInt(st.nextToken()); //회전의 수
        int [][] map = new int[N][M]; //배열
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int [] dx = {1, 0, -1, 0};
        int [] dy = {0, 1, 0, -1};
        for(int i=0; i<Math.min(N, M)/2; i++) {
            int cnt = R%(((N-2*i) + (M-2*i))*2 - 4); //시계 반대 방향을 돌릴 횟수
            for(int j=0; j<cnt; j++) {
                int idx = 0;
                int x = i;
                int y = i;
                int tmp = map[y][x];
                while(idx < 4) { //방향 바꾸기
                    int nx = x + dx[idx];
                    int ny = y + dy[idx];
                    if(nx < i || nx >= M - i || ny < i || ny >= N - i) { //배열의 범위를 벗어났을 때
                        idx++;
                        continue;
                    }
                    map[y][x] = map[ny][nx];
                    y = ny;
                    x = nx;
                } // end while
                map[i + 1][i] = tmp; //반시계 방향으로 한 칸 이동
            }  // end for
        } // end for

        StringBuilder sb = new StringBuilder();
        for(int [] arr : map) {
            for(int a : arr) sb.append(a).append(" "); //결과 출력
            sb.append("\n");
        }
        System.out.println(sb);
    }
}