package Jieun714;
/**
 * 문제: 상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다. 상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다. 예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.
 *      상근이는 올바른 등식을 만들려고 한다. 상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다. 따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다. 예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에, 상근이가 만들 수 없는 등식이다.
 *      숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100) 둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.
 * 출력: 첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다. 이 값은 263-1 이하이다.
 * 해결: 다이나믹 프로그래밍
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long [][] dp = new long[N][21];
        dp[0][arr[0]] = 1; //초기값
        for(int i=1; i<N-1; i++) {
            for(int j=0; j<21; j++) {
                if(dp[i-1][j] == 0) continue; //해당 배열의 값이 존재할 때만 수행
                int min = j - arr[i]; // 이전 계산 값 - 현재 값
                int max = j + arr[i]; // 이전 계산 값 + 현재 값
                if (0 <= min && min <= 20) dp[i][min] += dp[i - 1][j]; //계산된 값에 등식의 개수 누적
                if (0 <= max && max <= 20) dp[i][max] += dp[i - 1][j]; //계산된 값에 등식의 개수 누적
            }
        }
        System.out.println(dp[N-2][arr[N-1]]); //올바른 등식의 개수
    }
}