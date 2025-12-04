/*
1253. Gold 4 - 좋다

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    256 MB           80043	    20856     14808	         24.906%


    문제
        N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.
        N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.
        수의 위치가 다르면 값이 같아도 다른 수이다.


    입력
        첫째 줄에는 수의 개수 N(1 ≤ N ≤ 2,000), 두 번째 줄에는 i번째 수를 나타내는 Ai가 N개 주어진다. (|Ai| ≤ 1,000,000,000, Ai는 정수)


    출력
        좋은 수의 개수를 첫 번째 줄에 출력한다.


    예제 입력 1
        10
        1 2 3 4 5 6 7 8 9 10
    예제 출력 1
        8


    힌트
        3,4,5,6,7,8,9,10은 좋다.


    알고리즘 분류
        자료 구조
        정렬
        이분 탐색
        두 포인터
*/


// 메모리 : 15808KB
// 시간 : 208ms
// 코드 길이 : 2826B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1253 {
    static int N; // 수의 개수 (1 ≤ N ≤ 2000)
    static int[] A; // 문제에서 주어진 수를 저장하는 배열 (|Ai| ≤ 1000000000, Ai는 정수)
    static Set<Integer> goodNumber; // 좋은 수(문제에서 주어진 수 중 다른 수 두 개의 합으로 나타낼 수 있는 수)를 저장하는 Set
    static int answer; // 좋은 수(문제에서 주어진 수 중 다른 수 두 개의 합으로 나타낼 수 있는 수)의 개수

    public static void check(int index, int number) { // 수 number가 좋은 수(문제에서 주어진 수 중 다른 수 두 개의 합으로 나타낼 수 있는 수)인지 검사하는 메서드
        if (goodNumber.contains(number)) { // 수 number가 좋은 수일 경우
            answer += 1;

            return;
        }

        int left = 0; // 두 수의 합을 나타내는 수 중 작은 수의 인덱스
        int right = N - 1; // 두 수의 합을 나타내는 수 중 큰 수의 인덱스

        while (left < right) {
            if (left == index) { // 두 수의 합을 나타내는 수 중 작은 수가 수 number일 경우
                left += 1;
                continue;
            }
            else if (right == index) { // 두 수의 합을 나타내는 수 중 큰 수가 수 number일 경우
                right -= 1;
                continue;
            }

            int sum = A[left] + A[right]; // 두 수의 합

            if (sum > number) {
                right -= 1;
            }
            else if (sum < number) {
                left += 1;
            }
            else { // 두 수의 합이 수 number의 값과 같을 경우
                goodNumber.add(number);
                answer += 1;

                return;
            }
        }
    }

    public static void find() { // 좋은 수(문제에서 주어진 수 중 다른 수 두 개의 합으로 나타낼 수 있는 수)의 개수를 구하는 메서드
        goodNumber = new HashSet<>();
        Arrays.sort(A); // 문제에서 주어진 수들을 오름차순으로 정렬

        for (int n = 0; n < N; n++) {
            check(n, A[n]);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        A = new int[N];
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(token.nextToken());
        }

        find();
    }
}
