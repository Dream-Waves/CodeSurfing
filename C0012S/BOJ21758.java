/*
21758. Gold 5 - 꿀 따기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           11706	    4193      3182	         37.383%


    문제
        아래와 같이 좌우로 N개의 장소가 있다.
            9   9   4   1   4   9   9

        장소들 중 서로 다른 두 곳을 골라서 벌을 한 마리씩 둔다. 또, 다른 한 장소를 골라서 벌통을 둔다. 아래 그림에서 연한 회색의 장소는 벌이 있는 장소이고 진한 회색의 장소는 벌통이 있는 장소이다.
            [그림은 문제에서 참고]

        두 마리 벌은 벌통으로 똑바로 날아가면서 지나가는 모든 칸에서 꿀을 딴다. 각 장소에 적힌 숫자는 벌이 지나가면서 꿀을 딸 수 있는 양이다.
            1. 두 마리가 모두 지나간 장소에서는 두 마리 모두 표시된 양 만큼의 꿀을 딴다. (벌통이 있는 장소에서도 같다.)
            2. 벌이 시작한 장소에서는 어떤 벌도 꿀을 딸 수 없다.

        위의 그림과 같이 배치된 경우 두 마리의 벌 모두 4 + 1 + 4 + 9 + 9 = 27의 꿀을 따서, 전체 꿀의 양은 54가 된다.

            [그림은 문제에서 참고]
        위의 그림과 같이 배치된 경우 왼쪽 장소에서 출발한 벌은 9 + 4 + 4 + 9 + 9 = 35의 꿀을 따고 오른쪽 장소에서 출발한 벌은 4 + 9 + 9 = 22의 꿀을 따므로, 전체 꿀의 양은 57이 된다.

            [그림은 문제에서 참고]
        위의 그림과 같은 경우는 전체 꿀의 양이 31이 된다.

        장소들의 꿀 양을 입력으로 받아 벌들이 딸 수 있는 가능한 최대의 꿀의 양을 계산하는 프로그램을 작성하라.


    입력
        첫 번째 줄에 장소의 수 N이 주어진다.
        다음 줄에 왼쪽부터 각 장소에서 꿀을 딸 수 있는 양이 공백 하나씩을 사이에 두고 주어진다.


    출력
        첫 번째 줄에 가능한 최대의 꿀의 양을 출력한다.


    제한
        · 3 ≤ N ≤ 100000
        · 각 장소의 꿀의 양은 1 이상 10000 이하의 정수이다.


    서브태스크
        번호	    배점	    제한
        1	    11      N ≤ 20
        2	    13      N ≤ 500
        3	    31      N ≤ 5000
        4	    45      추가적인 제한이 없음.


    예제 입력 1
        7
        9 9 4 1 4 9 9
    예제 출력 1
        57

    예제 입력 2
        7
        4 4 9 1 9 4 4
    예제 출력 2
        54

    예제 입력 3
        3
        2 5 4
    예제 출력 3
        10


    알고리즘 분류
        그리디 알고리즘
        누적 합


    채점 및 기타 정보
        예제는 채점하지 않는다.
*/


// 메모리 : 25260KB
// 시간 : 296ms
// 코드 길이 : 2058B
// 100 점

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21758 {
    static int N; // 장소의 수 (3 ≤ N ≤ 100000)
    static int honey[]; // 해당 인덱스 장소의 꿀의 양을 저장하는 배열 (1 ≤ honey[i] ≤ 10000)
    static int honeySum[]; // 꿀의 양의 누적합을 저장하는 배열
    static int maxHoney; // 각 장소의 꿀의 양 중 최댓값

    public static void findMaxHoney() { // 벌들이 딸 수 있는 가능한 최대의 꿀의 양을 구하고 출력하는 메서드
        int rightHoneyComb = 0; // 벌통이 맨 오른쪽에 위치했을 때, 벌들이 딸 수 있는 최대의 꿀의 양
        int leftHoneyComb = 0; // 벌통이 맨 왼쪽에 위치했을 때, 벌들이 딸 수 있는 최대의 꿀의 양
        for (int n = 2; n < N; n++) {
            rightHoneyComb = Math.max(rightHoneyComb, ((2 * honeySum[N]) - (honey[1] + honey[n]) - honeySum[n]));
            leftHoneyComb = Math.max(leftHoneyComb, (honeySum[N] - (honey[N] + honey[n]) + honeySum[n - 1])); // (2 * honeySum[N]) - (honey[N] + honey[n]) - (honeySum[N] - honeySum[n - 1]);
        }

        int midHoneyComb = honeySum[N] - (honey[1] + honey[N]) + maxHoney; // 벌통이 양 끝이 아닌 장소에 위치했을 때, 벌들이 딸 수 있는 최대의 꿀의 양

        System.out.println(Math.max(Math.max(rightHoneyComb, leftHoneyComb), midHoneyComb));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token = new StringTokenizer(bf.readLine());
        honey = new int[N + 1];
        honeySum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            honey[i] = Integer.parseInt(token.nextToken());
            honeySum[i] = honeySum[i - 1] + honey[i];

            maxHoney = Math.max(maxHoney, honey[i]);
        }

        findMaxHoney();
    }
}
