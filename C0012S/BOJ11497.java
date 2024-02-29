/*
11497. Silver 1 - 통나무 건너뛰기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           9469	    5564      4471	         59.709%


    문제
        남규는 통나무를 세워 놓고 건너뛰기를 좋아한다. 그래서 N개의 통나무를 원형으로 세워 놓고 뛰어놀려고 한다. 남규는 원형으로 인접한 옆 통나무로 건너뛰는데, 이때 각 인접한 통나무의 높이 차가 최소가 되게 하려 한다.
            [그림은 문제에서 참고]

        통나무 건너뛰기의 난이도는 인접한 두 통나무 간의 높이의 차의 최댓값으로 결정된다. 높이가 {2, 4, 5, 7, 9}인 통나무들을 세우려 한다고 가정하자. 이를 [2, 9, 7, 4, 5]의 순서로 세웠다면, 가장 첫 통나무와 가장 마지막 통나무 역시 인접해 있다. 즉, 높이가 2인 것과 높이가 5인 것도 서로 인접해 있다. 배열 [2, 9, 7, 4, 5]의 난이도는 |2-9| = 7이다. 우리는 더 나은 배열 [2, 5, 9, 7, 4]를 만들 수 있으며 이 배열의 난이도는 |5-9| = 4이다. 이 배열보다 난이도가 낮은 배열은 만들 수 없으므로 이 배열이 남규가 찾는 답이 된다.


    입력
        입력은 T개의 테스트 케이스로 이루어져 있다. 첫 줄에 T가 주어진다.
        이어지는 각 줄마다 첫 줄에 통나무의 개수를 나타내는 정수 N(5 ≤ N ≤ 10,000), 둘째 줄에 각 통나무의 높이를 나타내는 정수 L_i가 주어진다. (1 ≤ L_i ≤ 100,000)


    출력
        각 테스트 케이스마다 한 줄에 주어진 통나무들로 만들 수 있는 최소 난이도를 출력하시오.


    예제 입력 1
        3
        7
        13 10 12 11 10 11 12
        5
        2 4 5 7 9
        8
        6 6 6 6 6 6 6 6
    예제 출력 1
        1
        4
        0


    알고리즘 분류
        그리디 알고리즘
        정렬
*/


// 메모리 : 52140KB
// 시간 : 596ms
// 코드 길이 : 2644B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11497 {
    static int N; // 통나무의 개수 (5 ≤ N ≤ 10000)
    static PriorityQueue<Integer> L; // 통나무의 높이 (1 ≤ L_i ≤ 100000)
    static int tree[]; // 통나무 건너뛰기의 난이도가 최소 난이도인 통나무 정보를 저장하는 배열

    public static void make() { // 최소 난이도를 가진 통나무 배열을 만드는 메서드
        int index = N / 2; // 통나무 개수의 절반
        tree[index] = L.poll(); // 배열의 가운데에 가장 높은 통나무의 높이 저장

        int leftIndex = index; // 배열의 가운데를 기준으로 왼쪽에 존재하는 인덱스
        int rightIndex = index; // 배열의 가운데를 기준으로 오른쪽에 존재하는 인덱스
        int minLevel = Integer.MIN_VALUE; // 통나무 건너뛰기의 최소 난이도 : 인접한 두 통나무 간의 높이의 차의 최댓값
        while (!L.isEmpty()) {
            // 배열의 가운데에서부터 양옆의 인덱스에 통나무의 높이 저장
            if (leftIndex > 0) {
                leftIndex -= 1;
                tree[leftIndex] = L.poll();
            }

            if (rightIndex < N - 1) {
                rightIndex += 1;
                tree[rightIndex] = L.poll();
            }

            // 인접한 두 통나무 간의 높이의 차와 통나무 건너뛰기의 최소 난이도(인접한 두 통나무 간의 높이의 차의 최댓값) 구하기
            minLevel = Math.max(minLevel, Math.abs(tree[leftIndex] - tree[leftIndex + 1]));
            minLevel = Math.max(minLevel, Math.abs(tree[rightIndex] - tree[rightIndex - 1]));
        }

        System.out.println(minLevel);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 개수

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(bf.readLine());

            L = new PriorityQueue<>(Comparator.reverseOrder()); // 우선 순위 큐를 만들어서 통나무의 높이가 높은 순으로 통나무의 높이를 저장
            StringTokenizer token = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                L.offer(Integer.parseInt(token.nextToken()));
            }

            tree = new int[N];
            make();
        }
    }
}
