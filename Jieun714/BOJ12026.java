package Jieun714;
/**
 * 문제: BOJ 거리는 보도블록 N개가 일렬로 놓여진 형태의 도로이다. 도로의 보도블록은 1번부터 N번까지 번호가 매겨져 있다.
 *      스타트의 집은 1번에 있고, 링크의 집은 N번에 있다. 스타트는 링크를 만나기 위해서 점프해가려고 한다.
 *      BOJ거리의 각 보도블록에는 B, O, J 중에 하나가 쓰여 있다. 1번은 반드시 B이다.
 *      스타트는 점프를 통해서 다른 보도블록으로 이동할 수 있다. 이때, 항상 번호가 증가하는 방향으로 점프를 해야 한다. 만약, 스타트가 현재 있는 곳이 i번이라면, i+1번부터 N번까지로 점프를 할 수 있다. 한 번 k칸 만큼 점프를 하는데 필요한 에너지의 양은 k*k이다.
 *      스타트는 BOJ를 외치면서 링크를 만나러 가려고 한다. 따라서, 스타트는 B, O, J, B, O, J, B, O, J, ... 순서로 보도블록을 밟으면서 점프를 할 것이다.
 *      스타트가 링크를 만나는데 필요한 에너지 양의 최솟값을 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 1 ≤ N ≤ 1,000이 주어진다.
 *      둘째 줄에는 보도블록에 쓰여 있는 글자가 1번부터 순서대로 주어진다.
 * 출력: 스타트가 링크를 만나는데 필요한 에너지 양의 최솟값을 출력한다. 만약, 스타트가 링크를 만날 수 없는 경우에는 -1을 출력한다.
 * 해결: 다이나믹 프로그래밍
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char [] arr = br.readLine().toCharArray();
        int [] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE); //MAX_VALUE로 초기화
        dp[0] = 0; //dp의 시작은 0으로 초기화
        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++) {
                if(arr[i] == 'B') {
                    if(arr[j] == 'O' && dp[i] != Integer.MAX_VALUE) { //다음 보도블럭이 'O'이면서, 이전 보도블록이 최대값이 아닐 경우
                        dp[j] = Math.min(dp[j], dp[i] + (int)Math.pow((j-i), 2));
                    }
                } else if(arr[i] == 'O') {
                    if(arr[j] == 'J' && dp[i] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[i] + (int)Math.pow((j-i), 2));
                    }
                } else {
                    if(arr[j] == 'B' && dp[i] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[i] + (int)Math.pow((j-i), 2));
                    }
                }
            }
        }
        if(dp[N-1] == Integer.MAX_VALUE) dp[N-1] = -1; //스타트가 링크를 만날 수 없는 경우라면 -1로 변경
        System.out.println(dp[N - 1]); //결과 출력
    }
}