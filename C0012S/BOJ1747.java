/*
1747. Silver 1 - 소수&팰린드롬

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    256 MB           25723	    8298      6161	         30.606%


    문제
        어떤 수와 그 수의 숫자 순서를 뒤집은 수가 일치하는 수를 팰린드롬이라 부른다. 예를 들어 79,197과 324,423 등이 팰린드롬 수이다.
        어떤 수 N (1 ≤ N ≤ 1,000,000)이 주어졌을 때, N보다 크거나 같고, 소수이면서 팰린드롬인 수 중에서, 가장 작은 수를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N이 주어진다.


    출력
        첫째 줄에 조건을 만족하는 수를 출력한다.


    예제 입력 1
        31
    예제 출력 1
        101


    알고리즘 분류
        수학
        브루트포스 알고리즘
        정수론
        소수 판정
        에라토스테네스의 체
*/


// 메모리 : 47720KB
// 시간 : 256ms
// 코드 길이 : 2036B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1747 {
    static int N; // 어떤 수 (1 ≤ N ≤ 1000000)
    static boolean primeNumberAndPalindromeNumber[]; // 소수이면서 팰림드롬수인지에 대한 판단을 저장하는 배열  // true : 소수이면서 팰림드롬수 X, false : 소수이면서 팰림드롬수 O

    public static void eratosthenesSieve() { // 에라토스테네스의 체
        for (int p = 2; p <= Math.sqrt(1003001); p++) {
            for (int r = (int) Math.pow(p, 2); r <= 1003001; r += p) {
                primeNumberAndPalindromeNumber[r] = true;
            }
        }
    }

    public static void distinguishPalindromeNumber(int index, String numString) { // 팰린드롬수인지 판별하는 함수
        if (primeNumberAndPalindromeNumber[index]) { // 소수가 아닐 경우
            return;
        }

        int numLength = numString.length();

        for (int n = 0; n < numLength / 2; n++) {
            if (numString.charAt(n) != numString.charAt(numLength - n - 1)) {
                primeNumberAndPalindromeNumber[index] = true;
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        primeNumberAndPalindromeNumber = new boolean[1003001 + 1]; // 1000000보다 크거나 같은 수 중 가장 작은 소수이면서 팰린드롬수인 수
        primeNumberAndPalindromeNumber[0] = true;
        primeNumberAndPalindromeNumber[1] = true;
        eratosthenesSieve(); // 에라토스테네스의 체 적용

        while (true) {
            distinguishPalindromeNumber(N, String.valueOf(N));

            if (!primeNumberAndPalindromeNumber[N]) { // 소수이면서 팰림드롬수일 경우
                System.out.println(N);
                break;
            }

            N += 1;
        }
    }
}
