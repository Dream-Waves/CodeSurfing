/*
Lv. 2 #68645 - 삼각 달팽이

    문제 설명
        정수 n이 매개변수로 주어집니다. 다음 그림과 같이 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return 하도록 solution 함수를 완성해주세요.
            [그림은 문제에서 참고]


    제한사항
        · n은 1 이상 1,000 이하입니다.


    입출력 예
        n	    result
        4	    [1,2,9,3,10,8,4,5,6,7]
        5	    [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
        6	    [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]


    입출력 예 설명
        입출력 예 #1
            · 문제 예시와 같습니다.

        입출력 예 #2
            · 문제 예시와 같습니다.

        입출력 예 #3
            · 문제 예시와 같습니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.02ms, 81.7MB)
        테스트 2 〉	통과 (0.04ms, 83.6MB)
        테스트 3 〉	통과 (0.02ms, 76.1MB)
        테스트 4 〉	통과 (0.64ms, 82.2MB)
        테스트 5 〉	통과 (1.19ms, 90.1MB)
        테스트 6 〉	통과 (0.77ms, 76MB)
        테스트 7 〉	통과 (15.96ms, 145MB)
        테스트 8 〉	통과 (17.11ms, 132MB)
        테스트 9 〉	통과 (18.50ms, 134MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

class PRO68645 {
    static int[][] triangle; // 좌측 상단부터 반시계 방향으로 달팽이 채우기를 진행할 배열
    static int[] dx = {1, 0, -1}; // 하, 우, 좌상
    static int[] dy = {0, 1, -1}; // 하, 우, 좌상
    static int[] answer; // 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 배열

    public static boolean check(int n, int x, int y) { // 해당 좌표가 삼각형 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < n && y >= 0 && y < n) {
            return true;
        }

        return false;
    }

    public static void make(int n) { // 달팽이 채우기를 진행하는 메서드
        int x = -1; // 행 인덱스
        int y = 0; // 열 인덱스
        int moveNum = n; // 한 방향에서 달팽이를 채울 횟수
        int direction = 0; // 달팽이를 채울 방향
        int snailOrder = 1; // 배열에 채울 달팽이 순서

        triangle = new int[n][n];
        while (moveNum > 0) {
            for (int d = 0; d < moveNum; d++) {
                int nx = x + dx[direction]; // 달팽이를 채울 행 인덱스
                int ny = y + dy[direction]; // 달팽이를 채울 열 인덱스

                if (check(n, nx, ny)) { // 달팽이를 채울 좌표가 삼각형 범위 내의 좌표일 경우
                    triangle[nx][ny] = snailOrder;

                    x = nx;
                    y = ny;
                    snailOrder += 1;
                }
            }

            moveNum -= 1;
            direction = (direction + 1) % 3;
        }
    }

    public static int[] find(int n, int size) { // 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 배열을 구하는 메서드
        answer = new int[size];

        int index = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (triangle[r][c] != 0) {
                    answer[index] = triangle[r][c];

                    index += 1;
                }
            }
        }

        return answer;
    }

    public int[] solution(int n) {
        make(n);

        return find(n, n * (n + 1) / 2);
    }
}
