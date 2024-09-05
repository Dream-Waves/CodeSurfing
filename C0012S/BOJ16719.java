/*
16719. Gold 5 - ZOAC

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           2946	    1506      1242	         53.696%


    문제
        2018년 12월, 처음 시작하게 된 ZOAC의 오프닝을 맡은 성우는 누구보다 화려하게 ZOAC를 알리려 한다.
        앞 글자부터 하나씩 보여주는 방식은 너무 식상하다고 생각한 성우는 문자열을 보여주는 새로운 규칙을 고안해냈다!
        규칙은 이러하다. 아직 보여주지 않은 문자 중 추가했을 때의 문자열이 사전 순으로 가장 앞에 오도록 하는 문자를 보여주는 것이다.
        예를 들어 ZOAC를 보여주고 싶다면, A → AC → OAC → ZOAC 순으로 보여주면 된다.
        바쁜 성우를 위하여 이 규칙대로 출력해주는 프로그램을 작성하시오.


    입력
        첫 번째 줄에 알파벳 대문자로 구성된 문자열이 주어진다. 문자열의 길이는 최대 100자이다.


    출력
        규칙에 맞게 순서대로 문자열을 출력한다.


    예제 입력 1
        ZOAC
    예제 출력 1
        A
        AC
        OAC
        ZOAC

    예제 입력 2
        BAC
    예제 출력 2
        A
        AC
        BAC

    예제 입력 3
        STARTLINK
    예제 출력 3
        A
        AI
        AIK
        AINK
        ALINK
        ARLINK
        ARTLINK
        SARTLINK
        STARTLINK


    힌트
        ZOAC는 한양대학교 ERICA 알고리즘 학회 ‘영과일’에서 주최하는 알고리즘 대회 Zero One Algorithm Contest 의 약자이다.


    알고리즘 분류
        구현
        문자열
        재귀
*/


// 메모리 : 14236KB
// 시간 : 108ms
// 코드 길이 : 1860B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16719 {
    static String word; // 문자열
    static int length; // 문자열의 길이
    static char charArray[]; // 보여 줄 문자열의 문자를 저장하는 배열
    static StringBuilder sb;

    public static void print() { // 보여 줄 문자열을 출력하는 메서드
        for (char character : charArray) {
            if (character != '\u0000') {
                sb.append(character);
            }
        }

        sb.append("\n");
    }

    public static void show(int start, int end) { // 문자열의 문자 중 보여 줄 문자를 구하는 메서드
        char minChar = '['; // 사전 순으로 가장 앞에 오는 문자
        int minIndex = 0; // 사전 순으로 가장 앞에 오는 문자의 문자열 인덱스
        for (int w = start; w <= end; w++) {
            if (word.charAt(w) < minChar) {
                minChar = word.charAt(w);
                minIndex = w;
            }
        }

        charArray[minIndex] = minChar; // 문자열 중 보여 줄 문자 선택
        print(); // 보여 줄 문자열 출력

        // 해당 문자 기준으로 오른쪽에 존재하는 문자들 검사
        if (minIndex != end) {
            show(minIndex + 1, end);
        }

        // 해당 문자 기준으로 왼쪽에 존재하는 문자들 검사
        if (minIndex != start) {
            show(start, minIndex - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        word = bf.readLine();

        length = word.length();
        charArray = new char[length];
        sb = new StringBuilder();
        show(0, length - 1);

        System.out.println(sb);
    }
}
