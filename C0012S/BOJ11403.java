/*
11403. Silver 1 - 경로 찾기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           42966	    26026     19187	         60.407%


    문제
        가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.


    출력
        총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.


    예제 입력 1
        3
        0 1 0
        0 0 1
        1 0 0
    예제 출력 1
        1 1 1
        1 1 1
        1 1 1

    예제 입력 2
        7
        0 0 0 1 0 0 0
        0 0 0 0 0 0 1
        0 0 0 0 0 0 0
        0 0 0 0 1 1 0
        1 0 0 0 0 0 0
        0 0 0 0 0 0 1
        0 0 1 0 0 0 0
    예제 출력 2
        1 0 1 1 1 1 1
        0 0 1 0 0 0 1
        0 0 0 0 0 0 0
        1 0 1 1 1 1 1
        1 0 1 1 1 1 1
        0 0 1 0 0 0 1
        0 0 1 0 0 0 0


    알고리즘 분류
        그래프 이론
        그래프 탐색
        플로이드–워셜
*/


// 메모리 : 16868KB
// 시간 : 284ms
// 코드 길이 : 2549B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11403 {
    static int N; // 정점의 개수 N (1 ≤ N ≤ 100)
    static int G[][]; // 가중치 없는 방향 그래프 G
    static int answerGraph[][]; // 정점 i에서 j로 가는 길이가 양수인 경로의 여부를 저장하는 2 차원 배열
    static boolean isChecked[][]; // 해당 행렬 원소를 검사했는지 여부

    public static void findAllRoute() { // 모든 정점 (i, j)에 대해서, i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하는 함수
        answerGraph = new int[N][N];
        for (int r = 0; r < N; r++) {
            isChecked = new boolean[N][N];

            findNextRoute(r, r); // 인접행렬의 모든 행에 대해서 해당 정점(r 행)의 경로 여부 구하기
        }

        // 답(정점 i에서 j로 가는 경로의 여부) 출력
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                sb.append(answerGraph[k][l]).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void findNextRoute(int x, int y) { // 정점 y에서 다음에 방문할 수 있는 정점이 있는지 검사하는 함수  // DFS Algorithm
        for (int c = 0; c < N; c++) { // 행렬의 열의 개수만큼 반복
            if ((G[y][c] == 1) && (!isChecked[y][c])) { // 다음에 검사할 수 있는 정점에서 다른 정점으로 갈 수 있는 경로가 있고, 그 경로가 검사되지 않았을 경우
                answerGraph[x][c] = 1; // 정점 x에서 정점 j로 가는 경로가 있다고 설정
                isChecked[y][c] = true; // 해당 행렬 원소 검사 체크
                findNextRoute(x, c); // 정점 c에서 다음에 방문할 수 있는 정점이 있는지 검사
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        G = new int[N][N];
        for (int i = 0; i < N; i++) { // 그래프의 인접 행렬
            token = new StringTokenizer(bf.readLine());

            for (int j = 0; j < N; j++) {
                G[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        findAllRoute();
    }
}
