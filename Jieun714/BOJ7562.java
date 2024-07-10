package Jieun714;
/**
 * 문제: 체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 * 입력: 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 *      각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다.
 *      체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 * 출력: 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 * 해결: BFS 너비우선 탐색
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
    public static int [] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int [] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    public static boolean [][] isVisited;
    public static int l, min, startX, startY, lastX, lastY;

    public static void bfs(int y, int x) {
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[] {y, x, 0});
        isVisited[y][x] = true;

        while(!que.isEmpty()) {
            int [] now = que.poll();
            for(int i=0; i<8; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if(nx<0 || nx>=l || ny<0 || ny>=l) continue;
                if(!isVisited[ny][nx]) { //방문하지 않았을 때
                    int cnt = now[2]+1;
                    if(ny == lastY && nx == lastX) { //나이트가 이동하려고 하는 칸에 도착했다면
                        min = Math.min(min, cnt);
                    } else {
                        isVisited[ny][nx] = true;
                        que.add(new int [] {ny, nx, cnt});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            l = Integer.parseInt(br.readLine()); //체스판의 한 변의 길이
            StringTokenizer st = new StringTokenizer(br.readLine());
            startY = Integer.parseInt(st.nextToken()); //나이트가 현재 있는 칸의 Y
            startX = Integer.parseInt(st.nextToken()); //나이트가 현재 있는 칸의 X

            st = new StringTokenizer(br.readLine());
            lastY = Integer.parseInt(st.nextToken()); //나이트가 이동하려고 하는 칸의 Y
            lastX = Integer.parseInt(st.nextToken()); //나이트가 이동하려고 하는 칸의 X

            if(startX==lastX && startY==lastY) {
                min = 0;
            } else {
                min = Integer.MAX_VALUE; //나이트 최소 이동 횟수 초기화
                isVisited = new boolean[l][l]; //방문 여부를 체크할 배열
                bfs(startY, startX);
            }
            sb.append(min).append("\n"); //나이트 최소 이동 횟수를 StringBuilder에 담기
        }
        System.out.println(sb); //결과 출력
    }
}