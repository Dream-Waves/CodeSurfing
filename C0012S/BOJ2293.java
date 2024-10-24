/*
2293. Gold 4 - 동전 1

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    0.5 초 (추가 시간 없음)	    4 MB             69350	    33024     25184	         47.620%


    문제
        n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.
        사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.


    입력
        첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다. 동전의 가치는 100,000보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 경우의 수를 출력한다. 경우의 수는 231보다 작다.


    예제 입력 1
        3 10
        1
        2
        5
    예제 출력 1
        10


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14296KB
// 시간 : 112ms
// 코드 길이 : 1281B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {
    static int N; // 동전의 종류의 개수
    static int K; // 동전을 이용하여 만들고 싶은 가치의 합
    static int coin[]; // 각 동전의 가치를 저장하는 배열
    static int value[]; // 해당 인덱스의 가치를 만들 수 있는 경우의 수를 저장하는 배열

    public static void find() { // K 원을 만들 수 있는 경우의 수를 구하는 메서드
        value = new int[K + 1];

        value[0] = 1;
        for (int n = 0; n < N; n++) {
            for (int k = coin[n]; k <= K; k++) {
                value[k] += value[k - coin[n]];
            }
        }

        System.out.println(value[K]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        coin = new int[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(bf.readLine());
        }

        find();
    }
}
