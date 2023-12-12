/*
1629. Silver 1 - 곱셈

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    0.5 초 (추가 시간 없음)	    128 MB           110933	    30978     22607	         26.923%


    문제
        자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.


    출력
        첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.


    예제 입력 1
        10 11 12
    예제 출력 1
        4


    알고리즘 분류
        수학
        분할 정복을 이용한 거듭제곱
*/


// 메모리 : 14136KB
// 시간 : 132ms
// 코드 길이 : 1023B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    static long A;
    static long C;

    public static long calculateRest(long exponent) { // A를 B 번 곱한 수를 C로 나눈 나머지를 구하는 메서드
        if (exponent == 1) {
            return A % C;
        }

        long mid = calculateRest(exponent / 2); // A^(exponent / 2)

        if (exponent % 2 == 0) {
            return mid * mid % C;
        }
        else {
            return (mid * mid % C) * A % C;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(token.nextToken());
        long B = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        System.out.println(calculateRest(B));
    }
}
