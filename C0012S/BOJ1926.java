/*
1926. Silver 1 - 그림

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           39361	    17179     11907	         42.303%


    문제
        어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라. 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자. 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다. 그림의 넓이란 그림에 포함된 1의 개수이다.


    입력
        첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다. 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다. (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)


    출력
        첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.


    예제 입력 1
        6 5
        1 1 0 1 1
        0 1 1 0 0
        0 0 0 0 0
        1 0 1 1 1
        0 0 1 1 1
        0 0 1 1 1
    예제 출력 1
        4
        9


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        깊이 우선 탐색
*/


// 메모리 : 45844KB
// 시간 : 444ms
// 코드 길이 : 3354B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {
    static int n; // 도화지의 세로 크기 (1 ≤ n ≤ 500)
    static int m; // 도화지의 가로 크기 (1 ≤ m ≤ 500)
    static int paper[][]; // 도화지의 정보를 저장하는 배열
    static boolean isChecked[][]; // 해당 좌표의 도화지 검사 여부를 저장하는 배열
    static int dx[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dy[] = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static boolean check(int x, int y) { // 해당 좌표가 도화지 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }

        return false;
    }

    public static int findPictureArea(int x, int y) { // 하나의 그림의 넓이를 구하는 메서드
        Queue<int[]> queue = new ArrayDeque<>();

        int size = 1; // 해당 그림의 넓이
        queue.offer(new int[] {x, y});
        isChecked[x][y] = true;
        while (!queue.isEmpty()) {
            int now[] = queue.poll(); // 검사할 좌표

            for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우 방향에 있는 다음 좌표 체크
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (check(nx, ny) && !isChecked[nx][ny]) { // 다음 좌표가 도화지의 범위를 벗어나지 않았고, 검사하지 않은 좌표일 경우
                    if (paper[nx][ny] == 1) { // 다음 좌표에 그림이 그려져 있을 경우
                        size += 1;
                        queue.offer(new int[] {nx, ny});
                        isChecked[nx][ny] = true;
                    }
                }
            }
        }

        return size;
    }

    public static void findPicture() { // 그림의 개수와 가장 넓은 그림의 넓이를 구하고 출력하는 메서드
        int pictureNum = 0; // 그림의 개수
        int maxSize = 0; // 가장 넓은 그림의 넓이
        isChecked = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (!isChecked[r][c] && (paper[r][c] == 1)) { // 검사하지 않은 좌표이고, 해당 좌표에 그림이 그려져 있을 경우
                    pictureNum += 1;
                    maxSize = Math.max(maxSize, findPictureArea(r, c));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pictureNum);
        sb.append("\n");
        sb.append(maxSize);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        findPicture();
    }
}
