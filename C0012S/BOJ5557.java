/*
5557. Gold 5 - 1학년

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           20116	    9303      7279	         46.298%


    문제
        상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다. 상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다. 예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.
        상근이는 올바른 등식을 만들려고 한다. 상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다. 따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다. 예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에, 상근이가 만들 수 없는 등식이다.
        숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100) 둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.


    출력
        첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다. 이 값은 263-1 이하이다.


    예제 입력 1
        11
        8 3 2 4 8 7 2 4 0 8 8
    예제 출력 1
        10

    예제 입력 2
        40
        1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1
    예제 출력 2
        7069052760


    힌트
        예제 1의 경우 다음과 같이 10가지 방법이 있다.
            8+3-2-4+8-7-2-4-0+8=8
            8+3-2-4+8-7-2-4+0+8=8
            8+3+2+4-8-7+2-4-0+8=8
            8+3+2+4-8-7+2-4+0+8=8
            8+3+2-4+8-7+2+4-0-8=8
            8+3+2-4+8-7+2+4+0-8=8
            8-3+2+4-8+7+2+4-0-8=8
            8-3+2+4-8+7+2+4+0-8=8
            8-3+2-4+8+7+2-4-0-8=8
            8-3+2-4+8+7+2-4+0-8=8


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14208KB
// 시간 : 128ms
// 코드 길이 : 1735B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    static int N; // 숫자의 개수 (3 ≤ N ≤ 100)
    static int number[]; // 상근이가 만들 등식에 사용할 숫자를 저장하는 배열

    public static void calculate() { // 상근이가 만들 수 있는 올바른 등식의 수를 구하는 메서드
        long possibleNumber[][] = new long[N][21]; // possibleNumber[n][k] : 배열 number의 인덱스 n의 수까지 이용해서 k를 만들 수 있는 경우의 수를 저장하는 배열

        possibleNumber[0][number[0]] = 1;
        for (int n = 1; n < N - 1; n++) {
            for (int k = 0; k <= 20; k++) {
                if (possibleNumber[n - 1][k] > 0) {
                    int plus = number[n] + k;
                    int minus = k - number[n];

                    if (plus >= 0 && plus <= 20) {
                        possibleNumber[n][plus] += possibleNumber[n - 1][k];
                    }
                    if (minus >= 0 && minus <= 20) {
                        possibleNumber[n][minus] += possibleNumber[n - 1][k];
                    }
                }
            }
        }

        System.out.println(possibleNumber[N - 2][number[N - 1]]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        number = new int[N];
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(token.nextToken());
        }

        calculate();
    }
}
