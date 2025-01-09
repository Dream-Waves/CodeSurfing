package Jieun714;
/**
 * 문제: IT 벤처 회사를 운영하고 있는 라이언은 매년 사내 해커톤 대회를 개최하여 우승자에게 상금을 지급하고 있습니다.
 *      이번 대회에서는 우승자에게 지급되는 상금을 이전 대회와는 다르게 다음과 같은 방식으로 결정하려고 합니다.
 *      해커톤 대회에 참가하는 모든 참가자들에게는 숫자들과 3가지의 연산문자(+, -, *) 만으로 이루어진 연산 수식이 전달되며, 참가자의 미션은 전달받은 수식에 포함된 연산자의 우선순위를 자유롭게 재정의하여 만들 수 있는 가장 큰 숫자를 제출하는 것입니다.
 *      단, 연산자의 우선순위를 새로 정의할 때, 같은 순위의 연산자는 없어야 합니다. 즉, + > - > * 또는 - > * > + 등과 같이 연산자 우선순위를 정의할 수 있으나 +,* > - 또는 * > +,-처럼 2개 이상의 연산자가 동일한 순위를 가지도록 연산자 우선순위를 정의할 수는 없습니다. 수식에 포함된 연산자가 2개라면 정의할 수 있는 연산자 우선순위 조합은 2! = 2가지이며, 연산자가 3개라면 3! = 6가지 조합이 가능합니다.
 *      만약 계산된 결과가 음수라면 해당 숫자의 절댓값으로 변환하여 제출하며 제출한 숫자가 가장 큰 참가자를 우승자로 선정하며, 우승자가 제출한 숫자를 우승상금으로 지급하게 됩니다.
 *      참가자에게 주어진 연산 수식이 담긴 문자열 expression이 매개변수로 주어질 때, 우승 시 받을 수 있는 가장 큰 상금 금액을 return 하도록 solution 함수를 완성해주세요.
 * [제한사항]
 *  - expression은 길이가 3 이상 100 이하인 문자열입니다.
 *  - expression은 공백문자, 괄호문자 없이 오로지 숫자와 3가지의 연산자(+, -, *) 만으로 이루어진 올바른 중위표기법(연산의 두 대상 사이에 연산기호를 사용하는 방식)으로 표현된 연산식입니다. 잘못된 연산식은 입력으로 주어지지 않습니다.
 *    즉, "402+-561*"처럼 잘못된 수식은 올바른 중위표기법이 아니므로 주어지지 않습니다.
 *  - expression의 피연산자(operand)는 0 이상 999 이하의 숫자입니다.
 *    즉, "100-2145*458+12"처럼 999를 초과하는 피연산자가 포함된 수식은 입력으로 주어지지 않습니다.
 *    "-56+100"처럼 피연산자가 음수인 수식도 입력으로 주어지지 않습니다.
 *  - expression은 적어도 1개 이상의 연산자를 포함하고 있습니다.
 *  - 연산자 우선순위를 어떻게 적용하더라도, expression의 중간 계산값과 최종 결괏값은 절댓값이 263 - 1 이하가 되도록 입력이 주어집니다.
 *  - 같은 연산자끼리는 앞에 있는 것의 우선순위가 더 높습니다.
 * 해결: 구현
 * */
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