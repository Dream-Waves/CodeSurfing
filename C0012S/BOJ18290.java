/*
18290. Silver 1 - NM과 K (1)

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           8708	    2586      1556	         26.178%


    문제
        크기가 N×M인 격자판의 각 칸에 정수가 하나씩 들어있다. 이 격자판에서 칸 K개를 선택할 것이고, 선택한 칸에 들어있는 수를 모두 더한 값의 최댓값을 구하려고 한다. 단, 선택한 두 칸이 인접하면 안된다. r행 c열에 있는 칸을 (r, c)라고 했을 때, (r-1, c), (r+1, c), (r, c-1), (r, c+1)에 있는 칸이 인접한 칸이다.


    입력
        첫째 줄에 N, M, K가 주어진다. 둘째 줄부터 N개의 줄에 격자판에 들어있는 수가 주어진다.


    출력
        선택한 칸에 들어있는 수를 모두 더한 값의 최댓값을 출력한다.


    제한
        · 1 ≤ N, M ≤ 10
        · 1 ≤ K ≤ min(4, N×M)
        · 격자판에 들어있는 수는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수이다.
        · 항상 K개의 칸을 선택할 수 있는 경우만 입력으로 주어진다.


    예제 입력 1
        1 1 1
        1
    예제 출력 1
        1

    예제 입력 2
        2 2 2
        1 2
        3 4
    예제 출력 2
        5

    예제 입력 3
        2 2 2
        5 4
        4 5
    예제 출력 3
        10

    예제 입력 4
        5 5 3
        1 9 8 -2 0
        -1 9 8 -3 0
        -5 1 9 -1 0
        0 0 0 9 8
        9 9 9 0 0
    예제 출력 4
        27


    알고리즘 분류
        브루트포스 알고리즘
        백트래킹
*/


// 메모리 : 16128KB
// 시간 : 1280ms
// 코드 길이 : 3302B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18290 {
    static int N; // 격자판의 세로 길이 (1 ≤ N ≤ 10)
    static int M; // 격자판의 가로 길이 (1 ≤ M ≤ 10)
    static int K; // 선택할 수 있는 칸의 수 (1 ≤ K ≤ min(4, N × M))
    static int board[][]; // 크기가 N × M인 격자판의 각 칸에 들어 있는 수를 저장하는 배열
    static boolean isSelected[][]; // 해당 격자판의 칸의 선택 여부를 저장하는 배열
    static int dr[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dc[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int maxValue; // 선택한 칸에 들어 있는 수를 모두 더한 값의 최댓값

    public static boolean checkIndex(int r, int c) { // 해당 좌표가 격자판의 범위를 벗어났는지 검사하는 메서드
        if (r >= 0 && r < N && c >= 0 && c < M) {
            return true;
        }

        return false;
    }

    public static boolean checkSpace(int r, int c) { // 해당 좌표의 격자판의 칸을 선택할 수 있는지 검사하는 메서드
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (checkIndex(nr, nc) && isSelected[nr][nc]) { // 인접한 좌표가 격자판의 범위를 벗어나지 않고, 이미 선택한 좌표일 경우
                return false;
            }
        }

        return true;
    }

    public static void sum(int nowRowIndex, int nowColumnIndex, int selectedNum, int sum) { // 격자판에서 칸 K 개를 선택하고, 선택한 칸에 들어 있는 수를 모두 더한 값의 최댓값을 구하는 메서드
        if (selectedNum >= K) {
            maxValue = Math.max(maxValue, sum);
            return;
        }

        for (int sr = nowRowIndex; sr < N; sr++) {
            for (int sc = nowColumnIndex; sc < M; sc++) {
                if (!isSelected[sr][sc] && checkSpace(sr, sc)) { // 해당 좌표가 선택되지 않은 좌표이고, 선택할 수 있는 격자판의 칸일 경우
                    isSelected[sr][sc] = true;
                    sum(nowRowIndex, nowColumnIndex, selectedNum + 1, sum + board[sr][sc]);
                    isSelected[sr][sc] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        maxValue = Integer.MIN_VALUE; // 격자판에 들어 있는 수는 -10000보다 크거나 같고, 10000보다 작거나 같은 정수이므로 정수의 최솟값으로 초기화
        isSelected = new boolean[N][M];
        sum(0, 0, 0, 0);

        System.out.println(maxValue);
    }
}
