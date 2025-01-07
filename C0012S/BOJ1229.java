/*
1229. Gold 4 - 육각수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           1877	    827       584	         45.948%


    문제
        육각수는 육각형을 이용해 정의할 수 있다. h_n은 한 변에 점 1, 2, ..., n개가 있는 육각형을 점 하나만 겹치게 그렸을 때 존재하는 서로 다른 점의 개수이다.
            [그림은 문제에서 참고]
                  그림 1

        그림1은 h_1, h_2, h_3, h_4를 의미하며, 처음 육각수 6개는 1, 6, 15, 28, 45, 66이다.

        자연수 N이 주어졌을 때, 합이 N이 되는 육각수 개수의 최솟값을 구해보자.
            N	    최소 개수	    합
            1	    1	        1
            2	    2	        1+1
            3	    3	        1+1+1
            4	    4	        1+1+1+1
            5	    5	        1+1+1+1+1
            6	    1	        6
            7	    2	        1+6
            8	    3	        1+1+6
            9	    4	        1+1+1+6
            10	    5	        1+1+1+1+6
            11	    6	        1+1+1+1+1+6
            12	    2	        6+6

        1791보다 큰 정수는 항상 육각수 4개의 합으로 만들 수 있다. 또한, 수가 충분히 크다면 항상 육각수 3개의 합으로 만들 수 있다. 또, 최소 개수는 항상 6 이하이고, 이것이 최소인 N은 11과 26밖에 없다. 답이 6인 가장 큰 N은 26, 5인 가장 큰 N은 130, 4인 가장 큰 N은 146858이다.


    입력
        첫째 줄에 N이 주어진다.


    출력
        첫째 줄에 N을 만들기 위해 필요한 육각수 개수의 최솟값을 출력한다.


    제한
        · 1 ≤ N ≤ 1,000,000


    예제 입력 1
        26
    예제 출력 1
        6

        1+1+6+6+6+6

    예제 입력 2
        130
    예제 출력 2
        5

        1+28+28+28+45

    예제 입력 3
        146858
    예제 출력 3
        4

        1+1+1326+145530

    예제 입력 4
        999999
    예제 출력 4
        3

        6+258840+741153

    예제 입력 5
        1000000
    예제 출력 5
        2

        285390+714610

    예제 입력 6
        145530
    예제 출력 6
        1

        145530은 269번째 육각수이다.


    알고리즘 분류
        수학
        다이나믹 프로그래밍
        런타임 전의 전처리
*/


// 메모리 : 49460KB
// 시간 : 2852ms
// 코드 길이 : 1827B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1229 {
    static int N; // 육각수를 이용하여 만들고자 하는 수
    static ArrayList<Integer> sixAngleNumber; // 육각수를 저장하는 리스트
    static int[] count; // 각 인덱스의 수를 만들기 위해 필요한 육각수 개수의 최솟값을 저장하는 배열

    public static void calculate() { // N보다 작은 육각수를 구하는 메서드
        sixAngleNumber = new ArrayList<>();

        int n = 1; // 한 변에 있는 점의 개수
        int nowSixAngleNumber = 1; // 한 변에 점이 n 개가 있는 육각형을 이용하여 구한 육각수
        while (nowSixAngleNumber < N) {
            nowSixAngleNumber = n * (2 * n - 1);
            sixAngleNumber.add(nowSixAngleNumber);
            n += 1;
        }
    }

    public static void find() { // N을 만들기 위해 필요한 육각수 개수의 최솟값을 구하고 출력하는 메서드
        count = new int[N + 1];

        count[1] = 1;
        for (int s = 2; s <= N; s++) {
            int minValue = Integer.MAX_VALUE;

            for (int number : sixAngleNumber) {
                if (number <= s) { // 육각수 리스트에 속한 육각수가 육각수를 이용하여 만들고자 하는 수 s보다 작거나 같을 경우
                    minValue = Math.min(minValue, count[s - number]);
                }
            }

            count[s] = minValue + 1;
        }

        System.out.println(count[N]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        calculate();
        find();
    }
}
