/*
1141. Silver 1 - 접두사

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           5017	    2704      2288	         55.547%


    문제
        접두사X 집합이란 집합의 어떤 한 단어가, 다른 단어의 접두어가 되지 않는 집합이다. 예를 들어, {hello}, {hello, goodbye, giant, hi}, 비어있는 집합은 모두 접두사X 집합이다. 하지만, {hello, hell}, {giant, gig, g}는 접두사X 집합이 아니다.
        단어 N개로 이루어진 집합이 주어질 때, 접두사X 집합인 부분집합의 최대 크기를 출력하시오.


    입력
        첫째 줄에 단어의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 단어가 주어진다. 단어는 알파벳 소문자로만 이루어져 있고, 길이는 최대 50이다. 집합에는 같은 단어가 두 번 이상 있을 수 있다.


    출력
        첫째 줄에 문제의 정답을 출력한다.


    예제 입력 1
        6
        hello
        hi
        h
        run
        rerun
        running
    예제 출력 1
        4

    예제 입력 2
        6
        a
        b
        cba
        cbc
        cbb
        ccc
    예제 출력 2
        6

    예제 입력 3
        6
        a
        ab
        abc
        abcd
        abcde
        abcdef
    예제 출력 3
        1

    예제 입력 4
        3
        topcoder
        topcoder
        topcoding
    예제 출력 4
        2


    알고리즘 분류
        그리디 알고리즘
        문자열
        정렬
*/


// 메모리 : 14292KB
// 시간 : 108ms
// 코드 길이 : 1589B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ1141 {
    static int N; // 단어의 개수 (1 ≤ N ≤ 50)
    static String word[]; // 단어를 저장하는 배열

    public static void find() { // 집합의 어떤 한 단어가 다른 단어의 접두어가 되지 않는 접두사X 집합의 최대 크기를 구하고 출력하는 메서드
        Arrays.sort(word, (o1, o2) -> o2.length() - o1.length()); // 단어의 길이 기준으로 내림차순 정렬

        HashSet<String> set = new HashSet<>(); // 접두사X 집합
        set.add(word[0]);
        for (int n = 1; n < N; n++) {
            boolean flag = true; // 해당 단어의 접두어 여부

            for (String prefix : set) {
                if (prefix.startsWith(word[n])) { // 접두사X 집합의 단어가 해당 단어를 접두어로 사용하고 있을 경우
                    flag = false;

                    break;
                }
            }

            if (flag) { // 해당 단어가 접두어로 사용되고 있지 않을 경우
                set.add(word[n]);
            }
        }

        System.out.println(set.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        word = new String[N];
        for (int i = 0; i < N; i++) {
            word[i] = bf.readLine();
        }

        find();
    }
}
