package Jieun714;
/**
 * 문제: 농부 민식이가 관리하는 농장은 N×M 격자로 이루어져 있다. 민식이는 농장을 관리하기 위해 산봉우리마다 경비원를 배치하려 한다. 이를 위해 농장에 산봉우리가 총 몇 개 있는지를 세는 것이 문제다.
 *      산봉우리의 정의는 다음과 같다. 산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합으로 이루어져 있다. (여기서 "인접하다"의 정의는 X좌표 차이와 Y좌표 차이 모두 1 이하일 경우로 정의된다.) 또한 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야한다.
 *      문제는 격자 내에 산봉우리의 개수가 총 몇 개인지 구하는 것이다.
 * 입력: 첫째 줄에 정수 N(1 < N ≤ 100), M(1 < M ≤ 70)이 주어진다. 둘째 줄부터 N+1번째 줄까지 각 줄마다 격자의 높이를 의미하는 M개의 정수가 입력된다. 격자의 높이는 500보다 작거나 같은 음이 아닌 정수이다.
 * 출력: 첫째 줄에 산봉우리의 개수를 출력한다.
 * 해결: BFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1245 {
    public static int N, M;
    public static int [][] peak;
    public static boolean [][] isVisited;
    public static boolean flag;
    public static int [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int [] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void bfs(int y, int x){
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[] {y, x});
        isVisited[y][x] = true;
        flag = true;

        while(!que.isEmpty()){
            int [] now = que.poll();
            for(int i=0; i<8; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if(ny<0 || ny>=N || nx<0 || nx>=M) continue; //해당 범위 안에 있을 때
                if(peak[now[0]][now[1]] < peak[ny][nx]) flag = false; //만약 다른 영역이 더 높다면
                if(peak[now[0]][now[1]] != peak[ny][nx]) continue; //해당 영역이 아니면 continue
                if(isVisited[ny][nx]) continue; //중복 방문 X

                isVisited[ny][nx] = true;
                que.add(new int[] {ny, nx});
            }
        } //while end
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //농장 행
        M = Integer.parseInt(st.nextToken()); //농장 열
        peak = new int[N][M]; //산봉우리 높이를 담는 배열
        isVisited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                peak[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0; //산봉우리 갯수
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!isVisited[i][j]) {
                    bfs(i, j);
                    if(flag) count++;
                }
            }
        }
        System.out.println(count); //결과 출력
    }
}