/*
16398. Gold 4 - 행성 연결

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           9912	    4746      3409	         44.773%


    문제
        홍익 제국의 중심은 행성 T이다. 제국의 황제 윤석이는 행성 T에서 제국을 효과적으로 통치하기 위해서, N개의 행성 간에 플로우를 설치하려고 한다.
        두 행성 간에 플로우를 설치하면 제국의 함선과 무역선들은 한 행성에서 다른 행성으로 무시할 수 있을 만큼 짧은 시간만에 이동할 수 있다. 하지만, 치안을 유지하기 위해서 플로우 내에 제국군을 주둔시켜야 한다.
        모든 행성 간에 플로우를 설치하고 플로우 내에 제국군을 주둔하면, 제국의 제정이 악화되기 때문에 황제 윤석이는 제국의 모든 행성을 연결하면서 플로우 관리 비용을 최소한으로 하려 한다.
        N개의 행성은 정수 1,…,N으로 표시하고, 행성 i와 행성 j사이의 플로우 관리비용은 C_ij이며, i = j인 경우 항상 0이다.
        제국의 참모인 당신은 제국의 황제 윤석이를 도와 제국 내 모든 행성을 연결하고, 그 유지비용을 최소화하자. 이때 플로우의 설치비용은 무시하기로 한다.


    입력
        입력으로 첫 줄에 행성의 수 N (1 ≤ N ≤ 1000)이 주어진다.
        두 번째 줄부터 N+1줄까지 각 행성간의 플로우 관리 비용이 N x N 행렬 (C_ij), (1 ≤ i, j ≤ N, 1 ≤ C_ij ≤ 100,000,000, C_ij = C_ji, C_ii = 0) 로 주어진다.


    출력
        모든 행성을 연결했을 때, 최소 플로우의 관리비용을 출력한다.


    예제 입력 1
        3
        0 2 3
        2 0 1
        3 1 0
    예제 출력 1
        3

    예제 입력 2
        5
        0 6 8 1 3
        6 0 5 7 3
        8 5 0 9 4
        1 7 9 0 6
        3 3 4 6 0
    예제 출력 2
        11


    알고리즘 분류
        그래프 이론
        최소 스패닝 트리
*/


// 메모리 : 138844KB
// 시간 : 992ms
// 코드 길이 : 3317B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398 {
    static int N; // 행성의 수 (1 ≤ N ≤ 1000)
    static int[] graph; // 각 행성의 연결된 행성의 정보를 저장하는 배열
    static PriorityQueue<Flow> flowPriorityQueue; // 각 행성의 플로우 설치 정보(행성, 연결할 행성, 플로우 관리 비용)를 저장하는 우선 순위 큐

    static class Flow implements Comparable<Flow> { // 플로우 설치 정보를 저장하는 클래스
        int planet; // 행성
        int otherPlanet; // 연결할 행성
        int cost; // 플로우 관리 비용

        public Flow(int planet, int otherPlanet, int cost) {
            this.planet = planet;
            this.otherPlanet = otherPlanet;
            this.cost = cost;
        }

        @Override
        public int compareTo(Flow o) {
            return this.cost - o.cost; // 플로우 관리 비용을 기준으로 오름차순 정렬
        }
    }

    public static int find(int v) { // 유니온 파인드의 파인드 메서드
        if (graph[v] != v) {
            return graph[v] = find(graph[v]);
        }

        return v;
    }

    public static void union(int u, int v) { // 유니온 파인드의 유니온 메서드
        u = find(u);
        v = find(v);

        if (u < v) {
            graph[v] = u;
        }
        else {
            graph[u] = v;
        }
    }

    public static void calculate() { // 플로우를 설치하여 모든 행성을 연결했을 때의 최소 관리 비용을 구하는 메서드
        graph = new int[N];
        for (int g = 0; g < N; g++) { // 각 행성의 연결된 행성 초기화
            graph[g] = g;
        }

        int flowNum = 0; // 행성을 연결한 플로우의 개수
        long minCost = 0; // 플로우를 설치하여 모든 행성을 연결했을 때의 최소 관리 비용
        while (flowNum < N - 1) { // 모든 행성을 연결했을 때의 플로우는 N - 1 개
            Flow nowPlanet = flowPriorityQueue.poll();

            if (find(nowPlanet.planet) != find(nowPlanet.otherPlanet)) { // 각 행성과 연결된 행성들 중 가장 작은 번호를 가진 행성의 번호가 다를 경우
                union(nowPlanet.planet, nowPlanet.otherPlanet); // 사이클이 생기지 않으므로 두 행성 연결

                minCost += nowPlanet.cost;
                flowNum += 1;
            }
        }

        System.out.println(minCost);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        flowPriorityQueue = new PriorityQueue<>();
        for (int n = 0; n < N; n++) {
            token = new StringTokenizer(bf.readLine());

            for (int p = 0; p < N; p++) {
                int expense = Integer.parseInt(token.nextToken()); // n 번 행성과 p 번 행성 간의 플로우 관리 비용

                if (expense != 0) {
                    flowPriorityQueue.offer(new Flow(n, p, expense));
                }
            }
        }

        calculate();
    }
}
