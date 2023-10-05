package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 23-10-05
 * BOJ 1189번: 컴백홈
 * [ 문제 ]
 * 한수는 캠프를 마치고 집에 돌아가려 한다. 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다.
 * 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.
 * T로 표시된 부분은 가지 못하는 부분이다.
 * 문제는 R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중 거리가 K인 가짓수를 구하는 것이다.
 *
 * [ 입력 ]
 * 첫 줄에 정수 R(1 <= R <= 5), C(1 <= C <= 5), K(1 <= K <= RxC)가 공백으로 구분되어 주어진다.
 * 두 번째부터 R+1번째 줄까지는 RxC 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가 C인 문자열이 주어진다.
 *
 * [ 출력 ]
 * 첫 줄에 거리가 K인 가짓수를 출력한다.
 *
 * [ 예제 입력 1 ]
 * 3 4 6
 * ....
 * .T..
 * ....
 *
 * [ 예제 출력 1 ]
 * 4
 * */

public class BOJ1189 {
    static int R, C, K;
    static char[][] map; // 지도를 입력받을 배열
    static boolean[][] isvisited; // 방문 여부 확인할 배열
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        map = new char[R][C];
        isvisited = new boolean[R][C];

        for(int i=0; i<R; i++){
            String st = bf.readLine();
            for(int j=0; j<C; j++) map[i][j] = st.charAt(j);
        }

        isvisited[R-1][0] = true; // 출발 지점(왼쪽 아래) 방문 처리
        search(R-1, 0, 1); // 왼쪽 아래에서 출발. 첫 칸부터 세야 하기 때문에 len=1부터 시작

        System.out.println(count);
    }

    static void search(int r, int c, int len){ // 현재 위치 (r, c), 이동한 거리 len
        if(r == 0 && c == C-1){ // 오른쪽 위에 도착했을 때
            if(len==K) count++; // 이동 거리가 K라면 카운트
            return;
        }

        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr<0 || nc<0 || nr>=R || nc>=C) continue; // 이동 가능한 좌표인지 확인 ① 범위 내 좌표인지 확인
            if(map[nr][nc]=='T' || isvisited[nr][nc]) continue; // 이동 가능한 좌표인지 확인 ② 장애물 존재 여부, 방문 여부 확인

            isvisited[nr][nc] = true; // 방문 처리
            search(nr, nc, len+1); // 다음 좌표 방문
            isvisited[nr][nc] = false;  // 방문 후 돌아나온다 - 비방문 처리
        }
    }
}
