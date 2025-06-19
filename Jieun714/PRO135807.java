package Jieun714;
/**
 * 문제: 철수와 영희는 선생님으로부터 숫자가 하나씩 적힌 카드들을 절반씩 나눠서 가진 후, 다음 두 조건 중 하나를 만족하는 가장 큰 양의 정수 a의 값을 구하려고 합니다.
 *       1. 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
 *       2. 영희가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, 철수가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
 *      철수가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayA와 영희가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayB가 주어졌을 때,
 *      주어진 조건을 만족하는 가장 큰 양의 정수 a를 return하도록 solution 함수를 완성해 주세요. 만약, 조건을 만족하는 a가 없다면, 0을 return 해 주세요.
 * 해결: 최대 공약수 구하기(구현)
 * */
public class PRO135807 {
    class Solution {
        public int find(int [] aArr, int [] bArr) {
            int answer = 0;
            int num = 2;
            while(num <= aArr[0]) {
                boolean flag = true;
                for(int i=0; i<aArr.length; i++) {
                    if(aArr[i]%num != 0 || bArr[i]%num == 0) { //aArr의 모든 요소가 num으로 나눠지고, bArr의 어떤 요소도 num으로 나눠지지 않아야 함
                        flag = false;  //조건을 만족하지 않으면 false로 설정하고 루프 종료
                        break;
                    }
                }
                if(flag) answer = num; //조건을 만족하면 answer 갱신
                num += 1;
            }
            return answer;
        }

        public int solution(int[] arrayA, int[] arrayB) {
            int answer = Math.max(find(arrayB, arrayA), find(arrayA, arrayB));
            return answer;
        }
    }
}
