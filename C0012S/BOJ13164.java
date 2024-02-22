/*
13164. Gold 5 - 행복 유치원

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           8070	    4430      3639	         55.532%


    문제
        행복 유치원 원장인 태양이는 어느 날 N명의 원생들을 키 순서대로 일렬로 줄 세우고, 총 K개의 조로 나누려고 한다. 각 조에는 원생이 적어도 한 명 있어야 하며, 같은 조에 속한 원생들은 서로 인접해 있어야 한다. 조별로 인원수가 같을 필요는 없다.
        이렇게 나뉘어진 조들은 각자 단체 티셔츠를 맞추려고 한다. 조마다 티셔츠를 맞추는 비용은 조에서 가장 키가 큰 원생과 가장 키가 작은 원생의 키 차이만큼 든다. 최대한 비용을 아끼고 싶어 하는 태양이는 K개의 조에 대해 티셔츠 만드는 비용의 합을 최소로 하고 싶어한다. 태양이를 도와 최소의 비용을 구하자.


    입력
        입력의 첫 줄에는 유치원에 있는 원생의 수를 나타내는 자연수 N(1 ≤ N ≤ 300,000)과 나누려고 하는 조의 개수를 나타내는 자연수 K(1 ≤ K ≤ N)가 공백으로 구분되어 주어진다. 다음 줄에는 원생들의 키를 나타내는 N개의 자연수가 공백으로 구분되어 줄 서 있는 순서대로 주어진다. 태양이는 원생들을 키 순서대로 줄 세웠으므로, 왼쪽에 있는 원생이 오른쪽에 있는 원생보다 크지 않다. 원생의 키는 109를 넘지 않는 자연수이다.


    출력
        티셔츠 만드는 비용이 최소가 되도록 K개의 조로 나누었을 때, 티셔츠 만드는 비용을 출력한다.


    예제 입력 1
        5 3
        1 3 5 6 10
    예제 출력 1
        3


    힌트
        1조: 1, 3
        2조: 5, 6
        3조: 10


    알고리즘 분류
        그리디 알고리즘
        정렬
*/


// 메모리 : 52860KB
// 시간 : 636ms
// 코드 길이 : 1307B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken()); // 유치원에 있는 원생의 수 (1 ≤ N ≤ 300000)
        int K = Integer.parseInt(token.nextToken()); // 나누려고 하는 조의 개수 (1 ≤ K ≤ N)

        token = new StringTokenizer(bf.readLine());
        int diff[] = new int[N - 1]; // 인접해 있는 원생들의 키 차이를 저장하는 배열
        int front = Integer.parseInt(token.nextToken()); // 앞에 서 있는 원생의 키
        for (int i = 1; i < N; i++) {
            int back = Integer.parseInt(token.nextToken()); // 뒤에 서 있는 원생의 키

            diff[i - 1] = back - front;
            front = back;
        }

        Arrays.sort(diff); // 오름차순 정렬

        int minCost = 0; // 티셔츠 만드는 최소 비용
        for (int k = 0; k < N - K; k++) {
            minCost += diff[k];
        }

        System.out.println(minCost);
    }
}
