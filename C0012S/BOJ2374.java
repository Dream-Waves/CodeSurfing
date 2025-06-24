/*
2374. Gold 4 - 같은 수로 만들기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           2670	    924       656	         35.137%


    문제
        n(1 ≤ n ≤ 1,000)개의 자연수 A[1], A[2], A[3], …, A[n]이 있다. 이 자연수에 Add(i)라는 연산을 하면, A[i]가 1만큼 증가한다. 이때, A[i]만 증가하는 것이 아니고, A[i]의 좌우로 인접한 같은 수의 그룹이 한번에 1씩 증가한다. A[1]과 A[n]은 인접해 있지 않다.
        예를 들어 수가 {1, 1, 1, 1, 3, 3, 1} 이었다고 해 보자. Add(2)를 하면 A[2]의 좌우로 인접한 같은 수가 1씩 증가하니까 {2, 2, 2, 2, 3, 3, 1}이 된다. 여기서 Add(4)를 하면 {3, 3, 3, 3, 3, 3, 1}이 되고, 여기서 Add(1)을 하면 {4, 4, 4, 4, 4, 4, 1}이 된다.
        이와 같이 Add라는 연산을 사용하여 A[1] = A[2] = A[3] = … = A[n]이 되도록 하려 한다. 이때, 최소 회수로 Add연산을 사용하는 방법을 찾는 것이 문제이다.


    입력
        첫째 줄에 정수 n이 주어진다. 다음 n개의 줄에는 차례로 A[1], A[2], …, A[n]이 주어진다. 모든 입력은 1,000,000,000을 넘지 않는다.


    출력
        첫째 줄에 최소의 Add연산 사용 회수를 출력한다. 이 값은 1025을 넘지 않는다.


    예제 입력 1
        3
        1
        5
        10
    예제 출력 1
        9


    알고리즘 분류
        자료 구조
        그리디 알고리즘
        스택
*/


// 메모리 : 14692KB
// 시간 : 120ms
// 코드 길이 : 1656B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2374 {
    static int N; // 자연수의 개수
    static int[] A; // 자연수들을 저장하는 배열

    public static long calculate(int start, int end, int max) { // 최소의 Add 연산 사용 횟수를 구하는 메서드
        if (start < 0 || start > end || start >= N || end < 0 || end >= N) { // 유효하지 않은 구간일 경우
            return 0;
        }

        int nowMax = 0; // 현재 구간에서의 최댓값
        int nowMaxIndex = 0; // 현재 구간에서의 최댓값의 인덱스
        for (int n = start; n <= end; n++) {
            if (A[n] > nowMax) {
                nowMax = A[n];
                nowMaxIndex = n;
            }
        }

        long minCount = max == 0 ? 0 : max - nowMax; // 현재 구간에서 필요한 연산
        minCount += calculate(start, nowMaxIndex - 1, nowMax); // 현재 구간에서의 최댓값을 기준으로 좌측 구간에서 최소의 Add 연산 사용 횟수 구하기
        minCount += calculate(nowMaxIndex + 1, end, nowMax); // 현재 구간에서의 최댓값을 기준으로 우측 구간에서 최소의 Add 연산 사용 횟수 구하기

        return minCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        A = new int[N];
        for (int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(bf.readLine());
        }

        System.out.println(calculate(0, N - 1, 0));
    }
}
