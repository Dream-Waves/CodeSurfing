/*
15662. Gold 5 - 톱니바퀴 (2)

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           4292	    2743      2219	         66.918%


    문제
        총 8개의 톱니를 가지고 있는 톱니바퀴 T개가 아래 그림과 같이 일렬로 놓여져 있다. 또, 톱니는 N극 또는 S극 중 하나를 나타내고 있다. 톱니바퀴에는 번호가 매겨져 있는데, 가장 왼쪽 톱니바퀴가 1번, 그 오른쪽은 2번, ..., 가장 오른쪽 톱니바퀴는 T번이다. 아래 그림은 T가 4인 경우이다.
            [그림은 문제에서 참고]

        이때, 톱니바퀴를 총 K번 회전시키려고 한다. 톱니바퀴의 회전은 한 칸을 기준으로 한다. 회전은 시계 방향과 반시계 방향이 있고, 아래 그림과 같이 회전한다.
            [그림은 문제에서 참고]

        톱니바퀴를 회전시키려면, 회전시킬 톱니바퀴와 회전시킬 방향을 결정해야 한다. 톱니바퀴가 회전할 때, 서로 맞닿은 극에 따라서 옆에 있는 톱니바퀴를 회전시킬 수도 있고, 회전시키지 않을 수도 있다. 톱니바퀴 A를 회전할 때, 그 옆에 있는 톱니바퀴 B와 서로 맞닿은 톱니의 극이 다르다면, B는 A가 회전한 방향과 반대방향으로 회전하게 된다. 예를 들어, 아래와 같은 경우를 살펴보자.
            [그림은 문제에서 참고]

        두 톱니바퀴의 맞닿은 부분은 초록색 점선으로 묶여있는 부분이다. 여기서, 3번 톱니바퀴를 반시계 방향으로 회전했다면, 4번 톱니바퀴는 시계 방향으로 회전하게 된다. 2번 톱니바퀴는 맞닿은 부분이 S극으로 서로 같기 때문에, 회전하지 않게 되고, 1번 톱니바퀴는 2번이 회전하지 않았기 때문에, 회전하지 않게 된다. 따라서, 아래 그림과 같은 모양을 만들게 된다.
            [그림은 문제에서 참고]

        위와 같은 상태에서 1번 톱니바퀴를 시계 방향으로 회전시키면, 2번 톱니바퀴가 반시계 방향으로 회전하게 되고, 2번이 회전하기 때문에, 3번도 동시에 시계 방향으로 회전하게 된다. 4번은 3번이 회전하지만, 맞닿은 극이 같기 때문에 회전하지 않는다. 따라서, 아래와 같은 상태가 된다.
            [그림은 문제에서 참고]

        톱니바퀴 T개의 초기 상태와 톱니바퀴를 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 상태를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 톱니바퀴의 개수 T (1 ≤ T ≤ 1,000)가 주어진다.
        둘째 줄부터 T개의 줄에 톱니바퀴의 상태가 가장 왼쪽 톱니바퀴부터 순서대로 주어진다. 상태는 8개의 정수로 이루어져 있고, 12시방향부터 시계방향 순서대로 주어진다. N극은 0, S극은 1로 나타나있다.
        다음 줄에는 회전 횟수 K(1 ≤ K ≤ 1,000)가 주어진다. 다음 K개 줄에는 회전시킨 방법이 순서대로 주어진다. 각 방법은 두 개의 정수로 이루어져 있고, 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향이다. 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.


    출력
        총 K번 회전시킨 이후에 12시방향이 S극인 톱니바퀴의 개수를 출력한다.


    예제 입력 1
        4
        10101111
        01111101
        11001110
        00000010
        2
        3 -1
        1 1
    예제 출력 1
        3

    예제 입력 2
        4
        11111111
        11111111
        11111111
        11111111
        3
        1 1
        2 1
        3 1
    예제 출력 2
        4

    예제 입력 3
        4
        10001011
        10000011
        01011011
        00111101
        5
        1 1
        2 1
        3 1
        4 1
        1 -1
    예제 출력 3
        2

    예제 입력 4
        4
        10010011
        01010011
        11100011
        01010101
        8
        1 1
        2 1
        3 1
        4 1
        1 -1
        2 -1
        3 -1
        4 -1
    예제 출력 4
        2

    예제 입력 5
        5
        10010011
        01010011
        11100011
        01010101
        01010011
        10
        1 1
        2 1
        3 1
        4 1
        1 -1
        2 -1
        3 -1
        4 -1
        5 1
        5 -1
    예제 출력 5
        5


    알고리즘 분류
        구현
        시뮬레이션
*/


// 메모리 : 15280B
// 시간 : 120ms
// 코드 길이 : 3762B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15662 {
    static int T; // 톱니바퀴의 개수 (1 ≤ T ≤ 1000)
    static int sawtooth[][]; // 톱니바퀴의 상태  // 0 : N극, 1 : S극

    public static void rotateOne(int wheelNum, int direction) { // 하나의 톱니바퀴를 회전시키는 메서드
        if (direction == 1) { // 시계 방향으로 회전했을 경우
            int temp = sawtooth[wheelNum][7];
            for (int l = 7; l > 0; l--) {
                sawtooth[wheelNum][l] = sawtooth[wheelNum][l - 1];
            }

            sawtooth[wheelNum][0] = temp;
        }
        else if (direction == -1) { // 반시계 방향으로 회전했을 경우
            int temp = sawtooth[wheelNum][0];
            for (int r = 0; r < 7; r++) {
                sawtooth[wheelNum][r] = sawtooth[wheelNum][r + 1];
            }

            sawtooth[wheelNum][7] = temp;
        }
    }

    public static void rotateLeft(int wheelNum, int direction) { // 왼쪽 톱니바퀴를 회전시키는 메서드
        if (wheelNum < 0) { // 왼쪽에 더 이상 톱니바퀴가 없을 경우
            return;
        }

        if (sawtooth[wheelNum][2] != sawtooth[wheelNum + 1][6]) { // 두 톱니바퀴의 맞닿은 톱니의 극이 다를 경우
            rotateLeft(wheelNum - 1, -direction);
            rotateOne(wheelNum, direction);
        }
    }

    public static void rotateRight(int wheelNum, int direction) { // 오른쪽 톱니바퀴를 회전시키는 메서드
        if (wheelNum > T - 1) { // 오른쪽에 더 이상 톱니바퀴가 없을 경우
            return;
        }

        if (sawtooth[wheelNum - 1][2] != sawtooth[wheelNum][6]) { // 두 톱니바퀴의 맞닿은 톱니의 극이 다를 경우
            rotateRight(wheelNum + 1, -direction);
            rotateOne(wheelNum, direction);
        }
    }

    public static void rotateAll(int wheelNum, int direction) { // 회전시킬 톱니바퀴와 맞닿은 톱니바퀴를 회전시키고, 해당 톱니바퀴를 회전시키는 메서드
        rotateLeft(wheelNum - 1, -direction);
        rotateRight(wheelNum + 1, -direction);
        rotateOne(wheelNum, direction);
    }

    public static void count() { // 12 시 방향이 S극인 톱니바퀴의 개수를 구하고 출력하는 메서드
        int answer = 0; // 12 시 방향이 S극인 톱니바퀴의 개수
        for (int c = 0; c < T; c++) {
            if (sawtooth[c][0] == 1) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());

        sawtooth = new int[T][8];
        for (int t = 0; t < T; t++) {
            String wheel = bf.readLine();
            for (int w = 0; w < 8; w++) {
                sawtooth[t][w] = wheel.charAt(w) - '0';
            }
        }

        int K = Integer.parseInt(bf.readLine()); // 회전 횟수 (1 ≤ K ≤ 1,000)
        StringTokenizer token;
        for (int k = 0; k < K; k++) {
            token = new StringTokenizer(bf.readLine());
            int wheelNum = Integer.parseInt(token.nextToken()); // 회전시킨 톱니바퀴의 번호
            int direction = Integer.parseInt(token.nextToken()); // 회전 방향  // 1 : 시계 방향, -1 : 반시계 방향

            rotateAll(wheelNum - 1, direction); // 톱니바퀴 회전
        }

        count(); // 톱니바퀴를 총 K 번 회전시킨 이후, 12 시 방향이 S극인 톱니바퀴의 개수를 구하고 출력
    }
}
