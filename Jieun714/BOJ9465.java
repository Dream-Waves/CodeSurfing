package Jieun714;
/**
 * 문제: 상근이의 여동생 상냥이는 문방구에서 스티커 2n개를 구매했다. 스티커는 그림 (a)와 같이 2행 n열로 배치되어 있다. 상냥이는 스티커를 이용해 책상을 꾸미려고 한다.
 *      상냥이가 구매한 스티커의 품질은 매우 좋지 않다. 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다. 즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.
 *      모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고, 점수의 합이 최대가 되게 스티커를 떼어내려고 한다. 먼저, 그림 (b)와 같이 각 스티커에 점수를 매겼다. 상냥이가 뗄 수 있는 스티커의 점수의 최댓값을 구하는 프로그램을 작성하시오.
 *      즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다. 위의 그림의 경우에 점수가 50, 50, 100, 60인 스티커를 고르면, 점수는 260이 되고 이 것이 최대 점수이다.
 *      가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.
 *
 * 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다. 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다.
 *      연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
 *
 * 출력: 각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최댓값을 출력한다.
 *
 * 해결: DP 알고리즘. 점화식을 작성
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465 {
    public static int T, N;
    public static int [][] arr; //스티커 점수를 담는 배열
    public static int [][] dp; //dp 계산을 위한 배열

    public static int solve(){
        for(int j=2; j<=N; j++){ //열만 N-1번 반복
            dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
            dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
        }
        return Math.max(dp[0][N], dp[1][N]); //최댓값 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        for(int i=0; i<T; i++){
            N = Integer.parseInt(br.readLine());
            arr = new int[2][N+1]; //배열의 위치마다 저장
            dp = new int[2][N+1]; //최대 값을 위한 배열

            for(int j=0; j<2; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=1; k<=N; k++){
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            } //for end

            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];

            int max = solve();
            sb.append(max).append("\n");
        } //for end
        System.out.println(sb);
    }
}