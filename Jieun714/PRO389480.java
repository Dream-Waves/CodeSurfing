package Jieun714;
/**
 * 문제: A도둑과 B도둑이 팀을 이루어 모든 물건을 훔치려고 합니다. 단, 각 도둑이 물건을 훔칠 때 남기는 흔적이 누적되면 경찰에 붙잡히기 때문에, 두 도둑 중 누구도 경찰에 붙잡히지 않도록 흔적을 최소화해야 합니다.
 *      물건을 훔칠 때 조건은 아래와 같습니다.
 *        - 물건 i를 훔칠 때,
 *            - A도둑이 훔치면 info[i][0]개의 A에 대한 흔적을 남깁니다.
 *            - B도둑이 훔치면 info[i][1]개의 B에 대한 흔적을 남깁니다.
 *        - 각 물건에 대해 A도둑과 B도둑이 남기는 흔적의 개수는 1 이상 3 이하입니다.
 *
 *      경찰에 붙잡히는 조건은 아래와 같습니다.
 *        - A도둑은 자신이 남긴 흔적의 누적 개수가 n개 이상이면 경찰에 붙잡힙니다.
 *        - B도둑은 자신이 남긴 흔적의 누적 개수가 m개 이상이면 경찰에 붙잡힙니다.
 *      각 물건을 훔칠 때 생기는 흔적에 대한 정보를 담은 2차원 정수 배열 info, A도둑이 경찰에 붙잡히는 최소 흔적 개수를 나타내는 정수 n, B도둑이 경찰에 붙잡히는 최소 흔적 개수를 나타내는 정수 m이 매개변수로 주어집니다.
 *      두 도둑 모두 경찰에 붙잡히지 않도록 모든 물건을 훔쳤을 때, A도둑이 남긴 흔적의 누적 개수의 최솟값을 return 하도록 solution 함수를 완성해 주세요. 만약 어떠한 방법으로도 두 도둑 모두 경찰에 붙잡히지 않게 할 수 없다면 -1을 return해 주세요.
 * 입력: info = [[1, 2], [2, 3], [2, 1]]	,  n = 4 , m = 4
 * 출력: 2
 * 해결: DP
 * */
import java.util.*;

public class PRO389480 {
    class Solution {
        public int solution(int[][] info, int n, int m) {
            int INF = Integer.MAX_VALUE;
            int[] dp = new int[m]; //최소 A 흔적
            Arrays.fill(dp, INF);
            dp[0] = 0;

            for (int i = 0; i < info.length; i++) {
                int[] next = new int[m];
                Arrays.fill(next, INF);

                for (int j = 0; j < m; j++) {
                    if (dp[j] == INF) continue; //불가능한 상태는 건너뜀

                    int newA = dp[j] + info[i][0];
                    int newB = j + info[i][1];

                    if (newA < n) { //A가 훔치는 경우
                        next[j] = Math.min(next[j], newA);
                    }
                    if (j + info[i][1] < m) { //B가 훔치는 경우
                        next[newB] = Math.min(next[newB], dp[j]);
                    }
                }

                dp = next;
            }

            int answer = INF;
            for (int b = 0; b < m; b++) { //A 흔적의 최소값을 찾음
                answer = Math.min(answer, dp[b]);
            }

            return answer == INF ? -1 : answer;
        }
    }
}