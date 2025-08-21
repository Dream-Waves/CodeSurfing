/*
22856. Gold 4 - 트리 순회

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    1024 MB          5607	    1814      1276	         30.866%


    문제
        노드가 N개인 이진 트리가 있다. 트리를 중위 순회와 유사하게 순회하려고 한다. 이를 유사 중위 순회라고 하자.
        순회의 시작은 트리의 루트이고 순회의 끝은 중위 순회할 때 마지막 노드이다. 이때 루트 노드는 항상 1번 노드이다.

        유사 중위 순회는 루트 노드에서 시작하며, 다음과 같이 진행된다.
            1. 현재 위치한 노드의 왼쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 왼쪽 자식 노드로 이동한다.
            2. 그렇지 않고 현재 위치한 노드의 오른쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 오른쪽 자식 노드로 이동한다.
            3. 그렇지 않고 현재 노드가 유사 중위 순회의 끝이라면, 유사 중위 순회를 종료한다.
            4. 그렇지 않고 부모 노드가 존재한다면, 부모 노드로 이동한다.
            5. 유사 중위 순회를 종료할 때까지 1 ~ 4를 반복한다.

            [그림은 문제에서 참고]
        위 그림에 있는 트리에서 중위 순회를 한다면 4 → 2 → 5 → 1 → 6 → 3 → 7 순으로 순회를 한다.

        따라서, 유사 중위 순회의 끝은 노드 7이 된다.

            [그림은 문제에서 참고]
        유사 중위 순회는 위 그림과 같이 루트인 노드 1에서 시작하여 노드 7에서 끝나고 1 → 2 → 4 → 2 → 5 → 2 → 1 → 3 → 6 → 3 → 7 이와 같은 순서로 순회를 진행한다. 유사 중위 순회를 진행하면서 총 10번 이동하였다.

        여기서 이동이라는 것은 하나의 노드에서 다른 노드로 한번 움직이는 것을 의미한다. 예를 들면, 노드 1에서 노드 2로 가는 것을 한번 이동하였다고 한다.
        유사 중위 순회를 하면서 이동한 횟수를 구하려고 한다.


    입력
        첫 번째 줄에 트리를 구성하는 노드의 개수 N이 주어진다.
        두 번째 줄부터 N + 1 번째 줄까지 현재 노드 a, 현재 노드의 왼쪽 자식 노드 b, 현재 노드의 오른쪽 자식 노드 c가 공백으로 구분되어 주어진다. 만약 자식 노드의 번호가 -1인 경우 자식 노드가 없다는 것을 의미한다.


    출력
        유사 중위 순회를 하면서 이동한 총 횟수를 출력한다.


    제한
        · 1 ≤ N ≤ 100,000
        · 1 ≤ a, b ≤ N


    예제 입력 1
        7
        1 2 3
        2 4 5
        3 6 7
        4 -1 -1
        5 -1 -1
        6 -1 -1
        7 -1 -1
    예제 출력 1
        10
    1 → 2 → 4 → 2 → 5 → 2 → 1 → 3 → 6 → 3 → 7

    예제 입력 2
        1
        1 -1 -1
    예제 출력 2
        0


    알고리즘 분류
        그래프 이론
        그래프 탐색
        트리
*/


// 메모리 : 61152KB
// 시간 : 420ms
// 코드 길이 : 2459B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ22856 {
    static int N; // 트리를 구성하는 노드의 개수
    static ArrayList<Integer>[] tree; // 트리 정보를 저장하는 배열
    static int allCount; // 유사 중위 순회를 수행하며 다시 루트 노드로 돌아올 경우의 이동 횟수
    static int rightCount; // 루트 노드부터 마지막 오른쪽 노드까지의 이동 횟수

    public static void calculateRightNodeCount() { // 루트 노드부터 마지막 오른쪽 노드까지의 이동 횟수를 구하는 메서드
        for (int r = 1; tree[r].get(1) != -1; r = tree[r].get(1)) {
            rightCount += 1;
        }
    }

    public static void similarInOrder(int node) { // 유사 중위 순회를 수행하며 다시 루트 노드로 돌아올 경우의 이동 횟수를 구하는 메서드
        if (tree[node].get(0) != -1) { // 왼쪽 자식 노드가 있을 경우
            allCount += 1; // 왼쪽 자식 노드 탐색 횟수 추가
            similarInOrder(tree[node].get(0));
            allCount += 1; // 현재 노드로 돌아오는 횟수 추가
        }

        if (tree[node].get(1) != -1) { // 오른쪽 자식 노드가 있을 경우
            allCount += 1; // 오른쪽 자식 노드 탐색 횟수 추가
            similarInOrder(tree[node].get(1));
            allCount += 1; // 현재 노드로 돌아오는 횟수 추가
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer token;
        for (int n = 0; n < N; n++) {
            token = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(token.nextToken()); // 현재 노드
            int b = Integer.parseInt(token.nextToken()); // 현재 노드의 왼쪽 자식 노드
            int c = Integer.parseInt(token.nextToken()); // 현재 노드의 오른쪽 자식 노드

            tree[a].add(b);
            tree[a].add(c);
        }

        calculateRightNodeCount();
        similarInOrder(1);

        System.out.println(allCount - rightCount);
    }
}
