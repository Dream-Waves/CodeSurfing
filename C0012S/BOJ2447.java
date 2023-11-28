/*
2447. Gold 5 - 별 찍기 - 10

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           70798	    39003     29121	         55.012%


    문제
        재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.

        크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
            ***
            * *
            ***

        N이 3보다 클 경우, 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이다. 예를 들어 크기 27의 패턴은 예제 출력 1과 같다.


    입력
        첫째 줄에 N이 주어진다. N은 3의 거듭제곱이다. 즉 어떤 정수 k에 대해 N=3k이며, 이때 1 ≤ k < 8이다.


    출력
        첫째 줄부터 N번째 줄까지 별을 출력한다.


    예제 입력 1
        27
    예제 출력 1
        ***************************
        * ** ** ** ** ** ** ** ** *
        ***************************
        ***   ******   ******   ***
        * *   * ** *   * ** *   * *
        ***   ******   ******   ***
        ***************************
        * ** ** ** ** ** ** ** ** *
        ***************************
        *********         *********
        * ** ** *         * ** ** *
        *********         *********
        ***   ***         ***   ***
        * *   * *         * *   * *
        ***   ***         ***   ***
        *********         *********
        * ** ** *         * ** ** *
        *********         *********
        ***************************
        * ** ** ** ** ** ** ** ** *
        ***************************
        ***   ******   ******   ***
        * *   * ** *   * ** *   * *
        ***   ******   ******   ***
        ***************************
        * ** ** ** ** ** ** ** ** *
        ***************************


    알고리즘 분류
        분할 정복
        재귀
*/


// 메모리 : 58484KB
// 시간 : 384ms
// 코드 길이 : 1607B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2447 {
    static int N; // N = 3^k (1 ≤ k < 8)
    static String star[][]; // 재귀적인 패턴으로 찍은 별을 저장하는 2 차원 배열
    static StringBuilder sb;

    public static void drawStar(int x, int y, int size) { // 2 차원 배열에 별을 찍는 메서드
        if (size == 1) {
            star[x][y] = "*";
            return;
        }

        int newSize = size / 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (r == 1 && c == 1) {
                    continue;
                }

                drawStar(x + r * newSize, y + c * newSize, newSize);
            }
        }
    }

    public static void printStar() { // 2 차원 배열에 찍은 별을 출력하는 메서드
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (star[i][j] == null) { // 공백을 출력하는 부분일 경우
                    sb.append(" ");
                }
                else {
                    sb.append(star[i][j]);
                }
            }

            sb.append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine()); // 한 변의 길이

        star = new String[N][N];
        drawStar(0, 0, N);

        sb = new StringBuilder();
        printStar();
        System.out.println(sb);
    }
}
