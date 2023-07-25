/*
17615. Silver 1 - 볼 모으기

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	        512 MB           5958	    2350      1824	         40.497%


    문제
        빨간색 볼과 파란색 볼이 <그림 1>에서 보인 것처럼 일직선상에 섞여 놓여 있을 때, 볼을 옮겨서 같은 색 볼끼리 인접하게 놓이도록 하려고 한다. 볼을 옮기는 규칙은 다음과 같다.
            1. 바로 옆에 다른 색깔의 볼이 있으면 그 볼을 모두 뛰어 넘어 옮길 수 있다. 즉, 빨간색 볼은 옆에 있는 파란색 볼 무더기를 한 번에 뛰어 넘어 옮길 수 있다. 유사하게, 파란색 볼은 옆에 있는 빨간색 볼 무더기를 한 번에 뛰어 넘어 옮길 수 있다.
            2. 옮길 수 있는 볼의 색깔은 한 가지이다. 즉, 빨간색 볼을 처음에 옮겼으면 다음에도 빨간색 볼만 옮길 수 있다. 유사하게, 파란색 볼을 처음에 옮겼으면 다음에도 파란색 볼만 옮길 수 있다.

        예를 들어, 처음에 볼이 <그림 1>에서 보인 것처럼 있을 때, 빨간 볼을 <그림 2>에서 보인 것처럼 옮긴 후, <그림 3>에서 보인 것처럼 옮긴다면 두 번 만에 같은 색끼리 모을 수 있다.
                R  B  B  B  R  B  R  R  R
                        <그림 1>

            R  B  B  B  [R]  B  [★]  R  R  R
                        <그림 2>

            [R]  B  B  B  B  [★]  R  R  R  R
                        <그림 3>

        반면에 파란색 볼을 선택하여 <그림 4>에서 보인 것처럼 옮기면(화살표에 있는 수는 옮기는 순서를 나타낸다) 네 번을 옮겨야 같은 색의 볼끼리 모을 수 있다.
            R  [B (4)]  [B (3)]  [B (2)]  R  [B (1)]  R  R  R  [★]
                                    <그림 4>

        일직선상에 놓여 있는 볼에 관한 정보가 주어질 때, 규칙에 따라 볼을 이동하여 같은 색끼리 모으되 최소 이동횟수를 찾는 프로그램을 작성하시오.


    입력
        첫 번째 줄에는 볼의 총 개수 N이 주어진다. (1 ≤ N ≤ 500,000) 다음 줄에는 볼의 색깔을 나타내는 문자 R(빨간색 볼) 또는 B(파란색 볼)가 공백 없이 주어진다. 문자열에는 R 또는 B 중 한 종류만 주어질 수도 있으며, 이 경우 답은 0이 된다.


    출력
        최소 이동횟수를 출력한다.


    서브태스크
        번호  배점  제한
         1	  15   N ≤ 10
         2	  22   N ≤ 1,000
         3	  14   N ≤ 500,000, 처음 상태에서 모든 파란 공은 연속해서 존재한다.
         4	  49   원래의 제약조건 이외에 아무 제약조건이 없다.


    예제 입력 1
        9
        RBBBRBRRR
    예제 출력 1
        2

    예제 입력 2
        8
        BBRBBBBR
    예제 출력 2
        1


    알고리즘 분류
        그리디 알고리즘


    채점 및 기타 정보
        예제는 채점하지 않는다.
*/


// 메모리 : 18660KB
// 시간 : 300ms
// 코드 길이 : 3111B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17615 {
    static int N; // 볼의 총 개수
    static String balls; // 볼의 색깔을 나타내는 문자들을 모은 문자열  // R : 빨간색 볼, B : 파란색 볼
    static char nowBallColor; // 현재 위치의 볼의 색깔
    static boolean flag; // 볼을 옮겨도 되는지의 여부

    public static int moveBallLeft(char ballColor) { // 해당 색깔의 볼을 왼쪽으로 옮겼을 때의 최소 이동 횟수
        int moveLeftCount = 0; // 볼의 왼쪽 이동 횟수
        flag = false;

        for (int l = 0; l < N; l++) {
            nowBallColor = balls.charAt(l);

            if (flag) { // 현재 위치의 볼을 옮겨도 된다면
                if (nowBallColor == ballColor) { // 현재 위치의 볼이 옮길 볼의 색깔과 같을 경우
                    moveLeftCount += 1; // 왼쪽으로 이동 및 볼의 왼쪽 이동 횟수 추가
                    continue; // 현재 위치의 볼을 옮겨도 된다면 그 다음 볼부터는 이동할 수 있으므로 볼의 옮김 여부 체크 패스
                }
            }

            if (nowBallColor != ballColor) { // 현재 위치의 볼의 색깔이 옮길 볼의 색깔과 다를 경우
                flag = true; // 그 다음의 볼부터는 옮겨도 된다.
            }
        }

        return moveLeftCount;
    }

    public static int moveBallRight(char ballColor) { // 해당 색깔의 볼을 오른쪽으로 옮겼을 때의 최소 이동 횟수
        int moveRightCount = 0; // 볼의 오른쪽 이동 횟수
        flag = false;

        for (int r = N - 1; r >= 0; r--) {
            nowBallColor = balls.charAt(r);

            if (flag) { // 현재 위치의 볼을 옮겨도 된다면
                if (nowBallColor == ballColor) { // 현재 위치의 볼이 옮길 볼의 색깔과 같을 경우
                    moveRightCount += 1; // 오른쪽으로 이동 및 볼의 오른쪽 이동 횟수 추가
                    continue; // 현재 위치의 볼을 옮겨도 된다면 그 다음 볼부터는 이동할 수 있으므로 볼의 옮김 여부 체크 패스
                }
            }

            if (nowBallColor != ballColor) { // 현재 위치의 볼의 색깔이 옮길 볼의 색깔과 다를 경우
                flag = true; // 그 다음의 볼부터는 옮겨도 된다.
            }
        }

        return moveRightCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        balls = bf.readLine();

        int minRedCount = Math.min(moveBallLeft('R'), moveBallRight('R')); // 빨간색 볼의 최소 이동 횟수
        int minBlueCount = Math.min(moveBallLeft('B'), moveBallRight('B')); // 파란색 볼의 최소 이동 횟수

        System.out.println(Math.min(minRedCount, minBlueCount)); // 같은 색끼리 모을 수 있는 볼의 최소 이동 횟수
    }
}
