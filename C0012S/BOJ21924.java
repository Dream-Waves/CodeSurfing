/*
21924. Gold 4 - 도시 건설

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           5157	    2298      1761	         42.019%


    문제
        채완이는 신도시에 건물 사이를 잇는 양방향 도로를 만들려는 공사 계획을 세웠다.
        공사 계획을 검토하면서 비용이 생각보다 많이 드는 것을 확인했다.
        채완이는 공사하는 데 드는 비용을 아끼려고 한다.
        모든 건물이 도로를 통해 연결되도록 최소한의 도로를 만들려고 한다.

            [그림은 문제에서 참고]
        위 그림에서 건물과 직선으로 표시된 도로, 해당 도로를 만들 때 드는 비용을 표시해놓은 지도이다.

            [그림은 문제에서 참고]
        그림에 있는 도로를 다 설치할 때 드는 비용은 62이다. 모든 건물을 연결하는 도로만 만드는 비용은 27로 절약하는 비용은 35이다.

        채완이는 도로가 너무 많아 절약되는 금액을 계산하는 데 어려움을 겪고 있다.
        채완이를 대신해 얼마나 절약이 되는지 계산해주자.


    입력
        첫 번째 줄에 건물의 개수 N (3 ≤ N ≤ 10^5)와 도로의 개수 M (2 ≤ M ≤ min((N(N - 1) / 2), 5 × 10^5))가 주어진다.
        두 번째 줄 부터 M + 1줄까지 건물의 번호 a, b (1 ≤ a, b ≤ N, a ≠ b)와 두 건물 사이 도로를 만들 때 드는 비용 c (1 ≤ c ≤ 10^6)가 주어진다. 같은 쌍의 건물을 연결하는 두 도로는 주어지지 않는다.


    출력
        예산을 얼마나 절약 할 수 있는지 출력한다. 만약 모든 건물이 연결되어 있지 않는다면 -1을 출력한다.


    예제 입력 1
        7 9
        1 2 15
        2 3 7
        1 3 3
        1 4 8
        3 5 6
        4 5 4
        4 6 12
        5 7 1
        6 7 6
    예제 출력 1
        35

        위에서 설명한 것과 같다.

    예제 입력 2
        8 10
        1 2 4
        2 3 9
        2 4 9
        3 4 4
        3 5 1
        4 6 14
        6 7 5
        5 7 3
        7 8 7
        6 8 3
    예제 출력 2
        30

    예제 입력 3
        5 4
        1 2 1
        2 3 1
        3 1 1
        4 5 5
    예제 출력 3
        -1


    알고리즘 분류
        그래프 이론
        최소 스패닝 트리
*/


// 메모리 : 162728KB
// 시간 : 928ms
// 코드 길이 : 3691B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21924 {
    static int N; // 건물의 개수
    static int M; // 도로의 개수
    static PriorityQueue<CityMap> cityMapPriorityQueue; // 각 건물의 연결할 도로 정보(건물, 연결할 건물, 도로를 만들 때 드는 비용)
    static int[] road; // 각 건물에 연결된 건물의 정보를 저장하는 배열
    static long allCost; // 모든 도로를 다 설치할 때 드는 비용

    static class CityMap implements Comparable<CityMap> { // 건물과 연결될 도로 정보를 저장하는 클래스
        int building; // 건물
        int nearBuilding; // 연결할 건물
        int cost; // 도로를 만들 때 드는 비용

        public CityMap(int building, int nearBuilding, int cost) {
            this.building = building;
            this.nearBuilding = nearBuilding;
            this.cost = cost;
        }

        @Override
        public int compareTo(CityMap o) {
            return this.cost - o.cost; // 도로를 만들 때 드는 비용을 기준으로 오름차순 정렬
        }
    }

    public static int find(int v) { // 유니온 파인드의 파인드 메서드
        if (road[v] != v) {
            return road[v] = find(road[v]);
        }

        return v;
    }

    public static void union(int u, int v) { // 유니온 파인드의 유니온 메서드
        u = find(u);
        v = find(v);

        if (u < v) {
            road[v] = u;
        }
        else {
            road[u] = v;
        }
    }

    public static void calculate() { // 도로를 설치하여 모든 건물을 연결했을 때 최대로 절약할 수 있는 예산을 구하는 메서드
        road = new int[N + 1];
        for (int r = 1; r <= N; r++) { // 각 건물의 연결된 건물 초기화
            road[r] = r;
        }

        int roadNum = 0; // 만들어진 도로 개수
        long minCost = 0; // 도로를 만들어 모든 건물이 연결되었을 때 드는 최소 비용
        while (roadNum < N - 1) {
            if (cityMapPriorityQueue.isEmpty()) { // 설치할 수 있는 도로를 다 설치했음에도 불구하고 모든 건물이 연결되어 있지 않을 경우
                System.out.println(-1);

                return;
            }

            CityMap nowCityMap = cityMapPriorityQueue.poll();

            if (find(nowCityMap.building) != find(nowCityMap.nearBuilding)) { // 각 건물과 연결된 건물들 중 가장 작은 번호를 가진 건물의 번호가 다를 경우
                union(nowCityMap.building, nowCityMap.nearBuilding);

                minCost += nowCityMap.cost;
                roadNum += 1;
            }
        }

        System.out.println(allCost - minCost);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        cityMapPriorityQueue = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(bf.readLine());

            int building = Integer.parseInt(token.nextToken());
            int nearBuilding = Integer.parseInt(token.nextToken());
            int cost = Integer.parseInt(token.nextToken());

            cityMapPriorityQueue.offer(new CityMap(building, nearBuilding, cost));

            allCost += cost;
        }

        calculate();
    }
}
