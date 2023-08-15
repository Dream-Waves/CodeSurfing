package Jieun714;
/**
 * 문제: 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다.
 *      이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다.
 *      한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다. 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로
 *      서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
 *
 * 입력: 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다.
 *      그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.
 *
 * 출력: 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
 *
 * 해결: BFS로는 Queue를 사용. DFS에서는 재귀를 사용해서 해결. 내가 푼 방식으로는 BFS와 DFS의 메모리와 시간의 차이가 거의 없었다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
    public static int T, M, N, K;
    public static int [][] map;
    public static int [] dx = {0, 0, -1, 1};
    public static int [] dy = {1, -1, 0, 0};

    /* DFS 재귀 - 메모리: 159202KB, 시간: 168ms */
    public static void dfs(int y, int x){
        for(int i=0; i<4; i++){
            int ny = y+ dy[i];
            int nx = x+ dx[i];
            if(nx>=0 && nx<M && ny>=0 && ny<N && map[ny][nx]==1){ //범위 안에 좌표 값이 존재하고, 배추가 있는 곳이라면
                map[ny][nx] = 0; // 배추 뽑기(1 -> 0)
                dfs(ny, nx); // 재귀
            }
        }
    }
    /* BFS Queue - 메모리: 15852KB, 시간: 180ms */
    public static void bfs(int y, int x){
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[]{y, x});
        map[y][x] = 0;
        while(!que.isEmpty()){ //큐가 빌 때까지
            int [] now = que.poll();
            for(int i=0; i<4; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if(nx>=0 && nx<M && ny>=0 && ny<N && map[ny][nx]==1){ //범위 안에 좌표 값이 존재하고, 배추가 있는 곳이라면
                    que.add(new int[]{ny, nx});
                    map[ny][nx] = 0; // 배추 뽑기(1 -> 0)
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); //결과를 한번에 출력하기 위해 StringBuilder 사용
        T = Integer.parseInt(br.readLine()); // 테스트 케이스의 갯수
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //배추밭의 가로길이
            N = Integer.parseInt(st.nextToken()); //배추밭의 세로길이
            K = Integer.parseInt(st.nextToken()); //배추가 심어져 있는 위치의 개수

            map = new int[N][M];
            for(int j=0; j<K; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); //배추의 위치 X
                int y = Integer.parseInt(st.nextToken()); //배추의 위치 Y

                map[y][x] = 1; //배추의 위치는 1로 표기
            }

            int cnt = 0; //필요한 배추흰지렁이 개수
            for(int y=0; y<N; y++){
                for(int x=0; x<M; x++) {
                    if(map[y][x] == 1) { //배추가 있다면
                        // bfs(y, x);
                        dfs(y, x);
                        cnt++; //카운트 증가
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb); //결과 출력
    }
}