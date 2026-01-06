/*
20165. Gold 5 - 인내의 도미노 장인 호석

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           2316	    1096      850	         46.909%


    문제
        사람을 화나게 하는 법은 다양하다. 그 중에서도 악질은 바로 열심히 세워놓은 도미노를 넘어뜨리는 것이다. 이번에 출시된 보드 게임인 "너 죽고 나 살자 게임"은 바로 이 점을 이용해서 2명이 공격과 수비를 하는 게임이다. 공격수는 도미노를 계속 넘어뜨리고 수비수는 도미노를 계속 세우려고 한다. 본 게임은 다음과 같이 진행된다.
            1. N 행 M 열의 2차원 격자 모양의 게임판의 각 격자에 도미노를 세운다. 각 도미노는 1 이상 5 이하의 높이를 가진다.
            2. 매 라운드는 공격수가 먼저 공격하고, 수비수는 공격이 끝난 뒤에 수비를 한다.
            3. 공격수는 특정 격자에 놓인 도미노를 동, 서, 남, 북 중 원하는 방향으로 넘어뜨린다. 길이가 K인 도미노가 특정 방향으로 넘어진다면, 그 방향으로 K-1 개의 도미노들 중에 아직 넘어지지 않은 것들이 같은 방향으로 연달아 넘어진다. 이 때, 당연히 도미노의 특성상, 연쇄적으로 도미노가 넘어질 수 있다. 이미 넘어진 도미노가 있는 칸을 공격한 경우에는 아무 일이 일어나지 않는다.
            4. 수비수는 넘어져 있는 도미노들 중에 원하는 것 하나를 다시 세울 수 있다. 넘어지지 않은 도미노를 세우려고 하면 아무 일이 일어나지 않는다.
            5. 총 R 번의 라운드동안 3, 4번 과정이 반복된다. 매 라운드마다 공격수가 해당 라운드에 넘어뜨린 도미노의 개수를 세고, R 라운드에 걸친 총합이 곧 공격수의 점수가 된다.

            [그림은 문제에서 참고]
        도미노 공격에 대한 예시 그림이다. 그림의 빨간 숫자들은 넘어진 도미노들을 의미한다.
        예를 들어 {3, 1, 2, 2, 2} 높이의 도미노가 일렬로 서 있는 과정에서 3번을 오른쪽으로 밀면 왼쪽의 3개가 오른쪽으로 넘어지게 된다. 이에 따라 새롭게 넘어진 도미노들의 연쇄작용이 발생하는데, 이 과정에서 4번째에 위치한 길이 2짜리 도미노도 넘어지게 된다. 이어서 마지막 도미노까지 쓰러지게 되는 것이다.

        인내를 빼면 시체라고 자부하는 호석이는 당신의 공격을 이겨내고자 수비수를 자처했다. 초기 도미노 판의 상태와, 각 라운드에서 둘의 행동의 기록을 받아서 공격수의 점수와 게임판의 최종 상태를 출력하는 프로그램을 작성하라.


    입력
        첫 번째 줄에는 게임판의 행 개수 N, 열 개수 M, 라운드 횟수 R 이 주어진다.
        이어서 N개의 줄에 게임판의 상태가 주어진다. 1행부터 주어지며, M 개의 숫자는 각 격자에 놓인 도미노의 길이를 의미한다.
        이어서 R×2 개의 줄에 걸쳐서 공격수와 수비수의 행동이 주어진다. 각 라운드는 두 줄로 이뤄지며, 첫 줄은 공격수, 두번째 줄은 수비수의 행동이 주어진다. 공격수의 행동은 "X Y D"로 주어진다. 이는 X행 Y열의 도미노를 D 방향으로 민다는 뜻이다. D는 E, W, S, N 중 하나이며 각각 동, 서, 남, 북 방향을 의미한다. 수비수의 행동은 "X Y"로 주어진다. 이는 X행 Y열의 도미노를 다시 세운다는 뜻이다.
        만약 이미 넘어진 격자의 도미노를 공격수가 넘어뜨리려 할 때에는 아무 일도 일어나지 않는다. 또한 만약 넘어지지 않은 도미노를 수비수가 세우려고 할 때에도 아무 일도 일어나지 않는다.


    출력
        첫 줄에 공격수의 점수를 출력한다.
        이어서 게임판의 상태를 N 줄에 걸쳐서 출력한다. 각 격자마다 넘어진 것은 F, 넘어지지 않은 것은 S 를 공백으로 구분하여 출력한다.


    제한
        입력되는 모든 값은 양의 정수이다.
        · 1 ≤ N, M ≤ 100
        · 1 ≤ R ≤ 10,000
        · 1 ≤ 도미노의 길이 ≤ 5
        · 공격수와 수비수는 격자를 벗어나는 행동은 하지 않는다.


    예제 입력 1
        5 5 3
        1 1 1 1 1
        1 2 2 1 1
        3 1 2 2 2
        1 3 2 1 1
        1 3 3 1 1
        3 1 E
        3 5
        5 3 N
        3 3
        5 2 N
        3 1
    예제 출력 1
        11
        S F S S S
        S F S S S
        S F S F S
        S F F S S
        S F F S S

        [그림은 문제에서 참고]
    <첫 번째 라운드>
        · 공격수: 3을 오른쪽으로 밀어서 5 개가 연쇄적으로 쓰러짐
        · 수비수: 가장 오른쪽(3, 5)에 넘어진 것을 일으킴

    <두 번째 라운드>
        · 공격수: (5, 3) 을 위로 밀어서 2개가 연쇄적으로 쓰러짐
        · 수비수: (3, 3)의 도미노를 세움

    <세 번째 라운드>
        · 공격수: (5, 2)을 위로 밀어서 4개가 연쇄적으로 쓰러짐
        · 수비수: (3, 1)의 도미노를 세움


    알고리즘 분류
        구현
        시뮬레이션


    채점 및 기타 정보
        · 11개 이상의 데이터를 맞아야 맞았습니다!!를 받는다.
*/


// 메모리 : 22448KB
// 시간 : 212ms
// 코드 길이 : 4709B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20165 {
    static int N; // 게임판의 행의 개수
    static int M; // 게임판의 열의 개수
    static int R; // 라운드 횟수
    static int[][] domino; // 게임판에 존재하는 도미노의 높이를 저장하는 배열  // 1 ≤ domino[r][c] ≤ 5
    static boolean[][] board; // 게임판의 상태를 저장하는 배열  // true : 도미노가 넘어져 있는 상태, false : 도미노가 세워져 있는 상태
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int score; // 공격수의 점수

    public static boolean check(int x, int y) { // 좌표가 게임판 내 좌표인지 검사하는 메서드  // 게임판 내 좌표일 경우 true 반환, 게임판 내 좌표가 아닐 경우 false 반환
        if (x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        }

        return false;
    }

    public static void attack(int x, int y, String direction) { // 공격수가 도미노를 넘어뜨리는 메서드
        if (board[x][y]) { // 넘어뜨릴 도미노가 넘어져 있을 경우
            return;
        }

        int d = 0; // 도미노를 미는 방향  // 0 : 북, 1 : 남, 2 : 서, 3 : 동
        if (direction.equals("E")) { // 도미노를 동쪽으로 밀 경우
            d = 3;
        }
        else if (direction.equals("W")) { // 도미노를 서쪽으로 밀 경우
            d = 2;
        }
        else if (direction.equals("S")) { // 도미노를 남쪽으로 밀 경우
            d = 1;
        }
        else if (direction.equals("N")) { // 도미노를 북쪽으로 밀 경우
            d = 0;
        }

        int count = domino[x][y]; // 넘어질 도미노의 개수

        for (int n = 0; n < count; n++) {
            if (!check(x, y)) { // 도미노가 게임판 내 위치하지 않을 경우
                return;
            }

            if (!board[x][y]) { // 도미노가 넘어져 있지 않을 경우
                board[x][y] = true;
                score += 1;
                count = Math.max(count, (n + domino[x][y]));
            }

            x += dx[d]; // 다음에 넘어뜨릴 도미노의 행의 좌표
            y += dy[d]; // 다음에 넘어뜨릴 도미노의 열의 좌표
        }
    }

    public static void defend(int x, int y) { // 수비수가 넘어져 있는 도미노를 세우는 메서드
        board[x][y] = false;
    }

    public static void print() { // 게임판의 상태를 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        sb.append(score);
        sb.append("\n");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]) { // 도미노가 넘어져 있을 경우
                    sb.append("F");
                }
                else { // 도미노가 세워져 있을 경우
                    sb.append("S");
                }

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
        R = Integer.parseInt(token.nextToken());

        domino = new int[N][M];
        board = new boolean[N][M];

        // 게임판의 각 격자에 위치하는 도미노 정보 입력
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {
                domino[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        // 각 라운드의 정보를 입력 받고, 그에 따라 보드 게임 수행
        for (int r = 0; r < R; r++) {
            for (int w = 0; w < 2; w++) {
                token = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(token.nextToken()) - 1;
                int y = Integer.parseInt(token.nextToken()) - 1;

                if (w == 0) { // 공격수 차례일 경우
                    attack(x, y, token.nextToken());
                }
                else { // 수비수 차례일 경우
                    defend(x, y);
                }
            }
        }

        // 게임이 종료된 후, 공격수의 점수와 게임판의 상태를 출력
        print();
    }
}
