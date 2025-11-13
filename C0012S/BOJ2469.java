/*
2469. Gold 5 - 사다리 타기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           3946	    1609      1281	         40.796%


    문제
        k명의 참가자들이 사다리 타기를 통하여 어떤 순서를 결정한다. 참가자들은 알파벳 대문자 첫 k개로 표현되며, 사다리 타기를 시작할 때의 순서는 아래 그림과 같이 항상 알파벳 순서대로이다.

        k=10 인 예를 들어 보자. 10명의 A, B, C, D, E, F, G, H, I, J 참가자들이 사다리 타기를 준비한다. 아래 그림은 10개의 세로 줄과 5개의 가로 줄을 가지고 있는 사다리의 한 예를 보여주고 있다.
            [그림은 문제에서 참고]

        이 사다리에서 점선은 가로 막대가 없음을, 굵은 가로 실선은 옆으로 건너갈 수 있는 가로 막대가 있음을 나타내고 있다.
        따라서 위에 제시된 사다리를 타면 그 최종 도달된 순서는 왼쪽으로부터 A, C, G, B, E, D, J, F, I, H 가 된다.

        사다리 타기는 세로 막대를 타고 내려오는 중에 가로막대를 만나면 그 쪽으로 옮겨 가면서 끝까지 내려가는 과정이다.  따라서 사다리 타기의 규칙 특성상 아래 그림과 같이 두 가로 막대가 직접 연결될 수는 없으므로 이 상황은 이 문제에서 고려할 필요가 없다.
            [그림은 문제에서 참고]

        우리는 하나의 가로 줄이 감추어진 사다리를 받아서 그 줄의 각 칸에 가로 막대를 적절히 넣어서 참가자들의 최종 순서가 원하는 순서대로 나오도록 만들려고 한다.
        입력에서 사다리의 전체 모양은 각 줄에 있는 가로 막대의 유무로 표현된다. 각 줄에서 가로 막대가 없는 경우에는 ‘*’(별)문자, 있을 경우에는 ‘-’(빼기) 문자로 표시된다. 그리고 감추어진 특정 가로 줄은 길이 k-1인 ‘?’ (물음표) 문자열로 표시되어 있다.


    입력
        첫 줄에는 참가한 사람의 수 k가 나온다(3 ≤ k ≤ 26). 그 다음 줄에는 가로 막대가 놓일 전체 가로 줄의 수를 나타내는 n이 나온다(3 ≤ n ≤ 1,000). 그리고 세 번째 줄에는 사다리를 타고 난 후 결정된 참가자들의 최종 순서가 길이 k인 대문자 문자열로 들어온다.
        k와 n, 그리고 도착순서 문자열이 나타난 다음, 이어지는 n개의 줄에는 앞서 설명한 바와 같이 ‘*’와 ‘-’ 문자로 이루어진 길이 k-1인 문자열이 주어진다. 그 중 감추어진 가로 줄은 길이가 k-1인 ‘?’ 문자열로 표시되어 있다.


    출력
        입력 파일 세 번째 줄에서 지정한 도착순서가 해당 사다리에서 만들어질 수 있도록 감추어진 가로 줄을 구성해야 한다.
        여러분은 감추어진 가로 줄의 상태를 재구성하여 이를 ‘*’(별) 문자와 ‘-’(빼기) 문자로 구성된 길이 k-1인 문자열로 만들어 출력하면 된다.
        그런데 어떤 경우에는 그 감추어진 가로 줄을 어떻게 구성해도 원하는 순서를 얻을 수 없는 경우도 있다.  이 경우에는  ‘x’(소문자 엑스)로 구성된 길이 k-1인 문자열을 출력해야 한다.


    예제 입력 1
        10
        5
        ACGBEDJFIH
        *-***-***
        -*-******
        ?????????
        -**-***-*
        **-*-*-*-
    예제 출력 1
        **-*-***-

    예제 입력 2
        11
        5
        CGBEDJFKIHA
        *-***-****
        -*-******-
        ??????????
        -**-***-*-
        **-*-*-*-*
    예제 출력 2
        xxxxxxxxxx


    알고리즘 분류
        구현
        문자열
*/


// 메모리 : 14628KB
// 시간 : 112ms
// 코드 길이 : 4272B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2469 {
    static int K; // 참가한 사람의 수 (3 ≤ K ≤ 26)
    static int N; // 가로 막대가 놓일 전체 가로 줄의 수 (3 ≤ N ≤ 1000)
    static String result; // 사다리를 타고 난 후 결정된 참가자들의 최종 순서
    static char[][] ladder; // 사다리 정보를 저장하는 배열
    static int hidden; // 사다리의 감추어진 줄의 인덱스
    static char[] top; // 사다리 타기 시작 순서의 참가자들 정보를 바탕으로 사다리의 시작부터 감추어진 줄까지 사다리를 탄 결과를 저장하는 배열
    static char[] bottom; // 사다리 타기의 결과 순서의 참가자들 정보를 바탕으로 사디리의 끝부터 감추어진 줄까지 사다리를 탄 결과를 저장하는 배열
    static char[] answer; // 사다리의 감추어진 줄의 구성을 저장하는 배열

    public static void swap(char[] array, int index1, int index2) { // 배열의 두 원소를 교환하는 메서드
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void moveTop() { // 사다리의 시작부터 감추어진 줄까지 사다리를 타는 메서드
        for (int r = 0; r < hidden; r++) {
            for (int c = 1; c < K; c++) {
                if (ladder[r][c] == '-') { // 가로 막대가 있을 경우
                    swap(top, c, c - 1);
                }
            }
        }
    }

    public static void moveBottom() { // 사디리의 끝부터 감추어진 줄까지 사다리를 타는 메서드
        for (int r = N - 1; r > hidden; r--) {
            for (int c = 1; c < K; c++) {
                if (ladder[r][c] == '-') { // 가로 막대가 있을 경우
                    swap(bottom, c, c - 1);
                }
            }
        }
    }

    public static void compare() { // 사다리의 시작부터 감추어진 줄까지 사다리를 탄 결과와 사디리의 끝부터 감추어진 줄까지 사다리를 탄 결과를 비교하여 사다리의 감추어진 줄의 구성을 구하는 메서드
        answer = new char[K - 1];

        for (int k = 0; k < K - 1; k++) {
            if (top[k] == bottom[k]) { // k 번째 원소의 값이 같을 경우
                answer[k] = '*'; // 가로 막대가 없다.
            }
            else {
                if ((top[k] == bottom[k + 1]) && (top[k + 1] == bottom[k])) { // k 번째 원소와 (k + 1) 번째 원소를 교환했을 때 값이 같을 경우
                    answer[k] = '-'; // 가로 막대가 있다.

                    swap(top, k + 1, k); // 사다리의 시작부터 감추어진 줄까지 사다리를 탄 결과에서 k 번째 원소와 (k + 1) 번째 원소 교환
                }
                else { // 원하는 사다리를 타고 난 후 결정된 참가자들의 최종 순서를 얻을 수 없는 경우
                    Arrays.fill(answer, 'x');

                    break;
                }
            }
        }
    }

    public static void print() { // 사다리의 감추어진 줄의 구성을 출력하는 메서드
        StringBuilder sb = new StringBuilder();

        for (int p = 0; p < K - 1; p++) {
            sb.append(answer[p]);
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        result = bf.readLine();

        ladder = new char[N][K + 1];
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();

            if (str.charAt(0) == '?') {
                hidden = i;
            }

            for (int j = 1; j < K; j++) {
                ladder[i][j] = str.charAt(j - 1);
            }
        }

        top = new char[K];
        for (int l = 0; l < K; l++) {
            top[l] = (char) ('A' + l);
        }

        bottom = result.toCharArray();

        moveTop();
        moveBottom();
        compare();
        print();
    }
}
