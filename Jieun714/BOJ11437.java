package Jieun714;
/**
 * 문제: N(2 ≤ N ≤ 50,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.
 *      두 노드의 쌍 M(1 ≤ M ≤ 10,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.
 * 입력: 첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.
 * 출력: M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.
 * 해결: LCA
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11437 {
    public static List<Integer>[] graph;
    public static int [] depth, parent;
    public static StringBuilder sb;

    //그래프 높이와 부모 계산
    public static void dfs(int n, int d, int p) {
        depth[n] = d; //현재 노드의 깊이 저장
        parent[n] = p; //현재 노드의 부모 저장

        for(int next : graph[n]) {
            if(next != p) { //부모가 아닐 경우
                dfs(next, d+1, n); //자식 노드 탐색
            }
        }
    }

    //LCA 찾기
    public static int find(int a, int b) {
        //깊이 맞추기
        while(depth[a] != depth[b]) {
            if(depth[a] > depth[b]) { //a가 더 깊으면 a를 부모로 올림
                a = parent[a];
            } else { //b가 더 깊으면 b를 부모로 올림
                b = parent[b];
            }
        }

        //같은 높이에서 올라가기
        while(a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //노드의 개수
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) graph[i] = new ArrayList<>();

        StringTokenizer st;
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //정점 1
            int b = Integer.parseInt(st.nextToken()); //정점 2
            graph[a].add(b);
            graph[b].add(a);
        }

        //트리 레벨 계산
        depth = new int[N+1];
        parent = new int[N+1];
        dfs(1, 0, 0);

        sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine()); //가장 가까운 공통 조상을 알고싶은 쌍의 개수
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //정점 1
            int b = Integer.parseInt(st.nextToken()); //정점 2
            sb.append(find(a, b)).append("\n");
        }

        System.out.println(sb);
    }
}