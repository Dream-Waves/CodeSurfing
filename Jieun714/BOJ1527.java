package Jieun714;
/**
 * 문제: 은민이는 4와 7을 좋아하고, 나머지 숫자는 싫어한다. 금민수는 어떤 수가 4와 7로만 이루어진 수를 말한다.
 *      A와 B가 주어졌을 때, A보다 크거나 같고, B보다 작거나 같은 자연수 중에 금민수인 것의 개수를 출력하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 A와 B가 주어진다. A는 1보다 크거나 같고, 1,000,000,000보다 작거나 같은 자연수이다. B는 A보다 크거나 같고, 1,000,000,000보다 작거나 같은 자연수이다.
 * 출력: 첫째 줄에 A보다 크거나 같고, B보다 작거나 같은 자연수 중에 금민수인 것의 개수를 출력한다.
 * 해결: 브루트포스
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1527 {
    public static long A, B, cnt;
    public static void dfs(long n) {
        if(B<n) return; //범위를 넘어서면 재귀 종료
        if(A<=n) cnt++; //해당 범위 안에 존재하면 금민수 개수 증가

        dfs((n*10)+4);
        dfs((n*10)+7);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        dfs(0);
        System.out.println(cnt); //결과 출력
    }
}