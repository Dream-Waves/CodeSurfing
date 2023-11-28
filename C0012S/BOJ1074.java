/*
1074. Silver 1 - Z

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    0.5 초 (추가 시간 없음)	    512 MB           73463	    29443     22014	         40.659%


    문제
        한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
            0   1
            2   3

        N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.

        다음 예는 22 × 22 크기의 배열을 방문한 순서이다.
            0   1   4   5
            2   3   6   7
            8   9   12  13
            10  11  14  15

        N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.

        다음은 N=3일 때의 예이다.
            0   1   4   5   16  17  20  21
            2   3   6   7   18  19  22  23
            8   9   12  13  24  25  28  29
            10  11  14  15  26  27  30  31
            32  33  36  37  48  49  52  53
            34  35  38  39  50  51  54  55
            40  41  44  45  56  57  60  61
            42  43  46  47  58  59  62  63


    입력
        첫째 줄에 정수 N, r, c가 주어진다.


    출력
        r행 c열을 몇 번째로 방문했는지 출력한다.


    제한
        · 1 ≤ N ≤ 15
        · 0 ≤ r, c < 2N


    예제 입력 1
        2 3 1
    예제 출력 1
        11

    예제 입력 2
        3 7 7
    예제 출력 2
        63

    예제 입력 3
        1 0 0
    예제 출력 3
        0

    예제 입력 4
        4 7 7
    예제 출력 4
        63

    예제 입력 5
        10 511 511
    예제 출력 5
        262143

    예제 입력 6
        10 512 512
    예제 출력 6
        786432


    알고리즘 분류
        분할 정복
        재귀
*/


// 메모리 : 14288KB
// 시간 : 128ms
// 코드 길이 : 1597B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
    static int order; // r행 c열을 방문한 순서

    static void zSearch(int size, int row, int column) {
        if (size == 1) {
            System.out.println(order);
            return;
        }

        int midSize = size / 2;

        if (row < midSize && column < midSize) { // 제 1 사분면
            zSearch(midSize, row, column);
        }
        else if (row >= midSize && column < midSize) { // 제 3 사분면
            order += midSize * midSize * 2;
            zSearch(midSize, row - midSize, column);
        }
        else if (row < midSize && column < size) { // 제 2 사분면  // column >= midSize
            order += midSize * midSize;
            zSearch(midSize, row, column - midSize);
        }
        else if (row >= midSize && column < size) { // 제 4 사분면  // column >= midSize
            order += midSize * midSize * 3;
            zSearch(midSize, row - midSize, column - midSize);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken());
        int r = Integer.parseInt(token.nextToken()); // 행
        int c = Integer.parseInt(token.nextToken()); // 열

        int mapSize = (int) Math.pow(2, N); // 한 변의 길이

        zSearch(mapSize, r, c);
    }
}
