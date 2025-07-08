/*
Lv. 2 #77485 - 행렬 테두리 회전하기

    문제 설명
        rows x columns 크기인 행렬이 있습니다. 행렬에는 1부터 rows x columns까지의 숫자가 한 줄씩 순서대로 적혀있습니다. 이 행렬에서 직사각형 모양의 범위를 여러 번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로 회전시키려 합니다. 각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현하며, 그 의미는 다음과 같습니다.
            · x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.

        다음은 6 x 6 크기 행렬의 예시입니다.
            [그림은 문제에서 참고]

        이 행렬에 (2, 2, 5, 4) 회전을 적용하면, 아래 그림과 같이 2행 2열부터 5행 4열까지 영역의 테두리가 시계방향으로 회전합니다. 이때, 중앙의 15와 21이 있는 영역은 회전하지 않는 것을 주의하세요.
            [그림은 문제에서 참고]

        행렬의 세로 길이(행 개수) rows, 가로 길이(열 개수) columns, 그리고 회전들의 목록 queries가 주어질 때, 각 회전들을 배열에 적용한 뒤, 그 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.


    제한사항
        · rows는 2 이상 100 이하인 자연수입니다.
        · columns는 2 이상 100 이하인 자연수입니다.
        · 처음에 행렬에는 가로 방향으로 숫자가 1부터 하나씩 증가하면서 적혀있습니다.
            · 즉, 아무 회전도 하지 않았을 때, i 행 j 열에 있는 숫자는 ((i-1) x columns + j)입니다.
        · queries의 행의 개수(회전의 개수)는 1 이상 10,000 이하입니다.
        · queries의 각 행은 4개의 정수 [x1, y1, x2, y2]입니다.
            · x1 행 y1 열부터 x2 행 y2 열까지 영역의 테두리를 시계방향으로 회전한다는 뜻입니다.
            · 1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns입니다.
            · 모든 회전은 순서대로 이루어집니다.
            · 예를 들어, 두 번째 회전에 대한 답은 첫 번째 회전을 실행한 다음, 그 상태에서 두 번째 회전을 실행했을 때 이동한 숫자 중 최솟값을 구하면 됩니다.


    입출력 예시
        rows	columns	    queries	                                    result
        6	    6	        [[2,2,5,4],[3,3,6,6],[5,1,6,3]]	            [8, 10, 25]
        3	    3	        [[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]	[1, 1, 5, 3]
        100	    97	        [[1,1,100,97]]	[1]


    입출력 예 설명
        입출력 예 #1
            · 회전을 수행하는 과정을 그림으로 표현하면 다음과 같습니다.
                [그림은 문제에서 참고]

        입출력 예 #2
            · 회전을 수행하는 과정을 그림으로 표현하면 다음과 같습니다.
                [그림은 문제에서 참고]

        입출력 예 #3
            · 이 예시에서는 행렬의 테두리에 위치한 모든 칸들이 움직입니다. 따라서, 행렬의 테두리에 있는 수 중 가장 작은 숫자인 1이 바로 답이 됩니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.04ms, 74.3MB)
        테스트 2 〉	통과 (0.03ms, 73.3MB)
        테스트 3 〉	통과 (9.07ms, 89.2MB)
        테스트 4 〉	통과 (6.81ms, 79.5MB)
        테스트 5 〉	통과 (11.52ms, 98.7MB)
        테스트 6 〉	통과 (14.17ms, 84.9MB)
        테스트 7 〉	통과 (13.91ms, 84.7MB)
        테스트 8 〉	통과 (8.72ms, 78.9MB)
        테스트 9 〉	통과 (7.22ms, 98MB)
        테스트 10 〉	통과 (6.68ms, 95.9MB)
        테스트 11 〉	통과 (7.10ms, 91.2MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

class PRO77485 {
    int[][] matrix; // 행렬의 정보를 저장하는 배열
    int[] answer; // 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 저장한 배열

    public void init(int rows, int columns) { // 행렬을 초기화하는 메서드
        matrix = new int[rows][columns];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix[x][y] = (columns * x) + y + 1;
            }
        }
    }

    public void rotate(int index, int[] area) { // 행렬을 회전하고, 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자를 구하는 메서드
        // 위쪽 영역 : 오른쪽으로 이동
        int lastTop = matrix[area[0] - 1][area[3] - 1]; // 위쪽 영역의 마지막 숫자
        int minNum = lastTop; // 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자
        for (int t = area[3] - 1; t >= area[1]; t--) {
            matrix[area[0] - 1][t] = matrix[area[0] - 1][t - 1];
            minNum = Math.min(minNum, matrix[area[0] - 1][t]);
        }

        // 오른쪽 영역 : 아래쪽으로 이동
        int lastRight = matrix[area[2] - 1][area[3] - 1]; // 오른쪽 영역의 마지막 숫자
        for (int r = area[2] - 1; r > area[0]; r--) {
            matrix[r][area[3] - 1] = matrix[r - 1][area[3] - 1];
            minNum = Math.min(minNum, matrix[r][area[3] - 1]);
        }
        matrix[area[0]][area[3] - 1] = lastTop;

        // 아래쪽 영역 : 왼쪽으로 이동
        int firstBottom = matrix[area[2] - 1][area[1] - 1]; // 아래쪽 영역의 첫 번째 숫자
        for (int b = area[1] - 1; b < area[3] - 2; b++) {
            matrix[area[2] - 1][b] = matrix[area[2] - 1][b + 1];
            minNum = Math.min(minNum, matrix[area[2] - 1][b]);
        }
        matrix[area[2] - 1][area[3] - 2] = lastRight;
        minNum = Math.min(minNum, lastRight);

        // 왼쪽 영역 : 위쪽으로 이동
        for (int l = area[0] - 1; l < area[2] - 2; l++) {
            matrix[l][area[1] - 1] = matrix[l + 1][area[1] - 1];
            minNum = Math.min(minNum, matrix[l][area[1] - 1]);
        }
        matrix[area[2] - 2][area[1] - 1] = firstBottom;
        minNum = Math.min(minNum, firstBottom);

        answer[index] = minNum;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        init(rows, columns);

        int rotateNum = queries.length; // 회전 횟수
        answer = new int[rotateNum];
        for (int q = 0; q < rotateNum; q++) {
            rotate(q, queries[q]);
        }

        return answer;
    }
}