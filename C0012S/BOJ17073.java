/*
17073. Gold 5 - 나무 위의 빗물

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           4600	    1884      1355	         38.396%


    문제
            [그림은 문제에서 참고]
        트리란, 사이클이 없는 연결 그래프를 의미한다. 위 그림은 1번 정점을 루트로 하는 어떤 트리를 나타낸 모습이다.

        사실 이 트리는 영훈이가 뒷마당에서 기르고 있는 나무이다. 어제는 비가 왔기 때문에, 트리의 1번 정점에는 W만큼의 물이 고여 있다. 1번 정점을 제외한 모든 정점에는 아직 물이 고여 있지 않은 상태이다.

        이제 매초마다 모든 정점은 아래의 작업을 순서대로 반복한다.
            · 물을 가지고 있으며, 자식 정점이 있다면 자식 정점 중 하나를 골라 물을 1 준다. 자식 정점이 여러 개라면 동일한 확률로 그 중 하나를 고른다.
            · 만약 부모 정점이 자신에게 물을 흘려보냈다면 받아서 쌓아 둔다.

        이때, 위 작업은 순서대로 진행되므로 부모 정점에게 받은 물을 즉시 자식 정점에게 줄 수는 없다.

        영훈이는 나무를 바라보면서 더 이상 물이 움직이지 않는 상태가 되었을 때 각 정점에 어느 정도의 물이 있게 될지 궁금해졌다. 더 이상 물이 움직이지 않을 때, i번 정점에 쌓인 물의 양의 기댓값을 P_i라 하자. 이때, P_i가 0보다 큰 정점들에 대해서 P_i들의 평균은 어느 정도가 될까?


    입력
        첫째 줄에 트리의 노드의 수 N과 1번 노드에 고인 물의 양을 의미하는 정수 W가 주어진다. (2 ≤ N ≤ 500,000, 1 ≤ W ≤ 10^9)
        다음 N-1줄에 걸쳐, 트리에 존재하는 간선의 정보가 U V의 형태로 주어진다. (1 ≤ U, V ≤ N, U ≠ V)
        이는 양 끝 정점이 각각 U와 V인 간선이 트리에 존재한다는 의미이다.
        입력으로 주어지는 트리는 항상 올바른 연결 트리임이 보장되며, 주어지는 트리의 루트는 항상 1번 정점이다.


    출력
        문제의 정답을 출력한다. 정답과의 차이가 10^(-3) 이하인 값은 모두 정답으로 인정된다.


    예제 입력 1
        5 20
        5 3
        3 4
        2 1
        1 3
    예제 출력 1
        6.6666666667


    알고리즘 분류
        수학
        그래프 이론
        그래프 탐색
        트리
*/


// 메모리 : 179928KB
// 시간 : 636ms
// 코드 길이 : 1810B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17073 {
    static int N; // 트리의 노드의 수 (2 ≤ N ≤ 500000)
    static int W; // 1 번 노드에 고인 물의 양 (1 ≤ W ≤ 10^9)
    static ArrayList<Integer>[] tree; // 트리의 정보를 저장하는 배열
    static double leaftNodeNum; // 트리의 리프 노드의 수

    public static void find() { // 나무에 있는 물이 더 이상 움직이지 않을 때, 각 정점에 쌓인 물의 양의 기댓값들의 평균을 구하는 메서드
        for (int n = 2; n <= N; n++) {
            if (tree[n].size() == 1) { // 현재 검사하고 있는 노드와 연결된 노드의 개수가 1 개일 경우
                leaftNodeNum += 1; // 리프 노드의 수 추가
            }
        }

        System.out.println(W / leaftNodeNum); // 나무에 있는 물이 더 이상 움직이지 않을 때, 각 정점에 쌓인 물의 양의 기댓값들의 평균
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        W = Integer.parseInt(token.nextToken());

        tree = new ArrayList[N + 1];
        for (int t = 1; t <= N; t++) {
            tree[t] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        find();
    }
}
