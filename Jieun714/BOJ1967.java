package Jieun714;
/**
 * 문제: 트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다.
 *      이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.
 *      이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.
 *      트리의 노드는 1부터 n까지 번호가 매겨져 있다.
 * 입력: 파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다. 간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고,
 *      두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.
 * 출력: 첫째 줄에 트리의 지름을 출력한다.
 * 해결: DFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967 {
    public static ArrayList<int []> [] list;
    public static boolean [] isVisited;
    public static int max, node;

    public static void dfs(int now, int total) {
        isVisited[now] = true;
        if (total > max) { //더 먼 거리일 경우
            max = total;
            node = now; //정점 변경
        }

        for(int i = 0; i<list[now].size(); i++) {
            int [] next = list[now].get(i);
            if(!isVisited[next[0]]) dfs(next[0], total + next[1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) list[i] = new ArrayList<>();
        for(int i = 0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()); //부모 노드
            int node2 = Integer.parseInt(st.nextToken()); //자식 노드
            int w = Integer.parseInt(st.nextToken()); //가중치

            list[node1].add(new int[]{node2, w});
            list[node2].add(new int[]{node1, w});
        }

        isVisited = new boolean[N+1]; //방문 배열 초기화
        dfs(1, 0); //가장 먼 정점 A 구하기

        isVisited = new boolean[N+1]; //방문 배열 초기화
        dfs(node, 0); //A에서부터 가장 먼 정점까지의 거리 구하기

        System.out.println(max); //최대 트리의 지름 출력
    }
}