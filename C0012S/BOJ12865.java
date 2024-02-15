/*
12865. Gold 5 - 평범한 배낭

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           125270	    46717     29942	         35.817%


    문제
        이 문제는 아주 평범한 배낭에 관한 문제이다.
        한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.
        준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.


    입력
        첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
        입력으로 주어지는 모든 수는 정수이다.


    출력
        한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.


    예제 입력 1
        4 7
        6 13
        4 8
        3 6
        5 12
    예제 출력 1
        14


    알고리즘 분류
        다이나믹 프로그래밍
        배낭 문제
*/


// 메모리 : 53692KB
// 시간 : 192ms
// 코드 길이 : 2203B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    static int N; // 준서가 여행에 필요하다고 생각하는 물건의 개수 (1 ≤ N ≤ 100)
    static int K; // 준서가 버틸 수 있는 무게 (1 ≤ K ≤ 100,000)
    static int object[][]; // 해당 인덱스의 물건에 대한 정보  // object[index][0] : 해당 인덱스의 물건의 무게, object[index][1] : 해당 인덱스의 물건의 가치
    static int bag[][]; // bag[n][k] : k 무게까지 담을 수 있는 배낭에 첫 번째부터 n 번째 물건을 사용하여 배낭에 담을 수 있는 가치의 최댓값

    public static void findMaxValue() { // K 무게까지의 물품을 넣을 수 있는 배낭의 물건들의 가치의 최댓값을 구하는 메서드
        // 초기의 bag[n][k] = bag[n - 1][k]
        // bag[n][k] = Math.max(bag[n][k], object[n][1] + bag[n - 1][k - object[n][0]])

        bag = new int[N + 1][K + 1];
        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                bag[n][k] = bag[n - 1][k];

                if (object[n][0] <= k) {
                    bag[n][k] = Math.max(bag[n][k], object[n][1] + bag[n - 1][k - object[n][0]]);
                }
            }
        }

        System.out.println(bag[N][K]); // 준서가 최대 K 무게까지의 물품을 넣을 수 있는 배낭의 물건들의 가치의 최댓값
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        object = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(bf.readLine());
            object[i][0] = Integer.parseInt(token.nextToken()); // 각 물건의 무게 W (1 ≤ W ≤ 100000)
            object[i][1] = Integer.parseInt(token.nextToken()); // 해당 물건의 가치 V (0 ≤ V ≤ 1000)
        }

        findMaxValue();
    }
}
