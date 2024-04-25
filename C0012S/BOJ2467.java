/*
2467. Gold 5 - 용액

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           37399	    14179     11001	         37.012%


    문제
        KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다. 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다. 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
        같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다. 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
        예를 들어, 주어진 용액들의 특성값이 [-99, -2, -1, 4, 98]인 경우에는 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액의 특성값이 0에 가장 가까운 용액이다. 참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
        산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.


    입력
        첫째 줄에는 전체 용액의 수 N이 입력된다. N은 2 이상 100,000 이하의 정수이다. 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 오름차순으로 입력되며, 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다. N개의 용액들의 특성값은 모두 서로 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.


    출력
        첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다. 출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다. 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.


    예제 입력 1
        5
        -99 -2 -1 4 98
    예제 출력 1
        -99 98

    예제 입력 2
        4
        -100 -2 -1 103
    예제 출력 2
        -2 -1


    알고리즘 분류
        이분 탐색
        두 포인터
*/


// 메모리 : 28484KB
// 시간 : 384ms
// 코드 길이 : 2523B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {
    static int N; // 전체 용액의 수 (2 ≤ N ≤ 100000)
    static long value[]; // 용액의 특성값을 저장하는 배열
    static StringBuilder sb;

    public static void find() { // 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 찾아내는 메서드
        long solutionValue1 = value[0]; // 특성값이 0에 가장 가까운 용액을 만들어내는 한 용액의 특성값
        long solutionValue2 = value[N - 1]; // 특성값이 0에 가장 가까운 용액을 만들어내는 다른 한 용액의 특성값
        long nearValue = solutionValue1 + solutionValue2; // 특성값이 0에 가장 가까운 용액의 특성값

        for (int n = 0, size = N - 1; n < size; n++) { // 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액 중 하나의 용액 선택
            int left = n + 1;
            int right = N - 1;

            while (left <= right) { // 이분 탐색을 이용하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액 중 다른 하나의 용액 선택
                int mid = (left + right) / 2;
                long sum = value[n] + value[mid];

                if (sum == 0) {
                    sb.append(value[n]);
                    sb.append(" ");
                    sb.append(value[mid]);

                    return;
                }
                if (Math.abs(nearValue) > Math.abs(sum)) {
                    solutionValue1 = value[n];
                    solutionValue2 = value[mid];
                    nearValue = sum;
                }

                if (sum < 0) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        sb.append(solutionValue1);
        sb.append(" ");
        sb.append(solutionValue2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        value = new long[N];
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(token.nextToken());
        }

        sb = new StringBuilder();
        find();

        System.out.println(sb);
    }
}
