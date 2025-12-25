package Jieun714;
/**
 * 문제: 지점의 개수 n, 출발지점을 나타내는 s, A의 도착지점을 나타내는 a, B의 도착지점을 나타내는 b, 지점 사이의 예상 택시요금을 나타내는 fares가 매개변수로 주어집니다.
 *      이때, A, B 두 사람이 s에서 출발해서 각각의 도착 지점까지 택시를 타고 간다고 가정할 때, 최저 예상 택시요금을 계산해서 return 하도록 solution 함수를 완성해 주세요.
 *      만약, 아예 합승을 하지 않고 각자 이동하는 경우의 예상 택시요금이 더 낮다면, 합승을 하지 않아도 됩니다.
 * 제한사항:
 *   -  지점갯수 n은 3 이상 200 이하인 자연수입니다.
 *   -  지점 s, a, b는 1 이상 n 이하인 자연수이며, 각기 서로 다른 값입니다.
 *      -  즉, 출발지점, A의 도착지점, B의 도착지점은 서로 겹치지 않습니다.
 *   -  fares는 2차원 정수 배열입니다.
 *      -  fares 배열의 크기는 2 이상 n x (n-1) / 2 이하입니다.
 *      -  fares 배열의 각 행은 [c, d, f] 형태입니다.
 *      -  c지점과 d지점 사이의 예상 택시요금이 f원이라는 뜻입니다.
 *      -  지점 c, d는 1 이상 n 이하인 자연수이며, 각기 서로 다른 값입니다.
 *      -  요금 f는 1 이상 100,000 이하인 자연수입니다.
 *      -  fares 배열에 두 지점 간 예상 택시요금은 1개만 주어집니다. 즉, [c, d, f]가 있다면 [d, c, f]는 주어지지 않습니다.
 *   -  출발지점 s에서 도착지점 a와 b로 가는 경로가 존재하는 경우만 입력으로 주어집니다.
 * 해결: 플로이드-워셜
 * */
public class PRO72413 {
    class Solution {
        public int [][] graph;

        public void floyd(int node) {
            for(int i=0; i<graph.length; i++) {
                if(i == node) continue; //출발지와 경유지가 같은 경우 스킵
                for(int j=0; j<graph.length; j++) {
                    //도착지가 같거나 해당 경로가 없으면 스킵
                    if(i == j || graph[i][node] == Integer.MAX_VALUE || graph[node][j] == Integer.MAX_VALUE) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][node]+graph[node][j]);
                }
            }
        }

        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = 0;
            graph = new int[n][n]; //그래프 초기화

            for(int [] f : fares) {
                int c = f[0]-1;
                int d = f[1]-1;
                graph[c][d] = f[2];
                graph[d][c] = f[2];
            }

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(i == j || graph[i][j] > 0) continue;
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }

            //플로이드 알고리즘
            for(int i=0; i<n; i++) floyd(i);

            answer = graph[s-1][a-1] + graph[s-1][b-1]; //합승하지 않고 각자 이동하는 경우
            for(int i=0; i<n; i++) {
                answer = Math.min(answer, graph[s-1][i]+graph[i][a-1]+graph[i][b-1]);
            }

            return answer;
        }
    }

}
