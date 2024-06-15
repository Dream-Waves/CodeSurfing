/*
Lv. 2 #17679 - [1차] 프렌즈4블록

    문제 설명
        블라인드 공채를 통과한 신입 사원 라이언은 신규 게임 개발 업무를 맡게 되었다. 이번에 출시할 게임 제목은 "프렌즈4블록".

        같은 모양의 카카오프렌즈 블록이 2×2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임이다.
            [그림은 문제에서 참고]

        만약 판이 위와 같이 주어질 경우, 라이언이 2×2로 배치된 7개 블록과 콘이 2×2로 배치된 4개 블록이 지워진다. 같은 블록은 여러 2×2에 포함될 수 있으며, 지워지는 조건에 만족하는 2×2 모양이 여러 개 있다면 한꺼번에 지워진다.
            [그림은 문제에서 참고]

        블록이 지워진 후에 위에 있는 블록이 아래로 떨어져 빈 공간을 채우게 된다.
            [그림은 문제에서 참고]

        만약 빈 공간을 채운 후에 다시 2×2 형태로 같은 모양의 블록이 모이면 다시 지워지고 떨어지고를 반복하게 된다.
            [그림은 문제에서 참고]

        위 초기 배치를 문자로 표시하면 아래와 같다.
            TTTANT
            RRFACC
            RRRFCC
            TRRRAA
            TTMMMF
            TMMTTJ

        각 문자는 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)을 의미한다

        입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.


    입력 형식
        · 입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
        · 2 ≦ n, m ≦ 30
        · board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.


    출력 형식
        입력으로 주어진 판 정보를 가지고 몇 개의 블록이 지워질지 출력하라.


    입출력 예제
        m	n	board	                                                        answer
        4	5	["CCBDE", "AAADE", "AAABF", "CCBBF"]	                        14
        6	6	["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]	15


    예제에 대한 설명
        · 입출력 예제 1의 경우, 첫 번째에는 A 블록 6개가 지워지고, 두 번째에는 B 블록 4개와 C 블록 4개가 지워져, 모두 14개의 블록이 지워진다.
        · 입출력 예제 2는 본문 설명에 있는 그림을 옮긴 것이다. 11개와 4개의 블록이 차례로 지워지며, 모두 15개의 블록이 지워진다.


    해설 보러가기
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.05ms, 68.6MB)
        테스트 2 〉	통과 (0.09ms, 75.3MB)
        테스트 3 〉	통과 (0.06ms, 76.6MB)
        테스트 4 〉	통과 (1.49ms, 72.3MB)
        테스트 5 〉	통과 (8.70ms, 76.5MB)
        테스트 6 〉	통과 (1.75ms, 71.1MB)
        테스트 7 〉	통과 (0.53ms, 72.3MB)
        테스트 8 〉	통과 (1.59ms, 74.6MB)
        테스트 9 〉	통과 (0.07ms, 73.7MB)
        테스트 10 〉	통과 (0.35ms, 78.8MB)
        테스트 11 〉	통과 (1.00ms, 75.4MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

public class PRO17679 {
    static int row; // 행의 개수
    static int column; // 열의 개수
    static char[][] map; // 블록이 지워지는 과정을 수행할 판의 배치 정보를 저장하는 배열
    static int[] dx = {0, 1, 0, 1}; // 현재, 하, 우, 우하
    static int[] dy = {0, 0, 1, 1}; // 현재, 하, 우, 우하
    static boolean[][] isChecked; // 해당 인덱스의 좌표의 블록의 지워질 여부를 저장하는 배열
    static int answer; // 지워지는 블록의 개수

    public static boolean check(int x, int y) { // 해당 좌표가 판 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < row && y >= 0 && y < column) {
            return true;
        }

        return false;
    }

    public static int disappear(int x, int y) { // 해당 좌표를 기준으로 지워질 블록을 체크하고, 지워질 블록의 개수를 구하는 메서드
        boolean flag = true; // 지워질 블록의 유무
        int disappearedBlock = 0; // 현재 위치를 기준으로 2 × 2 모양의 블록 내 지워질 블록의 개수
        for (int d = 1; d < 4; d++) { // 현재 위치를 기준으로 2 × 2 모양의 블록이 지워질 수 있는 블록인지 체크
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!check(nx, ny) || map[nx][ny] != map[x][y]) {
                flag = false;
                break;
            }
        }

        if (flag) { // 지워질 수 있는 블록이 있을 경우
            for (int b = 0; b < 4; b++) {
                int nx = x + dx[b];
                int ny = y + dy[b];

                if (!isChecked[nx][ny]) {
                    isChecked[nx][ny] = true;
                    disappearedBlock += 1;
                }
            }

        }

        return disappearedBlock;
    }

    public static void down() { // 블록을 아래로 떨어뜨리는 메서드
        for (int h = 0; h < row; h++) {
            for (int w = 0; w < column; w++) {
                if (isChecked[h][w]) { // 지워야 하는 블록일 경우
                    for (int k = h; k > 0; k--) {
                        map[k][w] = map[k - 1][w];
                        map[k - 1][w] = '#';
                    }
                }
            }
        }
    }

    public static void game() { // 지워지는 블록의 개수를 구하는 메서드
        while (true) {
            int nowDisappearedBlock = 0; // 해당 과정에서 지워질 블록의 개수
            isChecked = new boolean[row][column];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    if (map[r][c] != '#') { // 해당 좌표에 블록이 있을 경우
                        nowDisappearedBlock += disappear(r, c);
                    }
                }
            }

            if (nowDisappearedBlock == 0) { // 더 이상 지워질 블록이 없을 경우
                return;
            }

            answer += nowDisappearedBlock;

            down();
        }
    }

    public int solution(int m, int n, String[] board) {
        row = m;
        column = n;

        map = new char[row][column];
        for (int i = 0; i < row; i++) {
            map[i] = board[i].toCharArray();
        }

        game();

        return answer;
    }
}