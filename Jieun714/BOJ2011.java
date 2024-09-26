package Jieun714;
/**
 * 문제: 어떤 암호가 주어졌을 때, 그 암호의 해석이 몇 가지가 나올 수 있는지 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 5000자리 이하의 암호가 주어진다. 암호는 숫자로 이루어져 있다.
 * 출력: 나올 수 있는 해석의 가짓수를 구하시오. 정답이 매우 클 수 있으므로, 1000000으로 나눈 나머지를 출력한다.
 *      암호가 잘못되어 암호를 해석할 수 없는 경우에는 0을 출력한다.
 * 해결: 다이나믹 프로그래밍
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int mod = 1000000;
        int[] dp = new int[str.length() + 1];

        if(str.charAt(0) == '0') { //잘못된 입력일 때
            System.out.println(0); //0 출력
            return;
        } else {
            dp[0] = dp[1] = 1;
            for (int i = 2; i <= str.length(); i++) {
                // 한 자릿수 계산 했을 떄, 경우의 수
                if (str.charAt(i-1) != '0') dp[i] += dp[i-1] % mod;

                // 두 자릿수 계산이 가능할 떄, 경우의 수
                int temp = Integer.parseInt(str.substring(i-2, i));
                if(temp > 9 && temp <27) dp[i] += dp[i-2] % mod;
            }
        } //else end
        System.out.println(dp[str.length()] % mod);
    }
}