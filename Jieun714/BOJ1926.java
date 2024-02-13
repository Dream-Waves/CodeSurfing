package Jieun714;
/**
 * 문제: 어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라. 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자.
 *      가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다. 그림의 넓이란 그림에 포함된 1의 개수이다.
 * 입력: 첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다. 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다. (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)
 * 출력: 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.
 * 해결: bfs 사용
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {
    public static int n, m;
    public static int[][] pages;
    public static int cnt; //그림의 개수
    public static int max = 0; //그림 중 넓이가 가장 넓은 것의 넓이
    public static boolean[][] isVisited;
    public static int [] dx = {0, 0, -1, 1};
    public static int [] dy = {1, -1, 0, 0};

    public static boolean check(int y, int x){
        if(y>=n || y<0 || x>=m || x<0 ) { //범위를 벗어날 때
            return false;
        } else {
            //방문하지 않고 색칠된 부분의 그림일 때라면 true, 아니면 false
            return !isVisited[y][x] && pages[y][x] == 1;
        }
    }

    public static void bfs(int y, int x){
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[]{y, x});
        isVisited[y][x] = true; //방문체크 배열
        int picSize = 1; //그림의 크기

        while(!que.isEmpty()){
            int [] now = que.poll();
            for(int i=0; i<4; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if(check(ny, nx)){ //방문 가능한 좌표일때
                    que.add(new int[]{ny, nx}); //큐에 삽입
                    isVisited[ny][nx] = true; //방문 체크
                    picSize++; //그릠의 크기 늘리기
                }
            }
        }
        max = Math.max(max, picSize); //max 값 계산
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //도화지의 세로 크기
        m = Integer.parseInt(st.nextToken()); //도화지의 가로 크기
        pages = new int[n][m]; //그림의 정보를 담는 배열
        isVisited = new boolean[n][m]; //방문했는지 체크하는 배열

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                pages[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!isVisited[i][j] && pages[i][j] == 1) { //방문하지 않고, 색칠된 부분의 그림일 때
                    bfs(i, j); //함수 수행
                    cnt++; //그림의 개수 증가
                }
            }
        }
        System.out.println(cnt + "\n" + max); //그림의 개수와 그림 중 넓이가 가장 넓은 것의 넓이를 출력
    }
}