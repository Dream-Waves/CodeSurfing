/*
1303. Silver 1 - 전쟁 - 전투

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           16147	    6348      5050	         38.287%


    문제
        전쟁은 어느덧 전면전이 시작되었다. 결국 전투는 난전이 되었고, 우리 병사와 적국 병사가 섞여 싸우게 되었다. 그러나 당신의 병사들은 흰색 옷을 입고, 적국의 병사들은 파란색 옷을 입었기 때문에 서로가 적인지 아군인지는 구분할 수 있다. 문제는 같은 팀의 병사들은 모이면 모일수록 강해진다는 사실이다.
        N명이 뭉쳐있을 때는 N^2의 위력을 낼 수 있다. 과연 지금 난전의 상황에서는 누가 승리할 것인가? 단, 같은 팀의 병사들이 대각선으로만 인접한 경우는 뭉쳐 있다고 보지 않는다.


    입력
        첫째 줄에는 전쟁터의 가로 크기 N, 세로 크기 M(1 ≤ N, M ≤ 100)이 주어진다. 그 다음 두 번째 줄에서 M+1번째 줄에는 각각 (X, Y)에 있는 병사들의 옷색이 띄어쓰기 없이 주어진다. 모든 자리에는 병사가 한 명 있다. B는 파란색, W는 흰색이다. 당신의 병사와 적국의 병사는 한 명 이상 존재한다.


    출력
        첫 번째 줄에 당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력한다.


    예제 입력 1
        5 5
        WBWWW
        WWWWW
        BBBBB
        BBBWW
        WWWWW
    예제 출력 1
        130 65


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        깊이 우선 탐색
*/


// 메모리 : 14676KB
// 시간 : 132ms
// 코드 길이 : 3904B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1303 {
    static int N; // 전쟁터의 가로 크기 (1 ≤ N ≤ 100)
    static int M; // 전쟁터의 세로 크기 (1 ≤ M ≤ 100)
    static char battleField[][]; // 전쟁터의 해당 좌표에 있는 병사가 입은 옷의 색을 저장하는 배열
    static boolean isChecked[][]; // 해당 좌표의 전쟁터 자리에 있는 병사의 검사 여부를 저장하는 배열
    static int dx[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dy[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int whitePower; // 우리 병사의 위력의 합  // 흰색 옷을 입은 병사의 위력의 합
    static int bluePower; // 적국 병사의 위력의 합  // 파란색 옷을 입은 병사의 위력의 합

    public static boolean check(int x, int y) { // 해당 좌표가 전쟁터 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < M && y >= 0 && y < N) {
            return true;
        }

        return false;
    }

    public static int findTeamNumber(int x, int y, char team) { // 해당 팀의 병사의 수를 구하는 메서드
        Queue<int[]> queue = new ArrayDeque<>();

        int teamNumber = 1; // 해당 팀의 병사의 수
        queue.offer(new int[] {x, y});
        isChecked[x][y] = true;
        while (!queue.isEmpty()) {
            int now[] = queue.poll(); // 검사할 좌표

            for (int d = 0; d < 4; d++) { // 상, 하, 좌, 우 방향에 있는 다음 좌표 체크
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (check(nx, ny) && !isChecked[nx][ny]) { // 다음 좌표가 전쟁터의 범위를 벗어나지 않았고, 검사하지 않은 좌표일 경우
                    if (battleField[nx][ny] == team) { // 다음 좌표에 있는 병사가 같은 팀의 병사일 경우
                        teamNumber += 1;
                        queue.offer(new int[] {nx, ny});
                        isChecked[nx][ny] = true;
                    }
                }
            }
        }

        return teamNumber;
    }

    public static void calculatePower(int team, int number) { // 해당 팀의 병사의 위력의 합을 구하는 메서드  // calculatePower(팀, 해당 팀의 뭉쳐 있는 병사의 수)
        if (team == 'W') {
            whitePower += (int) Math.pow(number, 2);
        }
        else if (team == 'B') {
            bluePower += (int) Math.pow(number, 2);
        }
    }

    public static void calculateAnswer() { // 각 팀의 뭉쳐 있는 병사의 수를 구하고 위력의 합을 구하여 출력하는 메서드
        isChecked = new boolean[M][N];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (!isChecked[r][c]) { // 검사하지 않은 좌표일 경우
                    calculatePower(battleField[r][c], findTeamNumber(r, c, battleField[r][c]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(whitePower);
        sb.append(" ");
        sb.append(bluePower);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        battleField = new char[M][N];
        for (int m = 0; m < M; m++) {
            String str = bf.readLine();
            for (int n = 0; n < N; n++) {
                battleField[m][n] = str.charAt(n);
            }
        }

        calculateAnswer();
    }
}
