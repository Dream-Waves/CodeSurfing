package Jieun714;
/**
 * 문제: 카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
 *      이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.
 *        1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
 *        2. 이모티콘 판매액을 최대한 늘리는 것.
 *      1번 목표가 우선이며, 2번 목표가 그 다음입니다.
 *      이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.
 *        - n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
 *        - 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
 *      카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.
 *        - 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
 *        - 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
 * 제한사항
 *   - 1 ≤ users의 길이 = n ≤ 100
 *      - users의 원소는 [비율, 가격]의 형태입니다.
 *      - users[i]는 i+1번 고객의 구매 기준을 의미합니다.
 *      - 비율% 이상의 할인이 있는 이모티콘을 모두 구매한다는 의미입니다.
 *          - 1 ≤ 비율 ≤ 40
 *      - 가격이상의 돈을 이모티콘 구매에 사용한다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입한다는 의미입니다.
 *          - 100 ≤ 가격 ≤ 1,000,000
 *          - 가격은 100의 배수입니다.
 *      - 1 ≤ emoticons의 길이 = m ≤ 7
 *          - emoticons[i]는 i+1번 이모티콘의 정가를 의미합니다.
 *          - 100 ≤ emoticons의 원소 ≤ 1,000,000
 *          - emoticons의 원소는 100의 배수입니다.
 * 해결: 백트래킹
 * */
public class PRO150368 {
    class Solution {
        public int[] percent = {10, 20, 30, 40};
        public int plusUserMAX = 0;
        public int emoTotalMAX = 0;

        //이모티콘 별 할인율을 설정하는 함수
        public void choose(int[][] users, int[] emoticons, int now, int[] rate) {
            if(now == emoticons.length) {
                calc(users, emoticons, rate);
                return;
            }

            for(int d : percent) {
                rate[now] = d; //할인율 설정
                choose(users, emoticons, now+1, rate);
            }
        }

        //현재 이모티콘별 할인율을 기준으로 서비스 가입자와 판매액 계산
        public void calc(int [][] users, int[] emoticons, int[] rate) {
            int plusUser = 0; //이모티콘 플러스 서비스 가입자
            int totalPrice = 0; //이모티콘 판매액

            for(int[] user : users) {
                int charge = 0; //이모티콘 구매비용
                boolean flag = false; //이모티콘 플러스 서비스 가입자인지 판단하는 변수
                for(int i=0; i<emoticons.length; i++) {
                    if(user[0] <= rate[i])  //사용자의 기준 이상 할인할 경우
                        charge += emoticons[i]*(100-rate[i])/100;

                    if(charge >= user[1]) { //이모티콘 구매 비용이 사용자의 소지금을 초과했을 경우
                        plusUser += 1; //이모티콘 플러스 가입자 추가
                        flag = true;
                        break;
                    }
                }
                if(!flag) totalPrice += charge; //이모티콘 플러스 가입자가 아니면 판매액 추가
            }

            if(plusUser > plusUserMAX) { //현재 할인율에서 이모티콘 플러스 가입자가 많을 경우
                plusUserMAX = plusUser;
                emoTotalMAX = totalPrice;
            } else if(plusUser == plusUserMAX) { //최대 서비스 가입자수가 같을 경우
                emoTotalMAX = Math.max(emoTotalMAX, totalPrice); //이모티콘 최대 판매액 선택
            }
        }

        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = new int[2];
            choose(users, emoticons, 0, new int [emoticons.length]);
            answer[0] = plusUserMAX;
            answer[1] = emoTotalMAX;
            return answer;
        }
    }
}