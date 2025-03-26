/*
3078. Gold 4 - 좋은 친구

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           10827	    3460      2603	         31.814%


    문제
        상근이는 환갑을 바라보던 나이에 수능 시험을 다시보고 교대에 입학했고, 초등학교 선생님으로 취직했다.
            · 상근: 요즘 애들은 친구를 사귀지 않나봐. 내가 앞에서 보고 있으면, 친구가 있는 학생이 별로 없는 것 같아.
            · ??: 오빠! 오빠는 말콤의 친구와 성적이라는 책 안 읽어 봤어? 이 책에는 성적과 친구가 무슨 관계가 있는지 나와. 요즘 애들은 친구를 사귀기 전에 먼저 그 친구의 반 등수를 살펴봐. 말콤은 이 연구를 하기 위해서 6년동안 초등학교에서 선생님으로 위장 했었지. 하지만, 6년이라는 시간을 초등학교에서 보냈지만, 그 사람은 결국 결론을 얻지 못했어.
            · 상근: 근데?
            · ??: 말콤이 어느 날 자신이 초등학생이 되어 학교를 활보하는 꿈을 꾸었어. 근데 잠을 깨고 나니 내가 꿈을 꾸고 초등학생이 된건지, 아니면 초등학생이 꿈을 꾸고 지금의 내가 되어있는지를 모르겠는거야. 그래서 말콤은 상식적인 사고 방식에 큰 의문을 가졌지. 그 때 말콤은 깨달았던거야. 초등학교 친구는 부질없구나. 그제서야 알게된거야. 모든 학생은 자신과 반 등수의 차이가 K를 넘으면 친구가 아니라는거.
            · 상근: 아? 근데 K는 어떻게 구해?
            · ??: K는 문제에서 주어지지. 근데, 더 중요한 사실이 있어. 친구와 좋은 친구의 차이야. 말콤이 친구와 성적을 쓰고 2년 뒤에 낸 책인 좋은 친구라는 책에는 좋은 친구는 이름의 길이가 같아야 된다는 말이 나와.
            · 상근: 아! 그럼 난 오늘 집에 가서 우리 반에 좋은 친구가 몇 쌍이나 있는지 구해봐야 겠어!

        상근이네 반의 N명 학생들의 이름이 성적순으로 주어졌을 때, 좋은 친구가 몇 쌍이나 있는지 구하는 프로그램을 작성하시오. 좋은 친구는 등수의 차이가 K보다 작거나 같으면서 이름의 길이가 같은 친구이다.


    입력
        첫째 줄에 N과 K가 주어진다. (3 ≤ N ≤ 300,000, 1 ≤ K ≤ N) 다음 N개 줄에는 상근이네 반 학생의 이름이 성적순으로 주어진다. 이름은 알파벳 대문자로 이루어져 있고, 2글자 ~ 20글자이다.


    출력
        첫째 줄에 좋은 친구가 몇 쌍이 있는지 출력한다.


    예제 입력 1
        4 2
        IVA
        IVO
        ANA
        TOM
    예제 출력 1
        5

    예제 입력 2
        6 3
        CYNTHIA
        LLOYD
        STEVIE
        KEVIN
        MALCOLM
        DABNEY
    예제 출력 2
        2


    알고리즘 분류
        자료 구조
        슬라이딩 윈도우
        큐
*/


// 메모리 : 36604KB
// 시간 : 268ms
// 코드 길이 : 2127B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3078 {
    static int N; // 상근이네 반 학생 수
    static int K; // 좋은 친구가 될 수 있는 등수의 차이의 최댓값
    static int[] student; // 각 학생의 이름의 길이를 저장하는 배열
    static int[] nameLength; // 각 인덱스의 값을 이름의 길이로 가지고 있는 학생의 수를 저장하는 배열
    static long count; // 좋은 친구의 쌍의 수

    public static void find() { // 반 등수의 차이가 K 이하이고, 이름의 길이가 같은 좋은 친구의 쌍의 수를 구하는 메서드
        for (int n = 1; n < N; n++) {
            nameLength[student[n - 1]] -= 1; // 학생 (K + 1) 명의 집합의 첫 번째 학생의 이름의 길이와 같은 이름의 길이를 가진 학생 수 감소 (학생 (K + 1) 명의 집합의 첫 번째 학생 즉, 본인 제외)
            count += nameLength[student[n - 1]]; // 학생 (K + 1) 명의 집합의 첫 번째 학생과 좋은 친구를 맺은 쌍의 수 = 학생 (K + 1) 명의 집합의 첫 번째 학생의 이름의 길이와 같은 이름의 길이를 가진 학생 수

            if (n < N - K) { // 한 집합에 속한 학생의 수가 (K + 1) 명이 될 수 있을 경우
                nameLength[student[n + K]] += 1;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        student = new int[N];
        nameLength = new int[21];
        for (int i = 0; i < N; i++) {
            student[i] = bf.readLine().length();

            if ((i <= K)) { // 첫 번째 학생부터 학생 (K + 1) 명을 한 집합으로 구성
                nameLength[student[i]] += 1;
            }
        }

        find();
    }
}
