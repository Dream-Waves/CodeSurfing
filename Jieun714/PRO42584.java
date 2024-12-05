package Jieun714;
/**
 * 문제: 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때,
 *      가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
 * 제한사항:
 *  - prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
 *  - prices의 길이는 2 이상 100,000 이하입니다.
 * 해결: 스택
 * */
import java.util.*;
public class PRO42584 {
    public class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            Stack<Integer> stack = new Stack<>(); //stack 초기화

            for(int i=0; i<prices.length; i++) {
                //stack이 비어있지 않고, 스택의 peek 값에 해당하는 주가보다 현재 주가가 작을 때(주가가 떨어졌을때)
                while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                    int idx = stack.pop();
                    answer[idx] = i - idx; //가격 떨어진 시점 저장
                }
                stack.push(i); //prices의 인덱스 값 삽입
            }

            while(!stack.isEmpty()) { //스택에 남아 있는 데이터가 있을 때
                int idx = stack.pop();
                answer[idx] = prices.length -1 - idx; //가격 유지된 시점 저장
            }
            return answer; //결과 리턴
        }
    }
}