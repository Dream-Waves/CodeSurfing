/*
1927. Silver 2 - 최소 힙

    시간 제한	                            메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음) (하단 참고)	    128 MB           75596	    35868     28383	         49.123%


    문제
        널리 잘 알려진 자료구조 중 최소 힙이 있다. 최소 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.
            1. 배열에 자연수 x를 넣는다.
            2. 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.

        프로그램은 처음에 비어있는 배열에서 시작하게 된다.


    입력
        첫째 줄에 연산의 개수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다. 만약 x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산이고, x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우이다. x는 231보다 작은 자연수 또는 0이고, 음의 정수는 입력으로 주어지지 않는다.


    출력
        입력에서 0이 주어진 횟수만큼 답을 출력한다. 만약 배열이 비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.


    예제 입력 1
        9
        0
        12345678
        1
        2
        0
        0
        0
        0
        32
    예제 출력 1
        0
        1
        2
        12345678
        0


    비슷한 문제
        11279번. 최대 힙
        11286번. 절댓값 힙


    알고리즘 분류
        자료 구조
        우선순위 큐


    시간 제한
        Java 8: 2 초
        Java 8 (OpenJDK): 2 초
        Java 11: 2 초
        Kotlin (JVM): 2 초
*/


// 메모리 : 26244KB
// 시간 : 324ms
// 코드 길이 : 1498B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1927 {
    static int N; // 연산의 개수 N (1 ≤ N ≤ 100000)
    static PriorityQueue<Integer> heap; // 우선 순위 큐를 이용하여 만든 문제에서 필요한 최소 힙
    static StringBuilder sb;

    public static void operation(int x) { // 문제에서 요구하는 연산
        if (x == 0) { // 연산에 대한 정보를 나타내는 정수 x가 0일 경우
            if (heap.isEmpty()) { // 최소 힙이 비어 있을 경우
                sb.append(0); // 0을 출력하도록 한다.
            }
            else {
                sb.append(heap.poll()); // 가장 작은 값을 출력하도록 한 후, 최소 힙에서 제거
            }

            sb.append("\n");
        }
        else { // 연산에 대한 정보를 나타내는 정수 x가 0이 아닐 경우
            heap.offer(x); // 최소 힙에 추가
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        heap = new PriorityQueue<>();
        sb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            int x = Integer.parseInt(bf.readLine()); // 연산에 대한 정보를 나타내는 정수 x

            operation(x);
        }

        System.out.println(sb);
    }
}
