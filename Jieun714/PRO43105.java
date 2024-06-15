package Jieun714;
/**
 * 문제: 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
 *      삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
 *
 *      제한사항
 *      - 삼각형의 높이는 1 이상 500 이하입니다.
 *      - 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
 * 해결: 동적계획법
 * */
public class PRO43105 {
    class Solution {
        public int solution(int[][] triangle) {
            int answer = 0;
            int n = triangle.length; //정수 삼각형 높이
            int [][] dp = new int[n][triangle[n-1].length]; //dp 배열 초기화
            dp[0][0] = triangle[0][0]; //삼각형 꼭대기 선언

            for(int i=1; i<n; i++) {
                for(int j=0; j<triangle[i].length; j++) {
                    int num = 0; //더 큰 값 계산을 위한 초기화
                    if(0<=(j-1)) num = dp[i-1][j-1];
                    if(j<triangle[i-1].length) num = Math.max(num, dp[i-1][j]);
                    dp[i][j] = triangle[i][j] + num; //dp 배열에 누적
                    answer = Math.max(answer, dp[i][j]); //최대값 구하기
                }
            }
            return answer;
        }
    }
}
