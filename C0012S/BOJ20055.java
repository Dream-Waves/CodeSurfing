/*
20055. Gold 5 - 컨베이어 벨트 위의 로봇

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           15352	    8989      6003	         56.954%


    문제
        길이가 N인 컨베이어 벨트가 있고, 길이가 2N인 벨트가 이 컨베이어 벨트를 위아래로 감싸며 돌고 있다. 벨트는 길이 1 간격으로 2N개의 칸으로 나뉘어져 있으며, 각 칸에는 아래 그림과 같이 1부터 2N까지의 번호가 매겨져 있다.
            1       2         3       …    …    N - 1      N
            2N    2N - 1    2N - 2    …    …    N + 2    N + 1

        벨트가 한 칸 회전하면 1번부터 2N-1번까지의 칸은 다음 번호의 칸이 있는 위치로 이동하고, 2N번 칸은 1번 칸의 위치로 이동한다. i번 칸의 내구도는 Ai이다. 위의 그림에서 1번 칸이 있는 위치를 "올리는 위치", N번 칸이 있는 위치를 "내리는 위치"라고 한다.
        컨베이어 벨트에 박스 모양 로봇을 하나씩 올리려고 한다. 로봇은 올리는 위치에만 올릴 수 있다. 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다. 로봇은 컨베이어 벨트 위에서 스스로 이동할 수 있다. 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.
        컨베이어 벨트를 이용해 로봇들을 건너편으로 옮기려고 한다. 로봇을 옮기는 과정에서는 아래와 같은 일이 순서대로 일어난다.
            1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
                1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.

        종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자. 가장 처음 수행되는 단계는 1번째 단계이다.


    입력
        첫째 줄에 N, K가 주어진다. 둘째 줄에는 A1, A2, ..., A2N이 주어진다.


    출력
        몇 번째 단계가 진행 중일때 종료되었는지 출력한다.


    제한
        2 ≤ N ≤ 100
        1 ≤ K ≤ 2N
        1 ≤ Ai ≤ 1,000


    예제 입력 1
        3 2
        1 2 1 2 1 2
    예제 출력 1
        2

    예제 입력 2
        3 6
        10 10 10 10 10 10
    예제 출력 2
        31

    예제 입력 3
        4 5
        10 1 10 6 3 4 8 2
    예제 출력 3
        24

    예제 입력 4
        5 8
        100 99 60 80 30 20 10 89 99 100
    예제 출력 4
        472


    알고리즘 분류
        구현
        시뮬레이션
*/


// 메모리 : 16280KB
// 시간 : 256ms
// 코드 길이 : 4259B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {
    static int N; // 컨베이어 벨트의 길이 N
    static int K; // 내구도가 0인 칸의 개수의 상한선
    static int beltLength; // 벨트의 길이 2N
    static int conveyorBelt[]; // 컨베이어 벨트의 칸의 내구도를 저장하는 배열
    static int durabilityZero; // 내구도가 0인 칸의 개수
    static int robot[]; // 해당 인덱스의 칸에 로봇의 존재 여부를 저장하는 배열  // 0 : 로봇 0 개, 1 : 로봇 1 개

    public static void rotateConveyorBelt() { // 벨트를 회전하는 함수
        int lastBelt = conveyorBelt[beltLength - 1]; // 컨베이어 벨트의 마지막 칸의 내구도
        for (int c = beltLength - 1; c > 0; c--) { // 컨베이어 벨트 이동
            conveyorBelt[c] = conveyorBelt[c - 1];
        }
        conveyorBelt[0] = lastBelt; // 컨베이어 벨트의 첫 번째 칸의 내구도를 컨베이어 벨트의 마지막 칸의 내구도로 설정

        for (int b = N - 1; b > 0; b--) { // 로봇 이동  // N 번째 칸에서 로봇이 내리기 때문에 첫 번째 칸부터 N - 1 번째 칸까지만 봐 주면 된다.
            robot[b] = robot[b - 1];
        }
        robot[0] = 0; // 로봇 이동 후에는 첫 번째 칸에 로봇이 없다.
    }

    public static boolean canMove(int index) { // 로봇이 이동할 수 있는지 판별하는 함수  // true : 이동 가능, false : 이동 불가능
        if ((robot[index + 1] == 0) && (robot[index] == 1) && (conveyorBelt[index + 1] > 0)) {
            return true;
        }

        return false;
    }

    public static boolean canLift(int index) { // 로봇을 올릴 수 있는지 판별하는 함수  // true : 올리기 가능, false : 올리기 불가능
        if ((robot[index] == 0) && (conveyorBelt[index] >= 1)) {
            return true;
        }

        return false;
    }

    public static void moveRobot() { // 로봇을 옮기는 함수
        if (robot[N - 1] == 1) { // 로봇이 내리는 위치에 있을 경우
            robot[N - 1] = 0; // 로봇을 내린다.
        }

        for (int r = N - 2; r >= 0; r--) {
            if (canMove(r)) { // 로봇이 이동할 수 있을 경우
                robot[r] = 0; // 로봇이 존재하던 기존의 위치에 로봇이 존재하지 않다고 설정
                robot[r + 1] = 1; // 로봇이 존재하던 기존의 위치의 다음 위치에 로봇이 존재한다고 설정
                conveyorBelt[r + 1] -= 1; // 로봇이 존재하던 기존의 위치의 다음 위치의 내구도 감소

                if (conveyorBelt[r + 1] == 0) { // 로봇이 이동한 위치의 내구도가 0일 경우
                    durabilityZero += 1; // 내구도가 0인 칸의 개수 추가
                }
            }
        }

        if (canLift(0)) { // 로봇을 올릴 수 있을 경우
            robot[0] = 1; // 로봇을 '올리는 위치'에 로봇이 존재한다고 설정
            conveyorBelt[0] -= 1; // 로봇을 올린 위치의 내구도 감소

            if (conveyorBelt[0] == 0) { // 로봇을 올린 위치의 내구도가 0일 경우
                durabilityZero += 1; // 내구도가 0인 칸의 개수 추가
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        beltLength = 2 * N;
        conveyorBelt = new int[beltLength];
        token = new StringTokenizer(bf.readLine());
        for (int n = 0; n < 2 * N; n++) {
            conveyorBelt[n] = Integer.parseInt(token.nextToken());
        }

        robot = new int[N];
        int step = 0; // 과정 단계
        while (durabilityZero < K) { // 내구도가 0인 칸의 개수가 K 개 미만인 동안 반복
            step += 1;

            rotateConveyorBelt();
            moveRobot();
        }

        System.out.println(step);
    }
}
