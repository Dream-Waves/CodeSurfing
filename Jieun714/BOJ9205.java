package Jieun714;
/**
 * 문제: 송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고 한다. 올해는 맥주를 마시면서 걸어가기로 했다. 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 맥주 한 박스에는 맥주가 20개 들어있다. 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
 *      상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리이다. 따라서, 맥주를 더 구매해야 할 수도 있다. 미리 인터넷으로 조사를 해보니 다행히도 맥주를 파는 편의점이 있다. 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다. 하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
 *      편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표가 주어진다. 상근이와 친구들이 행복하게 페스티벌에 도착할 수 있는지 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)
 *      각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).
 *      다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
 *      송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)
 * 출력: 각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다.
 * 해결: BFS 탐색
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9205 {
    public static int n;
    public static int [][] stores;
    public static int [][] arr;
    public static boolean [] isVisited;
    public static String answer;
    public static void bfs(int x, int y) {
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[]{y, x}); //큐에 상근이네 집 좌표 삽입

        while(!que.isEmpty()) {
            int [] now = que.poll();
            if(Math.abs(now[0]-arr[1][1])+Math.abs(now[1]-arr[1][0]) <= 1000) {
                answer = "happy"; //페스티벌에 갈 수 있다면 happy
                break;
            }

            for(int i=0; i<n; i++) {
                int [] next = stores[i];
                if(isVisited[i]) continue; //민약 이미 방문한 편의점이라면 패스

                if(Math.abs(now[0]-next[1])+Math.abs(now[1]-next[0]) <= 1000) {
                    isVisited[i] = true; //방문 편의점 체크
                    que.add(new int[]{next[1], next[0]}); //큐에 편의점 좌표 삽입
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); //테스트케이스 수
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<t; i++) {
            n = Integer.parseInt(br.readLine()); //편의점 수
            arr = new int[2][2]; //상근이네 집, 펜타포트 락 페스티벌 좌표
            stores = new int[n][2]; //편의점 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[0][0] = Integer.parseInt(st.nextToken());
            arr[0][1] = Integer.parseInt(st.nextToken());

            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                stores[j][0] = Integer.parseInt(st.nextToken());
                stores[j][1] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            arr[1][0] = Integer.parseInt(st.nextToken());
            arr[1][1] = Integer.parseInt(st.nextToken());

            isVisited = new boolean[n];
            answer = "sad"; //디폴트값 sad
            bfs(arr[0][0], arr[0][1]);
            sb.append(answer).append("\n"); //정답을 StringBuilder에 추가
        }
        System.out.println(sb); //결과출력
    }
}