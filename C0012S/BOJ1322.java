/*
1322. Gold 4 - X와 K

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           2988	    1302      1036	         45.319%


    문제
        두 자연수 X와 K가 주어진다. 그러면, 다음 식을 만족하는 K번째로 작은 자연수 Y를 찾아야 한다.
            X + Y = X | Y
            |는 비트 연산 OR이다.


    입력
        첫째 줄에 X와 K가 주어진다. X와 K는 2,000,000,000보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 X + Y = X | Y를 만족하는 K번째 작은 Y를 출력한다. 정답은 231-1 보다 클 수도 있다.


    예제 입력 1
        5 1
    예제 출력 1
        2

    예제 입력 2
        5 5
    예제 출력 2
        18

    예제 입력 3
        10 3
    예제 출력 3
        5

    예제 입력 4
        1 1000000000
    예제 출력 4
        2000000000


    알고리즘 분류
        수학
        비트마스킹
*/


// 메모리 : 14304KB
// 시간 : 108ms
// 코드 길이 : 1771B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1322 {
    static long X; // 2000000000보다 작거나 같은 자연수
    static long K; // 2000000000보다 작거나 같은 자연수

    public static void find() { // X + Y = X | Y를 만족하는 K번째 작은 Y를 찾는 메서드
        long number = 1; // 비트 위치의 2의 거듭제곱의 값을 나타내는 수
        List<Long> bitNumberList = new ArrayList<>(); // X를 이진수로 표현했을 경우, 0이 위치한 비트의 2의 거듭제곱의 값을 나타내는 수를 저장하는 리스트

        while ((1L << bitNumberList.size()) <= K) {
            if ((X | number) != X) { // number가 이진수로 표현한 X에서 0이 위치한 비트의 2의 거듭제곱의 값을 나타내는 수일 경우
                bitNumberList.add(number);
            }

            number <<= 1;
        }

        long answer = 0;
        for (int b = bitNumberList.size() - 1; b >= 0; b--) {
            if (K == 0) { // K가 0일 경우
                break;
            }

            long value = 1L << b; // 2^b의 값을 나타내는 수
            if (K >= value) {
                answer += bitNumberList.get(b);
                K -= value;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        X = Long.parseLong(token.nextToken());
        K = Long.parseLong(token.nextToken());

        find();
    }
}
