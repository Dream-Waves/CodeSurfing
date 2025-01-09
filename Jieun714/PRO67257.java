package Jieun714;
import java.util.*;

public class PRO67257 {
    class Solution {
        public long answer = 0;
        public ArrayList<String> list = new ArrayList<>();
        public ArrayList<Character> setList;
        public ArrayList<String> operCombi = new ArrayList<>();
        public boolean[] isVisited;

        //연산자 조합구하기
        public void combi(StringBuilder current, int depth) {
            if (depth == setList.size()) {
                operCombi.add(current.toString());
                return;
            }

            for (int i=0; i<setList.size(); i++) {
                if (!isVisited[i]) {
                    isVisited[i] = true;
                    current.append(setList.get(i));
                    combi(current, depth + 1);
                    current.deleteCharAt(current.length() - 1); // 백트래킹
                    isVisited[i] = false;
                }
            }
        }

        //계산하기
        public void calc(String str) {
            ArrayList<String> copyList = new ArrayList<>(list);
            for(int i=0; i<str.length(); i++) {
                for(int j=0; j<copyList.size(); j++) {
                    if(copyList.get(j).equals(String.valueOf(str.charAt(i)))) { //같은 연산자일 때
                        long now = 0;
                        long front = Long.parseLong(copyList.get(j-1)); //연산자 앞의 값
                        long end = Long.parseLong(copyList.get(j+1)); //연산자 뒤의 값
                        if(copyList.get(j).equals("+")) now = front + end;
                        else if(copyList.get(j).equals("*")) now = front * end;
                        else now = front - end;

                        copyList.set(j-1, String.valueOf(now)); //j-1 인덱스에 위치한 값을 계산된 값으로 변경
                        copyList.remove(j); //계산이 끝난 연산자 삭제
                        copyList.remove(j); //계산이 끝난 end 값 삭제
                        j--; //인덱스 감소
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(copyList.get(0)))); //최대값 계산
        }

        public long solution(String expression) {
            Set<Character> set = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<expression.length(); i++) {
                char c = expression.charAt(i);
                if (Character.isDigit(c)) sb.append(c); //숫자면 StringBuilder에 추가
                else {
                    set.add(c);
                    if (sb.length() > 0) {
                        list.add(sb.toString()); //숫자 추가
                        list.add(String.valueOf(c)); //연산자 추가
                        sb.setLength(0); //StringBuilder에 초기화
                    }
                }
            }
            list.add(sb.toString());

            isVisited = new boolean[set.size()];
            setList = new ArrayList<>(set);
            combi(new StringBuilder(), 0); //연산자 조합구하기
            for(String ol : operCombi) calc(ol); //가장 큰 상금 금액 구하기

            return answer;
        }
    }
}