package Jieun714;
/**
 * 문제: 레스토랑을 운영하던 스카피는 코로나19로 인한 불경기를 극복하고자 메뉴를 새로 구성하려고 고민하고 있습니다.
 *      기존에는 단품으로만 제공하던 메뉴를 조합해서 코스요리 형태로 재구성해서 새로운 메뉴를 제공하기로 결정했습니다.
 *      어떤 단품메뉴들을 조합해서 코스요리 메뉴로 구성하면 좋을 지 고민하던 "스카피"는 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.
 *      단, 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고 합니다. 또한, 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함하기로 했습니다.
 *
 *      각 손님들이 주문한 단품메뉴들이 문자열 형식으로 담긴 배열 orders, "스카피"가 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수가 담긴 배열 course가 매개변수로 주어질 때,
 *      "스카피"가 새로 추가하게 될 코스요리의 메뉴 구성을 문자열 형태로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 * 입력: orders - ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"],  course - [2,3,4]
 * 출력: ["AC", "ACDE", "BCFG", "CDE"]
 * 해결: HashMap 활용
 * */
import java.util.*;
public class PRO72411 {
    class Solution {
        public Map<String, Integer> hashMap;

        public void combi(int r, String order, String newStr) {
            if(r == 0) {
                hashMap.put(newStr, hashMap.getOrDefault(newStr, 0) + 1); //hashMap에 조합 결과 추가
                return;
            }

            for(int i=0; i<order.length(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(newStr).append(order.charAt(i));
                combi(r-1, order.substring(i + 1), sb.toString()); //재귀 호출
            }
        }

        public String[] solution(String[] orders, int[] course) {
            //문자열 내 문자 정렬
            for(int i =0;i<orders.length; i++){
                char[] cArr = orders[i].toCharArray();
                Arrays.sort(cArr);
                orders[i] = String.valueOf(cArr);
            }

            List<String> list = new ArrayList<>(); //단품 메뉴 조합을 담을 리스트
            for(int n : course) { //단품 메뉴 갯수
                hashMap = new HashMap<>(); //hashMap 초기화
                for (String order : orders) { //단품 메뉴 갯수에 해당하는 조합 구하기
                    combi(n, order, "");
                }

                if(!hashMap.isEmpty()) { //hashMap이 비어있지않다면
                    List<Integer> countList = new ArrayList<>(hashMap.values());
                    int max = Collections.max(countList); //가장 많이 주문된 횟수

                    for (String key : hashMap.keySet()) {
                        if (max > 1 && hashMap.get(key) == max) { //최소 2번 이상 주문한 조합이면서 최대 주문된 횟수와 동일할 때
                            list.add(key); //단품 메뉴 조합 저장
                        }
                    }
                }
            }
            Collections.sort(list); //사전순으로 정렬
            String [] answer = new String[list.size()];
            for(int i=0; i<list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer; //결과 출력
        }
    }
}
