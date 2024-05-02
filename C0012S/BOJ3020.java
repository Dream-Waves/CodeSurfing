/*
3020. Gold 5 - 개똥벌레

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           18417	    8105      5971	         45.000%


    문제
        개똥벌레 한 마리가 장애물(석순과 종유석)로 가득찬 동굴에 들어갔다. 동굴의 길이는 N미터이고, 높이는 H미터이다. (N은 짝수) 첫 번째 장애물은 항상 석순이고, 그 다음에는 종유석과 석순이 번갈아가면서 등장한다.

        아래 그림은 길이가 14미터이고 높이가 5미터인 동굴이다. (예제 그림)
            [그림은 문제에서 참고]

        이 개똥벌레는 장애물을 피하지 않는다. 자신이 지나갈 구간을 정한 다음 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다.

        위의 그림에서 4번째 구간으로 개똥벌레가 날아간다면 파괴해야하는 장애물의 수는 총 여덟개이다. (4번째 구간은 길이가 3인 석순과 길이가 4인 석순의 중간지점을 말한다)
            [그림은 문제에서 참고]

        하지만, 첫 번째 구간이나 다섯 번째 구간으로 날아간다면 개똥벌레는 장애물 일곱개만 파괴하면 된다.

        동굴의 크기와 높이, 모든 장애물의 크기가 주어진다. 이때, 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N과 H가 주어진다. N은 항상 짝수이다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000)
        다음 N개 줄에는 장애물의 크기가 순서대로 주어진다. 장애물의 크기는 H보다 작은 양수이다.


    출력
        첫째 줄에 개똥벌레가 파괴해야 하는 장애물의 최솟값과 그러한 구간의 수를 공백으로 구분하여 출력한다.


    예제 입력 1
        6 7
        1
        5
        3
        3
        5
        1
    예제 출력 1
        2 3

    예제 입력 2
        14 5
        1
        3
        4
        2
        2
        4
        3
        4
        3
        3
        3
        2
        3
        3
    예제 출력 2
        7 2


    알고리즘 분류
        이분 탐색
        누적 합
*/


// 메모리 : 38076KB
// 시간 : 624ms
// 코드 길이 : 2718B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3020 {
    static int N; // 동굴의 길이 (2 ≤ N ≤ 200000)
    static int H; // 동굴의 높이 (2 ≤ H ≤ 500000)
    static int caveBottom[]; // 석순의 장애물의 높이를 저장하는 배열
    static int caveTop[]; // 종유석의 장애물의 높이를 저장하는 배열

    public static int crash(int cave[], int all, int height) { // 이분 탐색을 통해 height 높이에서 개똥벌레가 파괴해야 하는 장애물의 수를 구하는 메서드
        int left = 0;
        int right = all;
        while (left < right) {
            int mid = (left + right) / 2;

            if (cave[mid] >= height) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return all - right;
    }

    public static void find() { // 개똥벌레가 파괴해야 하는 장애물의 최솟값과 그러한 구간의 수를 구하고 출력하는 메서드
        Arrays.sort(caveBottom);
        Arrays.sort(caveTop);

        int minValue = Integer.MAX_VALUE; // 개똥벌레가 파괴해야 하는 장애물의 최솟값
        int minNum = 0; // 개똥벌레가 파괴해야 하는 장애물의 최솟값을 가진 구간의 수
        for (int h = 1; h <= H; h++) {
            int barrier = crash(caveBottom, N / 2, h) + crash(caveTop, N / 2, H - h + 1); // h 높이에서 개똥벌레가 파괴해야 하는 장애물(석순, 종유석)의 수

            if (minValue == barrier) {
                minNum += 1;
            }
            else if (minValue > barrier) {
                minValue = barrier;
                minNum = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minValue);
        sb.append(" ");
        sb.append(minNum);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        H = Integer.parseInt(token.nextToken());

        caveBottom = new int[N / 2];
        caveTop = new int[N / 2];
        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(bf.readLine()); // H보다 작은 양수인 장애물의 크기
            if (i % 2 == 0) {
                caveBottom[i / 2] = size;
            }
            else {
                caveTop[i / 2] = size;
            }
        }

        find();
    }
}
