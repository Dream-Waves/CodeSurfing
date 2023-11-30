package Jieun714;
/**
 * 문제: 홀순이(holsoon)와 짝순이(jjaksoon) 둘이서 숫자 게임을 한다. 예를 들어, 정수 1과 3이 주어지고, 이 둘을 통틀어 5번까지 마음대로 사용하여 그 합을 구하여 1,2,3,…을 만드는 놀이다. 이 경우 먼저 홀순이가 1 하나만을 사용하여 1을 만든다.
 *      짝순이는 1+1로 1을 두 번 사용하여 2를 만들고, 다시 홀순이는 3을 만들어야하는데 1+1+1로 1을 세 번 사용하거나 3을 한 번 사용하여 3을 만든다. 짝순이는 1+1+1+1, 1+3으로 4를 만든다. 서로 번갈아서 상대방의 수보다 1이 큰 수를 만들어야 한다.
 *      단, 1과 3을 통틀어 최대 5번 사용한다. 이런 식으로 진행하면 13까지는 만들 수 있지만 14를 만들지 못하게 되므로 짝순이가 졌다. 숫자 게임에서 사용하는 정수 N개와 최대 사용 횟수 K가 주어질 때, 누가 어느 수에서 이기는지를 판별하는 프로그램을 작성해보자.
 *      사용하는 정수에는 반드시 1이 포함된다. 그렇지 않으면 홀순이가 1을 만들지 못하므로 무조건 지게 된다. 1이 꼭 있으니 상대방이 만든 방법에 1만 한 번 더 쓰면 된다고 생각하기 쉽지만, 최대 사용 횟수가 정해져 있으므로, 이 방법이 수가 커지는 경우에는 잘 되지 않는다.
 *      위에서 13을 홀순이가 만들었지만 짝순이는 최대 사용 횟수 때문에 14를 만들지 못하고 진다.
 * 입력: 첫째 줄에 숫자 게임에서 사용하는 정수의 수 N이, 둘째 줄에는 사용하는 정수가 크기 순으로 주어진다. 셋째 줄에는 최대 사용 횟수 K가 주어진다.
 * 출력: 첫째 줄에 누가 몇 번째 수에서 이겼는지를 출력한다. 예제에서는 짝순이가 14를 못 만들어서, 홀순이가 14에서 이겼다.
 * 해결: 다이나믹 프로그래밍
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1679 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 숫자 게임에 사용하는 정수의 수
        int [] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); //사용하는 정수
        }
        int K = Integer.parseInt(br.readLine()); //최대 사용 횟수

        int max = arr[N-1]*K+1; //최대 크기
        int [] dp = new int[max];

        for(int i=0; i<N; i++){
            dp[arr[i]] = 1; //dp 배열 초기화
        }

        //동적 계획법
        for(int l=0; l<max; l++){
            if(dp[l] != 0) continue;
            dp[l] = l;

            for(int i=0; i<N; i++){
                if(l > arr[i]) {
                    dp[l] = Math.min(dp[l], dp[arr[i]] + dp[l - arr[i]]);
                }
            }

            if(dp[l] > K){ //최대 사용 횟수를 넘겼다면
                StringBuilder sb = new StringBuilder();
                sb.append(l%2==0 ? "holsoon " : "jjaksoon ").append("win at ").append(l);
                System.out.println(sb); //결과 출력
                return;
            }
        }
    }
}
