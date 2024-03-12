/*
1527. Silver 1 - 금민수의 개수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           4336	    1678      1395	         40.447%


    문제
        은민이는 4와 7을 좋아하고, 나머지 숫자는 싫어한다. 금민수는 어떤 수가 4와 7로만 이루어진 수를 말한다.
        A와 B가 주어졌을 때, A보다 크거나 같고, B보다 작거나 같은 자연수 중에 금민수인 것의 개수를 출력하는 프로그램을 작성하시오.


    입력
        첫째 줄에 A와 B가 주어진다. A는 1보다 크거나 같고, 1,000,000,000보다 작거나 같은 자연수이다. B는 A보다 크거나 같고, 1,000,000,000보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 A보다 크거나 같고, B보다 작거나 같은 자연수 중에 금민수인 것의 개수를 출력한다.


    예제 입력 1
        1 10
    예제 출력 1
        2

    예제 입력 2
        11 20
    예제 출력 2
        0

    예제 입력 3
        74 77
    예제 출력 3
        2

    예제 입력 4
        1000000 5000000
    예제 출력 4
        64


    알고리즘 분류
        브루트포스 알고리즘
*/


// 메모리 : 14164KB
// 시간 : 124ms
// 코드 길이 : 1107B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1527 {
    static long A; // 1 ≤ A ≤ 1000000000
    static long B; // A ≤ B ≤ 1000000000
    static long answer; // 4와 7로만 이루어진 수인 금민수의 개수

    public static void find(long number) { // A보다 크거나 같고 B보다 작거나 같은 자연수 중 금민수의 개수를 구하는 메서드
        if (number > B) {
            return;
        }

        if (number >= A) {
            answer += 1;
        }

        // 4와 7로만 이루어진 수를 만들어 재귀 함수 호출
        find(number * 10 + 4);
        find(number * 10 + 7);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        A = Long.parseLong(token.nextToken());
        B = Long.parseLong(token.nextToken());

        find(0);

        System.out.println(answer);
    }
}
