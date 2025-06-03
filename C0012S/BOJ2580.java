/*
2580. Gold 4 - 스도쿠

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           114394	    34058     21755	         27.384%


    문제
        스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이랑 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다. 이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 작은 칸으로 이루어진 정사각형 판 위에서 이뤄지는데, 게임 시작 전 일부 칸에는 1부터 9까지의 숫자 중 하나가 쓰여 있다.
            [그림은 문제에서 참고]

        나머지 빈 칸을 채우는 방식은 다음과 같다.
            1. 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
            2. 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.

        위의 예의 경우, 첫째 줄에는 1을 제외한 나머지 2부터 9까지의 숫자들이 이미 나타나 있으므로 첫째 줄 빈칸에는 1이 들어가야 한다.
            [그림은 문제에서 참고]

        또한 위쪽 가운데 위치한 3x3 정사각형의 경우에는 3을 제외한 나머지 숫자들이 이미 쓰여있으므로 가운데 빈 칸에는 3이 들어가야 한다.
            [그림은 문제에서 참고]

        이와 같이 빈 칸을 차례로 채워 가면 다음과 같은 최종 결과를 얻을 수 있다.
            [그림은 문제에서 참고]

        게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.


    입력
        아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다. 스도쿠 판의 빈 칸의 경우에는 0이 주어진다. 스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.


    출력
        모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.
        스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.


    제한
        · 12095번 문제에 있는 소스로 풀 수 있는 입력만 주어진다.
            · C++14: 80ms
            · Java: 292ms
            · PyPy3: 1172ms


    예제 입력 1
        0 3 5 4 6 9 2 7 8
        7 8 2 1 0 5 6 0 9
        0 6 0 2 7 8 1 3 5
        3 2 1 0 4 6 8 9 7
        8 0 4 9 1 3 5 0 6
        5 9 6 8 2 0 4 1 3
        9 1 7 6 5 2 0 8 0
        6 0 3 7 0 1 9 5 2
        2 5 8 3 9 4 7 6 0
    예제 출력 1
        1 3 5 4 6 9 2 7 8
        7 8 2 1 3 5 6 4 9
        4 6 9 2 7 8 1 3 5
        3 2 1 5 4 6 8 9 7
        8 7 4 9 1 3 5 2 6
        5 9 6 8 2 7 4 1 3
        9 1 7 6 5 2 3 8 4
        6 4 3 7 8 1 9 5 2
        2 5 8 3 9 4 7 6 1


    알고리즘 분류
        구현
        백트래킹
*/


// 메모리 : 22280KB
// 시간 : 572ms
// 코드 길이 : 3833B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2580 {
    static int[][] puzzle; // 스도쿠 판의 정보를 저장하는 배열
    static ArrayList<int[]> zeroIndex; // 스도쿠 판의 빈 칸의 인덱스를 저장하는 리스트

    public static boolean check(int row, int column, int number) { // 스도쿠 판의 좌표 (row, column)에 숫자 number를 작성할 수 있는지 검사하는 메서드
        // 3 × 3 정사각형 검사
        for (int rs = (row - row % 3), re = (3 + row - row % 3); rs < re; rs++) {
            for (int cs = (column - column % 3), ce = (3 + column - column % 3); cs < ce; cs++) {
                if ((rs == row) && (cs == column)) { // 좌표 (row, column)일 경우
                    continue;
                }

                if (puzzle[rs][cs] == number) { // 좌표 (row, column)이 포함된 3 × 3 정사각형 안의 좌표에 숫자 number가 적혀 있을 경우
                    return false;
                }
            }
        }

        // 가로 줄 검사
        for (int c = 0; c < 9; c++) {
            if ((puzzle[row][c] == number) && (c != column)) { // 좌표 (row, c)에 적힌 숫자가 숫자 number이고, 좌표 (row, c)가 좌표 (row, column)가 아닐 경우
                return false;
            }
        }

        // 세로 줄 검사
        for (int r = 0; r < 9; r++) {
            if ((puzzle[r][column] == number) && (r != row)) { // 좌표 (r, column)에 적힌 숫자가 숫자 number이고, 좌표 (r, column)가 좌표 (row, column)가 아닐 경우
                return false;
            }
        }

        return true;
    }

    public static void game(int order) { // 스도쿠 게임을 수행하는 메서드
        if (order >= zeroIndex.size()) { // 마지막 빈 칸까지 모두 채웠을 경우
            print(); // 스도쿠 판 출력

            System.exit(0); // 스도쿠 판의 모든 빈 칸을 채울 방법을 찾았을 경우, 프로그램 종료
        }

        int row = zeroIndex.get(order)[0]; // 스도쿠 판의 (order + 1) 번째 비어 있는 칸의 x 좌표
        int column = zeroIndex.get(order)[1]; // 스도쿠 판의 (order + 1) 번째 비어 있는 칸의 y 좌표
        for (int n = 1; n <= 9; n++) {
            if (check(row, column, n)) { // 해당 칸에 숫자 n을 작성할 수 있을 경우
                puzzle[row][column] = n; // 해당 칸에 숫자 n 작성

                game(order + 1); // 스도쿠 판의 다음 빈 칸에 들어갈 숫자 찾기
            }
        }

        // 해당 칸에 작성할 수 있는 숫자가 없을 경우
        puzzle[row][column] = 0;
    }

    public static void print() { // 스도쿠 판을 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sb.append(puzzle[r][c]);
                sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token;
        puzzle = new int[9][9];
        zeroIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            token = new StringTokenizer(bf.readLine());

            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = Integer.parseInt(token.nextToken());

                if (puzzle[i][j] == 0) { // 스도쿠 판의 해당 칸이 비어 있을 경우
                    zeroIndex.add(new int[] {i, j});
                }
            }
        }

        game(0);
    }
}
