package Jieun714;
/**
 * 문제: 당신은 비밀 조직의 보안 시스템을 뚫고 중요한 정보를 해독해야 합니다. 시스템은 1부터 n까지의 서로 다른 정수 5개가 오름차순으로 정렬된 비밀 코드를 가지고 있으며, 당신은 이 비밀 코드를 맞혀야 합니다.
 *      당신은 비밀 코드를 알아내기 위해 암호 분석 도구를 사용하며, m번의 시도를 할 수 있습니다. 각 시도마다 서로 다른 5개의 정수를 입력하면, 시스템은 그 중 몇 개가 비밀 코드에 포함되어 있는지 알려줍니다.
 *      만약 비밀 코드가 [3, 5, 7, 9, 10]이고, 입력한 정수가 [1, 2, 3, 4, 5]라면 비밀 코드에 포함된 정수는 3, 5 두 개이므로 시스템은 2를 응답합니다.
 *      당신은 m번의 시도 후, 비밀 코드로 가능한 정수 조합의 개수를 알고 싶습니다.
 *      정수 n, 입력한 정수를 담은 2차원 정수 배열 q와 시스템 응답을 담은 1차원 정수 배열 ans가 매개변수로 주어집니다. 이때, 비밀 코드로 가능한 정수 조합 개수를 return 하도록 solution 함수를 완성해 주세요.
 * 해결: 조합
 * */
public class PRO388352 {
    class Solution {
        public int answer = 0;
        public int [] select = new int[5]; //5개 숫자 조합을 저장할 배열
        public int [][] qCopy; //입력한 정수를 카피할 배열
        public int [] ansCopy; //일치하는 개수를 카피할 배열

        public void combi(int n, int r, int start) { //조합 함수
            if (r == 5) {
                check();  //조건 검증
                return;
            }

            // start부터 n까지의 숫자 중 하나를 선택
            for (int i = start; i <= n; i++) {
                select[r] = i;
                combi(n, r + 1, i + 1);
            }
        }

        public void check() { //5개의 숫자가 유효한 지 체크하는 함수
            int [] ans = ansCopy.clone(); //원본 보존을 위해 깊은 복사

            for (int i = 0; i < qCopy.length; i++) {
                for (int num : select) { //조합한 숫자를 순서대로 확인
                    for(int k = 0; k < 5; k++) {
                        if(num == qCopy[i][k]) ans[i]--; //일치하는 숫자가 있을 경우, ans 개수 감소
                        if(ans[i] == -1) return; //일치하는 개수가 초과했을 경우 리턴
                    }
                }
                if (ans[i] != 0) return; //일치하는 개수가 맞지 않을 경우 리턴
            }
            answer++; //모든 조건에 만족했을 경우, answer 추가
        }

        public int solution(int n, int[][] q, int[] ans) {
            qCopy = q;
            ansCopy = ans;
            combi(n, 0, 1);
            return answer;
        }
    }
}