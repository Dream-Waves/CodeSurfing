package Jieun714;
/**
 * 문제: 건덕이는 문자가 일렬로 적혀있는 보드에서 게임을 하고 있다. 보드에는 'N' 개의 알파벳 대문자가 나란히 적혀있다. 건덕이는 또 다른 'M' 자리 영어 단어를 가지고 게임을 진행한다.
 *      우선 보드의 한 지점에서 첫 번째 문자가 보드의 문자와 일치하는지 확인한다. L(왼쪽) 또는 R(오른쪽) 방향으로 이동한 후에 다음 문자와 보드의 문자가 일치하는지 확인한다.
 *      일치할 경우 점수를 1점 얻는다. 단, 이동한 후 보드 바깥으로 벗어날 수 없다. 건덕이가 가지고 있는 단어의 끝에 도달하면 게임을 종료한다.
 *      보드와 가지고 있는 단어가 주어졌을 때, 건덕이가 얻을 수 있는 최대 점수를 구하는 프로그램을 작성해보자.
 * 입력: 첫째 줄에 보드의 길이와 건덕이가 가지고 있는 문자열의 길이를 나타내는 정수 'N, M'이 공백으로 구분되어 주어진다.(2 < N, M < 5,000)
 *      둘째 줄에는 보드의 문자가 순서대로 주어진다. 셋째 줄에는 가지고 있는 문자열이 주어진다. 이 때, 두 문자열은 모두 알파벳 대문자로만 구성되어 있다.
 * 출력: 건덕이가 얻을 수 있는 최대 점수를 출력한다.
 * 해결: DP
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ27980 {
    public static int N, M;
    public static String nString, mString;
    public static int [][] dp;

    public static int dfs(int idx1, int idx2) {
        if (idx2 == M) return 0; //더 이상 비교할 문자가 없을 경우 리턴

        //이미 계산된 상태라면 바로 반환
        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        int score = 0;
        if (idx1 > 0) score = dfs(idx1 - 1, idx2 + 1); //L 방향 이동
        if (idx1 < N - 1) score = Math.max(score, dfs(idx1 + 1, idx2 + 1)); //R 방향 이동

        //현재 위치의 문자가 일치할 경우, socre + 1
        if (nString.charAt(idx1) == mString.charAt(idx2)) ++score;

        return dp[idx1][idx2] = score;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nString = br.readLine();
        mString = br.readLine();

        dp = new int[N][M];
        int answer = 0;
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1); //dp 초기화
        for (int i = 0; i < N; i++) answer = Math.max(dfs(i, 0), answer);

        System.out.println(answer); //결과 출력
    }
}
