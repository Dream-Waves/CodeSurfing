/*
Lv. 2 #92335 - k진수에서 소수 개수 구하기

    문제 설명
        양의 정수 n이 주어집니다. 이 숫자를 k진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.
            · 0P0처럼 소수 양쪽에 0이 있는 경우
            · P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
            · 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
            · P처럼 소수 양쪽에 아무것도 없는 경우
            · 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
                · 예를 들어, 101은 P가 될 수 없습니다.

        예를 들어, 437674을 3진수로 바꾸면 211020101011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. (211, 2, 11을 k진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.) 211은 P0 형태에서 찾을 수 있으며, 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.

        정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록 solution 함수를 완성해 주세요.


    제한사항
        · 1 ≤ n ≤ 1,000,000
        · 3 ≤ k ≤ 10


    입출력 예
        n	    k	result
        437674	3	3
        110011	10	2


    입출력 예 설명
        입출력 예 #1
            문제 예시와 같습니다.

        입출력 예 #2
            110011을 10진수로 바꾸면 110011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 11, 11 2개입니다. 이와 같이, 중복되는 소수를 발견하더라도 모두 따로 세어야 합니다.


    문제가 잘 안풀린다면😢
        힌트가 필요한가요? [코딩테스트 연습 힌트 모음집]으로 오세요! → 클릭
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (11.18ms, 76.4MB)
        테스트 2 〉	통과 (0.10ms, 77.6MB)
        테스트 3 〉	통과 (0.26ms, 76.1MB)
        테스트 4 〉	통과 (0.16ms, 79.5MB)
        테스트 5 〉	통과 (0.14ms, 77MB)
        테스트 6 〉	통과 (0.26ms, 73.6MB)
        테스트 7 〉	통과 (0.14ms, 75.8MB)
        테스트 8 〉	통과 (0.18ms, 71.9MB)
        테스트 9 〉	통과 (0.20ms, 78.4MB)
        테스트 10 〉	통과 (0.20ms, 72.4MB)
        테스트 11 〉	통과 (0.11ms, 78.2MB)
        테스트 12 〉	통과 (0.14ms, 75.9MB)
        테스트 13 〉	통과 (0.13ms, 77.1MB)
        테스트 14 〉	통과 (0.08ms, 77MB)
        테스트 15 〉	통과 (0.17ms, 78.2MB)
        테스트 16 〉	통과 (0.09ms, 72.3MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

public class PRO92335 {
    static String number; // 양의 정수 n을 k진수로 변환한 수
    static int answer; // 양의 정수 n을 k진수로 변환한 수 안의 조건에 맞는 소수의 개수

    public static String makeNumber(int n, int k) { // 양의 정수 n을 k진수로 변환하는 메서드
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append(n % k);

            n /= k;
        }

        return sb.reverse().toString();
    }

    public static boolean checkPrimeNumber(long originalNumber) { // originalNumber가 소수인지 검사하는 메서드
        if (originalNumber == 1) {
            return false;
        }

        long size = (long) Math.sqrt(originalNumber);
        for (int i = 2; i <= size; i++) {
            if (originalNumber % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void find() { // 양의 정수 n을 k진수로 변환한 수 안에 조건에 맞는 소수가 몇 개인지 구하는 메서드
        String numberList[] = number.split("0"); // 조건에 맞는 수들만 분리
        for (int m = 0, len = numberList.length; m < len; m++) {
            if (numberList[m].equals("")) { // 공백일 경우
                continue;
            }

            long subNumber = Long.parseLong(numberList[m]); // 10진수로 변환
            if (checkPrimeNumber(subNumber)) { // 소수일 경우
                answer += 1;
            }
        }
    }

    public int solution(int n, int k) {
        number = makeNumber(n, k);
        find();

        return answer;
    }
}