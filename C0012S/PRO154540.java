/*
Lv. 2 #154540 - 무인도 여행

    문제 설명
        메리는 여름을 맞아 무인도로 여행을 가기 위해 지도를 보고 있습니다. 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다. 지도는 1 x 1크기의 사각형들로 이루어진 직사각형 격자 형태이며, 격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가 적혀있습니다. 지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다. 이때, 상, 하, 좌, 우로 연결되는 땅들은 하나의 무인도를 이룹니다. 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상, 하, 좌, 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다. 어떤 섬으로 놀러 갈지 못 정한 메리는 우선 각 섬에서 최대 며칠씩 머물 수 있는지 알아본 후 놀러갈 섬을 결정하려 합니다.
        지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때, 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return 하는 solution 함수를 완성해주세요. 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.


    제한사항
        · 3 ≤ maps의 길이 ≤ 100
            · 3 ≤ maps[i]의 길이 ≤ 100
            · maps[i]는 'X' 또는 1 과 9 사이의 자연수로 이루어진 문자열입니다.
            · 지도는 직사각형 형태입니다.


    입출력 예
        maps	                                result
        ["X591X","X1X5X","X231X", "1XXX1"]	    [1, 1, 27]
        ["XXX","XXX","XXX"]	                    [-1]


    입출력 예 설명
        입출력 예 #1
            위 문자열은 다음과 같은 지도를 나타냅니다.
                [그림은 문제에서 참고]

            연결된 땅들의 값을 합치면 다음과 같으며
                [그림은 문제에서 참고]

            이를 오름차순으로 정렬하면 [1, 1, 27]이 됩니다.

        입출력 예 #2
            위 문자열은 다음과 같은 지도를 나타냅니다.
                [그림은 문제에서 참고]

            섬이 존재하지 않기 때문에 -1을 배열에 담아 반환합니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (2.84ms, 85MB)
        테스트 2 〉	통과 (3.88ms, 87.3MB)
        테스트 3 〉	통과 (2.35ms, 80.5MB)
        테스트 4 〉	통과 (2.48ms, 84.7MB)
        테스트 5 〉	통과 (3.63ms, 79.3MB)
        테스트 6 〉	통과 (4.40ms, 76.4MB)
        테스트 7 〉	통과 (3.96ms, 75.7MB)
        테스트 8 〉	통과 (4.27ms, 74.4MB)
        테스트 9 〉	통과 (6.46ms, 72.5MB)
        테스트 10 〉	통과 (6.47ms, 79.7MB)
        테스트 11 〉	통과 (5.01ms, 78.3MB)
        테스트 12 〉	통과 (8.07ms, 76.5MB)
        테스트 13 〉	통과 (23.71ms, 72.6MB)
        테스트 14 〉	통과 (7.08ms, 89.9MB)
        테스트 15 〉	통과 (6.63ms, 90.7MB)
        테스트 16 〉	통과 (7.25ms, 79.3MB)
        테스트 17 〉	통과 (3.16ms, 79.1MB)
        테스트 18 〉	통과 (11.22ms, 95.8MB)
        테스트 19 〉	통과 (8.95ms, 91.7MB)
        테스트 20 〉	통과 (2.41ms, 84.7MB)
        테스트 21 〉	통과 (3.90ms, 87.8MB)
        테스트 22 〉	통과 (2.12ms, 75.2MB)
        테스트 23 〉	통과 (13.76ms, 86.7MB)
        테스트 24 〉	통과 (6.46ms, 95.2MB)
        테스트 25 〉	통과 (2.40ms, 83.7MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO154540 {
    int width; // 지도의 너비
    int height; // 지도의 높이
    boolean[][] isChecked; // 각 지도의 땅의 방문 여부를 저장하는 배열
    int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우
    ArrayList<Integer> answer; // 각 섬에서 머무를 수 있는 최대 기간을 저장하는 리스트

    public boolean check(int x, int y) { // 해당 좌표가 지도 범위 내의 좌표인지 구하는 메서드
        if (x >= 0 && x < height && y >= 0 && y < width) {
            return true;
        }

        return false;
    }

    public void find(String[] maps, int x, int y) { // 해당 좌표의 섬에서 최대 며칠 머무를 수 있는지 구하는 메서드
        Queue<int[]> queue = new ArrayDeque<>();

        int day = maps[x].charAt(y) - '0'; // 메리가 해당 좌표의 섬에서 머무를 수 있는 최대 기간
        queue.offer(new int[] {x, y});
        isChecked[x][y] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll(); // 현재 검사하고 있는 좌표

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d]; // 다음에 검사할 x 좌표
                int ny = now[1] + dy[d]; // 다음에 검사할 y 좌표

                if (check(nx, ny) && !isChecked[nx][ny]) { // 다음에 검사할 좌표가 지도 범위 내의 좌표이고, 아직 검사하지 않은 좌표일 경우
                    if (maps[nx].charAt(ny) != 'X') { // 다음에 검사할 좌표의 위치가 바다가 아닐 경우
                        day += (maps[nx].charAt(ny) - '0'); // 메리가 머물 수 있는 기간 추가
                        isChecked[nx][ny] = true;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }

        if (day > 0) { // 메리가 해당 좌표의 섬에서 0 일보다 더 머무를 수 있을 경우
            answer.add(day);
        }
    }

    public int[] solution(String[] maps) {
        width = maps[0].length();
        height = maps.length;
        isChecked = new boolean[height][width];
        answer = new ArrayList<>();

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                char island = maps[r].charAt(c); // (r, c) 좌표의 섬의 정보

                if (!isChecked[r][c] && (island != 'X')) { // 해당 좌표가 아직 검사하지 않은 좌표이고, 해당 좌표의 위치가 바다가 아닐 경우
                    find(maps, r, c);
                }
            }
        }

        if (answer.isEmpty()) { // 메리가 머무를 수 있는 섬이 존재하지 않을 경우
            answer.add(-1);
        }
        else { // 메리가 머무를 수 있는 섬이 존재할 경우
            Collections.sort(answer); // 각 섬에서 머무를 수 있는 최대 기간을 저장하는 리스트 오름차순 정렬
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
