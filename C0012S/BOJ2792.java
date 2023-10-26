/*
2792. Silver 1 - 보석 상자

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           7708	    2890      2085	         38.222%


    문제
        보석 공장에서 보석 상자를 유치원에 기증했다. 각각의 보석은 M가지 서로 다른 색상 중 한 색상이다. 원장 선생님은 모든 보석을 N명의 학생들에게 나누어 주려고 한다. 이때, 보석을 받지 못하는 학생이 있어도 된다. 하지만, 학생은 항상 같은 색상의 보석만 가져간다.
        한 아이가 너무 많은 보석을 가져가게 되면, 다른 아이들이 질투를 한다. 원장 선생님은 이런 질투심을 수치화하는데 성공했는데, 질투심은 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수이다. 원장 선생님은 질투심이 최소가 되게 보석을 나누어 주려고 한다.
        상자에 빨간 보석이 4개 (RRRR), 파란 보석이 7개 (BBBBBBB) 있었고, 이 보석을 5명의 아이들에게 나누어 주는 경우를 생각해보자. RR, RR, BB, BB, BBB로 보석을 나누어주면 질투심은 3이 되고, 이 값보다 작게 나누어 줄 수 없다.
        상자 안의 보석 정보와 학생의 수가 주어졌을 때, 질투심이 최소가 되게 보석을 나누어주는 방법을 알아내는 프로그램을 작성하시오.


    입력
        첫째 줄에 아이들의 수 N과 색상의 수 M이 주어진다. (1 ≤ N ≤ 109, 1 ≤ M ≤ 300,000, M ≤ N)
        다음 M개 줄에는 구간 [1, 109]에 포함되는 양의 정수가 하나씩 주어진다. K번째 줄에 주어지는 숫자는 K번 색상 보석의 개수이다.


    출력
        첫째 줄에 질투심의 최솟값을 출력한다.


    예제 입력 1
        5 2
        7
        4
    예제 출력 1
        3

    예제 입력 2
        7 5
        7
        1
        7
        4
        4
    예제 출력 2
        4


    알고리즘 분류
        이분 탐색
        매개 변수 탐색
*/


// 메모리 : 34772KB
// 시간 : 388ms
// 코드 길이 : 1935B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2792 {
    static int N; // 아이들의 수 (1 ≤ N ≤ 109)
    static int M; // 색상의 수 (1 ≤ M ≤ 300,000, M ≤ N)
    static int color[];
    static int left = 1;
    static int right = 0;

    public static void binarySearch() {
        int jealousy = 0; // 질투심의 최솟값

        while (left <= right) {
            int mid = (left + right) / 2; // 아이들에게 보석을 나누어 줘서 생기는 질투심의 최솟값
            int sum = 0; // 질투심이 mid가 되기 위해 보석을 나눠 줘야 하는 최소 아이들의 수의 합

            for (int m = 0; m < M; m++) {
                if (color[m] % mid == 0) { // 질투심과 보석 개수가 같을 경우
                    sum += color[m] / mid;
                }
                else { // 질투심보다 보석 개수가 작을 경우
                    sum += color[m] / mid + 1;
                }
            }

            if (sum > N) {
                left = mid + 1;
            }
            else { // 아이들의 수 이내로 해당 질투심을 만들 수 있을 경우
                right = mid - 1;
                jealousy = mid;
            }
        }

        System.out.println(jealousy);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        color = new int[M];
        for (int m = 0; m < M; m++) {
            color[m] = Integer.parseInt(bf.readLine());
            right = Math.max(right, color[m]); // 이분 탐색 시 필요한 최대 범위
        }

        binarySearch();
    }
}
