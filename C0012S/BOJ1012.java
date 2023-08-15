/*
1012. Silver 2 - 유기농 배추

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           158204	    63241     42411	         37.829%


    문제
        차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
        한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
            1	1	0	0	0	0	0	0	0	0
            0	1	0	0	0	0	0	0	0	0
            0	0	0	0	1	0	0	0	0	0
            0	0	0	0	1	0	0	0	0	0
            0	0	1	1	0	0	0	1	1	1
            0	0	0	0	1	0	0	1	1	1


    입력
        입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.


    출력
        각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.


    예제 입력 1
        2
        10 8 17
        0 0
        1 0
        1 1
        4 2
        4 3
        4 5
        2 4
        3 4
        7 4
        8 4
        9 4
        7 5
        8 5
        9 5
        7 6
        8 6
        9 6
        10 10 1
        5 5
    예제 출력 1
        5
        1

    예제 입력 2
        1
        5 3 6
        0 2
        1 2
        2 2
        3 2
        4 2
        4 0
    예제 출력 2
        2


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        깊이 우선 탐색
*/


// 메모리 : 16008KB
// 시간 : 176ms
// 코드 길이 : 4012B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int M; // 배추를 심은 배추밭의 가로 길이 M (1 ≤ M ≤ 50)
    static int N; // 배추를 심은 배추밭의 세로 길이 N (1 ≤ N ≤ 50)
    static int field[][]; // 배추밭의 해당 좌표에 배추가 심어져 있는지를 저장하는 배열  // 0 : 배추가 심어져 있지 않은 땅, 1 : 배추가 심어져 있는 땅
    static boolean isChecked[][]; // 해당 밭의 좌표 검사 여부
    static int dx[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int dy[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우

    public static boolean check(int x, int y) { // 좌표가 유효한지 검사하는 함수  // 유효한 좌표일 경우 true, 유효하지 않은 좌표일 경우 false 반환
        if (x >= 0 && x < M && y >= 0 && y < N) { // 배추밭의 범위 안에 있을 경우
            return true;
        }

        return false;
    }

    public static void calculateEarthwormsNum(int x, int y) { // BFS  // 해당 좌표의 인접한 좌표에 배추가 있는지 검사하는 함수
        Queue<int[]> que = new ArrayDeque<>();

        que.offer(new int[] {x, y}); // 검사를 시작할 처음 좌표 큐에 저장
        isChecked[x][y] = true; // 검사를 시작할 처음 좌표 검사 처리
        while (!que.isEmpty()) { // 큐가 비어 있지 않으면 계속 반복
            int now[] = que.poll(); // 검사를 시작할 좌표

            for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우 검사
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (check(nx, ny)) { // 좌표의 범위 안에 있을 경우
                    if (!isChecked[nx][ny] && (field[nx][ny] == 1)) { // 해당 좌표를 검사하지 않았고, 해당 좌표에 배추가 심어져 있을 경우
                        que.offer(new int[] {nx, ny}); // 해당 좌표 큐에 추가
                        isChecked[nx][ny] = true; // 해당 좌표 검사 처리
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 개수 T

        for (int t = 0; t < T; t++) {
            StringTokenizer token = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            int K = Integer.parseInt(token.nextToken()); // 배추가 심어져 있는 위치의 개수 K (1 ≤ K ≤ 2500)

            field = new int[M][N];
            for (int k = 0; k < K; k++) {
                token = new StringTokenizer(bf.readLine());
                int X = Integer.parseInt(token.nextToken()); // 배추의 위치 X (0 ≤ X ≤ M-1)
                int Y = Integer.parseInt(token.nextToken()); // 배추의 위치 Y (0 ≤ Y ≤ N-1)

                field[X][Y] = 1;
            }

            isChecked = new boolean[M][N];
            int earthwormsNum = 0; // 필요한 최소의 배추흰지렁이 마리 수
            for (int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    if (!isChecked[m][n] && (field[m][n] == 1)) { // 해당 좌표를 검사하지 않았고, 해당 좌표에 배추가 심어져 있을 경우
                        calculateEarthwormsNum(m, n); // 해당 좌표를 검사하여 인접한 배추가 있을 경우, 해당 좌표를 포함한 배추가 있는 구역 검사 처리
                        earthwormsNum += 1; // 배추가 있는 구역일 경우 배추흰지렁이 마리 수 추가
                    }
                }
            }

            System.out.println(earthwormsNum);
        }
    }
}
