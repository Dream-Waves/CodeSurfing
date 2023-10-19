package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 2023-10-19
 * BOJ 1141번: 접두사
 * [ 문제 ]
 * 접두사X 집합이란 집합의 어떤 한 단어가, 다른 단어의 접두어가 되지 않는 집합이다. 예를 들어, {hello}, {hello, goodbye, giant, hi}, 비어있는 집합은 모두 접두사X 집합이다. 하지만, {hello, hell}, {giant, gig, g}는 접두사X 집합이 아니다.
 *
 * 단어 N개로 이루어진 집합이 주어질 때, 접두사X 집합인 부분집합의 최대 크기를 출력하시오.
 *
 * [ 입력 ]
 * 첫째 줄에 단어의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 단어가 주어진다. 단어는 알파벳 소문자로만 이루어져 있고, 길이는 최대 50이다. 집합에는 같은 단어가 두 번 이상 있을 수 있다.
 *
 * [ 출력 ]
 * 첫째 줄에 문제의 정답을 출력한다.
 *
 * [ 예제 입력 1 ]
 * 6
 * hello
 * hi
 * h
 * run
 * rerun
 * running
 *
 * [ 예제 출력 1 ]
 * 4
 *
 * [ 예제 입력 2 ]
 * 6
 * a
 * b
 * cba
 * cbc
 * cbb
 * ccc
 *
 * [ 예제 출력 2 ]
 * 6
 *
 * [ 예제 입력 3 ]
 * 6
 * a
 * ab
 * abc
 * abcd
 * abcde
 * abcdef
 *
 * [ 예제 출력 3 ]
 * 1
 * */

public class BOJ1141 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        String[] input = new String[N];

        for(int i=0; i<N; i++) input[i] = bf.readLine();

        Arrays.sort(input, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        }); // 문자열의 길이 비교해서 내림차순 정렬

        HashSet<String> set = new HashSet<>();
        // 접두사X 집합 set
        // 집합에 같은 단어가 두 번 이상 있을 경우를 위해 HashSet 사용

        set.add(input[0]);

        for(int i=1; i<N; i++){ // 1번째 단어부터 N-1번째 단어까지 돌면서
            boolean check = false;
            for(String s : set){ // set 안에 들어 있는 단어들과 비교
                if(s.indexOf(input[i])==0) {
                    // 만약 set 내 단어에 i번째 단어가 포함되어 있고 i번째 단어가 set 내 단어의 0번 인덱스에서 시작된다면
                    check = true; // i번째 단어가 접두사가 될 수 있다고 판단
                    break; // 반복문 도는 횟수 줄이기 위한 break
                }
            }
            if(!check) set.add(input[i]); // i번째 단어가 set 내 다른 단어의 접두어가 되지 않는다면 접두사X 집합 set에 추가한다
        }

        System.out.println(set.size()); // 접두사X 집합의 최대 크기 출력
    }
}
