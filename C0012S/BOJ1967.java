/*
1967. Gold 4 - 트리의 지름

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           59866	    24291     18346	         40.722%


    문제
        트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다. 이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.
            [그림은 문제에서 참고]

        이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.

        입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오. 아래와 같은 트리가 주어진다면 트리의 지름은 45가 된다.
            [그림은 문제에서 참고]

        트리의 노드는 1부터 n까지 번호가 매겨져 있다.


    입력
        파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다. 간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.


    출력
        첫째 줄에 트리의 지름을 출력한다.


    예제 입력 1
        12
        1 2 3
        1 3 2
        2 4 5
        3 5 11
        3 6 9
        4 7 1
        4 8 7
        5 9 15
        5 10 4
        6 11 6
        6 12 10
    예제 출력 1
        45


    알고리즘 분류
        그래프 이론
        그래프 탐색
        트리
        깊이 우선 탐색
        트리의 지름
*/


// 메모리 : 23452KB
// 시간 : 192ms
// 코드 길이 : 3061B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967 {
    static int N; // 노드의 개수
    static ArrayList<Node>[] tree; // 트리 정보를 저장하는 배열
    static boolean[] isVisited; // 각 노드의 방문 여부를 저장하는 배열
    static int farthestNodeNumber; // 임의의 노드에서 가장 먼 노드
    static int distance; // 임의의 노드와 가장 먼 노드의 거리

    static class Node { // 노드의 정보를 저장하는 클래스
        int number; // 노드의 번호
        int weight; // 노드의 가중치

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }

    public static void find(int nodeNumber, int totalDistance) { // nodeNumber 노드와 가장 먼 노드를 구하는 메서드
        if (distance < totalDistance) { // 임의의 노드에서 nodeNumber 노드까지 움직인 거리가 임의의 노드와 가장 먼 노드의 거리보다 클 경우
            distance = totalDistance;
            farthestNodeNumber = nodeNumber;
        }

        for (Node nextNode : tree[nodeNumber]) { // 현재 노드와 연결된 노드들
            if (!isVisited[nextNode.number]) { // 방문하지 않은 노드일 경우
                isVisited[nextNode.number] = true;
                find(nextNode.number, totalDistance + nextNode.weight);
            }
        }
    }

    public static void calculate() { // 트리의 지름을 구하는 메서드
        isVisited = new boolean[N + 1];
        isVisited[1] = true;
        find(1, 0); // 루트 노드인 1 노드와 가장 먼 노드를 구한다.

        distance = 0;
        isVisited = new boolean[N + 1];
        isVisited[farthestNodeNumber] = true;
        find(farthestNodeNumber, 0); // 루트 노드인 1 노드와 가장 먼 노드인 farthestNodeNumber 노드와 가장 먼 노드를 구한다.

        System.out.println(distance);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        if (N == 1) { // 노드가 1 개일 경우
            System.out.println(0); // 트리의 지름은 0이다.
            return;
        }

        tree = new ArrayList[N + 1];
        for (int a = 1; a <= N; a++) {
            tree[a] = new ArrayList<>();
        }

        StringTokenizer token;
        for (int i = 1; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            int parent = Integer.parseInt(token.nextToken()); // 부모 노드
            int child = Integer.parseInt(token.nextToken()); // 자식 노드
            int weight = Integer.parseInt(token.nextToken()); // 가중치

            tree[parent].add(new Node(child, weight));
            tree[child].add(new Node(parent, weight));
        }

        calculate();
    }
}
