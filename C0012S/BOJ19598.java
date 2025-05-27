/*
19598. Gold 5 - 최소 회의실 개수

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    256 MB           10145	    4570      3547	         45.162%


    문제
        서준이는 아빠로부터 N개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하라는 미션을 받았다. 각 회의는 시작 시간과 끝나는 시간이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다. 단, 회의는 한번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작 시간은 끝나는 시간보다 항상 작다. N이 너무 커서 괴로워 하는 우리 서준이를 도와주자.


    입력
        첫째 줄에 배열의 크기 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N+1 줄까지 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 231−1보다 작거나 같은 자연수 또는 0이다.


    출력
        첫째 줄에 최소 회의실 개수를 출력한다.


    예제 입력 1
        3
        0 40
        15 30
        5 10
    예제 출력 1
        2

        2개 회의실로 3개 회의를 모두 진행할 수 있다. 예를 들어, 첫번째 회의실에서 첫번째 회의를 진행하고 두번째 회의실에서 두번째 회의와 세번째 회의를 진행하면 된다. 1개 회의실로 3개 회의를 진행할 수 없고 3개 이상의 회의실로 3개 회의를 모두 진행할 수 있지만 최소 회의실 개수를 구해야 하기 때문에 2가 정답이 된다.

    예제 입력 2
        2
        10 20
        5 10
    예제 출력 2
        1


    알고리즘 분류
        자료 구조
        그리디 알고리즘
        정렬
        우선순위 큐
*/


// 메모리 : 49928KB
// 시간 : 688ms
// 코드 길이 : 2162B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ19598 {
    static int N; // 배열의 크기 (1 ≤ N ≤ 100000)
    static int[][] meeting; // 각 회의의 시작 시간과 끝나는 시간을 저장하는 배열
    static PriorityQueue<Integer> room; // 각 회의실의 모든 회의가 끝나는 시간을 저장하는 우선 순위 큐

    public static void find() { // N 개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하는 메서드
        Arrays.sort(meeting, (o1, o2) -> ((o1[0] == o2[0]) ? (o1[1] - o2[1]) : (o1[0] - o2[0]))); // 회의의 시작 시간이 같을 경우 회의가 끝나는 시간을 기준으로 오름차순으로 정렬하고, 회의의 시작 시간이 다를 경우 회의가 시작하는 시간을 기준으로 오름차순으로 정렬한다.

        room = new PriorityQueue<>();
        room.add(meeting[0][1]);
        for (int n = 1; n < N; n++) {
            if (meeting[n][0] >= room.peek()) { // 해당 회의의 시작 시간이 회의실의 모든 회의가 끝나는 시간들 중 가장 일찍 끝나는 시간과 같거나 그 이후일 경우
                room.poll(); // 기존의 회의실을 사용할 수 있으므로 해당 회의실의 회의가 끝나는 시간 제거
            }

            room.offer(meeting[n][1]); // 회의실 추가 및 회의실의 회의가 끝나는 시간 갱신
        }

        System.out.println(room.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        meeting = new int[N][2];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            meeting[i][0] = Integer.parseInt(token.nextToken()); // 회의 시작 시간
            meeting[i][1] = Integer.parseInt(token.nextToken()); // 회의 끝나는 시간
        }

        find();
    }
}
