/*
2240. Gold 5 - 자두나무

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           18129	    6747      4896	         38.497%


    문제
        자두는 자두를 좋아한다. 그래서 집에 자두나무를 심어두고, 여기서 열리는 자두를 먹고는 한다. 하지만 자두는 키가 작아서 자두를 따먹지는 못하고, 자두가 떨어질 때까지 기다린 다음에 떨어지는 자두를 받아서 먹고는 한다. 자두를 잡을 때에는 자두가 허공에 있을 때 잡아야 하는데, 이는 자두가 말랑말랑하여 바닥에 떨어지면 못 먹을 정도로 뭉개지기 때문이다.
        매 초마다, 두 개의 나무 중 하나의 나무에서 열매가 떨어지게 된다. 만약 열매가 떨어지는 순간, 자두가 그 나무의 아래에 서 있으면 자두는 그 열매를 받아먹을 수 있다. 두 개의 나무는 그다지 멀리 떨어져 있지 않기 때문에, 자두는 하나의 나무 아래에 서 있다가 다른 나무 아래로 빠르게(1초보다 훨씬 짧은 시간에) 움직일 수 있다. 하지만 자두는 체력이 그다지 좋지 못해서 많이 움직일 수는 없다.
        자두는 T(1≤T≤1,000)초 동안 떨어지게 된다. 자두는 최대 W(1≤W≤30)번만 움직이고 싶어 한다. 매 초마다 어느 나무에서 자두가 떨어질지에 대한 정보가 주어졌을 때, 자두가 받을 수 있는 자두의 개수를 구해내는 프로그램을 작성하시오. 자두는 1번 자두나무 아래에 위치해 있다고 한다.


    입력
        첫째 줄에 두 정수 T, W가 주어진다. 다음 T개의 줄에는 각 순간에 자두가 떨어지는 나무의 번호가 1 또는 2로 주어진다.


    출력
        첫째 줄에 자두가 받을 수 있는 자두의 최대 개수를 출력한다.


    예제 입력 1
        7 2
        2
        1
        1
        2
        2
        1
        1
    예제 출력 1
        6


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14132KB
// 시간 : 100ms
// 코드 길이 : 2414B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {
    static int T; // 자두가 떨어지는 시간
    static int W; // 자두가 최대로 움직이는 횟수
    static int tree[]; // 해당 인덱스 초에 자두가 떨어지는 나무의 번호를 저장하는 배열
    static int plum[][]; // plum[w][t] : 자두가 t 초 동안 w 번 움직여서 최대로 먹을 수 있는 자두의 개수

    public static void find() { // 자두가 받을 수 있는 자두의 최대 개수를 구하고 출력하는 메서드
        plum = new int[W + 1][T + 1];
        int maxPlum = Integer.MIN_VALUE; // 자두가 받을 수 있는 자두의 최대 개수
        for (int t = 0; t <= T; t++) {
            if (tree[t] == 1) {
                plum[0][t] = plum[0][t - 1] + 1;
                maxPlum = Math.max(maxPlum, plum[0][t]);
            }
        }

        for (int w = 1; w <= W; w++) {
            for (int s = 1; s <= T; s++) {
                if (((w % 2 == 0) && tree[s] == 1) || ((w % 2 == 1) && (tree[s] == 2))) { // 짝수 번 움직이고 자두가 떨어지는 나무의 번호가 1이거나, 홀수 번 움직이고 자두가 떨어지는 나무의 번호가 2일 경우
                    plum[w][s] = plum[w][s - 1] + 1;
                }
                else if (((w % 2 == 0) && (tree[s] == 2)) || ((w % 2 == 1) && (tree[s] == 1))) { // 짝수 번 움직이고 자두가 떨어지는 나무의 번호가 2이거나, 홀수 번 움직이고 자두가 떨어지는 나무의 번호가 1일 경우
                    plum[w][s] = Math.max(plum[w][s - 1], plum[w - 1][s - 1] + 1);
                }
                else {
                    plum[w][s] = plum[w][s - 1];
                }

                maxPlum = Math.max(maxPlum, plum[w][s]);
            }
        }

        System.out.println(maxPlum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(token.nextToken());
        W = Integer.parseInt(token.nextToken());

        tree = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            tree[i] = Integer.parseInt(bf.readLine());
        }

        find();
    }
}
