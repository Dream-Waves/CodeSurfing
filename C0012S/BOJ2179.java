/*
2179. Gold 3 - 비슷한 단어

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           9645	    2618      1808	         27.139%


    문제
        N개의 영단어들이 주어졌을 때, 가장 비슷한 두 단어를 구해내는 프로그램을 작성하시오.
        두 단어의 비슷한 정도는 두 단어의 접두사의 길이로 측정한다. 접두사란 두 단어의 앞부분에서 공통적으로 나타나는 부분문자열을 말한다. 즉, 두 단어의 앞에서부터 M개의 글자들이 같으면서 M이 최대인 경우를 구하는 것이다. "AHEHHEH", "AHAHEH"의 접두사는 "AH"가 되고, "AB", "CD"의 접두사는 ""(길이가 0)이 된다.
        접두사의 길이가 최대인 경우가 여러 개일 때에는 입력되는 순서대로 제일 앞쪽에 있는 단어를 답으로 한다. 즉, 답으로 S라는 문자열과 T라는 문자열을 출력한다고 했을 때, 우선 S가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력하고, 그런 경우도 여러 개 있을 때에는 그 중에서 T가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력한다.


    입력
        첫째 줄에 N(2 ≤ N ≤ 20,000)이 주어진다. 다음 N개의 줄에 알파벳 소문자로만 이루어진 길이 100자 이하의 서로 다른 영단어가 주어진다.


    출력
        첫째 줄에 S를, 둘째 줄에 T를 출력한다. 단, 이 두 단어는 서로 달라야 한다. 즉, 가장 비슷한 두 단어를 구할 때 같은 단어는 제외하는 것이다.


    예제 입력 1
        9
        noon
        is
        lunch
        for
        most
        noone
        waits
        until
        two
    예제 출력 1
        noon
        noone

    예제 입력 2
        4
        abcd
        abe
        abc
        abchldp
    예제 출력 2
        abcd
        abc


    알고리즘 분류
        자료 구조
        문자열
        정렬
        집합과 맵
        해시를 사용한 집합과 맵
*/


// 메모리 : 18268KB
// 시간 : 1412ms
// 코드 길이 : 2350B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2179 {
    static int N; // 영단어들의 개수
    static String[] wordArray; // 영단어들을 저장하는 배열
    static String S; // 접두사의 길이가 최대일 때, 입력 순서가 제일 앞에 있는 단어
    static String T; // 접두사의 길이가 최대인 경우가 여러 개일 때, 입력 순서가 제일 앞에 있는 단어

    public static int check(String word1, String word2) { // 두 단어의 접두사 길이를 구하는 메서드
        int count = 0; // 두 단어의 접두사 길이

        for (int c = 0, len = Math.min(word1.length(), word2.length()); c < len; c++) {
            if (word1.charAt(c) != word2.charAt(c)) { // 위치가 같은 문자가 같지 않을 경우
                break;
            }

            count += 1;
        }

        return count;
    }

    public static void find() { // 접두사의 길이가 최대일 때를 구해 문제에서 원하는 단어 S와 단어 T를 구하는 메서드
        int maxPrevLength = Integer.MIN_VALUE; // 접두사 길이의 최댓값

        for (int n = 0; n < N - 1; n++) {
            for (int w = n + 1; w < N; w++) {
                int prevLength = check(wordArray[n], wordArray[w]); // 단어 wordArray[n]과 단어 wordArray[w]의 접두사 길이

                if (prevLength > maxPrevLength) { // 길이가 더 긴 접두사를 찾았을 경우
                    maxPrevLength = prevLength; // 접두사 길이의 최댓값 갱신
                    S = wordArray[n]; // 단어 S 갱신
                    T = wordArray[w]; // 단어 T 갱신
                }
            }
        }
    }

    public static void print() { // 문제에서 원하는 형태로 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        sb.append(S);
        sb.append("\n");
        sb.append(T);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        wordArray = new String[N];
        for (int i = 0; i < N; i++) {
            wordArray[i] = bf.readLine();
        }

        find();

        print();
    }
}
