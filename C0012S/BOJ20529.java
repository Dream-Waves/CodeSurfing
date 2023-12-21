/*
20529. Silver 1 - 가장 가까운 세 사람의 심리적 거리

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    1536 MB          8659	    3326      2558	         36.079%


    문제
        여러분은 요즘 유행하는 심리검사인 MBTI에 대해 들어보았는가?
        MBTI(Myers-Briggs Type Indicator)는 C.G.Jung의 심리유형론을 근거로 하여 Katharine Cook Briggs와 Isabel Briggs Myers가 보다 쉽고 일상생활에 유용하게 활용할 수 있도록 고안한 자기보고식 성격유형지표이다. (출처: 위키백과)

        MBTI는 아래와 같이 네 가지 척도로 사람들의 성격을 구분한다.
            · 외향(E) / 내향(I)
            · 감각(S) / 직관(N)
            · 사고(T) / 감정(F)
            · 판단(J) / 인식(P)

        각 척도마다 두 가지 분류가 존재하므로, MBTI는 총 2^4 = 16$가지 유형이 있음을 알 수 있다. 일반적으로 MBTI의 유형들은 각 분류를 나타내는 알파벳 한 글자씩을 따 네 글자로 표시하게 된다. 모든 유형의 목록은 다음과 같다.
            · ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ

        MBTI 성격 유형을 이용하면 두 사람 사이의 심리적인 거리를 정의할 수 있다. 이는 두 사람의 MBTI 유형에서 서로 다른 분류에 속하는 척도의 수로 정의된다. 예를 들어, MBTI 유형이 ISTJ인 사람과 ISFJ인 사람 사이의 거리는 1이며, INTP인 사람과 ENTJ인 사람 사이의 거리는 2이다.

        이 정의를 확장해서 세 사람 사이의 심리적인 거리도 정의할 수 있다. 세 사람 A, B, C가 있을 때 이들의 심리적인 거리는
            (A와 B 사이의 심리적인 거리) + (B와 C 사이의 심리적인 거리) + (A와 C 사이의 심리적인 거리)
        로 정의한다.

        대학교에서 심리학 교수로 일하는 종서는 자신이 가르치는 학생들의 심리적인 특성을 분석하고 싶어한다.
        오늘이 생일인 종서를 위해 N명의 학생들의 MBTI 유형이 주어질 때, 가장 가까운 세 학생 사이의 심리적인 거리를 구해보자.


    입력
        첫 줄에는 테스트 케이스의 수를 나타내는 정수 T가 주어진다.
        각 테스트 케이스의 첫 줄에는 학생의 수를 나타내는 하나의 정수 N이 주어지며, 두 번째 줄에는 각 학생의 MBTI 성격 유형을 나타내는 문자열들이 사이에 공백을 두고 주어진다.


    출력
        각 테스트 케이스에 대한 답을 정수 형태로 한 줄에 하나씩 출력한다.


    제한
        · 1 ≤ T ≤ 50
        · 3 ≤ N ≤ 100000
        · 각 테스트 케이스의 N의 합은 100000을 넘지 않는다.


    예제 입력 1
        3
        3
        ENTJ INTP ESFJ
        4
        ESFP ESFP ESFP ESFP
        5
        INFP INFP ESTP ESTJ ISTJ
    예제 출력 1
        8
        0
        4

    · 첫 번째 테스트 케이스의 경우, ENTJ와 INTP의 심리적인 거리는 2, ENTJ와 ESFJ의 심리적인 거리는 2, INTP와 ESFJ의 심리적인 거리는 4이므로 세 사람의 심리적인 거리는 2 + 2 + 4 = 8이다.
    · 두 번째 테스트 케이스의 경우, 어떤 사람 둘을 골라도 심리적인 거리가 0이므로 가장 가까운 세 사람의 심리적인 거리는 0이다.
    · 세 번째 테스트 케이스의 경우, 심리적인 거리를 최소화하려면 MBTI가 ESTP, ESTJ, ISTJ인 세 사람을 골라야 한다. ESTP와 ESTJ의 심리적인 거리는 1, ESTP와 ISTJ의 심리적인 거리는 2, ESTJ와 ISTJ의 심리적인 거리는 1이므로 세 사람의 심리적인 거리는 1 + 2 + 1 = 4이다.


    알고리즘 분류
        브루트포스 알고리즘
        비둘기집 원리
*/


// 메모리 : 24640KB
// 시간 : 268ms
// 코드 길이 : 3402B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20529 {
    static int N; // 학생의 수 (3 ≤ N ≤ 100000)
    static String mbtiArray[]; // 각 학생의 MBTI 성격 유형을 저장하는 배열
    static boolean isSelected[]; // 해당 인덱스의 학생을 선택했는지의 여부를 저장하는 배열
    static String selectedMbti[]; // 전체 학생 중 선택된 세 학생의 MBTI 성격 유형을 저장하는 배열
    static int shortestMindDistance; // 가장 가까운 세 학생의 심리적 거리

    public static int calculateMindDistance(String mbti1, String mbti2) { // 두 사람 사이의 심리적 거리를 구하는 메서드
        int mindDistance = 0;
        for (int m = 0; m < 4; m++) {
            if (mbti1.charAt(m) != mbti2.charAt(m)) {
                mindDistance += 1;
            }
        }

        return mindDistance;
    }

    public static int calculateSelectedStudentMindDistance() { // 선택된 세 학생의 심리적 거리를 구하는 메서드
        int selectedStudentMindDistance = 0;

        selectedStudentMindDistance += calculateMindDistance(selectedMbti[0], selectedMbti[1]);
        selectedStudentMindDistance += calculateMindDistance(selectedMbti[0], selectedMbti[2]);
        selectedStudentMindDistance += calculateMindDistance(selectedMbti[1], selectedMbti[2]);

        return selectedStudentMindDistance;
    }

    public static void calculateShortestMindDistance(int start, int r) { // 전체 학생 중 세 학생을 선택해 심리적 거리를 구하고, 그중 가장 가까운 세 학생의 심리적 거리를 구하는 메서드
        if (r == 3) {
            int num = 0;
            for (int v = 0; v < N; v++) {
                if (isSelected[v]) {
                    selectedMbti[num] = mbtiArray[v];
                    num += 1;
                }
            }

            shortestMindDistance = Math.min(shortestMindDistance, calculateSelectedStudentMindDistance());

            return;
        }

        for (int a = start; a < N; a++) {
            isSelected[a] = true;
            calculateShortestMindDistance(a + 1, r + 1);
            isSelected[a] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 수 (1 ≤ T ≤ 50)

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(bf.readLine());
            mbtiArray = new String[N];

            StringTokenizer token = new StringTokenizer(bf.readLine());
            for (int n = 0; n < N; n++) {
                mbtiArray[n] = token.nextToken();
            }

            isSelected = new boolean[N];
            selectedMbti = new String[3];
            shortestMindDistance = Integer.MAX_VALUE;
            if (N > 32) { // 비둘기집 원리에 의하여 학생의 수가 32를 넘으면 반드시 같은 MBTI 성격 유형이 3 개는 나오므로 가장 가까운 세 학생의 심리적 거리는 0이다.
                shortestMindDistance = 0;
            }
            else {
                calculateShortestMindDistance(0, 0);
            }

            System.out.println(shortestMindDistance);
        }
    }
}
