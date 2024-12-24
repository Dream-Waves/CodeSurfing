/*
7511. Gold 5 - 소셜 네트워킹 어플리케이션

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           3143	    1296      1037	         40.287%


    문제
        어렸을때부터 컴퓨터 프로그래밍에 엄청난 소질을 보인 상근이는 항상 소셜 네트워킹 웹사이트를 만들고 싶어 했다. 상근이는 페이스북을 벤치마킹하기 위해 지난 3년간 열심히 사용을 했고, 이제 페이스북의 단점을 보완한 새 소셜 네트워킹 웹 2.0 어플리케이션을 만들려고 한다.
        사람들은 소셜 네트워킹 어플리케이션에 가입을 한 다음, 현실에서 아는 사람을 친구로 추가하기 시작한다. 이러한 친구 관계 정보를 이용해 친구 관계 그래프를 그릴 수 있다.
        소셜 네트워킹 어플리케이션에서 가장 중요한 기능은 한 사람이 다른 사람의 페이지를 방문했을 때, 친구 관계 그래프에서 두 사람 사이의 경로를 보여주는 기능이다. 경로가 없는 경우에는 보여주지 않는다.
        상근이의 서비스는 매우 유명해졌고, 위의 기능은 사람들이 점점 많아질수록 경로를 구하는 시간이 매우 느려지게 되었다. 그 이유는 두 사람 사이의 경로가 없는 경우에 경로를 찾기 위해 너무 오랜시간 그래프를 탐색하기 때문이었다. 따라서, 상근이는 두 사람 사이의 경로가 존재하는지 안 하는지를 미리 구해보려고 한다.
        유저의 수와 각 유저의 친구 관계가 입력으로 주어진다. 이때, 주어지는 두 사람이 친구 관계 그래프상에서 경로가 존재하는지 안 하는지를 구하는 프로그램을 작성하시오.


    입력
        입력은 여러 개의 테스트 케이스로 이루어져 있다.
        각 테스트 케이스의 첫째 줄에는 유저의 수 1 ≤ n ≤ 10^6이 주어진다. 둘째 줄에는 친구 관계의 수 1 ≤ k ≤ 10^5가 주어진다. 다음 k개 줄에는 두 정수 0 ≤ a, b < n이 주어진다. 두 수는 친구 관계를 나타내며, 유저 a와 b가 친구라는 소리이다. 다음 줄에는 미리 구할 쌍의 수 1 ≤ m ≤ 10^5가 주어진다. 다음 m개 줄에는 구해야하는 쌍을 나타내는 u, v가 주어진다.


    출력
        각 테스트 케이스마다 "Scenario i:"를 출력한다. i는 테스트 케이스 번호이며, 1부터 시작한다. 그 다음, 각각의 쌍마다 두 사람을 연결하는 경로가 있으면 1, 없으면 0을 출력한다.
        각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.


    예제 입력 1
        2
        3
        1
        0 1
        2
        0 1
        1 2
        5
        3
        0 1
        1 2
        3 4
        2
        0 2
        1 3
    예제 출력 1
        Scenario 1:
        1
        0

        Scenario 2:
        1
        0


    알고리즘 분류
        자료 구조
        그래프 이론
        분리 집합
*/


// 메모리 : 166076KB
// 시간 : 772ms
// 코드 길이 : 2712B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7511 {
    static int N; // 유저의 수 (1 ≤ N ≤ 10^6)
    static int K; // 친구 관계의 수 (1 ≤ K ≤ 10^5)
    static int M; // 구해야 하는 쌍의 수 (1 ≤ M ≤ 10^5)
    static int[] friend; // 친구 관계 정보를 저장하는 배열
    static StringBuilder sb;

    public static int find(int v) { // 유니온 파인드의 파인드 연산 메서드
        if (friend[v] != v) {
            return friend[v] = find(friend[v]);
        }

        return v;
    }

    public static void union(int u, int v) { // 유니온 파인드의 유니온 연산 메서드
        u = find(u);
        v = find(v);

        if (u < v) {
            friend[v] = u;
        }
        else {
            friend[u] = v;
        }
    }

    public static void checkPath(int u, int v) { // 두 사람이 친구 관계 그래프 상에서 경로의 존재 여부를 구하는 메서드
        if (find(u) == find(v)) { // 두 사람을 연결하는 경로가 있을 경우
            sb.append(1);
        }
        else { // 두 사람을 연결하는 경로가 없을 경우
            sb.append(0);
        }

        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("Scenario ");
            sb.append(t);
            sb.append(":\n");

            N = Integer.parseInt(bf.readLine());
            K = Integer.parseInt(bf.readLine());

            friend = new int[N];
            for (int i = 0; i < N; i++) { // 친구 관계 정보 초기화
                friend[i] = i; // 초기에는 본인으로 지정
            }

            for (int k = 0; k < K; k++) { // 친구 관계 입력
                token = new StringTokenizer(bf.readLine());
                int u = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());

                union(u, v);
            }

            M = Integer.parseInt(bf.readLine());
            for (int m = 0; m < M; m++) { // 구해야 하는 쌍 입력
                token = new StringTokenizer(bf.readLine());
                int u = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());

                checkPath(u, v);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
