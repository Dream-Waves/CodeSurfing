/*
1713. Silver 1 - 후보 추천하기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           19973	    6074      4654	         31.764%


    문제
        월드초등학교 학생회장 후보는 일정 기간 동안 전체 학생의 추천에 의하여 정해진 수만큼 선정된다. 그래서 학교 홈페이지에 추천받은 학생의 사진을 게시할 수 있는 사진틀을 후보의 수만큼 만들었다. 추천받은 학생의 사진을 사진틀에 게시하고 추천받은 횟수를 표시하는 규칙은 다음과 같다.
            1. 학생들이 추천을 시작하기 전에 모든 사진틀은 비어있다.
            2. 어떤 학생이 특정 학생을 추천하면, 추천받은 학생의 사진이 반드시 사진틀에 게시되어야 한다.
            3. 비어있는 사진틀이 없는 경우에는 현재까지 추천 받은 횟수가 가장 적은 학생의 사진을 삭제하고, 그 자리에 새롭게 추천받은 학생의 사진을 게시한다. 이때, 현재까지 추천 받은 횟수가 가장 적은 학생이 두 명 이상일 경우에는 그러한 학생들 중 게시된 지 가장 오래된 사진을 삭제한다.
            4. 현재 사진이 게시된 학생이 다른 학생의 추천을 받은 경우에는 추천받은 횟수만 증가시킨다.
            5. 사진틀에 게시된 사진이 삭제되는 경우에는 해당 학생이 추천받은 횟수는 0으로 바뀐다.

        후보의 수 즉, 사진틀의 개수와 전체 학생의 추천 결과가 추천받은 순서대로 주어졌을 때, 최종 후보가 누구인지 결정하는 프로그램을 작성하시오.


    입력
        첫째 줄에는 사진틀의 개수 N이 주어진다. (1 ≤ N ≤ 20) 둘째 줄에는 전체 학생의 총 추천 횟수가 주어지고, 셋째 줄에는 추천받은 학생을 나타내는 번호가 빈 칸을 사이에 두고 추천받은 순서대로 주어진다. 총 추천 횟수는 1,000번 이하이며 학생을 나타내는 번호는 1부터 100까지의 자연수이다.


    출력
        사진틀에 사진이 게재된 최종 후보의 학생 번호를 증가하는 순서대로 출력한다.


    예제 입력 1
        3
        9
        2 1 4 3 5 6 2 7 2
    예제 출력 1
        2 6 7


    알고리즘 분류
        구현
        시뮬레이션
*/


// 메모리 : 14684KB
// 시간 : 116ms
// 코드 길이 : 2963B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1713 {
    static int N; // 사진틀의 개수 N (1 ≤ N ≤ 20)
    static ArrayList<int[]> picture; // 추천받은 학생의 사진을 게시하는 사진틀의 정보를 담는 리스트  // [{추천받은 학생 번호, 추천받은 학생의 추천받은 횟수}, {추천받은 학생 번호, 추천받은 학생의 추천받은 횟수}, {추천받은 학생 번호, 추천받은 학생의 추천받은 횟수}, ...]

    public static void take(int studentNumber) { // 추천받은 학생의 사진을 사진틀에 게시하고 추천받은 횟수를 표시하는 메서드
        // 추천받은 학생이 사진틀에 게시된 사진이 존재하는 학생일 경우
        for (int nowPicture[] : picture) {
            if (nowPicture[0] == studentNumber) {
                nowPicture[1] += 1;

                return;
            }
        }

        // 추천받은 학생이 사진틀에 게시된 사진이 존재하지 않는 학생일 경우
        if (picture.size() == N) { // 비어 있는 사진틀이 없을 경우
            int min = Integer.MAX_VALUE; // 현재까지 추천받은 횟수 중 가장 작은 횟수
            int minIndex = 0; // 현재까지 추천받은 횟수가 가장 적은 학생의 사진의 인덱스
            for (int m = 0, num = picture.size(); m < num; m++) {
                if (picture.get(m)[1] < min) {
                    min = picture.get(m)[1];
                    minIndex = m;
                }
            }

            picture.remove(minIndex); // 현재까지 추천받은 횟수가 가장 적은 학생들의 사진 중 게시된 지 가장 오래된 사진 제거
        }

        picture.add(new int[] {studentNumber, 1}); // 추천받은 학생의 사진을 사진틀에 게시
    }

    public static void print() { // 사진틀에 사진이 게재된 최종 후보의 학생 번호를 증가하는 순서대로 출력하는 메서드
        picture.sort(Comparator.comparingInt((int o[]) -> o[0])); // 학생의 번호를 기준으로 오름차순 정렬

        StringBuilder sb = new StringBuilder();
        for (int nowPicture[] : picture) {
            sb.append(nowPicture[0]);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int recommendNumber = Integer.parseInt(bf.readLine()); // 전체 학생의 총 추천 횟수

        picture = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int r = 0; r < recommendNumber; r++) {
            take(Integer.parseInt(token.nextToken())); // 추천받은 학생을 나타내는 번호
        }

        print();
    }
}
