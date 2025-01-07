package Jieun714;
/**
 * 문제: 육각수는 육각형을 이용해 정의할 수 있다. hn은 한 변에 점 1, 2, ..., n개가 있는 육각형을 점 하나만 겹치게 그렸을 때 존재하는 서로 다른 점의 개수이다.
 *      그림1은 h1, h2, h3, h4를 의미하며, 처음 육각수 6개는 1, 6, 15, 28, 45, 66이다.
 *      자연수 N이 주어졌을 때, 합이 N이 되는 육각수 개수의 최솟값을 구해보자.
 *      1791보다 큰 정수는 항상 육각수 4개의 합으로 만들 수 있다. 또한, 수가 충분히 크다면 항상 육각수 3개의 합으로 만들 수 있다.
 *      또, 최소 개수는 항상 6 이하이고, 이것이 최소인 N은 11과 26밖에 없다. 답이 6인 가장 큰 N은 26, 5인 가장 큰 N은 130, 4인 가장 큰 N은 146858이다.
 * 입력: 첫째 줄에 N이 주어진다.
 * 출력: 첫째 줄에 N을 만들기 위해 필요한 육각수 개수의 최솟값을 출력한다.
 * 해결: DP(다이나믹 프로그래밍)
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1229 {
    public static ArrayList<Integer> hList;
    public static void calc(int n, int now) { //육각수의 점의 개수를 구하는 함수
        int next = hList.get(hList.size()-1);
        if(next <= n) {
            hList.add(next);
            calc(n, now+4);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        hList = new ArrayList<>();
        hList.add(1);
        hList.add(6);
        calc(N, 9); //육각수 계산

        int [] dp = new int[N+1];
        for(int i=1; i<=N; i++) dp[i] = 6; //최소 개수는 항상 6 이하임으로 6으로 dp 초기화

        for(int i=1; i<=N; i++) {
            for (Integer now : hList) {
                if (i < now) break; //점의 개수를 초과하게 되면 다음 계산으로
                dp[i] = Math.min(dp[i], dp[i - now] + 1); //dp[i]의 최솟값 계산
            }
        }
        System.out.println(dp[N]); //육각수 개수의 최솟값 출력
    }
}
