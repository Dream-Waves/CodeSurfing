/*
17070. Gold 5 - 파이프 옮기기 1

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	        512 MB           40512	    19166     13152	         46.086%


    문제
        유현이가 새 집으로 이사했다. 새 집의 크기는 N×N의 격자판으로 나타낼 수 있고, 1×1크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 (r, c)로 나타낼 수 있다. 여기서 r은 행의 번호, c는 열의 번호이고, 행과 열의 번호는 1부터 시작한다. 각각의 칸은 빈 칸이거나 벽이다.

        오늘은 집 수리를 위해서 파이프 하나를 옮기려고 한다. 파이프는 아래와 같은 형태이고, 2개의 연속된 칸을 차지하는 크기이다.
            [그림은 문제에서 참고]

        파이프는 회전시킬 수 있으며, 아래와 같이 3가지 방향이 가능하다.
            [그림은 문제에서 참고]

        파이프는 매우 무겁기 때문에, 유현이는 파이프를 밀어서 이동시키려고 한다. 벽에는 새로운 벽지를 발랐기 때문에, 파이프가 벽을 긁으면 안 된다. 즉, 파이프는 항상 빈 칸만 차지해야 한다.
        파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 파이프는 밀면서 회전시킬 수 있다. 회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.
        파이프가 가로로 놓여진 경우에 가능한 이동 방법은 총 2가지, 세로로 놓여진 경우에는 2가지, 대각선 방향으로 놓여진 경우에는 3가지가 있다.

        아래 그림은 파이프가 놓여진 방향에 따라서 이동할 수 있는 방법을 모두 나타낸 것이고, 꼭 빈 칸이어야 하는 곳은 색으로 표시되어져 있다.
            [그림은 문제에서 참고]
                    가로

            [그림은 문제에서 참고]
                    세로

            [그림은 문제에서 참고]
                    대각선

        가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.


    입력
        첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다. 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.


    출력
        첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다. 이동시킬 수 없는 경우에는 0을 출력한다. 방법의 수는 항상 1,000,000보다 작거나 같다.


    예제 입력 1
        3
        0 0 0
        0 0 0
        0 0 0
    예제 출력 1
        1

    예제 입력 2
        4
        0 0 0 0
        0 0 0 0
        0 0 0 0
        0 0 0 0
    예제 출력 2
        3

    예제 입력 3
        5
        0 0 1 0 0
        0 0 0 0 0
        0 0 0 0 0
        0 0 0 0 0
        0 0 0 0 0
    예제 출력 3
        0

    예제 입력 4
        6
        0 0 0 0 0 0
        0 1 0 0 0 0
        0 0 0 0 0 0
        0 0 0 0 0 0
        0 0 0 0 0 0
        0 0 0 0 0 0
    예제 출력 4
        13


    알고리즘 분류
        다이나믹 프로그래밍
        그래프 이론
        그래프 탐색
*/


// 메모리 : 16644KB
// 시간 : 412ms
// 코드 길이 : 2690B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
    static int N; // 새 집의 크기 (3 ≤ N ≤ 16)
    static int house[][]; // 새 집의 상태를 저장하는 배열  // 0 : 빈 칸, 1 : 벽
    static int dx[] = {0, 1, 1}; // 우, 하, 우하
    static int dy[] = {1, 0, 1}; // 우, 하, 우하
    static int way[][]; // 파이프의 한쪽 끝을 해당 좌표로 이동시키는 방법의 수를 저장하는 배열

    public static boolean check(int x, int y) { // 해당 좌표가 새 집의 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }

        return false;
    }

    public static int find(int x, int y, int direction) { // 파이프의 한쪽 끝을 (N - 1, N - 1)로 이동시키는 방법의 수를 구하는 메서드
        if ((x == N - 1) && (y == N - 1)) {
            return 1;
        }

        for (int d = 0; d < 3; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (check(nx, ny)) {
                if (house[nx][ny] == 1) { // 다음에 이동할 방향에 벽이 있을 경우
                    continue;
                }
                else if (direction == 0 && d == 1) { // 기존의 파이프 방향이 가로이고, 다음에 이동할 방향이 세로일 경우
                    continue;
                }
                else if (direction == 1 && d == 0) { // 기존의 파이프 방향이 세로이고, 다음에 이동할 방향이 가로일 경우
                    continue;
                }
                else if (d == 2) { // 다음에 이동할 방향이 대각선일 경우
                    if (house[x + dx[0]][y + dy[0]] == 1 || house[x + dx[1]][y + dy[1]] == 1) { // 가로 방향에 벽이 있거나 세로 방향에 벽이 있을 경우
                        continue;
                    }
                }

                way[nx][ny] += find(nx, ny, d);
            }
        }

        return way[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        house = new int[N][N];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        way = new int[N][N];
        System.out.println(find(0, 1, 0));
    }
}
