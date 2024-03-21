/*
11057. Silver 1 - 오르막 수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           54589	    26764     20802	         47.809%


    문제
        오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
        예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
        수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.


    입력
        첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.


    출력
        첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.


    예제 입력 1
        1
    예제 출력 1
        10

    예제 입력 2
        2
    예제 출력 2
        55

    예제 입력 3
        3
    예제 출력 3
        220


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14324KB
// 시간 : 124ms
// 코드 길이 : 969B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11057 {
    static int N; // 수의 길이 (1 ≤ N ≤ 1000)
    static int ascentNum[][]; // 오르막 수의 개수

    public static void checkAscentNum() { // 오르막 수의 개수를 구하는 메서드
        for (int i = 0; i < 10; i++) {
            ascentNum[0][i] = 1;
        }

        for (int n = 1; n <= N; n++) {
            for (int r = 0; r < 10; r++) {
                for (int c = r; c < 10; c++) {
                    ascentNum[n][r] += (ascentNum[n - 1][c] % 10007);
                }
            }
        }

        System.out.println(ascentNum[N][0] % 10007);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        ascentNum = new int[N + 1][10];
        checkAscentNum();
    }
}
