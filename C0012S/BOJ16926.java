/*
16926. Silver 1 - 배열 돌리기 1

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           17545	    8404      5482	         46.580%


    문제
        크기가 N×M인 배열이 있을 때, 배열을 돌려보려고 한다. 배열은 다음과 같이 반시계 방향으로 돌려야 한다.
            A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
               ↓                                       ↑
            A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
               ↓         ↓                   ↑         ↑
            A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
               ↓                                       ↑
            A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]

        예를 들어, 아래와 같은 배열을 2번 회전시키면 다음과 같이 변하게 된다.
            1 2 3 4       2 3 4 8       3 4 8 6
            5 6 7 8       1 7 7 6       2 7 8 2
            9 8 7 6   →   5 6 8 2   →   1 7 6 3
            5 4 3 2       9 5 4 3       5 9 5 4
             <시작>         <회전1>        <회전2>

        배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.


    입력
        첫째 줄에 배열의 크기 N, M과 수행해야 하는 회전의 수 R이 주어진다.
        둘째 줄부터 N개의 줄에 배열 A의 원소 Aij가 주어진다.


    출력
        입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.


    제한
        · 2 ≤ N, M ≤ 300
        · 1 ≤ R ≤ 1,000
        · min(N, M) mod 2 = 0
        · 1 ≤ Aij ≤ 108


    예제 입력 1
        4 4 2
        1 2 3 4
        5 6 7 8
        9 10 11 12
        13 14 15 16
    예제 출력 1
        3 4 8 12
        2 11 10 16
        1 7 6 15
        5 9 13 14

    예제 입력 2
        5 4 7
        1 2 3 4
        7 8 9 10
        13 14 15 16
        19 20 21 22
        25 26 27 28
    예제 출력 2
        28 27 26 25
        22 9 15 19
        16 8 21 13
        10 14 20 7
        4 3 2 1

    예제 입력 3
        2 2 3
        1 1
        1 1
    예제 출력 3
        1 1
        1 1


    알고리즘 분류
        구현
*/


// 메모리 : 28068KB
// 시간 : 768ms
// 코드 길이 : 2842B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {
    static int N; // 행의 길이 (2 ≤ N ≤ 300)
    static int M; // 열의 길이 (2 ≤ M ≤ 300)
    static int map[][]; // 크기가 N × M인 배열
    static int dx[] = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int dy[] = {1, 0, -1, 0}; // 우, 하, 좌, 상

    public static boolean check(int x, int y, int circle) { // 해당 좌표가 배열의 회전 그룹의 범위를 벗어났는지 검사하는 메서드
        if (x >= circle && x < N - circle && y >= circle && y < M - circle) {
            return true;
        }

        return false;
    }

    public static void rotate() { // 배열을 회전시키는 메서드
        int rotateNum = Math.min(N, M) / 2; // 배열의 회전 그룹의 개수

        for (int n = 0; n < rotateNum; n++) {
            int x = n;
            int y = n;
            int firstMapValue = map[x][y]; // 배열의 회전 그룹의 첫 번째 원소

            int d = 0;
            while (d < 4) { // 우, 하, 좌, 상 방향으로 이동하면서 배열을 회전시킬 수 있도록 값 변경
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (check(nx, ny, n)) { // 배열의 회전 그룹의 범위를 벗어나지 않았을 경우
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;

                    continue;
                }

                d += 1;
            }

            map[n + 1][n] = firstMapValue; // 변경하지 못한 배열의 원소의 값 변경
        }
    }

    public static void print() { // 배열을 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sb.append(map[r][c]);
                sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        int R = Integer.parseInt(token.nextToken()); // 회전의 수 (1 ≤ R ≤ 1,000)

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        for (int t = 0; t < R; t++) { // 배열 R 번 회전
            rotate();
        }

        print();
    }
}
