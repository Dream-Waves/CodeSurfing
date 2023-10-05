package Jieun714;
/**
 * 문제: 한수는 캠프를 마치고 집에 돌아가려 한다. 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다. 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.
 *      위 예제는 한수가 집에 돌아갈 수 있는 모든 경우를 나타낸 것이다. T로 표시된 부분은 가지 못하는 부분이다. 문제는 R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중 거리가 K인 가짓수를 구하는 것이다.
 * 입력: 첫 줄에 정수 R(1 ≤ R ≤ 5), C(1 ≤ C ≤ 5), K(1 ≤ K ≤ R×C)가 공백으로 구분되어 주어진다. 두 번째부터 R+1번째 줄까지는 R×C 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가 C인 문자열이 주어진다.
 * 출력: 첫 줄에 거리가 K인 가짓수를 출력한다.
 * 해결: DFS
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189 {
    public static int R, C, K, result;
    public static char [][] map;
    public static boolean [][] isVisited;
    public static int[] dr = {0, 0, -1, 1};	//열
    public static int[] dc = {1, -1, 0, 0}; //행

    public static void dfs(int r, int c, int depth){
        if(r == 0 && c == C-1) { //도착 지점: 오른쪽 위
            if(depth == K){ //깊이와 K(한수가 집까지도 도착하는 경우 중 거리)가 같을 떄
                result++; //가짓수를 늘려준다
                return;
            }
        }

        for(int i=0; i<4; i++) { //상하좌우 계산
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(0<=nr && nr<R && 0<=nc && nc <C) {
                if(isVisited[nr][nc] || map[nr][nc] == 'T') continue; //방문했거나 맵의 정보가 'T'라면, for문으로
                isVisited[nr][nc] = true;
                dfs(nr, nc, depth+1); //현재 좌표와 현재 깊이+1
                isVisited[nr][nc] = false;
            }
        } //for end
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken()); //행의 길이
        C = Integer.parseInt(st.nextToken()); //열의 길이
        K = Integer.parseInt(st.nextToken()); //한수가 집까지도 도착하는 경우 중 거리
        map = new char[R][C]; //맵의 정보를 담는 배열
        isVisited = new boolean[R][C]; //방문한 위치를 체크하기 위한 배열

        for(int i=0; i<R; i++){
            map[i] = br.readLine().toCharArray();
        }

        isVisited[R-1][0] = true;
        dfs(R-1, 0, 1); //시작 지점: 왼쪽 아래

        System.out.println(result); //결과 출력
    }
}
