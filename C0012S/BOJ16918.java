/*
16918. Silver 1 - 봄버맨

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           14878	    5900      4291	         39.537%


    문제
        봄버맨은 크기가 R×C인 직사각형 격자판 위에서 살고 있다. 격자의 각 칸은 비어있거나 폭탄이 들어있다.
        폭탄이 있는 칸은 3초가 지난 후에 폭발하고, 폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다. 즉, 폭탄이 있던 칸이 (i, j)인 경우에 (i+1, j), (i-1, j), (i, j+1), (i, j-1)도 함께 파괴된다. 만약, 폭탄이 폭발했을 때, 인접한 칸에 폭탄이 있는 경우에는 인접한 폭탄은 폭발 없이 파괴된다. 따라서, 연쇄 반응은 없다.

        봄버맨은 폭탄에 면역력을 가지고 있어서, 격자판의 모든 칸을 자유롭게 이동할 수 있다. 봄버맨은 다음과 같이 행동한다.
            · 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
            · 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
            · 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
            · 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
            · 3과 4를 반복한다.

        폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하려고 한다.

        예를 들어, 초기 상태가 아래와 같은 경우를 보자.
            ...
            .O.
            ...

        1초가 지난 후에는 아무 일도 벌어지지 않기 때문에, 위와 같다고 볼 수 있다. 1초가 더 흐른 후에 격자판의 상태는 아래와 같아진다.
            OOO
            OOO
            OOO

        1초가 지난 후엔 가운데에 있는 폭탄이 폭발해 가운데 칸과 인접한 네 칸이 빈 칸이 된다.
            O.O
            ...
            O.O


    입력
        첫째 줄에 R, C, N (1 ≤ R, C, N ≤ 200)이 주어진다. 둘째 줄부터 R개의 줄에 격자판의 초기 상태가 주어진다. 빈 칸은 '.'로, 폭탄은 'O'로 주어진다.


    출력
        총 R개의 줄에 N초가 지난 후의 격자판 상태를 출력한다.


    예제 입력 1
        6 7 3
        .......
        ...O...
        ....O..
        .......
        OO.....
        OO.....
    예제 출력 1
        OOO.OOO
        OO...OO
        OOO...O
        ..OO.OO
        ...OOOO
        ...OOOO

    예제 입력 2
        6 7 4
        .......
        ...O...
        ....O..
        .......
        OO.....
        OO.....
    예제 출력 2
        OOOOOOO
        OOOOOOO
        OOOOOOO
        OOOOOOO
        OOOOOOO
        OOOOOOO

    예제 입력 3
        6 7 5
        .......
        ...O...
        ....O..
        .......
        OO.....
        OO.....
    예제 출력 3
        .......
        ...O...
        ....O..
        .......
        OO.....
        OO.....


    힌트
        아래는 시간이 지난 후 예제 격자판의 상태이다.
                .......
                ...O...
                ....O..
                .......
                OO.....
                OO.....
            <초기 상태, 1초 후>

                OOOOOOO
                OOOOOOO
                OOOOOOO
                OOOOOOO
                OOOOOOO
                OOOOOOO
                <2초 후>

                OOO.OOO
                OO...OO
                OOO...O
                ..OO.OO
                ...OOOO
                ...OOOO
                <3초 후>

                OOOOOOO
                OOOOOOO
                OOOOOOO
                OOOOOOO
                OOOOOOO
                OOOOOOO
                <4초 후>

                .......
                ...O...
                ....O..
                .......
                OO.....
                OO.....
                <5초 후>


    알고리즘 분류
        구현
        시뮬레이션
*/


// 메모리 : 17252KB
// 시간 : 240ms
// 코드 길이 : 3992B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {
    static int R; // 격자판의 행의 수
    static int C; // 격자판의 열의 수
    static int N; // 폭탄을 설치한 후, 지난 시간
    static char map[][][]; // 격자판의 정보를 저장하는 배열  // map[행의 수][열의 수][폭탄 설치 후 흐른 시간]
    static int dx[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dy[] = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static boolean check(int x, int y) { // 해당 좌표가 격자판 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < R && y >= 0 && y < C) {
            return true;
        }

        return false;
    }

    public static void set() { // 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치하는 메서드
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c][0] == '.') { // 폭탄이 설치되어 있지 않은 칸일 경우
                    map[r][c][0] = 'O'; // 폭탄 설치
                }
                else { // 폭탄이 설치된 칸일 경우
                    map[r][c][1] += 1; // 폭탄 설치 후 흐른 시간 증가
                }
            }
        }
    }

    public static void bomb(int x, int y) { // 폭탄을 폭발시키는 메서드
        if (map[x][y][0] == 'O') { // 폭탄이 설치된 칸일 경우
            map[x][y][0] = '.'; // 폭탄 폭발

            // 폭탄이 설치된 칸에 인접한 칸 파괴
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (check(nx, ny)) {
                    if ((map[nx][ny][0] == 'O') && (map[nx][ny][1] == '3')) { // 인접한 칸에도 설치 후 3 초가 흐른 폭탄이 설치되어 있을 경우
                        continue;
                    }

                    map[nx][ny][0] = '.';
                    map[nx][ny][1] = '0';
                }
            }
        }

        map[x][y][1] = '0'; // 해당 칸의 폭탄 설치 후 흐른 시간 초기화
    }

    public static void play() { // 봄버맨의 행동을 수행하는 메서드
        while (N > 1) {
            set(); // 폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치

            // 3 초 전에 설치된 폭탄 모두 폭발
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c][1] == '3') { // 폭탄 설치 후 3 초가 흐른 경우
                        bomb(r, c);
                    }
                }
            }

            N -= 1; // 시간 감소
        }
    }

    public static void print() { // N 초가 지난 후의 격자판의 상태를 출력하는 메서드
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                sb.append(map[x][y][0]);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());

        map = new char[R][C][1];
        for (int i = 0; i < R; i++) {
            String str = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = new char[] {str.charAt(j), '0'};

                if (map[i][j][0] == 'O') {
                    map[i][j][1] = '1';
                }
            }
        }

        play(); // 봄버맨의 행동 수행
        print(); // N 초가 지난 후의 격자판의 상태 출력
    }
}
