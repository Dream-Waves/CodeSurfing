/*
17352. Gold 5 - 여러분의 다리가 되어 드리겠습니다!

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           5099	    2547      2017	         49.594%


    문제
        선린월드에는 N개의 섬이 있다. 섬에는 1, 2, ..., N의 번호가 하나씩 붙어 있다. 그 섬들을 N - 1개의 다리가 잇고 있으며, 어떤 두 섬 사이든 다리로 왕복할 수 있다.
        어제까지는 그랬다.
        "왜 다리가 N - 1개밖에 없냐, 통행하기 불편하다"며 선린월드에 불만을 갖던 욱제가 다리 하나를 무너뜨렸다! 안 그래도 불편한 통행이 더 불편해졌다. 서로 왕복할 수 없는 섬들이 생겼기 때문이다. 일단 급한 대로 정부는 선린월드의 건축가를 고용해, 서로 다른 두 섬을 다리로 이어서 다시 어떤 두 섬 사이든 왕복할 수 있게 하라는 지시를 내렸다.
        그런데 그 건축가가 당신이다! 안 그래도 천하제일 코딩대회에 참가하느라 바쁜데...


    입력
        첫 줄에 정수 N이 주어진다. (2 ≤ N ≤ 300,000)
        그 다음 N - 2개의 줄에는 욱제가 무너뜨리지 않은 다리들이 잇는 두 섬의 번호가 주어진다.


    출력
        다리로 이을 두 섬의 번호를 출력한다. 여러 가지 방법이 있을 경우 그 중 아무거나 한 방법만 출력한다.


    예제 입력 1
        4
        1 2
        1 3
    예제 출력 1
        1 4

        "4 1"이나 "2 4", "4 3" 등 가능한 정답은 많이 있지만, 아무거나 하나만 출력해야 한다.

    예제 입력 2
        2
    예제 출력 2
        1 2

    예제 입력 3
        5
        1 2
        2 3
        4 5
    예제 출력 3
        3 4


    알고리즘 분류
        자료 구조
        그래프 이론
        그래프 탐색
        분리 집합
*/


// 메모리 : 80508KB
// 시간 : 484ms
// 코드 길이 : 1860B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17352 {
    static int N; // 섬의 개수
    static int[] bridge; // 각 섬과 다리를 이은 섬의 정보를 저장하는 배열

    public static int find(int v) { // 유니온 파인드의 파인드 메서드
        if (bridge[v] != v) {
            return bridge[v] = find(bridge[v]);
        }

        return v;
    }

    public static void union(int u, int v) { // 유니온 파인드의 유니온 메서드
        u = find(u);
        v = find(v);

        if (u < v) {
            bridge[v] = u;
        }
        else {
            bridge[u] = v;
        }
    }

    public static void make() { // 다리로 이어지지 않은 섬을 찾고, 두 섬의 번호를 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        for (int n = 1; n <= N; n++) {
            if (find(n) == n) { // 다리로 이어진 섬의 번호가 해당 섬의 번호일 경우
                sb.append(n);
                sb.append(" ");
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        bridge = new int[N + 1];
        for (int i = 1; i <= N; i++) { // 다리를 이은 섬 정보 초기화
            bridge[i] = i; // 초기에는 본인으로 지정
        }

        StringTokenizer token;
        for (int j = 0, num = N - 2; j < num; j++) {
            token = new StringTokenizer(bf.readLine());

            int u = Integer.parseInt(token.nextToken());
            int v = Integer.parseInt(token.nextToken());

            union(u, v);
        }

        make();
    }
}
