/*
1522. Silver 1 - 문자열 교환

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           2290	    1197      1020	         55.195%


    문제
        a와 b로만 이루어진 문자열이 주어질 때,  a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를 최소로 하는 프로그램을 작성하시오.
        이 문자열은 원형이기 때문에, 처음과 끝은 서로 인접해 있는 것이다.
        예를 들어,  aabbaaabaaba이 주어졌을 때, 2번의 교환이면 a를 모두 연속으로 만들 수 있다.


    입력
        첫째 줄에 문자열이 주어진다. 문자열의 길이는 최대 1,000이다.


    출력
        첫째 줄에 필요한 교환의 회수의 최솟값을 출력한다.


    예제 입력 1
        abababababababa
    예제 출력 1
        3

    예제 입력 2
        ba
    예제 출력 2
        0

    예제 입력 3
        aaaabbbbba
    예제 출력 3
        0

    예제 입력 4
        abab
    예제 출력 4
        1

    예제 입력 5
        aabbaaabaaba
    예제 출력 5
        2

    예제 입력 6
        aaaa
    예제 출력 6
        0


    알고리즘 분류
        브루트포스 알고리즘
        슬라이딩 윈도우
*/


// 메모리 : 14212KB
// 시간 : 120ms
// 코드 길이 : 1853B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1522 {
    static String str; // a와 b로만 이루어진 문자열
    static int strLength; // 문자열의 길이  // 최대 1000
    static int aCount; // 문자열의 a의 개수
    static int minCount; // 교환 횟수의 최솟값

    public static void countA() { // 문자열의 a의 개수를 세는 메서드
        for (int a = 0; a < strLength; a++) {
            if (str.charAt(a) == 'a') {
                aCount += 1;
            }
        }
    }

    public static void solve() { // 문자열의 a를 모두 연속으로 만들기 위해서 필요한 교환의 최소 횟수를 구하는 메서드
        for (int i = 0; i < strLength; i++) { // 전체 문자열의 각 문자의 인덱스
            int bCount = 0; // 해당 범위 내 문자열의 b의 개수

            for (int b = i, range = i + aCount; b < range; b++) { // 전체 문자열의 각 문자의 인덱스에서부터 a의 개수만큼의 길이의 문자열을 검사
                if (str.charAt(b % strLength) == 'b') { // 해당 범위 내 문자열에 b가 있을 경우
                    bCount += 1; // b의 개수 증가
                }
            }

            minCount = Math.min(minCount, bCount); // b의 개수만큼 교환해야 하므로 전체 문자열의 각 문자의 인덱스에서부터 a의 개수만큼의 길이의 문자열들의 b의 개수의 최솟값을 구한다.
        }

        System.out.println(minCount);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        str = bf.readLine();
        strLength = str.length();

        countA();

        minCount = Integer.MAX_VALUE;
        solve();
    }
}
