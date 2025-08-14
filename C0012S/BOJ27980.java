/*
27980. Gold 5 - 문자열 게임

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           259	    137       106	         58.242%


    문제
        건덕이는 문자가 일렬로 적혀있는 보드에서 게임을 하고 있다. 보드에는 N 개의 알파벳 대문자가 나란히 적혀있다. 건덕이는 또 다른 M 자리 영어 단어를 가지고 게임을 진행한다.
        우선 보드의 한 지점에서 첫 번째 문자가 보드의 문자와 일치하는지 확인한다. L(왼쪽) 또는 R(오른쪽) 방향으로 이동한 후에 다음 문자와 보드의 문자가 일치하는지 확인한다. 일치할 경우 점수를 1점 얻는다. 단, 이동한 후 보드 바깥으로 벗어날 수 없다. 건덕이가 가지고 있는 단어의 끝에 도달하면 게임을 종료한다.

        예를 들어, 보드에 "KONKUK" 이라는 문자가 적혀있고, "KONDUCK" 이라는 단어로 게임을 시작한다면, 2번째 문자부터 RRRRLL 순으로 이동한다면 마지막 1개의 문자만이 일치해 점수를 1점 얻는다. 최대 점수를 얻으려면, 1번째 문자부터 RRRRLR 순으로 이동하면 된다. 이 경우 "KONU"가 일치하여 점수를 4점 얻는다.
            [그림은 문제에서 참고]

        보드와 가지고 있는 단어가 주어졌을 때, 건덕이가 얻을 수 있는 최대 점수를 구하는 프로그램을 작성해보자.


    입력
        첫째 줄에 보드의 길이와 건덕이가 가지고 있는 문자열의 길이를 나타내는 정수 N, M이 공백으로 구분되어 주어진다. (2 ≤ N, M ≤ 5,000)
        둘째 줄에는 보드의 문자가 순서대로 주어진다.
        셋째 줄에는 가지고 있는 문자열이 주어진다.
        이 때, 두 문자열은 모두 알파벳 대문자로만 구성되어 있다.


    출력
        건덕이가 얻을 수 있는 최대 점수를 출력한다.


    예제 입력 1
        6 7
        KONKUK
        KONDUCK
    예제 출력 1
        4

    예제 입력 2
        2 7
        AB
        AAAAAAA
    예제 출력 2
        4

    예제 입력 3
        3 10
        AAA
        HELLOWORLD
    예제 출력 3
        0


    알고리즘 분류
        다이나믹 프로그래밍
        문자열
*/


// 메모리 : 114428KB
// 시간 : 1024ms
// 코드 길이 : 2749B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ27980 {
    static int N; // 보드의 길이 (2 ≤ N ≤ 5000)
    static int M; // 건덕이가 가지고 있는 단어의 길이 (2 ≤ M ≤ 5000)
    static String boardWord; // 보드에 작성되어 있는 단어
    static String konduckWord; // 건덕이가 가지고 있는 단어
    static int[][] score; // score[now][movedNum] : 건덕이가 가지고 있는 단어의 movedNum 번째 문자에서 보드의 now 번째 문자를 선택하여 앞으로 얻을 수 있는 최대 점수

    public static int move(int movedNum, int now) { // 보드에서 이동하고, 이동해서 얻은 문자를 통해 점수를 계산하는 메서드
        if (movedNum >= M) { // 건덕이가 가지고 있는 단어의 문자 개수 이상으로 보드에서 문자를 선택했을 경우
            return 0;
        }

        if (score[now][movedNum] != -1) { // 이미 구한 점수가 있을 경우
            return score[now][movedNum];
        }

        int nowScore = 0; // movedNum만큼 이동한 현재 위치에서 앞으로의 이동에 따라 얻을 수 있는 최대 점수
        if (now > 0) { // 현재 위치에서 왼쪽 방향으로 이동할 수 있을 경우
            nowScore = Math.max(nowScore, move(movedNum + 1, now - 1));
        }

        if (now < N - 1) { // 현재 위치에서 오른쪽 방향으로 이동할 수 있을 경우
            nowScore = Math.max(nowScore, move(movedNum + 1, now + 1));
        }

        if (boardWord.charAt(now) == konduckWord.charAt(movedNum)) { // 현재 위치의 보드의 문자와 건덕이가 가지고 있는 단어의 이동한 만큼의 순서의 문자가 같을 경우
            nowScore += 1;
        }

        return (score[now][movedNum] = nowScore);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        boardWord = bf.readLine();
        konduckWord = bf.readLine();

        score = new int[N][M];
        for (int i = 0; i < N; i++) { // 점수 계산 여부 구분을 위한 초기화
            Arrays.fill(score[i], -1);
        }

        int maxScore = 0; // 건덕이가 얻을 수 있는 최대 점수
        for (int n = 0; n < N; n++) { // 보드 시작 위치에 따른 점수 계산
            maxScore = Math.max(maxScore, move(0, n));
        }

        System.out.println(maxScore);
    }
}
