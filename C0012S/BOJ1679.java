/*
1679. Silver 1 - 숫자놀이

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           755	    337       254	         45.601%


    문제
        홀순이(holsoon)와 짝순이(jjaksoon) 둘이서 숫자 게임을 한다. 예를 들어, 정수 1과 3이 주어지고, 이 둘을 통틀어 5번까지 마음대로 사용하여 그 합을 구하여 1,2,3,…을 만드는 놀이다. 이 경우 먼저 홀순이가 1 하나만을 사용하여 1을 만든다. 짝순이는 1+1로 1을 두 번 사용하여 2를 만들고, 다시 홀순이는 3을 만들어야하는데 1+1+1로 1을 세 번 사용하거나 3을 한 번 사용하여 3을 만든다. 짝순이는 1+1+1+1, 1+3으로 4를 만든다. 서로 번갈아서 상대방의 수보다 1이 큰 수를 만들어야 한다. 단, 1과 3을 통틀어 최대 5번 사용한다. 이런 식으로 진행하면 13까지는 만들 수 있지만 14를 만들지 못하게 되므로 짝순이가 졌다.
        숫자 게임에서 사용하는 정수 N개와 최대 사용 횟수 K가 주어질 때, 누가 어느 수에서 이기는지를 판별하는 프로그램을 작성해보자. 사용하는 정수에는 반드시 1이 포함된다. 그렇지 않으면 홀순이가 1을 만들지 못하므로 무조건 지게 된다. 1이 꼭 있으니 상대방이 만든 방법에 1만 한 번 더 쓰면 된다고 생각하기 쉽지만, 최대 사용 횟수가 정해져 있으므로, 이 방법이 수가 커지는 경우에는 잘 되지 않는다. 위에서 13을 홀순이가 만들었지만 짝순이는 최대 사용 횟수 때문에 14를 만들지 못하고 진다.


    입력
        첫째 줄에 숫자 게임에서 사용하는 정수의 수 N이, 둘째 줄에는 사용하는 정수가 크기 순으로 주어진다. 셋째 줄에는 최대 사용 횟수 K가 주어진다.


    출력
        첫째 줄에 누가 몇 번째 수에서 이겼는지를 출력한다. 예제에서는 짝순이가 14를 못 만들어서, 홀순이가 14에서 이겼다.


    제한
        · 1 ≤ N ≤ 1,000
        · 1 ≤ K ≤ 50
        · 숫자 게임에서 사용하는 정수는 1000보다 작거나 같은 자연수이고, 중복되는 수가 주어지지 않는다.


    예제 입력 1
        2
        1 3
        5
    예제 출력 1
        holsoon win at 14


    알고리즘 분류
        다이나믹 프로그래밍
        그래프 이론
        그래프 탐색
        너비 우선 탐색
*/


// 메모리 : 14136KB
// 시간 : 116ms
// 코드 길이 : 2655B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1679 {
    static int N; // 숫자 게임에서 사용하는 정수의 수 (1 ≤ N ≤ 1,000)
    static int K; // 최대 사용 횟수 (1 ≤ K ≤ 50)
    static int numList[]; // 사용하는 정수 배열
    static int d[]; // 인덱스의 수를 만들기 위해 최소로 사용되는 정수의 개수를 저장하는 배열
    static int num; // 현재 만들 정수

    public static void numberGame() { // 홀순이와 짝순이가 숫자 게임을 하는 메서드
        // 1 = 1  // 2 = 1 + 1  // 3 = 1 + 1 + 1, 3  // 4 = 1 + 1 + 1 + 1, 1 + 3
        // 5 = 1 + 1 + 1 + 1 + 1, 1 + 1 + 3
        // d[1] = 1  // d[2] = 2  // d[3] = 1  // d[4] = 2 = d[1] + d[3]  // d[5] = d[1] + d[4] = d[2] + d[3]
        // d[num] = d[num - 사용하는 정수] + d[정수]
        for (int l = 0; l < N; l++) { // 사용하는 정수로 주어진 수를 만들기 위해서는 사용하는 정수 하나만 사용해서 만들 수 있다. 그러므로 사용하는 정수를 만들기 위한 정수의 개수를 1로 초기화
            d[numList[l]] = 1;
        }

        while (d[num] <= K) { // 해당 수(num)를 만들기 위해 사용한 정수의 개수가 K 개 이하일 경우, 다음의 수(num + 1)를 만들기 위해 사용한 정수의 개수 구하기
            num += 1;

            if (d[num] != 0) { // 이미 해당 정수를 만들기 위한 정수의 개수를 구했을 경우
                continue; // 다음 반복으로 이동
            }

            d[num] = num;
            for (int i = 0; i < N; i++) {
                if (num > numList[i]) {
                    d[num] = Math.min(d[num], d[num - numList[i]] + d[numList[i]]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token = new StringTokenizer(bf.readLine());
        numList = new int[N];
        for (int n = 0; n < N; n++) {
            numList[n] = Integer.parseInt(token.nextToken());
        }

        K = Integer.parseInt(bf.readLine());

        d = new int[numList[N - 1] * K + 1];
        numberGame();

        StringBuilder sb = new StringBuilder();
        if (num % 2 == 0) {
            sb.append("holsoon ");
        }
        else {
            sb.append("jjaksoon ");
        }

        sb.append("win at ");
        sb.append(num);

        System.out.println(sb);
    }
}
