/*
2688. Silver 1 - 줄어들지 않아

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           5733	    3068      2596	         52.297%


    문제
        어떤 숫자가 줄어들지 않는다는 것은 그 숫자의 각 자리 수보다 그 왼쪽 자리 수가 작거나 같을 때 이다.
            예를 들어, 1234는 줄어들지 않는다.

        줄어들지 않는 4자리 수를 예를 들어 보면 0011, 1111, 1112, 1122, 2223이 있다. 줄어들지 않는 4자리수는 총 715개가 있다.
        이 문제에서는 숫자의 앞에 0(leading zero)이 있어도 된다. 0000, 0001, 0002는 올바른 줄어들지 않는 4자리수이다.
        n이 주어졌을 때, 줄어들지 않는 n자리 수의 개수를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 테스트 케이스의 개수 T(1 <= T <= 1,000)이 주어진다. 각 테스트 케이스는 숫자 하나 n으로 이루어져 있다. (1 <= n <= 64)


    출력
        각 테스트 케이스에 대해 한 줄에 하나씩 줄어들지 않는 n자리 수의 개수를 출력한다.


    예제 입력 1
        3
        2
        3
        4
    예제 출력 1
        55
        220
        715


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14036KB
// 시간 : 116ms
// 코드 길이 : 1875B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2688 {
    static int n; // n 자리의 수
    static long d[][]; // d[idx][num], idx 자리의 수에 num이 등장하는 개수를 저장하는 2 차원 배열
    static long sum[]; // 줄어들지 않는 n 자리 수의 개수 배열

    public static void init() { // 줄어들지 않는 n 자리 수의 개수를 계산하는 함수  // 미리 1 자리 수부터 64 자리 수까지 계산
        d = new long[65][11]; // 점화식을 계산할 때, 인덱스 범위를 벗어나는 오류를 피하기 위해 d[idx][0] ~ d[idx][9]을 d[idx][1] ~ d[idx][10]으로 설정
        Arrays.fill(d[1], 1); // d[1][0] ~ d[1][9] 모두 1 → d[1][1] ~ d[1][10] 모두 1
        d[1][0] = 0;
        sum = new long[65];
        sum[1] = 10; // 줄어들지 않는 1 자리 수의 개수는 10  // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9

        // d[idx + 1][num] = d[idx][0] + d[idx][1] + ... + d[idx][num]
        // d[idx + 1][num] = d[idx + 1][num - 1] + d[idx][num]
        for (int i = 1; i < 64; i++) {
            for (int v = 1; v <= 10; v++) {
                d[i + 1][v] = d[i + 1][v - 1] + d[i][v]; // d[자리 수][num] 계산
                sum[i + 1] += d[i + 1][v]; // 줄어들지 않는 해당 자리 수의 개수 추가
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 개수

        init(); // 1 자리 수부터 64 자리 수까지 줄어들지 않는 자리 수의 개수 계산
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(bf.readLine());
            System.out.println(sum[n]);
        }
    }
}
