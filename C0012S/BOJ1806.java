/*
1806. Gold 4 - 부분합

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    0.5 초 (하단 참고)	        128 MB           119800	    33640     23753	         26.402%


    문제
        10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.


    출력
        첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.


    예제 입력 1
        10 15
        5 1 3 5 10 7 4 9 2 8
    예제 출력 1
        2


    알고리즘 분류
        누적 합
        두 포인터


    시간 제한
        Java 8: 1 초
        Java 8 (OpenJDK): 1 초
        Java 11: 1 초
        Kotlin (JVM): 1 초
        Java 15: 1 초
*/


// 메모리 : 23836KB
// 시간 : 272ms
// 코드 길이 : 1944B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    static int N; // 수열의 길이
    static int S; // 부분합의 값이 어떤 수의 값 이상이 되어야 하는 그 어떤 수
    static int[] number; // 수열의 값을 저장하는 배열

    public static void find() { // 부분합이 S 이상이 되는 부분 수열들의 길이 중 최솟값을 구하는 메서드
        int left = 0; // 왼쪽 인덱스
        int right = 0; // 오른쪽 인덱스
        int sum = 0; // 수열에서 연속된 수들의 부분합
        int min = Integer.MAX_VALUE; // 부분합이 S 이상이 되는 부분 수열들의 길이 중 최솟값

        while (right <= N) { // 오른쪽 인덱스가 N 이하인 동안
            if (sum >= S) { // 부분합이 S 이상일 경우
                min = Math.min(min, right - left);

                sum -= number[left];
                left += 1;
            }
            else { // 부분합이 S 미만일 경우
                sum += number[right];

                right += 1;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min); // 부분합이 S 이상이 되지 못할 경우 0을 출력하고, 부분합이 S 이상이 될 경우 그러한 경우의 부분 수열들의 길이 중 최솟값을 출력
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());

        number = new int[N + 1];
        token = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(token.nextToken());
        }

        find();
    }
}
