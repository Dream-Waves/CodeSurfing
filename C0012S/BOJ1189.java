/*
1189. Silver 1 - 컴백홈

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           5578	    3089      2422	         54.648%


    문제
        한수는 캠프를 마치고 집에 돌아가려 한다. 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다. 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.
                  cdef  ...f  ..ef  ..gh  cdeh  cdej  ...f
                  bT..  .T.e  .Td.  .Tfe  bTfg  bTfi  .Tde
                  a...  abcd  abc.  abcd  a...  a.gh  abc.
            거리 :  6     6     6     8     8    10    6

        위 예제는 한수가 집에 돌아갈 수 있는 모든 경우를 나타낸 것이다. T로 표시된 부분은 가지 못하는 부분이다. 문제는 R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중 거리가 K인 가짓수를 구하는 것이다.


    입력
        첫 줄에 정수 R(1 ≤ R ≤ 5), C(1 ≤ C ≤ 5), K(1 ≤ K ≤ R×C)가 공백으로 구분되어 주어진다. 두 번째부터 R+1번째 줄까지는 R×C 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가 C인 문자열이 주어진다.


    출력
        첫 줄에 거리가 K인 가짓수를 출력한다.


    예제 입력 1
        3 4 6
        ....
        .T..
        ....
    예제 출력 1
        4


    알고리즘 분류
        그래프 이론
        브루트포스 알고리즘
        그래프 탐색
        깊이 우선 탐색
        백트래킹
*/


// 메모리 : 15004KB
// 시간 : 144ms
// 코드 길이 : 3014B
// 정답

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189 {
    static int R; // 정수 R (1 ≤ R ≤ 5)
    static int C; // 정수 C (1 ≤ C ≤ 5)
    static int K; // 정수 K (1 ≤ K ≤ R × C)
    static char road[][]; // 한수의 캠프에서부터 집까지의 길을 나타내는 2 차원 배열
    static boolean isVisited[][]; // 한수가 캠프를 마치고 집까지 돌아가기 위해 해당 위치를 방문했는지의 여부를 저장하는 2 차원 배열
    static int dx[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dy[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int way; // 한수가 집까지 도착하는 경우 중 거리가 K인 가짓수

    public static boolean check(int x, int y) { // 해당 좌표가 한수의 캠프에서부터 집까지의 길 안의 좌표인지 검사하는 함수  // 길 안의 좌표일 경우 true, 길 안의 좌표가 아닐 경우 false
        if (x >= 0 && x < R && y >= 0 && y < C) {
            return true;
        }

        return false;
    }

    public static void findWayToHome(int row, int column, int count) { // 한수가 집까지 도착하는 경우 중 거리가 K인 가짓수를 구하는 함수
        if (row == 0 && column == C - 1) { // 집에 도착했을 경우
            if (count == K) { // 한수의 캠프로부터 집까지의 거리가 K일 경우
                way += 1; // 한수가 집까지 도착하는 가짓수 추가
                return;
            }
        }

        for (int d = 0; d < 4; d++) {
            int nx = row + dx[d]; // 다음 좌표의 행의 인덱스
            int ny = column + dy[d]; // 다음 좌표의 열의 인덱스

            if (check(nx, ny) && !isVisited[nx][ny] && road[nx][ny] != 'T') { // 다음 좌표가 캠프에서부터 집까지의 길 안의 좌표의 범위를 벗어나지 않았고, 방문하지 않은 좌표이며 가지 못하는 부분인 'T'로 표시된 부분이 아닐 경우
                isVisited[nx][ny] = true;
                findWayToHome(nx, ny, count + 1);
                isVisited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        road = new char[R][C];
        isVisited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            String roadString = bf.readLine();

            for (int c = 0; c < C; c++) {
                road[r][c] = roadString.charAt(c);
            }
        }

        isVisited[R - 1][0] = true; // 한수의 현재 위치 방문 체크
        findWayToHome(R - 1, 0, 1);

        System.out.println(way);
    }
}
