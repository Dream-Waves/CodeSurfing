package Jieun714;
/**
 * 문제: 올해 Z대학 컴퓨터공학부에 새로 입학한 민욱이는 학부에 개설된 모든 전공과목을 듣고 졸업하려는 원대한 목표를 세웠다. 어떤 과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다. 공학인증을 포기할 수 없는 불쌍한 민욱이는 선수과목 조건을 반드시 지켜야만 한다. 민욱이는 선수과목 조건을 지킬 경우 각각의 전공과목을 언제 이수할 수 있는지 궁금해졌다. 계산을 편리하게 하기 위해 아래와 같이 조건을 간소화하여 계산하기로 하였다.
 *      1. 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
 *      2. 모든 과목은 매 학기 항상 개설된다.
 *      모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 걸리는지 계산하는 프로그램을 작성하여라.
 * 입력: 첫 번째 줄에 과목의 수 N(1 ≤ N ≤ 1000)과 선수 조건의 수 M(0 ≤ M ≤ 500000)이 주어진다. 선수과목 조건은 M개의 줄에 걸쳐 한 줄에 정수 A B 형태로 주어진다. A번 과목이 B번 과목의 선수과목이다. A < B인 입력만 주어진다. (1 ≤ A < B ≤ N)
 * 출력: 1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지를 한 줄에 공백으로 구분하여 출력한다.
 * 해결: 다이나믹 프로그래밍
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14567 {
    public static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //과목의 수
        int M = Integer.parseInt(st.nextToken()); //선수 조건의 수
        graph = new ArrayList[N + 1]; //선수과목 조건을 담는 리스트 배열
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //b의 선수 과목
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        int [] dp = new int[N+1];
        Arrays.fill(dp, 1);
        for(int i=1; i<=N; i++) {
            if(graph[i].isEmpty()) continue; //i 번째 과목을 선수 과목으로 둔 과목이 없다면 다음으로
            for(int n : graph[i]) { //i 번째 과목을 선수 과목으로 둔 과목 n
                dp[n] = Math.max(dp[n], dp[i] + 1); //최소 이수 학기 계산
            }
        }

        for(int i=1; i<=N; i++) System.out.print(dp[i] + " ");
    }
}