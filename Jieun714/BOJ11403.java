package Jieun714;
/**
 * 문제: 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
 *
 * 입력: 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다.
 *      i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.
 *
 * 출력: 총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11403 {
    public static int N;
    public static int [][] graph, result;
    public static boolean [] isVisited;

    // DFS - 재귀
    public static void dfs(int x, int y){
        isVisited[y] = true; //방문 체크
        result[x][y] = 1; //경로가 존재할 때만 돌기 때문에, 경로 체크

        for(int i=0; i<N; i++){
            if(!isVisited[i] && graph[y][i]==1) // 해당 열에 방문하지 않고, 양수인 경로가 있다면
                dfs(x, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //정점의 개수
        graph = new int[N][N]; //그래프의 인접 행렬을 담는 배열
        result = new int[N][N]; //결과를 담는 배열 - 인접행렬 형식으로
        isVisited = new boolean[N]; //열의 방문 여부를 체크하기 위한 배열

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x=0; x<N; x++){
            isVisited = new boolean[N]; //초기화
            for(int y=0; y<N; y++){
                if(!isVisited[y] && graph[x][y] == 1) //해당 열에 방문하지 않고, 양수인 경로가 있다면
                    dfs(x, y); //함수 호출
            } //for end
        }

        // 결과 출력
        for(int [] col : result){
            for(int c : col){
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
