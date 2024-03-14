/*
14719. Gold 5 - 빗물

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           18342	    10357     8092	         56.627%


    문제
        2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.
            [그림은 문제에서 참고]

        비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?


    입력
        첫 번째 줄에는 2차원 세계의 세로 길이 H과 2차원 세계의 가로 길이 W가 주어진다. (1 ≤ H, W ≤ 500)
        두 번째 줄에는 블록이 쌓인 높이를 의미하는 0이상 H이하의 정수가 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.
        따라서 블록 내부의 빈 공간이 생길 수 없다. 또 2차원 세계의 바닥은 항상 막혀있다고 가정하여도 좋다.


    출력
        2차원 세계에서는 한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라.
        빗물이 전혀 고이지 않을 경우 0을 출력하여라.


    예제 입력 1
        4 4
        3 0 1 4
    예제 출력 1
        5

    예제 입력 2
        4 8
        3 1 2 3 4 1 1 2
    예제 출력 2
        5

    예제 입력 3
        3 5
        0 0 0 2 0
    예제 출력 3
        0


    힌트
        힌트 1:
            [그림은 문제에서 참고]

        힌트 2:
            [그림은 문제에서 참고]

        힌트 3:
            [그림은 문제에서 참고]


    알고리즘 분류
        구현
        시뮬레이션
*/


// 메모리 : 14256KB
// 시간 : 132ms
// 코드 길이 : 2083B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
    static int W; // 2 차원 세계의 가로 길이 (1 ≤ W ≤ 500)
    static int world[]; // 2 차원 세계에 쌓인 블록의 높이를 저장하는 배열

    public static void calculate() { // 고이는 빗물의 총량을 구하는 메서드
        int rainSum = 0; // 고이는 빗물의 총량
        int left = world[0]; // 현재 블록을 기준으로 왼쪽에 있는 블록의 높이 중 가장 높은 높이
        for (int c = 1; c < W - 1; c++) {
            int right = world[c]; // 현재 블록을 기준으로 오른쪽에 있는 블록의 높이 중 가장 높은 높이

            // 현재 블록을 기준으로 왼쪽에 있는 블록의 높이 중 가장 높은 높이 구하기
            left = Math.max(left, world[c]);

            // 현재 블록을 기준으로 오른쪽에 있는 블록의 높이 중 가장 높은 높이 구하기
            for (int r = c + 1; r < W; r++) {
                right = Math.max(right, world[r]);
            }

            int block = Math.min(left, right);
            if (block > world[c]) { // 현재 블록을 기준으로 왼쪽과 오른쪽에 있는 블록의 높이 중 낮은 높이가 현재 블록의 높이보다 높을 경우
                rainSum = rainSum + (block - world[c]);
            }
        }

        System.out.println(rainSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int H = Integer.parseInt(token.nextToken()); // 2 차원 세계의 세로 길이 (1 ≤ H ≤ 500)
        W = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(bf.readLine());
        world = new int[W];
        for (int w = 0; w < W; w++) {
            world[w] = Integer.parseInt(token.nextToken());
        }

        calculate();
    }
}
