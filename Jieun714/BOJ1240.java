package Jieun714;
/**
 * 문제: N개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
 * 입력: 첫째 줄에 노드의 개수 N과 거리를 알고 싶은 노드 쌍의 개수 M이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에는 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.
 * 출력: M개의 줄에 차례로 입력받은 두 노드 사이의 거리를 출력한다.
 * 해결: 그래프 + DFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1240 {
    public static class Node {
        int node, dis;
        public Node(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }
    }

    public static int N, M, min;
    public static List<Node>[] graph;
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int prev, int start, int end, int total) {
        if(start == end) {
            sb.append(total).append("\n");
            return;
        }
        for (Node n : graph[start]) {
            if(n.node != prev) { //이전에 방문한 노드가 아닐 경우
                dfs(start, n.node, end, total+n.dis);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //노드의 개수
        M = Integer.parseInt(st.nextToken()); //노드 쌍의 개수
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, dis));
            graph[b].add(new Node(a, dis));
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            dfs(-1, s, e, 0);
        }
        System.out.println(sb);
    }
}
