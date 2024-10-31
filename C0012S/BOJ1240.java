/*
1240. Gold 5 - 노드사이의 거리

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           11639	    6353      4883	         54.340%


    문제
        N개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.


    입력
        첫째 줄에 노드의 개수 N과 거리를 알고 싶은 노드 쌍의 개수 M이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.


    출력
        M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.


    제한
        · 2 ≤ N ≤ 1000
        · 1 ≤ M ≤ 1000
        · 트리 상에 연결된 두 점과 거리는 10000 이하인 자연수이다.
        · 트리 노드의 번호는 1부터 N까지 자연수이며, 두 노드가 같은 번호를 갖는 경우는 없다.


    예제 입력 1
        4 2
        2 1 2
        4 3 2
        1 4 3
        1 2
        3 2
    예제 출력 1
        2
        7


    알고리즘 분류
        그래프 이론
        그래프 탐색
        트리
        너비 우선 탐색
        깊이 우선 탐색
*/


// 메모리 : 35972KB
// 시간 : 264ms
// 코드 길이 : 3219B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1240 {
    static class Node { // 연결된 노드의 정보를 저장하는 클래스
        int number; // 노드 번호
        int distance; // 연결된 노드와의 거리

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }
    }

    static int N; // 노드의 개수 (2 ≤ N ≤ 1000)
    static int M; // 거리를 알고 싶은 노드 쌍의 개수 (1 ≤ M ≤ 1000)
    static ArrayList<Node> tree[]; // 트리의 정보를 저장하는 배열
    static boolean isChecked[]; // 각 노드의 검사 여부를 저장하는 배열
    static StringBuilder sb;

    public static void find(int person, int friend) { // 거리를 알고 싶은 두 노드의 거리를 구하는 메서드
        Queue<int[]> queue = new ArrayDeque<>();

        int distance = 0; // 거리를 알고 싶은 두 노드의 거리
        queue.offer(new int[] {person, distance});
        while (!queue.isEmpty()) {
            int now[] = queue.poll(); // 현재 노드의 번호와 현재 노드까지의 거리를 저장하는 배열

            for (Node connectedNode : tree[now[0]]) { // 현재 노드와 연결된 노드들 검사
                if (!isChecked[connectedNode.number]) { // 연결된 노드가 검사하지 않은 노드일 경우
                    distance = now[1] + connectedNode.distance;

                    if (connectedNode.number == friend) { // 연결된 노드가 거리를 알고 싶은 노드일 경우
                        sb.append(distance);
                        sb.append("\n");

                        return;
                    }

                    isChecked[connectedNode.number] = true;
                    queue.offer(new int[] {connectedNode.number, distance});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        tree = new ArrayList[N + 1];
        for (int t = 0; t <= N; t++) {
            tree[t] = new ArrayList<>();
        }

        // 트리 상에 연결된 두 점과 거리 연결
        for (int n = 1; n < N; n++) {
            token = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());
            int distance = Integer.parseInt(token.nextToken());

            tree[u].add(new Node(v, distance));
            tree[v].add(new Node(u, distance));
        }

        // 거리를 알고 싶은 노드 쌍 입력
        sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            token = new StringTokenizer(bf.readLine());

            isChecked = new boolean[N + 1];
            find(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
        }

        System.out.println(sb);
    }
}
