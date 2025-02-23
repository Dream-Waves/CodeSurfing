/*
9205. Gold 5 - 맥주 마시면서 걸어가기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           38210	    16058     11707	         40.330%


    문제
        송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고 한다. 올해는 맥주를 마시면서 걸어가기로 했다. 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 맥주 한 박스에는 맥주가 20개 들어있다. 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
        상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리이다. 따라서, 맥주를 더 구매해야 할 수도 있다. 미리 인터넷으로 조사를 해보니 다행히도 맥주를 파는 편의점이 있다. 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다. 하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
        편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표가 주어진다. 상근이와 친구들이 행복하게 페스티벌에 도착할 수 있는지 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)
        각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).
        다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
        송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)


    출력
        각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다.


    예제 입력 1
        2
        2
        0 0
        1000 0
        1000 1000
        2000 1000
        2
        0 0
        1000 0
        2000 1000
        2000 2000
    예제 출력 1
        happy
        sad


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
*/


// 메모리 : 14804KB
// 시간 : 124ms
// 코드 길이 : 3158B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205 {
    static int N; // 편의점의 개수 (0 ≤ N ≤ 100)
    static int[][] location; // 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표를 저장하는 배열
    static boolean[] isVisited; // 편의점의 방문 여부를 저장하는 배열
    static StringBuilder sb;

    static class Coordinate { // 좌표 정보를 저장하는 클래스
        int x; // x 좌표
        int y; // y 좌표

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int calculateDistance(int x1, int x2, int y1, int y2) { // 좌표 (x1, y1)과 좌표 (x2, y2)의 거리를 구하는 메서드
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void go() { // 상근이와 친구들이 행복하게 페스티벌에 도착할 수 있는지 구하는 메서드
        Queue<Coordinate> queue = new ArrayDeque<>();
        isVisited = new boolean[N + 1];

        queue.offer(new Coordinate(location[0][0], location[0][1])); // 상근이네 집에서 출발
        while (!queue.isEmpty()) {
            Coordinate now = queue.poll(); // 현재 위치한 좌표

            if (calculateDistance(now.x, location[N + 1][0], now.y, location[N + 1][1]) <= 1000) { // 현재 위치한 좌표와 펜타포트 락 페스티벌의 좌표의 거리가 1000 이하일 경우
                sb.append("happy"); // 상근이와 친구들이 행복하게 페스티벌에 도착

                return;
            }

            for (int n = 1; n <= N; n++) {
                if (!isVisited[n]) { // 방문하지 않은 편의점일 경우
                    if (calculateDistance(now.x, location[n][0], now.y, location[n][1]) <= 1000) { // 현재 위치한 좌표와 편의점의 좌표의 거리가 1000 이하일 경우
                        queue.offer(new Coordinate(location[n][0], location[n][1])); // 이동할 좌표로 추가
                        isVisited[n] = true; // 편의점 방문 표시
                    }
                }
            }
        }

        sb.append("sad"); // 맥주가 바닥나서 더 이동할 수 없으므로 페스티벌에 도착 불가
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(bf.readLine());

            location = new int[N + 2][2];
            for (int i = 0, num = N + 2; i < num; i++) {
                token = new StringTokenizer(bf.readLine());

                location[i][0] = Integer.parseInt(token.nextToken());
                location[i][1] = Integer.parseInt(token.nextToken());
            }

            go();
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
