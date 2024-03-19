/*
1124. Silver 1 - 언더프라임

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           5991	    2277      1746	         40.398%


    문제
        자연수 X를 소인수분해하면, 곱해서 X가 되는 소수의 목록을 얻을 수 있다. 예를 들어, 12 = 2 × 2 × 3이다. 1은 소수가 아니다.
        어떤 수 X를 소인수분해 해서 구한 소수의 목록의 길이가 소수이면, 그 수를 언더프라임 이라고 한다. 12는 목록에 포함된 소수의 개수가 3개이고, 3은 소수이니 12는 언더프라임이다.
        두 정수 A와 B가 주어졌을 때, A보다 크거나 같고, B보다 작거나 같은 정수 중에서 언더프라임인 것의 개수를 구해보자.


    입력
        첫째 줄에 두 정수 A와 B가 주어진다.


    출력
        첫째 줄에 A보다 크거나 같고, B보다 작거나 같은 언더프라임 개수를 출력한다.


    제한
        · 2 ≤ A ≤ B ≤ 100,000


    예제 입력 1
        2 10
    예제 출력 1
        5

    예제 입력 2
        100 105
    예제 출력 2
        2

    예제 입력 3
        17 17
    예제 출력 3
        0

    예제 입력 4
        123 456
    예제 출력 4
        217


    알고리즘 분류
        수학
        정수론
        소수 판정
        에라토스테네스의 체
*/


// 메모리 : 14408KB
// 시간 : 3276ms
// 코드 길이 : 2332B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1124 {
    static int A;
    static int B;
    static boolean primeNumber[]; // 소수인지에 대한 판단을 저장하는 배열  // true : 소수 X, false : 소수 O

    public static void eratosthenesSieve() { // 에라토스테네스의 체
        for (int p = 2; p <= Math.sqrt(B); p++) {
            for (int r = (int) Math.pow(p, 2); r <= B; r += p) {
                primeNumber[r] = true;
            }
        }
    }

    public static int primeFactorization(int number) { // 소인수 분해  // primeFactorization(언더프라임인지 판별할 수)
        int divisor = 0; // 소수인 약수의 개수
        for (int i = 2; i <= B; i++) {
            while (number % i == 0) {
                number /= i;

                if (!primeNumber[i]) { // 약수가 소수일 경우
                    divisor += 1;
                }
            }

            if (number == 1) {
                break;
            }
        }

        return divisor;
    }

    public static void findUnderPrime() { // A보다 크거나 같고 B보다 작거나 같은 정수 중 언더프라임의 개수를 구하고 출력하는 메서드
        int underprimeNumber = 0; // 어떤 수를 소인수분해 해서 구한 소수의 목록의 길이가 소수인 언더프라임의 개수
        for (int n = A; n <= B; n++) {
            int divisor = primeFactorization(n); // 소수인 약수의 개수

            if (!primeNumber[divisor]) { // 소인수분해 해서 구한 소수의 목록의 길이가 소수인지  // 언더프라임인지
                underprimeNumber += 1;
            }
        }

        System.out.println(underprimeNumber);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(token.nextToken());
        B = Integer.parseInt(token.nextToken());

        primeNumber = new boolean[B + 1];
        primeNumber[0] = true;
        primeNumber[1] = true;
        eratosthenesSieve(); // 에라토스테네스의 체 적용

        findUnderPrime();
    }
}
