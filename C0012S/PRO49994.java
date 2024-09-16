/*
Lv. 2 #49994 - 방문 길이

    문제 설명
        게임 캐릭터를 4가지 명령어를 통해 움직이려 합니다. 명령어는 다음과 같습니다.
            · U: 위쪽으로 한 칸 가기
            · D: 아래쪽으로 한 칸 가기
            · R: 오른쪽으로 한 칸 가기
            · L: 왼쪽으로 한 칸 가기

        캐릭터는 좌표평면의 (0, 0) 위치에서 시작합니다. 좌표평면의 경계는 왼쪽 위(-5, 5), 왼쪽 아래(-5, -5), 오른쪽 위(5, 5), 오른쪽 아래(5, -5)로 이루어져 있습니다.
            [그림은 문제에서 참고]

        예를 들어, "ULURRDLLU"로 명령했다면
            [그림은 문제에서 참고]

            · 1번 명령어부터 7번 명령어까지 다음과 같이 움직입니다.
                [그림은 문제에서 참고]

            · 8번 명령어부터 9번 명령어까지 다음과 같이 움직입니다.
                [그림은 문제에서 참고]

        이때, 우리는 게임 캐릭터가 지나간 길 중 캐릭터가 처음 걸어본 길의 길이를 구하려고 합니다. 예를 들어 위의 예시에서 게임 캐릭터가 움직인 길이는 9이지만, 캐릭터가 처음 걸어본 길의 길이는 7이 됩니다. (8, 9번 명령어에서 움직인 길은 2, 3번 명령어에서 이미 거쳐 간 길입니다)
        단, 좌표평면의 경계를 넘어가는 명령어는 무시합니다.

        예를 들어, "LULLLLLLU"로 명령했다면
            [그림은 문제에서 참고]

            · 1번 명령어부터 6번 명령어대로 움직인 후, 7, 8번 명령어는 무시합니다. 다시 9번 명령어대로 움직입니다.
                [그림은 문제에서 참고]

                이때 캐릭터가 처음 걸어본 길의 길이는 7이 됩니다.

        명령어가 매개변수 dirs로 주어질 때, 게임 캐릭터가 처음 걸어본 길의 길이를 구하여 return 하는 solution 함수를 완성해 주세요.


    제한사항
        · dirs는 string형으로 주어지며, 'U', 'D', 'R', 'L' 이외에 문자는 주어지지 않습니다.
        · dirs의 길이는 500 이하의 자연수입니다.


    입출력 예
        dirs	        answer
        "ULURRDLLU"	    7
        "LULLLLLLU"	    7


    입출력 예 설명
        입출력 예 #1
            문제의 예시와 같습니다.

        입출력 예 #2
            문제의 예시와 같습니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (27.24ms, 79.7MB)
        테스트 2 〉	통과 (25.69ms, 83.8MB)
        테스트 3 〉	통과 (22.56ms, 78.9MB)
        테스트 4 〉	통과 (30.51ms, 79.4MB)
        테스트 5 〉	통과 (22.73ms, 88.2MB)
        테스트 6 〉	통과 (22.82ms, 80.2MB)
        테스트 7 〉	통과 (19.98ms, 83.9MB)
        테스트 8 〉	통과 (17.85ms, 85.1MB)
        테스트 9 〉	통과 (20.63ms, 80.9MB)
        테스트 10 〉	통과 (22.80ms, 80.1MB)
        테스트 11 〉	통과 (25.40ms, 74.9MB)
        테스트 12 〉	통과 (24.59ms, 78MB)
        테스트 13 〉	통과 (28.08ms, 75.1MB)
        테스트 14 〉	통과 (26.39ms, 74.4MB)
        테스트 15 〉	통과 (28.62ms, 90.8MB)
        테스트 16 〉	통과 (28.10ms, 77.7MB)
        테스트 17 〉	통과 (25.86ms, 83.3MB)
        테스트 18 〉	통과 (33.74ms, 71.5MB)
        테스트 19 〉	통과 (25.99ms, 80.9MB)
        테스트 20 〉	통과 (28.88ms, 79.2MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO49994 {
    static int nowX; // 현재 게임 캐릭터가 위치하는 좌표평면의 y 좌표
    static int nowY; // 현재 게임 캐릭터가 위치하는 좌표평면의 x 좌표
    static Set<String> set; // 게임 캐릭터가 움직인 경로를 저장하는 set
    static int answer; // 게임 캐릭터가 처음 걸어본 길의 길이

    public static boolean check(int x, int y) { // 해당 좌표가 좌표평면 범위 내의 좌표인지 검사하는 메서드
        if (x >= -5 && x <= 5 && y >= -5 && y <= 5) {
            return true;
        }

        return false;
    }

    public static boolean move(String dir) { // 게임 캐릭터를 4 가지 명령어를 통해 움직이는 메서드  // 움직일 수 있는 경우 true 반환, 움직일 수 없는 경우 false 반환
        if (dir.equals("U")) { // 위쪽으로 한 칸 이동
            if (check(nowX - 1, nowY)) {
                nowX -= 1;

                return true;
            }
        }
        else if (dir.equals("D")) { // 아래쪽으로 한 칸 이동
            if (check(nowX + 1, nowY)) {
                nowX += 1;

                return true;
            }
        }
        else if (dir.equals("R")) { // 오른쪽으로 한칸 이동
            if (check(nowX, nowY + 1)) {
                nowY += 1;

                return true;
            }
        }
        else if (dir.equals("L")) { // 왼쪽으로 한 칸 이동
            if (check(nowX, nowY - 1)) {
                nowY -= 1;

                return true;
            }
        }

        return false;
    }

    public static void go(String dirs) { // 게임 캐릭터를 주어진 명령어를 통해 움직이는 메서드
        for (String dir : dirs.split("")) {
            String path1 = "(" + nowX + ", " + nowY + ")"; // 경로 1 : (nowX, nowY) → (nextX, nextY)
            String path2 = path1; // 경로 2 : (nextX, nextY) → (nowX, nowY)

            if (move(dir)) { // 게임 캐릭터를 이동시켰을 경우
                path1 += (" → (" + nowX + ", " + nowY + ")");
                path2 = ("(" + nowX + ", " + nowY + ")" + " → ") + path2;

                if (!set.contains(path1) && !set.contains(path2)) { // 게임 캐릭터가 처음 걸어 본 길일 경우
                    set.add(path1); // 게임 캐릭터가 움직인 경로 추가
                    answer += 1; // 게임 캐릭터가 처음 걸어본 길의 길이 증가
                }
            }
        }
    }

    public int solution(String dirs) {
        set = new HashSet<>();
        go(dirs);

        return answer;
    }
}
