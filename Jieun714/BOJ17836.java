package Jieun714;
/**
 * 문제: 용사는 마왕이 숨겨놓은 공주님을 구하기 위해 (N, M) 크기의 성 입구 (1,1)으로 들어왔다. 마왕은 용사가 공주를 찾지 못하도록 성의 여러 군데 마법 벽을 세워놓았다. 용사는 현재의 가지고 있는 무기로는 마법 벽을 통과할 수 없으며, 마법 벽을 피해 (N, M) 위치에 있는 공주님을 구출해야만 한다.
 *      마왕은 용사를 괴롭히기 위해 공주에게 저주를 걸었다. 저주에 걸린 공주는 T시간 이내로 용사를 만나지 못한다면 영원히 돌로 변하게 된다. 공주님을 구출하고 프러포즈 하고 싶은 용사는 반드시 T시간 내에 공주님이 있는 곳에 도달해야 한다. 용사는 한 칸을 이동하는 데 한 시간이 걸린다. 공주님이 있는 곳에 정확히 T시간만에 도달한 경우에도 구출할 수 있다. 용사는 상하좌우로 이동할 수 있다.
 *      성에는 이전 용사가 사용하던 전설의 명검 "그람"이 숨겨져 있다. 용사가 그람을 구하면 마법의 벽이 있는 칸일지라도, 단숨에 벽을 부수고 그 공간으로 갈 수 있다. "그람"은 성의 어딘가에 반드시 한 개 존재하고, 용사는 그람이 있는 곳에 도착하면 바로 사용할 수 있다. 그람이 부술 수 있는 벽의 개수는 제한이 없다.
 *      우리 모두 용사가 공주님을 안전하게 구출 할 수 있는지, 있다면 얼마나 빨리 구할 수 있는지 알아보자.
 * 입력: 첫 번째 줄에는 성의 크기인 N, M 그리고 공주에게 걸린 저주의 제한 시간인 정수 T가 주어진다. 첫 줄의 세 개의 수는 띄어쓰기로 구분된다. (3 ≤ N, M ≤ 100, 1 ≤ T ≤ 10000)
 *      두 번째 줄부터 N+1번째 줄까지 성의 구조를 나타내는 M개의 수가 띄어쓰기로 구분되어 주어진다. 0은 빈 공간, 1은 마법의 벽, 2는 그람이 놓여있는 공간을 의미한다. (1,1)과 (N,M)은 0이다.
 * 출력: 용사가 제한 시간 T시간 이내에 공주에게 도달할 수 있다면, 공주에게 도달할 수 있는 최단 시간을 출력한다.
 *      만약 용사가 공주를 T시간 이내에 구출할 수 없다면, "Fail"을 출력한다.
 * 해결: BFS(너비 우선 탐색)
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836 {
    public static int N, M, T;
    public static int [][] map;
    public static int [] dx = {0, 0, -1, 1};
    public static int [] dy = {1, -1, 0, 0};
    public static boolean [][][] isVisited;
    public static int min = Integer.MAX_VALUE;

    public static boolean check(int y, int x) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static void bfs(int x, int y) {
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int [] {y, x, 0, 0}); //y좌표, x좌표, 그람의 수, 이동 시간
        isVisited[y][x][0] = true;

        while(!que.isEmpty()) {
            int [] now = que.poll();
            if(now[3] > T) break; // T시간이 넘었다면 break
            if(now[0] == N-1 && now[1] == M-1) {
                min = Math.min(min, now[3]);
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];
                if(!check(ny, nx)) continue;

                if(now[2] > 0) { //소유한 그람이 있을 때
                    if(!isVisited[ny][nx][1]) que.add(new int[] {ny, nx, now[2], now[3]+1});
                    isVisited[ny][nx][1] = true; //그람을 갖고 있을 때의 방문 처리
                } else {
                    if(isVisited[ny][nx][0]) continue;
                    if(map[ny][nx] == 0) {
                        que.add(new int[] {ny, nx, now[2], now[3]+1});
                        isVisited[ny][nx][0] = true; //그람을 갖고 있지 않을 때의 방문 처리
                    } else if(map[ny][nx] == 2) {
                        que.add(new int[] {ny, nx, now[2]+1, now[3]+1}); //그람 추가
                        isVisited[ny][nx][0] = true; //그람을 갖고 있지 않을 때의 방문 처리
                    }
                }
            } //end for
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //성의 행
        M = Integer.parseInt(st.nextToken()); //성의 열
        T = Integer.parseInt(st.nextToken()); //저주의 제한 시간
        map = new int[N][M]; //성의 구조를 담는 배열
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        isVisited = new boolean[N][M][2];
        bfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? "Fail" : min);
    }
}