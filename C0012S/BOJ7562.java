/*
7562. Silver 1 - 나이트의 이동

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           66443	    35625     26452	         52.420%


    문제
        체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
            [그림은 문제에서 참고]


    입력
        입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
        각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다. 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.


    출력
        각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.


    예제 입력 1
        3
        8
        0 0
        7 0
        100
        0 0
        30 50
        10
        1 1
        1 1
    예제 출력 1
        5
        28
        0


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
*/


// 메모리 : 77632KB
// 시간 : 344ms
// 코드 길이 : 3016B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ7562 {
    static int l; // 체스판의 한 변의 길이 (4 ≤ l ≤ 300)
    static int dx[] = {-2, -2, 2, 2, -1, 1, -1, 1}; // 상좌, 상우, 하좌, 하우, 좌상, 좌하, 우상, 우하
    static int dy[] = {-1, 1, -1, 1, -2, -2, 2, 2}; // 상좌, 상우, 하좌, 하우, 좌상, 좌하, 우상, 우하
    static StringBuilder sb;

    public static boolean check(int x, int y) { // 해당 좌표가 체스판 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < l && y >= 0 && y < l) {
            return true;
        }

        return false;
    }

    public static void find(int presentX, int presentY, int destinationX, int destinationY) { // 나이트가 최소 몇 번만에 이동할 수 있는지 구하는 메서드
        Queue<int[]> queue = new ArrayDeque<>();
        boolean isVisited[][] = new boolean[l][l]; // 해당 좌표의 방문 여부를 저장하는 배열
        int minCount = Integer.MAX_VALUE; // 나이트가 현재 있는 칸에서 이동하려고 하는 칸까지 이동할 수 있는 횟수의 최솟값

        isVisited[presentX][presentY] = true;
        queue.offer(new int[] {presentX, presentY, 0});
        while (!queue.isEmpty()) {
            int now[] = queue.poll(); // 현재 나이트가 위치하고 있는 칸의 좌표 정보와 해당 칸까지의 이동 횟수를 저장하는 배열

            if ((now[0] == destinationX) && (now[1] == destinationY)) { // 이동하려고 하는 칸에 도달했을 경우
                minCount = Math.min(minCount, now[2]);
            }

            for (int d = 0; d < 8; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (check(nx, ny) && !isVisited[nx][ny]) { // 체스판 범위 내의 좌표이고, 방문하지 않은 좌표일 경우
                    isVisited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny, now[2] + 1});
                }
            }
        }

        sb.append(minCount);
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 개수

        sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            l = Integer.parseInt(bf.readLine());
            String present[] = bf.readLine().split(" "); // 나이트가 현재 있는 칸의 좌표를 저장하는 배열
            String destination[] = bf.readLine().split(" "); // 나이트가 이동하려고 하는 칸의 좌표를 저장하는 배열

            find(Integer.parseInt(present[0]), Integer.parseInt(present[1]), Integer.parseInt(destination[0]), Integer.parseInt(destination[1]));
        }

        System.out.println(sb);
    }
}
