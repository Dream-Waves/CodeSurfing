package Jieun714;
/**
 * 문제: 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
 *      명령어    수신 탑(높이)
 *      I 숫자   큐에 주어진 숫자를 삽입합니다.
 *      D 1	    큐에서 최댓값을 삭제합니다.
 *      D -1	큐에서 최솟값을 삭제합니다.
 *      이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
 *
 * 제한사항
 *    - operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
 *    - operations의 원소는 큐가 수행할 연산을 나타냅니다.
 *       -  원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
 *    - 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
 *
 * 입출력 예시
 * operations = ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]
 * return = [0, 0]
 * 해결: 이중우선순위큐
 * */
import java.util.*;

public class PRO42628 {
    class Solution {
        public int[] solution(String[] operations) {
            int[] answer = {};
            PriorityQueue<Integer> minQue = new PriorityQueue<>(); //오름차순
            PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder()); //내림차순

            for(String str : operations) {
                String [] arr = str.split(" ");
                if(arr[0].equals("I")) { //큐에 삽입
                    minQue.offer(Integer.parseInt(arr[1]));
                    maxQue.offer(Integer.parseInt(arr[1]));
                } else {
                    if(!maxQue.isEmpty()) {
                        if(arr[1].equals("1")) minQue.remove(maxQue.poll()); //최대값 삭제
                        else maxQue.remove(minQue.poll()); //최솟값 삭제
                    }
                }
            }

            if(maxQue.isEmpty() && minQue.isEmpty()) answer = new int [] {0, 0};
            else answer = new int [] {maxQue.poll(), minQue.poll()};

            return answer; //결과 출력
        }
    }
}