/*
5904. Gold 5 - Moo 게임

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           9788	    3278      2465	         35.401%


    문제
        Moo는 술자리에서 즐겁게 할 수 있는 게임이다. 이 게임은 Moo수열을 각 사람이 하나씩 순서대로 외치면 되는 게임이다.

        Moo 수열은 길이가 무한대이며, 다음과 같이 생겼다.
            m o o m o o o m o o m o o o o m o o m o o o m o o m o o o o o

        Moo 수열은 다음과 같은 방법으로 재귀적으로 만들 수 있다. 먼저, S(0)을 길이가 3인 수열 "m o o"이라고 하자. 1보다 크거나 같은 모든 k에 대해서, S(k)는 S(k-1)과 o가 k+2개인 수열 "m o ... o" 와 S(k-1)을 합쳐서 만들 수 있다.
            S(0) = "m o o"
            S(1) = "m o o m o o o m o o"
            S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o"

            위와 같은 식으로 만들면, 길이가 무한대인 문자열을 만들 수 있으며, 그 수열을 Moo 수열이라고 한다.

        N이 주어졌을 때, Moo 수열의 N번째 글자를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N (1 ≤ N ≤ 109)이 주어진다.


    출력
        N번째 글자를 출력한다.


    예제 입력 1
        11
    예제 출력 1
        m


    알고리즘 분류
        분할 정복
        재귀
*/


// 메모리 : 14072KB
// 시간 : 100ms
// 코드 길이 : 2633B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5904 {
    static int N; // Moo 수열에서 찾으려는 글자의 순서
    static int k; // Moo 수열 S(k)의 k
    static int allLength; // Moo 수열의 전체 길이
    static int midLength; // Moo 수열의 중앙에 들어갈 문자열의 길이
    static int sideLenth; // Moo 수열의 왼쪽 측면과 오른쪽 측면에 들어갈 문자열의 길이
    static StringBuilder mooMid; // Moo 수열의 중앙에 들어갈 문자열을 저장하는 StringBuilder

    public static void find() { // Moo 수열에서 찾으려는 글자의 순서를 포함하는 Moo 수열 S(k)의 최소 k를 찾는 메서드
        int prevLength = 0; // Moo 수열 S(k - 1)의 전체 길이

        while (true) {
            allLength = (k + 3) + 2 * prevLength;
            prevLength = allLength;

            if (allLength >= N) { // Moo 수열의 전체 길이의 값이 Moo 수열에서 찾으려는 글자의 순서의 값보다 크거나 같을 경우
                break;
            }

            k += 1;
        }

        N -= 1; // N 번째 글자를 찾기 위해 인덱스로 접근해야 하므로 1을 마이너스해 준다.
    }

    public static void make() { // Moo 수열 S(k)의 중앙에 들어갈 문자열을 만들면서 N 번째 글자를 찾는 메서드
        mooMid.append("m").append("o".repeat(k + 2)); // o가 (k + 2) 개인 수열

        midLength = mooMid.length();
        sideLenth = (allLength - midLength) / 2;

        if ((N >= sideLenth) && (N < (sideLenth + midLength))) { // Moo 수열에서 찾으려는 글자가 Moo 수열 S(k)의 중앙에 들어갈 문자열에 포함되어 있을 경우
            System.out.println(mooMid.charAt(N - sideLenth));
            return;
        }
        else if (N >= sideLenth + midLength) { // Moo 수열에서 찾으려는 글자가 Moo 수열 S(k)의 오른쪽 측면에 들어갈 문자열에 포함되어 있을 경우
            N -= (sideLenth + midLength);
        }

        // Moo 수열에서 찾으려는 글자가 Moo 수열 S(k)의 왼쪽 측면에 들어갈 문자열이나 오른쪽 측면에 들어갈 문자열에 포함되어 있을 경우
        allLength = sideLenth;
        k -= 1;

        mooMid.setLength(0);
        make();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        mooMid = new StringBuilder();

        find();
        make();
    }
}
