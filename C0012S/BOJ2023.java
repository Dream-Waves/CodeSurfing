/*
2023. Gold 5 - 신기한 소수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    4 MB             24266	    11758     8319	         45.921%


    문제
        수빈이가 세상에서 가장 좋아하는 것은 소수이고, 취미는 소수를 가지고 노는 것이다. 요즘 수빈이가 가장 관심있어 하는 소수는 7331이다.
        7331은 소수인데, 신기하게도 733도 소수이고, 73도 소수이고, 7도 소수이다. 즉, 왼쪽부터 1자리, 2자리, 3자리, 4자리 수 모두 소수이다! 수빈이는 이런 숫자를 신기한 소수라고 이름 붙였다.
        수빈이는 N자리의 숫자 중에서 어떤 수들이 신기한 소수인지 궁금해졌다. N이 주어졌을 때, 수빈이를 위해 N자리 신기한 소수를 모두 찾아보자.


    입력
        첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다.


    출력
        N자리 수 중에서 신기한 소수를 오름차순으로 정렬해서 한 줄에 하나씩 출력한다.


    예제 입력 1
        4
    예제 출력 1
        2333
        2339
        2393
        2399
        2939
        3119
        3137
        3733
        3739
        3793
        3797
        5939
        7193
        7331
        7333
        7393


    알고리즘 분류
        수학
        정수론
        백트래킹
        소수 판정
*/


// 메모리 : 16220KB
// 시간 : 164ms
// 코드 길이 : 1434B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023 {
    static int N; // 신기한 소수의 자리 수
    static StringBuilder sb;

    public static boolean checkPrime(int number) { // number가 소수인지 검사하는 메서드  // true : 소수 O, false : 소수 X
        for (int p = 2; p <= Math.sqrt(number); p++) {
            if (number % p == 0) { // number의 제곱근 이하의 수에 약수가 있을 경우
                return false;
            }
        }

        return true;
    }

    public static void find(String numberStr, int len) { // N 자리의 숫자 중에서 신기한 소수를 찾는 메서드
        if (len == N) {
            sb.append(numberStr);
            sb.append("\n");

            return;
        }

        for (int n = 1; n <= 9; n += 2) {
            String newNumberStr = numberStr + n;

            if (checkPrime(Integer.parseInt(newNumberStr))) { // 소수일 경우
                find(newNumberStr, newNumberStr.length());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        sb = new StringBuilder();
        find("2", 1);
        find("3", 1);
        find("5", 1);
        find("7", 1);

        System.out.println(sb);
    }
}
