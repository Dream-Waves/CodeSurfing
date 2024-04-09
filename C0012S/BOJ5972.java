/*
5972. Gold 5 - 택배 배송

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           7217	    4292      3450	         59.411%


    문제
        농부 현서는 농부 찬홍이에게 택배를 배달해줘야 합니다. 그리고 지금, 갈 준비를 하고 있습니다. 평화롭게 가려면 가는 길에 만나는 모든 소들에게 맛있는 여물을 줘야 합니다. 물론 현서는 구두쇠라서 최소한의 소들을 만나면서 지나가고 싶습니다.

        농부 현서에게는 지도가 있습니다. N (1 <= N <= 50,000) 개의 헛간과, 소들의 길인 M (1 <= M <= 50,000) 개의 양방향 길이 그려져 있고, 각각의 길은 C_i (0 <= C_i <= 1,000) 마리의 소가 있습니다. 소들의 길은 두 개의 떨어진 헛간인 A_i 와 B_i (1 <= A_i <= N; 1 <= B_i <= N; A_i != B_i)를 잇습니다. 두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있습니다. 농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있습니다.

        다음 지도를 참고하세요.
                   [2]---
                  / |    \
                 /1 |     \ 6
                /   |      \
             [1]   0|    --[3]
                \   |   /     \2
                4\  |  /4      [6]
                  \ | /       /1
                   [4]-----[5]
                        3

        농부 현서가 선택할 수 있는 최선의 통로는 1 -> 2 -> 4 -> 5 -> 6 입니다. 왜냐하면 여물의 총합이 1 + 0 + 3 + 1 = 5 이기 때문입니다.

        농부 현서의 지도가 주어지고, 지나가는 길에 소를 만나면 줘야할 여물의 비용이 주어질 때 최소 여물은 얼마일까요? 농부 현서는 가는 길의 길이는 고려하지 않습니다.


    입력
        첫째 줄에 N과 M이 공백을 사이에 두고 주어집니다.
        둘째 줄부터 M+1번째 줄까지 세 개의 정수 A_i, B_i, C_i가 주어집니다.


    출력
        첫째 줄에 농부 현서가 가져가야 될 최소 여물을 출력합니다.


    예제 입력 1
        6 8
        4 5 3
        2 4 0
        4 1 4
        2 1 1
        5 6 1
        3 6 2
        3 2 6
        3 4 4
    예제 출력 1
        5


    알고리즘 분류
        그래프 이론
        데이크스트라
        최단 경로
*/


// 메모리 : 90744KB
// 시간 : 736ms
// 코드 길이 : 2358B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5972 {
    static int N; // 헛간의 개수 (1 ≤ N ≤ 50000)
    static ArrayList<int[]> path[]; // 해당 인덱스의 헛간과 연결되어 있는 헛간과 그 헛간으로 가는 데 드는 여물의 비용을 저장하는 배열
    static int feed[]; // 각 인덱스의 헛간에 도착하는 데 드는 최소 여물의 비용

    public static void findMinPath(int start) { // 농부 현서가 농부 찬홍이에게 택배를 배달하기 위해 가져가야 될 최소 여물의 비용을 구하는 메서드
        feed = new int[N + 1];
        Arrays.fill(feed, Integer.MAX_VALUE);
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        feed[start] = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int d = 0, len = path[now].size(); d < len; d++) {
                int next[] = path[now].get(d);

                int newFeed = feed[now] + next[1];
                if (feed[next[0]] > newFeed) {
                    queue.offer(next[0]);
                    feed[next[0]] = newFeed;
                }
            }
        }

        System.out.println(feed[N]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken()); // 소들의 길의 개수 (1 ≤ M ≤ 50000)

        path = new ArrayList[N + 1];
        for (int a = 0; a <= N; a++) {
            path[a] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            token = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(token.nextToken()); // 헛간 A_m (1 ≤ A_m ≤ N, A_m != B_m)
            int B = Integer.parseInt(token.nextToken()); // 헛간 B_m (1 ≤ B_m ≤ N, A_m != B_m)
            int C = Integer.parseInt(token.nextToken()); // 헛간 A_m에서 헛간 B_m로 가는 데 있는 소의 수 C_m (0 ≤ C_m ≤ 1000)

            path[A].add(new int[] {B, C});
            path[B].add(new int[] {A, C});
        }

        findMinPath(1);
    }
}
