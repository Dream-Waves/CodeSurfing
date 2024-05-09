package Jieun714;
/**
 * 문제: 주차장의 요금표와 차량이 들어오고(입차) 나간(출차) 기록이 주어졌을 때, 차량별로 주차 요금을 계산하려고 합니다.
 *      어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주합니다.
 *      00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산합니다.
 *      누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
 *      누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
 *      초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
 *      ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
 *      주차 요금을 나타내는 정수 배열 fees, 자동차의 입/출차 내역을 나타내는 문자열 배열 records가 매개변수로 주어집니다.
 *      차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return 하도록 solution 함수를 완성해주세요.
 * 해결: Map을 활용한 구현
 * */
import java.util.*;
public class PRO92341 {
    class Solution {
        public int[] solution(int[] fees, String[] records) {
            Map<String, Integer> total = new TreeMap(); //누적 주차 시간을 담는 Map, 차량번호를 오름차순으로 담기위해 TreeMap으로 선언
            Map<String, String> timeMap = new HashMap<String, String>(); //입차 시간을 담는 Map
            for(String str : records) {
                String [] arr = str.split(" ");
                if(arr[2].equals("IN")) { //입차
                    timeMap.put(arr[1], arr[0]);
                } else { //출차
                    String [] start = timeMap.get(arr[1]).split(":");
                    String [] end = arr[0].split(":");
                    timeMap.remove(arr[1]); //입차시간 Map에서 삭제
                    int num = (Integer.parseInt(end[0])*60+Integer.parseInt(end[1])) - (Integer.parseInt(start[0])*60+Integer.parseInt(start[1]));
                    total.put(arr[1], total.containsKey(arr[1]) ? total.get(arr[1])+num : num);
                }
            }
            //출차된 내역이 없는 차량 계산
            for(String key : timeMap.keySet()) {
                String [] arr = timeMap.get(key).split(":");
                int start = Integer.parseInt(arr[0])*60+Integer.parseInt(arr[1]);
                int end = 23*60 + 59; //23:59에 출차된 것으로 계산
                int num = end-start;
                total.put(key, total.containsKey(key) ? total.get(key)+num : num);
            }

            int[] answer = new int[total.size()];
            int i = 0;
            for(String key : total.keySet()) {
                int sum = fees[1]; //기본 요금
                int left = total.get(key) - fees[0]; //누적시간-기본시간
                if(0<left) { //시간이 초과됐을 때
                    sum += ((left/fees[2] + (left%fees[2]>0 ? 1 : 0)) * fees[3]);
                }
                answer[i++] = sum; //해당 차량의 주차요금 저장
            }
            return answer;
        }
    }
}