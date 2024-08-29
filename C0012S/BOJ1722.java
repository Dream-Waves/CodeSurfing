/*
1722. Gold 5 - 순열의 순서

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           14867	    4357      3379	         33.512%


    문제
        1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지가 있다.
        임의의 순열은 정렬을 할 수 있다. 예를 들어  N=3인 경우 {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}의 순서로 생각할 수 있다. 첫 번째 수가 작은 것이 순서상에서 앞서며, 첫 번째 수가 같으면 두 번째 수가 작은 것이, 두 번째 수도 같으면 세 번째 수가 작은 것이….
        N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다. k가 주어지면 k번째 순열을 구하고, 임의의 순열이 주어지면 이 순열이 몇 번째 순열인지를 출력하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N(1 ≤ N ≤ 20)이 주어진다. 둘째 줄의 첫 번째 수는 소문제 번호이다. 1인 경우 k(1 ≤ k ≤ N!)를 입력받고, 2인 경우 임의의 순열을 나타내는 N개의 수를 입력받는다. N개의 수에는 1부터 N까지의 정수가 한 번씩만 나타난다.


    출력
        k번째 수열을 나타내는 N개의 수를 출력하거나, 몇 번째 수열인지를 출력하면 된다.


    예제 입력 1
        4
        1 3
    예제 출력 1
        1 3 2 4

    예제 입력 2
        4
        2 1 3 2 4
    예제 출력 2
        3


    알고리즘 분류
        수학
        구현
        조합론
*/


// 메모리 : 14372B
// 시간 : 100ms
// 코드 길이 : 2820B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1722 {
    static int N; // 임의의 수 (1 ≤ N ≤ 20)
    static String problemString; // 소문제 번호에 따라 문제에 주어지는 순열의 순서 k 또는 순열의 정보 (1 ≤ k ≤ N!)
    static long factorial[]; // 인덱스에 해당하는 수로 만들 수 있는 순열의 경우의 수 저장
    static boolean isSelected[]; // 수의 선택 여부를 저장하는 배열
    static int selection[]; // 순열을 저장하는 배열

    public static void initFactorial() { // 팩토리얼을 초기화하는 메서드
        factorial = new long[21];
        factorial[0] = 1;
        for (int f = 1; f <= N; f++) {
            factorial[f] = factorial[f - 1] * f;
        }
    }

    public static void findPermutation() { // k 번째 순열을 구하는 메서드
        long k = Long.parseLong(problemString); // 순열의 순서 (1 ≤ k ≤ N!)
        for (int f = 0; f < N; f++) {
            for (int n = 1; n <= N; n++) {
                if (!isSelected[n]) {
                    if (factorial[N - f - 1] < k) {
                        k -= factorial[N - f - 1];
                    }
                    else {
                        selection[f] = n;
                        isSelected[n] = true;

                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int s = 0; s < N; s++) {
            sb.append(selection[s]);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void findOrder() { // 주어진 순열의 순서를 구하는 메서드
        int index = 0;
        for (String numberStr : problemString.split(" ")) {
            selection[index++] = Integer.parseInt(numberStr);
        }

        long order = 1;
        for (int f = 0; f < N; f++) {
            for (int n = 1; n < selection[f]; n++) {
                if (!isSelected[n]) {
                    order += factorial[N - f - 1];
                }
            }

            isSelected[selection[f]] = true;
        }

        System.out.println(order);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        String str = bf.readLine();
        int problemNumber = str.charAt(0) - '0'; // 소문제 번호
        problemString = str.substring(2);

        initFactorial();

        isSelected = new boolean[21];
        selection = new int[21];
        if (problemNumber == 1) {
            findPermutation();
        }
        else if (problemNumber == 2) {
            findOrder();
        }
    }
}
