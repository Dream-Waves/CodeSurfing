/*
1149. Silver 1 - RGB거리

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    0.5 초 (추가 시간 없음)	    128 MB           100558	    54975     41008	         53.988%


    문제
        RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
        집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
            · 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
            · N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
            · i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.


    입력
        첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.


    예제 입력 1
        3
        26 40 83
        49 60 57
        13 89 99
    예제 출력 1
        96

    예제 입력 2
        3
        1 100 100
        100 1 100
        100 100 1
    예제 출력 2
        3

    예제 입력 3
        3
        1 100 100
        100 100 100
        1 100 100
    예제 출력 3
        102

    예제 입력 4
        6
        30 19 5
        64 77 64
        15 19 97
        4 71 57
        90 86 84
        93 32 91
    예제 출력 4
        208

    예제 입력 5
        8
        71 39 44
        32 83 55
        51 37 63
        89 29 100
        83 58 11
        65 13 15
        47 25 29
        60 66 19
    예제 출력 5
        253


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 18400KB
// 시간 : 136ms
// 코드 길이 : 2819B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {
    static int d[][]; // d[집의 수][집을 칠할 색]  // 인덱스에 해당하는 순서의 집을 해당 색으로 칠할 때, 인덱스에 해당하는 집의 수만큼 칠하는 비용의 최솟값을 저장하는 배열
    // cost[x]를 x 개의 집을 칠하는 비용의 최솟값을 저장하는 배열이라고 가정
    // cost[x] = cost[x - 1] + 집을 칠할 수 있는 색들의 비용 중 최솟값
    // 이전의 집을 어떤 색으로 칠했는지에 따라 현재 집을 칠할 수 있는 색이 변한다.
    // 현재의 집을 빨간색, 초록색, 파란색 중 하나로 칠한다고 가정한 후, 그 이전까지 집을 칠한 비용의 최솟값과 현재 칠할 색의 비용을 더한다.
    // 그렇게 현재의 집을 빨간색으로 칠할 경우의 값과 초록색으로 칠할 경우의 값, 그리고 파란색으로 칠할 경우의 값 중 최솟값이 집의 개수만큼 집을 칠할 때 드는 비용의 최솟값이다.
    // d[x][r] = Math.min(d[x - 1][g], d[x - 1][b]) + x 번째 집을 빨간색으로 칠하는 비용
    // d[x][g] = Math.min(d[x - 1][r], d[x - 1][b]) + x 번째 집을 초록색으로 칠하는 비용
    // d[x][b] = Math.min(d[x - 1][r], d[x - 1][g]) + x 번째 집을 파란색으로 칠하는 비용
    // cost[x] = d[x][r], d[x][g], d[x][b] 중 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine()); // 집의 수 N (2 ≤ N ≤ 1,000)

        d = new int[N + 1][N + 1];
        for (int n = 1; n <= N; n++) {
            StringTokenizer token = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(token.nextToken()); // 집을 빨간색으로 칠하는 비용
            int g = Integer.parseInt(token.nextToken()); // 집을 초록색으로 칠하는 비용
            int b = Integer.parseInt(token.nextToken()); // 집을 파란색으로 칠하는 비용

            d[n][0] = Math.min(d[n - 1][1], d[n - 1][2]) + r; // n 개의 집을 칠할 때, n 번째 집을 빨간색으로 칠할 경우 드는 비용의 최솟값
            d[n][1] = Math.min(d[n - 1][0], d[n - 1][2]) + g; // n 개의 집을 칠할 때, n 번째 집을 파란색으로 칠할 경우 드는 비용의 최솟값
            d[n][2] = Math.min(d[n - 1][0], d[n - 1][1]) + b; // n 개의 집을 칠할 때, n 번째 집을 초록색으로 칠할 경우 드는 비용의 최솟값
        }

        System.out.println(Math.min(Math.min(d[N][0], d[N][1]), d[N][2])); // n 개의 집을 칠할 때 드는 비용의 최솟값
    }
}
