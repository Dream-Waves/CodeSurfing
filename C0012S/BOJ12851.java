/*
12851. Gold 4 - 숨바꼭질 2

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    512 MB           65101	    18422     12797	         25.792%


    문제
        수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
        수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고, 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.


    입력
        첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.


    출력
        첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
        둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.


    예제 입력 1
        5 17
    예제 출력 1
        4
        2


    비슷한 문제
        1697번. 숨바꼭질
        13549번. 숨바꼭질 3
        13913번. 숨바꼭질 4


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
*/


// 메모리 : 20808KB
// 시간 : 196ms
// 코드 길이 : 3667B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ12851 {
    static int N; // 수빈이가 있는 위치 (0 ≤ N ≤ 100000)
    static int K; // 동생이 있는 위치 (0 ≤ K ≤ 100000)
    static int[] time; // 해당 위치로 이동하는 데 걸리는 시간을 저장하는 배열
    static int[] dx = {-1, 1, 2}; // X - 1, X + 1, 2 * X
    static int minTime; // 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
    static int way; // 수빈이가 동생을 찾을 수 있는 가장 빠른 시간으로 찾는 방법의 수

    public static boolean check(int x) { // 숨바꼭질을 할 수 있는 범위 내의 위치인지 검사하는 메서드
        if (x >= 0 && x <= 100000) {
            return true;
        }

        return false;
    }

    public static void find() { // 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지와 가장 빠른 시간으로 찾는 방법의 가짓수를 구하는 메서드
        Queue<Integer> queue = new ArrayDeque<>();
        time = new int[100001];

        if (N == K) { // 수빈이와 동생의 위치가 같을 경우
            way += 1;
            return;
        }

        int nx = 0; // 수빈이가 이동할 다음 위치
        minTime = Integer.MAX_VALUE;
        queue.offer(N);
        while (!queue.isEmpty()) {
            int now = queue.poll(); // 현재 수빈이의 위치

            if (minTime < time[now]) { // 현재 수빈이의 위치까지 이동하는 데 걸린 시간이 수빈이가 동생을 찾을 수 있는 가장 빠른 시간보다 클 경우
                continue;
            }

            for (int d = 0; d < 3; d++) {
                if (d == 2) {
                    nx = now * dx[d];
                }
                else {
                    nx = now + dx[d];
                }

                if (nx == K) { // 수빈이가 이동할 다음 위치가 동생이 있는 위치일 경우
                    if (minTime > time[now] + 1) { // 수빈이가 다음 위치로 이동할 때 걸리는 시간이 수빈이가 동생을 찾을 수 있는 가장 빠른 시간보다 빠를 경우
                        time[nx] = time[now] + 1;
                        minTime = time[nx];
                        way = 1;
                    }
                    else if (minTime == time[now] + 1) { // 수빈이가 다음 위치로 이동할 때 걸리는 시간이 수빈이가 동생을 찾을 수 있는 가장 빠른 시간과 같을 경우
                        time[nx] = time[now] + 1;
                        way += 1;
                    }
                }
                else if (check(nx) && (time[nx] == 0 || time[nx] == time[now] + 1)) { // 수빈이가 이동할 다음 위치가 숨바꼭질을 할 수 있는 범위 내이고, 아직 이동한 위치가 아니거나 기존의 이동 시간과 같은 시간이 걸릴 경우
                    queue.offer(nx);
                    time[nx] = time[now] + 1;
                }
            }
        }
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();

        sb.append(minTime);
        sb.append("\n");
        sb.append(way);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        find();
        print();
    }
}
