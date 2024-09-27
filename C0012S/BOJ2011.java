/*
2011. Gold 5 - 암호코드

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           58922	    11837     8724	         20.524%


    문제
        상근이와 선영이가 다른 사람들이 남매간의 대화를 듣는 것을 방지하기 위해서 대화를 서로 암호화 하기로 했다. 그래서 다음과 같은 대화를 했다.
            · 상근: 그냥 간단히 암호화 하자. A를 1이라고 하고, B는 2로, 그리고 Z는 26으로 하는거야.
            · 선영: 그럼 안돼. 만약, "BEAN"을 암호화하면 25114가 나오는데, 이걸 다시 글자로 바꾸는 방법은 여러 가지가 있어.
            · 상근: 그렇네. 25114를 다시 영어로 바꾸면, "BEAAD", "YAAD", "YAN", "YKD", "BEKD", "BEAN" 총 6가지가 나오는데, BEAN이 맞는 단어라는건 쉽게 알수 있잖아?
            · 선영: 예가 적절하지 않았네 ㅠㅠ 만약 내가 500자리 글자를 암호화 했다고 해봐. 그 때는 나올 수 있는 해석이 정말 많은데, 그걸 언제 다해봐?
            · 상근: 얼마나 많은데?
            · 선영: 구해보자!

        어떤 암호가 주어졌을 때, 그 암호의 해석이 몇 가지가 나올 수 있는지 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 5000자리 이하의 암호가 주어진다. 암호는 숫자로 이루어져 있다.


    출력
        나올 수 있는 해석의 가짓수를 구하시오. 정답이 매우 클 수 있으므로, 1000000으로 나눈 나머지를 출력한다.
        암호가 잘못되어 암호를 해석할 수 없는 경우에는 0을 출력한다.


    예제 입력 1
        25114
    예제 출력 1
        6

    예제 입력 2
        1111111111
    예제 출력 2
        89


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 14192KB
// 시간 : 104ms
// 코드 길이 : 1846B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
    static String code; // 암호
    static int decoder[]; // decoder[n] : n 번째에 오는 문자까지 포함하여 나올 수 있는 암호의 해석 가짓수

    public static void decode() { // 나올 수 있는 암호의 해석 가짓수를 구하는 메서드
        int N = code.length(); // 암호의 길이
        decoder = new int[N + 1];

        if (code.charAt(0) == '0') { // 암호가 '0'으로 시작할 경우
            System.out.println(0);

            return;
        }

        decoder[0] = 1; // n = 0일 때, 암호를 해석할 수 없으므로 0의 값을 저장해야 하지만 점화식 계산을 위해 1을 저장한다.
        decoder[1] = 1; // 첫 번째 문자의 해석 가짓수는 1 개이므로 1을 저장한다.
        for (int n = 2; n <= N; n++) {
            char prevCharacter = code.charAt(n - 2); // 암호의 n - 1 번째 문자
            char nowCharacter = code.charAt(n - 1); // 암호의 n 번째 문자

            if (nowCharacter > '0') { // 암호의 n 번째 문자만 이용하여 하나의 영문자로 해석할 경우
                decoder[n] = (decoder[n - 1] % 1000000);
            }

            if ((prevCharacter == '1') || ((prevCharacter == '2') && (nowCharacter <= '6'))) { // 암호의 n - 1 번째 문자와 암호의 n 번째 문자를 이용하여 하나의 영문자로 해석할 수 있을 경우
                decoder[n] += (decoder[n - 2] % 1000000);
            }
        }

        System.out.println(decoder[N] % 1000000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        code = bf.readLine();

        decode();
    }
}
