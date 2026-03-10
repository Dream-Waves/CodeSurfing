package Jieun714;
/**
 * 문제: 영선회사에는 매우 좋은 문화가 있는데, 바로 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬하는 내리 칭찬이 있다. 즉, 상사가 한 직속 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬을 받는다.
 *      모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 이 수치 또한 부하들에게 똑같이 칭찬 받는다.
 *      직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력하시오.
 * 입력: 첫째 줄에는 회사의 직원 수 n명, 최초의 칭찬의 횟수 m이 주어진다. 직원은 1번부터 n번까지 번호가 매겨져 있다. (2 ≤ n, m ≤ 100,000)
 *      둘째 줄에는 직원 n명의 직속 상사의 번호가 주어진다. 직속 상사의 번호는 자신의 번호보다 작으며, 최종적으로 1번이 사장이다. 1번의 경우, 상사가 없으므로 -1이 입력된다.
 *      다음 m줄에는 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w가 주어진다. (2 ≤ i ≤ n, 1 ≤ w ≤ 1,000)
 *      사장은 상사가 없으므로 칭찬을 받지 않는다.
 * 출력: 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력하시오.
 * 해결: DFS + DP
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14267 {
    public static int N, M;
    public static List<Integer>[] graph;
    public static int [] total;

    public static void dfs(int now) {
        for(int next : graph[now]) { //현재 부하의 직속 부하 탐색
            total[next] += total[now]; //직속 부하에게 내리 칭찬
            dfs(next); //다음 직속 부하으로 dfs 탐색
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //회사의 직원 수
        M = Integer.parseInt(st.nextToken()); //최초의 칭찬의 횟수
        graph = new ArrayList[N];
        for(int i=0; i<N; i++) graph[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n != -1) graph[n-1].add(i); //상사 -> 직속 부하
        }

        total = new int[N]; //누적 칭찬 수를 담는 배열
        for(int j=0; j<M; j++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) -1; //직원 번호
            int w = Integer.parseInt(st.nextToken()); //칭찬의 수치
            total[i] += w; //누적 칭찬
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();
        for(int n : total) sb.append(n).append(" ");

        System.out.println(sb); //결과 출력
    }
}