/*
14567. Gold 5 - 선수과목 (Prerequisite)

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    5 초	    256 MB           7349	    4789      3676	         65.608%


    문제
        올해 Z대학 컴퓨터공학부에 새로 입학한 민욱이는 학부에 개설된 모든 전공과목을 듣고 졸업하려는 원대한 목표를 세웠다. 어떤 과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다. 공학인증을 포기할 수 없는 불쌍한 민욱이는 선수과목 조건을 반드시 지켜야만 한다. 민욱이는 선수과목 조건을 지킬 경우 각각의 전공과목을 언제 이수할 수 있는지 궁금해졌다. 계산을 편리하게 하기 위해 아래와 같이 조건을 간소화하여 계산하기로 하였다.
            1. 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
            2. 모든 과목은 매 학기 항상 개설된다.

        모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 걸리는지 계산하는 프로그램을 작성하여라.


    입력
        첫 번째 줄에 과목의 수 N(1 ≤ N ≤ 1000)과 선수 조건의 수 M(0 ≤ M ≤ 500000)이 주어진다. 선수과목 조건은 M개의 줄에 걸쳐 한 줄에 정수 A B 형태로 주어진다. A번 과목이 B번 과목의 선수과목이다. A < B인 입력만 주어진다. (1 ≤ A < B ≤ N)


    출력
        1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지를 한 줄에 공백으로 구분하여 출력한다.


    예제 입력 1
        3 2
        2 3
        1 2
    예제 출력 1
        1 2 3

    예제 입력 2
        6 4
        1 2
        1 3
        2 5
        4 5
    예제 출력 2
        1 2 2 1 3 1


    힌트
        3개의 과목이 있고, 2번 과목을 이수하기 위해서는 1번 과목을 이수해야 하고, 3번 과목을 이수하기 위해서는 2번 과목을 이수해야 한다.


    알고리즘 분류
        다이나믹 프로그래밍
        그래프 이론
        위상 정렬
        방향 비순환 그래프
*/


// 메모리 : 127644KB
// 시간 : 540ms
// 코드 길이 : 2332B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14567 {
    static int N; // 과목의 수 (1 ≤ N ≤ 1000)
    static int M; // 선수 조건의 수 (0 ≤ M ≤ 500000)
    static ArrayList<Integer> condition[]; // 각 과목의 선수과목 리스트를 저장하는 배열
    static int prerequisite[]; // 각 과목이 최소 몇 학기에 이수할 수 있는지를 저장하는 배열
    static StringBuilder sb;

    public static void find() { // 각 과목이 최소 몇 학기에 이수할 수 있는지를 구하고 출력하는 메서드
        prerequisite = new int[N + 1];
        sb = new StringBuilder();

        for (int n = 1; n <= N; n++) {
            if (condition[n].isEmpty()) { // 선수과목이 없는 과목일 경우
                prerequisite[n] = 1; // 바로 1 학기에 들을 수 있다.
            }
            else { // 선수과목이 있는 과목일 경우
                for (int s = 0, size = condition[n].size(); s < size; s++) {
                    prerequisite[n] = Math.max(prerequisite[n], (prerequisite[condition[n].get(s)] + 1)); // (해당 과목의 선수과목을 들을 수 있는 학기 + 1 학기)의 값들 중 최댓값의 학기에 들을 수 있다.
                }
            }

            sb.append(prerequisite[n]);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        condition = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            condition[i] = new ArrayList<>();
        }

        for (int c = 0; c < M; c++) {
            token = new StringTokenizer(bf.readLine());
            int prevSubject = Integer.parseInt(token.nextToken()); // B 번 과목 nextSubject의 선수과목 A 번 과목
            int nextSubject = Integer.parseInt(token.nextToken()); // B 번 과목

            condition[nextSubject].add(prevSubject);
        }

        find();
    }
}
