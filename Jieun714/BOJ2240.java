package Jieun714;
/**
 * 문제: 자두는 자두를 좋아한다. 그래서 집에 자두나무를 심어두고, 여기서 열리는 자두를 먹고는 한다. 하지만 자두는 키가 작아서 자두를 따먹지는 못하고, 자두가 떨어질 때까지 기다린 다음에 떨어지는 자두를 받아서 먹고는 한다. 자두를 잡을 때에는 자두가 허공에 있을 때 잡아야 하는데, 이는 자두가 말랑말랑하여 바닥에 떨어지면 못 먹을 정도로 뭉개지기 때문이다.
 *      매 초마다, 두 개의 나무 중 하나의 나무에서 열매가 떨어지게 된다. 만약 열매가 떨어지는 순간, 자두가 그 나무의 아래에 서 있으면 자두는 그 열매를 받아먹을 수 있다. 두 개의 나무는 그다지 멀리 떨어져 있지 않기 때문에, 자두는 하나의 나무 아래에 서 있다가 다른 나무 아래로 빠르게(1초보다 훨씬 짧은 시간에) 움직일 수 있다. 하지만 자두는 체력이 그다지 좋지 못해서 많이 움직일 수는 없다.
 *      자두는 T(1≤T≤1,000)초 동안 떨어지게 된다. 자두는 최대 W(1≤W≤30)번만 움직이고 싶어 한다. 매 초마다 어느 나무에서 자두가 떨어질지에 대한 정보가 주어졌을 때, 자두가 받을 수 있는 자두의 개수를 구해내는 프로그램을 작성하시오. 자두는 1번 자두나무 아래에 위치해 있다고 한다.
 * 입력: 첫째 줄에 두 정수 T, W가 주어진다. 다음 T개의 줄에는 각 순간에 자두가 떨어지는 나무의 번호가 1 또는 2로 주어진다.
 * 출력: 첫째 줄에 자두가 받을 수 있는 자두의 최대 개수를 출력한다.
 * 해결: 다이나믹 프로그래밍
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); //떨어지는 자두 개수
        int W = Integer.parseInt(st.nextToken()); //자두가 최대 움직일 수 있는 개수

        int [][] dp = new int[T+1][W+1];
        for(int i=1; i<=T; i++) {
            int tree = Integer.parseInt(br.readLine()); //자두가 떨어지는 나무의 번호
            for(int j=0; j<=W; j++) {
                if(j==0) { //새로운 자두의 시작일때, dp 초기화
                    if(tree == 1) dp[i][j] = dp[i-1][j] + 1;
                    else dp[i][j] = dp[i-1][j];
                    continue;
                }

                if(j%2 == 0) { //짝수번 움직일 때
                    if(tree == 1) dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]+1);
                    else dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i-1][j]);
                } else { //훌수번 움직일 때
                    if(tree == 1) dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i-1][j]);
                    else dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]+1);
                }
            }
        } //for end

        int result = 0;
        for(int i=0; i<=W; i++) result = Math.max(result, dp[T][i]); //자두가 받을 수 있는 자두의 최대 개수
        System.out.println(result); //결과 출력
    }
}