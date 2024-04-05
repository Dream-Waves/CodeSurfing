/*
12919. Gold 5 - A와 B 2

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           12887	    4248      3401	         31.877%


    문제
        수빈이는 A와 B로만 이루어진 영어 단어 존재한다는 사실에 놀랐다. 대표적인 예로 AB (Abdominal의 약자), BAA (양의 울음 소리), AA (용암의 종류), ABBA (스웨덴 팝 그룹)이 있다.

        이런 사실에 놀란 수빈이는 간단한 게임을 만들기로 했다. 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
            · 문자열의 뒤에 A를 추가한다.
            · 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.

        주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오.


    입력
        첫째 줄에 S가 둘째 줄에 T가 주어진다. (1 ≤ S의 길이 ≤ 49, 2 ≤ T의 길이 ≤ 50, S의 길이 < T의 길이)


    출력
        S를 T로 바꿀 수 있으면 1을 없으면 0을 출력한다.


    예제 입력 1
        A
        BABA
    예제 출력 1
        1

    예제 입력 2
        BAAAAABAA
        BAABAAAAAB
    예제 출력 2
        1

    예제 입력 3
        A
        ABBA
    예제 출력 3
        0


    알고리즘 분류
        문자열
        브루트포스 알고리즘
        재귀
*/


// 메모리 : 14204KB
// 시간 : 124ms
// 코드 길이 : 1652B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12919 {
    static String S; // 1 ≤ S의 길이 ≤ 49
    static int answer; // S를 T로 만들 수 있으면 1, 만들 수 없으면 0

    public static String minusA(String str) { // 문자열의 맨 뒤에 있는 A를 제거하는 메서드
        return str.substring(0, str.length() - 1);
    }

    public static String minusBAndReverse(String str) { // 문자열의 맨 앞에 있는 B를 제거하고, 문자열을 뒤집는 메서드
        str = str.substring(1); // 문자열의 맨 앞에 있는 B 제거

        StringBuffer buffer = new StringBuffer(str);

        return buffer.reverse().toString();
    }

    public static void find(String str) { // S를 T로 만들 수 있으면 answer을 1로 바꾸고, 만들 수 없으면 answer을 0으로 두는 메서드
        if (str.length() == S.length()) {
            if (str.equals(S)) {
                answer = 1;
            }

            return;
        }

        if (str.charAt(str.length() - 1) == 'A') { // 문자열의 마지막 글자가 A일 경우
            find(minusA(str));
        }
        if (str.charAt(0) == 'B') { // 문자열의 첫 번째 글자가 B일 경우
            find(minusBAndReverse(str));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        S = bf.readLine();
        String T = bf.readLine(); // 2 ≤ T의 길이 ≤ 50 (S의 길이 < T의 길이)

        find(T);

        System.out.println(answer);
    }
}
