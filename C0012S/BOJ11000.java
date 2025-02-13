/*
11000. Gold 5 - 강의실 배정

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           58597	    17773     13098	         29.668%


    문제
        수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.
        김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
        참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
        수강신청 대충한 게 찔리면, 선생님을 도와드리자!


    입력
        첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)
        이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 10^9)


    출력
        강의실의 개수를 출력하라.


    예제 입력 1
        3
        1 3
        2 4
        3 5
    예제 출력 1
        2


    알고리즘 분류
        · 자료 구조
        · 그리디 알고리즘
        · 정렬
        · 우선순위 큐
*/


// 메모리 : 68356KB
// 시간 : 656ms
// 코드 길이 : 1959B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000 {
    static int N; // 수업의 개수
    static int[][] time; // 각 수업의 시작 시간과 끝나는 시간을 저장하는 배열
    static PriorityQueue<Integer> room; // 각 강의실의 수업이 끝나는 시간을 저장하는 우선 순위 큐

    public static void find() { // 모든 수업을 가능하게 하는 최소의 강의실 개수를 구하는 메서드
        Arrays.sort(time, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]); // 수업의 시작 시간이 같을 경우 수업의 끝나는 시간을 기준으로 오름차순 정렬, 수업의 시작 시간이 같지 않을 경우 수업의 시작 시간을 기준으로 오름차순 정렬

        room = new PriorityQueue<>();
        room.offer(time[0][1]);
        for (int n = 1; n < N; n++) {
            if (room.peek() <= time[n][0]) { // 사용된 강의실의 수업이 끝나는 시간보다 현재 수업의 시작 시간이 늦거나 같을 경우
                room.poll(); // 사용된 강의실의 수업이 끝나는 시간 제거
            }

            room.offer(time[n][1]); // 현재 수업의 끝나는 시간을 강의실의 수업이 끝나는 시간으로 저장
        }

        System.out.println(room.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        time = new int[N][2];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());

            time[i][0] = Integer.parseInt(token.nextToken());
            time[i][1] = Integer.parseInt(token.nextToken());
        }

        find();
    }
}
