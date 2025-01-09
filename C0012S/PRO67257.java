/*
Lv. 2 #67257 - [카카오 인턴] 수식 최대화

    문제 설명
        IT 벤처 회사를 운영하고 있는 라이언은 매년 사내 해커톤 대회를 개최하여 우승자에게 상금을 지급하고 있습니다.
        이번 대회에서는 우승자에게 지급되는 상금을 이전 대회와는 다르게 다음과 같은 방식으로 결정하려고 합니다.
        해커톤 대회에 참가하는 모든 참가자들에게는 숫자들과 3가지의 연산문자(+, -, *) 만으로 이루어진 연산 수식이 전달되며, 참가자의 미션은 전달받은 수식에 포함된 연산자의 우선순위를 자유롭게 재정의하여 만들 수 있는 가장 큰 숫자를 제출하는 것입니다.
        단, 연산자의 우선순위를 새로 정의할 때, 같은 순위의 연산자는 없어야 합니다. 즉, + > - > * 또는 - > * > + 등과 같이 연산자 우선순위를 정의할 수 있으나 +,* > - 또는 * > +,-처럼 2개 이상의 연산자가 동일한 순위를 가지도록 연산자 우선순위를 정의할 수는 없습니다. 수식에 포함된 연산자가 2개라면 정의할 수 있는 연산자 우선순위 조합은 2! = 2가지이며, 연산자가 3개라면 3! = 6가지 조합이 가능합니다.
        만약 계산된 결과가 음수라면 해당 숫자의 절댓값으로 변환하여 제출하며 제출한 숫자가 가장 큰 참가자를 우승자로 선정하며, 우승자가 제출한 숫자를 우승상금으로 지급하게 됩니다.

        예를 들어, 참가자 중 네오가 아래와 같은 수식을 전달받았다고 가정합니다.
            "100-200*300-500+20"

        일반적으로 수학 및 전산학에서 약속된 연산자 우선순위에 따르면 더하기와 빼기는 서로 동등하며 곱하기는 더하기, 빼기에 비해 우선순위가 높아 * > +,- 로 우선순위가 정의되어 있습니다.
        대회 규칙에 따라 + > - > * 또는 - > * > + 등과 같이 연산자 우선순위를 정의할 수 있으나 +,* > - 또는 * > +,- 처럼 2개 이상의 연산자가 동일한 순위를 가지도록 연산자 우선순위를 정의할 수는 없습니다.
        수식에 연산자가 3개 주어졌으므로 가능한 연산자 우선순위 조합은 3! = 6가지이며, 그 중 + > - > * 로 연산자 우선순위를 정한다면 결괏값은 22,000원이 됩니다.
        반면에 * > + > - 로 연산자 우선순위를 정한다면 수식의 결괏값은 -60,420 이지만, 규칙에 따라 우승 시 상금은 절댓값인 60,420원이 됩니다.
        참가자에게 주어진 연산 수식이 담긴 문자열 expression이 매개변수로 주어질 때, 우승 시 받을 수 있는 가장 큰 상금 금액을 return 하도록 solution 함수를 완성해주세요.


    [제한사항]
        · expression은 길이가 3 이상 100 이하인 문자열입니다.
        · expression은 공백문자, 괄호문자 없이 오로지 숫자와 3가지의 연산자(+, -, *) 만으로 이루어진 올바른 중위표기법(연산의 두 대상 사이에 연산기호를 사용하는 방식)으로 표현된 연산식입니다. 잘못된 연산식은 입력으로 주어지지 않습니다.
            · 즉, "402+-561*"처럼 잘못된 수식은 올바른 중위표기법이 아니므로 주어지지 않습니다.
        · expression의 피연산자(operand)는 0 이상 999 이하의 숫자입니다.
            · 즉, "100-2145*458+12"처럼 999를 초과하는 피연산자가 포함된 수식은 입력으로 주어지지 않습니다.
            · "-56+100"처럼 피연산자가 음수인 수식도 입력으로 주어지지 않습니다.
        · expression은 적어도 1개 이상의 연산자를 포함하고 있습니다.
        · 연산자 우선순위를 어떻게 적용하더라도, expression의 중간 계산값과 최종 결괏값은 절댓값이 263 - 1 이하가 되도록 입력이 주어집니다.
        · 같은 연산자끼리는 앞에 있는 것의 우선순위가 더 높습니다.


    입출력 예
        expression	            result
        "100-200*300-500+20"	60420
        "50*6-3*2"	            300


    입출력 예에 대한 설명
        입출력 예 #1
            * > + > - 로 연산자 우선순위를 정했을 때, 가장 큰 절댓값을 얻을 수 있습니다.

            연산 순서는 아래와 같습니다.
                100-200*300-500+20
                = 100-(200*300)-500+20
                = 100-60000-(500+20)
                = (100-60000)-520
                = (-59900-520)
                = -60420

            따라서, 우승 시 받을 수 있는 상금은 |-60420| = 60420 입니다.

        입출력 예 #2
            - > * 로 연산자 우선순위를 정했을 때, 가장 큰 절댓값을 얻을 수 있습니다.

            연산 순서는 아래와 같습니다.(expression에서 + 연산자는 나타나지 않았으므로, 고려할 필요가 없습니다.)
                50*6-3*2
                = 50*(6-3)*2
                = (50*3)*2
                = 150*2
                = 300

            따라서, 우승 시 받을 수 있는 상금은 300 입니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.25ms, 81.5MB)
        테스트 2 〉	통과 (0.26ms, 87.6MB)
        테스트 3 〉	통과 (0.30ms, 74.6MB)
        테스트 4 〉	통과 (0.33ms, 72.8MB)
        테스트 5 〉	통과 (0.61ms, 83.9MB)
        테스트 6 〉	통과 (0.37ms, 90.7MB)
        테스트 7 〉	통과 (0.42ms, 75MB)
        테스트 8 〉	통과 (0.46ms, 83.1MB)
        테스트 9 〉	통과 (0.81ms, 87.1MB)
        테스트 10 〉	통과 (0.68ms, 77.8MB)
        테스트 11 〉	통과 (0.79ms, 75.7MB)
        테스트 12 〉	통과 (0.71ms, 77.7MB)
        테스트 13 〉	통과 (0.77ms, 80MB)
        테스트 14 〉	통과 (0.89ms, 90MB)
        테스트 15 〉	통과 (0.70ms, 78.9MB)
        테스트 16 〉	통과 (0.37ms, 71.5MB)
        테스트 17 〉	통과 (0.30ms, 82.4MB)
        테스트 18 〉	통과 (0.27ms, 88MB)
        테스트 19 〉	통과 (0.37ms, 80.4MB)
        테스트 20 〉	통과 (0.42ms, 71.6MB)
        테스트 21 〉	통과 (0.75ms, 81.3MB)
        테스트 22 〉	통과 (1.02ms, 91.9MB)
        테스트 23 〉	통과 (0.25ms, 81.8MB)
        테스트 24 〉	통과 (0.99ms, 82.3MB)
        테스트 25 〉	통과 (0.76ms, 85.1MB)
        테스트 26 〉	통과 (0.31ms, 83.9MB)
        테스트 27 〉	통과 (0.74ms, 74.4MB)
        테스트 28 〉	통과 (0.77ms, 80.9MB)
        테스트 29 〉	통과 (1.00ms, 74.6MB)
        테스트 30 〉	통과 (0.62ms, 82.4MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO67257 {
    static ArrayList<Long> numberList; // 수식에 존재하는 숫자 리스트
    static ArrayList<Character> operatorList; // 수식에 존재하는 연산자 리스트
    static int operatorNum; // 수식에 사용되는 연산자 종류의 개수
    static boolean[] isSelected; // 연산자의 선택 여부
    static int[] selection; // 연산자 우선 순위를 저장하는 배열  // 인덱스의 값을 기준으로 0을 + 연산으로, 1을 - 연산으로, 2를 * 연산으로 지정
    static HashMap<Integer, Character> operatorMap; // selection의 인덱스 값을 Key로 삼아 연산자를 저장하는 HashMap
    static long maxAnswer; // 연산자 우선 순위에 따라 계산한 결괏값들의 절댓값 중 최댓값

    public static void splitNumber(String expression) { // 수식의 피연산자와 연산자를 분리하는 메서드
        numberList = new ArrayList<>();
        operatorList = new ArrayList<>();

        int index = 0;
        for (int i = 0, len = expression.length(); i < len; i++) {
            if (expression.charAt(i) - '0' < 0) {
                numberList.add(Long.parseLong(expression.substring(index, i)));
                operatorList.add(expression.charAt(i));

                index = i + 1;
            }
        }

        operatorNum = operatorList.size();
        numberList.add(Long.parseLong(expression.substring(index, expression.length())));
    }

    public static long operation(Long number1, Long number2, Character operator) { // 연산자에 따라 연산하는 메서드
        if (operator == '+') {
            return number1 + number2;
        }
        else if (operator == '-') {
            return number1 - number2;
        }

        return number1 * number2;
    }

    public static long calculate() { // 연산자의 우선 순위에 따라 수식을 계산하는 메서드
        ArrayList<Long> copiedNumberList = (ArrayList<Long>) numberList.clone();
        ArrayList<Character> copiedOperatorList = (ArrayList<Character>) operatorList.clone();

        for (int n = 0; n < operatorNum; n++) {
            for (int p = 0, size = copiedOperatorList.size(); p < size;) {
                if (copiedOperatorList.get(p) == operatorMap.get(selection[n])) { // 현재 연산자 우선 순위의 연산자와 같은 연산자일 경우
                    long value = operation(copiedNumberList.get(p), copiedNumberList.get(p + 1), operatorMap.get(selection[n])); // 현재 연산자 우선 순위에 해당하는 연산의 결괏값

                    copiedNumberList.remove(p + 1);
                    copiedNumberList.remove(p);
                    copiedNumberList.add(p, value);

                    copiedOperatorList.remove(p);
                    size--;

                    continue;
                }

                p++;
            }
        }

        return Math.abs(copiedNumberList.get(0));
    }

    public static void findOrder(int selectedNum) { // 연산자의 우선 순위의 조합을 구하고, 연산자 우선 순위에 따라 계산한 결괏값들의 절댓값 중 최댓값을 구하는 메서드
        if (selectedNum >= operatorNum) {
            maxAnswer = Math.max(maxAnswer, calculate());

            return;
        }

        for (int o = 0; o < operatorNum; o++) {
            if (!isSelected[o]) {
                isSelected[o] = true;
                selection[selectedNum] = o;
                findOrder(selectedNum + 1);
                isSelected[o] = false;
            }
        }
    }

    public long solution(String expression) {
        splitNumber(expression);

        operatorMap = new HashMap<>(); // {연산자 고유 숫자, 연산자}
        operatorMap.put(0, '+');
        operatorMap.put(1, '-');
        operatorMap.put(2, '*');

        if (operatorNum >= 3) { // 수식의 연산자 개수가 3 개 이상일 경우
            operatorNum = 3; // 연산자 종류의 개수는 최대 3 개
        }

        isSelected = new boolean[operatorNum];
        selection = new int[operatorNum];
        findOrder(0);

        return maxAnswer;
    }
}
