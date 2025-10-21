/*
1916. Gold 5 - 최소비용 구하기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    0.5 초	    128 MB           124550	    43101     28419	         33.567%


    문제
        N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.


    입력
        첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
        그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.


    출력
        첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.


    예제 입력 1
        5
        8
        1 2 2
        1 3 3
        1 4 1
        1 5 10
        2 4 2
        3 4 1
        3 5 1
        4 5 3
        1 5
    예제 출력 1
        4


    알고리즘 분류
        그래프 이론
        최단 경로
        데이크스트라
*/


// 메모리 : 50580KB
// 시간 : 404ms
// 코드 길이 : 3685B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
    static int N; // 도시의 개수 (1 ≤ N ≤ 1000)
    static int M; // 버스의 개수 (1 ≤ M ≤ 100000)
    static ArrayList<Bus>[] busLine; // busLine[start] : start 번 도시에서 출발하는 버스의 버스 정보(출발 도시 번호, 도착 도시 번호, 버스 비용)
    static int[] busCost; // busCost[end] : 출발 도시에서 end 번 도시까지 가는 데 드는 최소 비용

    static class Bus implements Comparable<Bus> { // 버스 정보를 저장하는 클래스
        int start; // 버스의 출발 도시
        int end; // 버스의 도착 도시
        int cost; // 버스의 비용

        public Bus(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus bus) {
            return Integer.compare(this.cost, bus.cost); // 버스의 비용을 기준으로 오름차순 정렬
        }
    }

    public static void find(int startCity, int endCity) { // 출발 도시에서 도착 도시까지 가는 데 드는 최소 비용을 구하는 메서드
        PriorityQueue<Bus> queue = new PriorityQueue<>();
        busCost = new int[N + 1];

        queue.offer(new Bus(0, startCity, 0));
        Arrays.fill(busCost, Integer.MAX_VALUE);
        busCost[startCity] = 0; // 출발 도시에서 출발 도시까지 가는 데 드는 비용은 0으로 설정

        while (!queue.isEmpty()) {
            Bus nowBus = queue.poll(); // 현재 버스 정보

            if (nowBus.end == endCity) { // 현재 버스의 도착 도시가 최종 도착 도시와 같을 경우
                break;
            }

            for (Bus nextBus : busLine[nowBus.end]) { // 현재 버스의 도착 도시에서 출발하는 버스들에 대해 검사
                int nextCost = nowBus.cost + nextBus.cost; // 다음 버스까지 탔을 경우의 비용

                if (busCost[nextBus.end] > nextCost) { // 출발 도시에서 다음 버스의 도착 도시까지 가는 데 드는 최소 비용이 현재 계산한 다음 버스까지 탔을 경우의 비용보다 클 경우
                    busCost[nextBus.end] = nextCost; // 출발 도시에서 다음 버스의 도착 도시까지 가는 데 드는 최소 비용 갱신
                    queue.offer(new Bus(nowBus.end, nextBus.end, nextCost));
                }
            }
        }

        System.out.println(busCost[endCity]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        busLine = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            busLine[i] = new ArrayList<>();
        }

        StringTokenizer token;
        for (int j = 0; j < M; j++) {
            token = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(token.nextToken()); // 버스의 출발 도시
            int end = Integer.parseInt(token.nextToken()); // 버스의 도착 도시
            int cost = Integer.parseInt(token.nextToken()); // 버스의 비용

            busLine[start].add(new Bus(start, end, cost));
        }

        token = new StringTokenizer(bf.readLine());
        find(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
    }
}
