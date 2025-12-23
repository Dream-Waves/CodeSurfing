/*
13701. Gold 4 - 중복 제거

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    5 초	    8 MB (하단 참고)   6420	    2542      1527	         38.252%


    문제
        문제: N개의 정수 A1, A2, ..., AN 을 읽고, 이들 중에서 반복되는 수를 제외하고 남은 N'개의 수 B1, B2, ..., BN’ 을 입력된 순서대로 출력하시오. 이때,
            1. 0 ≤ Ai < 225 = 33554432, i=1,2,…,N.
            2. 입력의 개수 N은 1 이상 500만 이하이다.


    입력
        첫째 줄에 A1, A2, ..., AN이 주어진다.


    출력
        B1, B2, ..., BN’을 출력한다.


    예제 입력 1
        12 1 449 12 555 1201 912 555 19372
    예제 출력 1
        12 1 449 555 1201 912 19372

    예제 입력 2
        21003957 20891590 11382885 18340118 11354168 5461061 12693617 2552341 14639514 25224366 19239852 136782 17206566 18675414 9536557 24961835 2507460 32083310 4485200 19506627 21087117 9270314 12953612 10216350 8170712 20436397 11382885 29305594 27169105
    예제 출력 2
        21003957 20891590 11382885 18340118 11354168 5461061 12693617 2552341 14639514 25224366 19239852 136782 17206566 18675414 9536557 24961835 2507460 32083310 4485200 19506627 21087117 9270314 12953612 10216350 8170712 20436397 29305594 27169105


    힌트
        메모리 제한에 유의하시오.


    알고리즘 분류
        비트마스킹
        비트 집합


    메모리 제한
        Java 8: 768 MB
        Java 8 (OpenJDK): 768 MB
        Java 11: 768 MB
        Kotlin (JVM): 768 MB
*/


// 메모리 : 316432KB
// 시간 : 1116ms
// 코드 길이 : 1271B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13701_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int[] A = new int[1 << 20]; // 입력되는 N 개의 정수 A_n 중 반복되는 수를 저장하는 배열  // int 타입은 4 바이트(32 비트)이므로 원소 1 개가 32 개의 숫자를 검사할 수 있다. → A는 2^25 미만이므로 2^25 / 32 = 2^20을 배열 크기로 설정한다.
        StringBuilder sb = new StringBuilder();

        while (token.hasMoreTokens()) {
            int number = Integer.parseInt(token.nextToken());
            int arrayIndex = number / 32; // 배열 인덱스
            int bitIndex = number % 32; // 비트 인덱스

            if ((A[arrayIndex] & (1 << bitIndex)) == 0) { // 입력된 적 없는 수일 경우
                A[arrayIndex] |= (1 << bitIndex); // 현재 입력된 숫자의 비트를 1로 설정
                sb.append(number);
                sb.append(" ");
            }
        }

        System.out.println(sb);
    }
}
