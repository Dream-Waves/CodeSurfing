package Jieun714;
/**
 * 문제: 루트가 있는 트리(rooted tree)가 주어지고, 그 트리 상의 두 정점이 주어질 때 그들의 가장 가까운 공통 조상(Nearest Common Ancestor)은 다음과 같이 정의됩니다.
 *        - 두 노드의 가장 가까운 공통 조상은, 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은(즉 두 노드에 가장 가까운) 노드를 말합니다.
 *      루트가 있는 트리가 주어지고, 두 노드가 주어질 때 그 두 노드의 가장 가까운 공통 조상을 찾는 프로그램을 작성하세요
 * 입력: 첫 줄에 테스트 케이스의 개수 T가 주어집니다.
 *      각 테스트 케이스마다, 첫째 줄에 트리를 구성하는 노드의 수 N이 주어집니다. (2 ≤ N ≤ 10,000)
 *      그리고 그 다음 N-1개의 줄에 트리를 구성하는 간선 정보가 주어집니다. 한 간선 당 한 줄에 두 개의 숫자 A B 가 순서대로 주어지는데, 이는 A가 B의 부모라는 뜻입니다. (당연히 정점이 N개인 트리는 항상 N-1개의 간선으로 이루어집니다!) A와 B는 1 이상 N 이하의 정수로 이름 붙여집니다.
 *      테스트 케이스의 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드가 주어집니다.
 * 출력: 각 테스트 케이스 별로, 첫 줄에 입력에서 주어진 두 노드의 가장 가까운 공통 조상을 출력합니다.
 * 해결: 그래프, 최소 공통 조상
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3584 {
    public static int [] parent;
    public static boolean [] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트 케이스 개수

        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine()); //노드 개수
            parent = new int[N+1]; //부모 노드 저장 배열
            isVisited = new boolean[N+1]; //방문 체크 배열

            for(int j=0; j<N-1; j++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parent[B] = A; //B의 부모는 A
            }
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            //node1의 모든 조상(부모들)을 방문 처리
            while(node1 != 0) {
                isVisited[node1] = true;
                node1 = parent[node1];
            }

            int answer = 0;
            //node1과 겹치는 노드(공통 조상) 찾기
            while(node2 != 0) {
                if(isVisited[node2]) { //최초 공통 조상(LCA)의 경우, break
                    answer = node2;
                    break;
                }
                node2 = parent[node2];
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb); //결과 출력
    }
}