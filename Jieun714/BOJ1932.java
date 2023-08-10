package Jieun714;
/**
 * 문제: 맨 위층부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라.
 *      아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 *      삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
 * 입력: 첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.
 * 출력: 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
 * 해설: DP 사용
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //삼각형의 크기
        int max = Integer.MIN_VALUE;
        int [][] arr = new int[N+1][N+1]; //삼각형의 정수를 담는 배열
        int [][] dp = new int[N+1][N+1]; //dp 계산을 담는 배열

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j]; //대각선 왼쪽과 오른쪽에 있는 것 중 최대값 선택
                max = Math.max(max, dp[i][j]); //최대값 업데이트
            }
        }
        System.out.println(max); //결과
    }
}