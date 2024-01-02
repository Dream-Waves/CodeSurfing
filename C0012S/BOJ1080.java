/*
1080. Silver 1 - 행렬

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           22002	    9268      7413	         41.974%


    문제
        0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
        행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)


    입력
        첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.


    출력
        첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.


    예제 입력 1
        3 4
        0000
        0010
        0000
        1001
        1011
        1001
    예제 출력 1
        2

    예제 입력 2
        3 3
        111
        111
        111
        000
        000
        000
    예제 출력 2
        1

    예제 입력 3
        1 1
        1
        0
    예제 출력 3
        -1

    예제 입력 4
        18 3
        001
        100
        100
        000
        011
        010
        100
        100
        010
        010
        010
        110
        101
        101
        000
        110
        000
        110
        001
        100
        011
        000
        100
        010
        011
        100
        101
        101
        010
        001
        010
        010
        111
        110
        111
        001
    예제 출력 4
        7


    알고리즘 분류
        그리디 알고리즘
*/


// 메모리 : 14160KB
// 시간 : 124ms
// 코드 길이 : 2466B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1080 {
    static int N; // 50보다 작거나 같은 자연수인 행의 개수
    static int M; // 50보다 작거나 같은 자연수인 열의 개수
    static int matrix[][][]; // 행렬을 저장하는 3 차원 배열  // matrix[0] : 행렬 A  // matrix[1] : 행렬 B
    static int count; // 행렬 A를 행렬 B로 바꾸는 데 필요한 연산의 횟수의 최솟값

    public static void reverse(int row, int column) { // 3 × 3 크기의 부분 행렬에 있는 모든 원소를 뒤집는 연산을 구현한 메서드
        for (int r = row; r < row + 3; r++) {
            for (int c = column; c < column + 3; c++) {
                matrix[0][r][c] = 1 - matrix[0][r][c];
            }
        }
    }

    public static void calculate() { // 행렬 A를 3 × 3 크기의 부분 행렬에 있는 모든 원소를 뒤집는 연산을 통해 행렬 B로 바꾸는 데 필요한 연산의 횟수의 최솟값을 계산하는 메서드
        for (int n = 0; n < N - 2; n++) {
            for (int m = 0; m < M - 2; m++) {
                if (matrix[0][n][m] != matrix[1][n][m]) {
                    reverse(n, m);
                    count += 1;
                }
            }
        }
    }

    public static void verify() { // 행렬 A를 행렬 B로 바꾸는 데 성공했는지 검증하는 메서드
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < M; l++) {
                if (matrix[0][k][l] != matrix[1][k][l]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        matrix = new int[2][N][M];
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < N; i++) {
                String matrixString = bf.readLine();

                for (int j = 0; j < M; j++) {
                    matrix[t][i][j] = matrixString.charAt(j) - '0';
                }
            }
        }

        calculate();
        verify();
    }
}
