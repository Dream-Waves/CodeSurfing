/*
Lv. 2 #17684 - [3차] 압축

    문제 설명
        신입사원 어피치는 카카오톡으로 전송되는 메시지를 압축하여 전송 효율을 높이는 업무를 맡게 되었다. 메시지를 압축하더라도 전달되는 정보가 바뀌어서는 안 되므로, 압축 전의 정보를 완벽하게 복원 가능한 무손실 압축 알고리즘을 구현하기로 했다.
        어피치는 여러 압축 알고리즘 중에서 성능이 좋고 구현이 간단한 LZW(Lempel–Ziv–Welch) 압축을 구현하기로 했다. LZW 압축은 1983년 발표된 알고리즘으로, 이미지 파일 포맷인 GIF 등 다양한 응용에서 사용되었다.

        LZW 압축은 다음 과정을 거친다.
            1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
            2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
            3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
            4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
            5. 단계 2로 돌아간다.

        압축 알고리즘이 영문 대문자만 처리한다고 할 때, 사전은 다음과 같이 초기화된다. 사전의 색인 번호는 정수값으로 주어지며, 1부터 시작한다고 하자.
            색인 번호	    1	2	3	...	    24	25	26
            단어	        A	B	C	...	    X	Y	Z

        예를 들어 입력으로 KAKAO가 들어온다고 하자.
            1. 현재 사전에는 KAKAO의 첫 글자 K는 등록되어 있으나, 두 번째 글자까지인 KA는 없으므로, 첫 글자 K에 해당하는 색인 번호 11을 출력하고, 다음 글자인 A를 포함한 KA를 사전에 27 번째로 등록한다.
            2. 두 번째 글자 A는 사전에 있으나, 세 번째 글자까지인 AK는 사전에 없으므로, A의 색인 번호 1을 출력하고, AK를 사전에 28 번째로 등록한다.
            3. 세 번째 글자에서 시작하는 KA가 사전에 있으므로, KA에 해당하는 색인 번호 27을 출력하고, 다음 글자 O를 포함한 KAO를 29 번째로 등록한다.
            4. 마지막으로 처리되지 않은 글자 O에 해당하는 색인 번호 15를 출력한다.

            현재 입력(w)	다음 글자(c)	출력	    사전 추가(w+c)
            K	        A	        11	    27: KA
            A	        K	        1	    28: AK
            KA	        O	        27	    29: KAO
            O		                15

            이 과정을 거쳐 다섯 글자의 문장 KAKAO가 4개의 색인 번호 [11, 1, 27, 15]로 압축된다.

        입력으로 TOBEORNOTTOBEORTOBEORNOT가 들어오면 다음과 같이 압축이 진행된다.
            현재 입력(w)	다음 글자(c)	출력	    사전 추가(w+c)
            T	        O	        20	    27: TO
            O	        B	        15	    28: OB
            B	        E	        2	    29: BE
            E	        O	        5	    30: EO
            O	        R	        15	    31: OR
            R	        N	        18	    32: RN
            N	        O	        14	    33: NO
            O	        T	        15	    34: OT
            T	        T	        20	    35: TT
            TO	        B	        27	    36: TOB
            BE	        O	        29	    37: BEO
            OR	        T	        31	    38: ORT
            TOB	        E	        36	    39: TOBE
            EO	        R	        30	    40: EOR
            RN	        O	        32	    41: RNO
            OT		                34


    입력 형식
        입력으로 영문 대문자로만 이뤄진 문자열 msg가 주어진다. msg의 길이는 1 글자 이상, 1000 글자 이하이다.


    출력 형식
        주어진 문자열을 압축한 후의 사전 색인 번호를 배열로 출력하라.


    입출력 예제
        msg	                        answer
        KAKAO	                    [11, 1, 27, 15]
        TOBEORNOTTOBEORTOBEORNOT	[20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
        ABABABABABABABAB	        [1, 2, 27, 29, 28, 31, 30]


    해설 보러가기
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (10.14ms, 77.4MB)
        테스트 2 〉	통과 (10.54ms, 73.5MB)
        테스트 3 〉	통과 (11.87ms, 78.8MB)
        테스트 4 〉	통과 (15.10ms, 79.8MB)
        테스트 5 〉	통과 (11.85ms, 73MB)
        테스트 6 〉	통과 (13.15ms, 88.1MB)
        테스트 7 〉	통과 (12.51ms, 77MB)
        테스트 8 〉	통과 (14.52ms, 85.3MB)
        테스트 9 〉	통과 (0.05ms, 81.4MB)
        테스트 10 〉	통과 (11.49ms, 77.1MB)
        테스트 11 〉	통과 (13.26ms, 79.5MB)
        테스트 12 〉	통과 (11.96ms, 84.6MB)
        테스트 13 〉	통과 (17.51ms, 83MB)
        테스트 14 〉	통과 (11.97ms, 88.3MB)
        테스트 15 〉	통과 (17.41ms, 86.4MB)
        테스트 16 〉	통과 (14.34ms, 73.4MB)
        테스트 17 〉	통과 (13.91ms, 80.1MB)
        테스트 18 〉	통과 (11.79ms, 74.1MB)
        테스트 19 〉	통과 (13.43ms, 68.6MB)
        테스트 20 〉	통과 (13.95ms, 79.4MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO17684 {
    static HashMap<String, Integer> dictionary; // 사전 정보를 저장하는 HashMap
    static int len; // 압축할 메시지의 길이
    static int index; // 현재 검사하는 메시지의 인덱스
    static ArrayList<Integer> afterZip; // 주어진 문자열을 압축한 후의 사전 색인 번호를 저장하는 리스트

    public static void makeDictionary() { // 사전 초기 정보를 저장하는 메서드
        for (int c = 1; c <= 26; c++) {
            dictionary.put(String.valueOf((char) (c + '@')), c);
        }
    }

    public static String find(String msg, String now) { // 현재 검사하는 글자를 기준으로 사전에 추가해야 할 단어를 사전에 추가하고, 사전에서 찾아서 출력할 단어를 구하는 메서드
        index += 1;
        while (index <= len - 1) {
            char next = msg.charAt(index); // 현재 검사하고 있는 단어의 다음 글자
            if (!dictionary.containsKey(now + next)) { // 현재 검사하고 있는 단어와 해당 단어의 다음 글자를 합쳐서 만든 단어가 사전에 포함되어 있지 않을 경우
                dictionary.put(now + next, dictionary.size() + 1); // 사전에 현재 검사하고 있는 단어와 해당 단어의 다음 글자를 합쳐서 만든 단어 추가

                break;
            }

            now += next;
            index += 1;
        }

        return now;
    }

    public int[] solution(String msg) {
        dictionary = new HashMap<>();

        makeDictionary();

        len = msg.length();
        afterZip = new ArrayList<>();
        while (index < len) {
            String nowWord = find(msg, String.valueOf(msg.charAt(index))); // 사전에서 찾아서 출력할 단어

            afterZip.add(dictionary.get(nowWord));
        }

        int size = afterZip.size();
        int[] answerZip = new int[size]; // 주어진 문자열을 압축한 후의 사전 색인 번호를 저장하는 배열
        for (int z = 0; z < size; z++) {
            answerZip[z] = afterZip.get(z);
        }

        return answerZip;
    }
}
