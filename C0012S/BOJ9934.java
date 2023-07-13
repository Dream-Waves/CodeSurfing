/*
9934. Silver 1 - 완전 이진 트리

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           6252	    4155      3327	         68.162%


    문제
        상근이는 슬로베니아의 도시 Donji Andrijevci를 여행하고 있다. 이 도시의 도로는 깊이가 K인 완전 이진 트리를 이루고 있다. 깊이가 K인 완전 이진 트리는 총 2K-1개의 노드로 이루어져 있다. (아래 그림) 각 노드에는 그 곳에 위치한 빌딩의 번호가 붙여져 있다. 또, 가장 마지막 레벨을 제외한 모든 집은 왼쪽 자식과 오른쪽 자식을 갖는다.
                1                               3
            2       3                   6               2
                                    1       4       5       7
                    깊이가 2와 3인 완전 이진 트리

        상근이는 도시에 있는 모든 빌딩에 들어갔고, 들어간 순서대로 번호를 종이에 적어 놓았다. 한국으로 돌아온 상근이는 도시가 어떻게 생겼는지 그림을 그려보려고 하였으나, 정확하게 기억이 나지 않아 실패했다. 하지만, 어떤 순서로 도시를 방문했는지 기억해냈다.
            1. 가장 처음에 상근이는 트리의 루트에 있는 빌딩 앞에 서있다.
            2. 현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동한다.
            3. 현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 현재 노드에 있는 빌딩을 들어가고 종이에 번호를 적는다.
            4. 현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동한다.
            5. 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동한다.

        왼쪽 그림에 나와있는 마을이라면, 상근이는 2-1-3 순서대로 빌딩을 들어갔을 것이고, 오른쪽 그림의 경우에는 1-6-4-3-5-2-7 순서로 들어갔을 것이다. 상근이가 종이에 적은 순서가 모두 주어졌을 때, 각 레벨에 있는 빌딩의 번호를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 K (1 ≤ K ≤ 10)가 주어진다.
        둘째 줄에는 상근이가 방문한 빌딩의 번호가 들어간 순서대로 주어진다. 모든 빌딩의 번호는 중복되지 않으며, 구간 [1,2K)에 포함된다.


    출력
        총 K개의 줄에 걸쳐서 정답을 출력한다. i번째 줄에는 레벨이 i인 빌딩의 번호를 출력한다. 출력은 왼쪽에서부터 오른쪽 순서대로 출력한다.


    예제 입력 1
        2
        2 1 3
    예제 출력 1
        1
        2 3

    예제 입력 2
        3
        1 6 4 3 5 2 7
    예제 출력 2
        3
        6 2
        1 4 5 7


    알고리즘 분류
        트리
        재귀
*/


// 메모리 : 16328KB
// 시간 : 212ms
// 코드 길이 : 1804B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9934 {
    static int K; // 깊이 K
    static int buildingNum[]; // 방문한 빌딩의 번호 배열
    static ArrayList<Integer> tree[]; // 깊이가 K인 완전 이진 트리

    public static void searchTree(int left, int right, int depth) { // 완전 이진 트리 중위 순회
        if (depth == K) {
            return;
        }

        int mid = (left + right) / 2; // 해당 깊이의 루트 노드
        tree[depth].add(buildingNum[mid]); // 해당 깊이의 노드로 추가

        searchTree(left, mid - 1, depth + 1); // 루트 노드를 기준으로 왼쪽 완전 이진 트리 순회
        searchTree(mid + 1, right, depth + 1); // 루트 노드를 기준으로 오른쪽 완전 이진 트리 순회
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        int nodeNum = (int) (Math.pow(2, K) - 1); // 깊이가 K인 완전 이진 트리의 총 노드의 개수 2^K - 1

        tree = new ArrayList[K];
        for (int k = 0; k < K; k++) {
            tree[k] = new ArrayList<>();
        }

        buildingNum = new int[nodeNum];
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int n = 0; n < nodeNum; n++) {
            buildingNum[n] = Integer.parseInt(token.nextToken());
        }

        searchTree(0, nodeNum, 0);
        for (ArrayList<Integer> nodes : tree) {
            for (int node : nodes) {
                System.out.print(node + " ");
            }

            System.out.println();
        }
    }
}
