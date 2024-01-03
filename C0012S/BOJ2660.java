/*
2660. Gold 5 - 회장뽑기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           10245	    5627      4478	         55.483%


    문제
        월드컵 축구의 응원을 위한 모임에서 회장을 선출하려고 한다. 이 모임은 만들어진지 얼마 되지 않았기 때문에 회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다. 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.
        예를 들어 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다. 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말한다. 또한 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말한다.
        4점, 5점 등은 같은 방법으로 정해진다. 각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이라고 본다.
        회장은 회원들 중에서 점수가 가장 작은 사람이 된다. 회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성하시오.


    입력
        입력의 첫째 줄에는 회원의 수가 있다. 단, 회원의 수는 50명을 넘지 않는다. 둘째 줄 이후로는 한 줄에 두 개의 회원번호가 있는데, 이것은 두 회원이 서로 친구임을 나타낸다. 회원번호는 1부터 회원의 수만큼 붙어 있다. 마지막 줄에는 -1이 두 개 들어있다.


    출력
        첫째 줄에는 회장 후보의 점수와 후보의 수를 출력하고, 두 번째 줄에는 회장 후보를 오름차순으로 모두 출력한다.


    예제 입력 1
        5
        1 2
        2 3
        3 4
        4 5
        2 4
        5 3
        -1 -1
    예제 출력 1
        2 3
        2 3 4


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        최단 경로
        플로이드–워셜
*/


// 메모리 : 16024KB
// 시간 : 148ms
// 코드 길이 : 2803B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2660 {
    static int N; // 50 명을 넘지 않는 회원의 수
    static ArrayList<Integer> member[]; // 친구 정보를 담는 그래프
    static boolean isVisited[]; // 해당 인덱스의 회원을 방문했는지에 대한 여부를 저장하는 배열
    static int score[]; // 각 인덱스의 회원의 점수를 저장하는 배열

    public static void calculateScore(int start) { // start 회원의 점수를 계산하는 메서드  // BFS Algorithm 적용
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        isVisited[start] = true;
        score[start] = -1;
        while(!queue.isEmpty()) {
            for (int s = 0, friendNum = queue.size(); s < friendNum; s++) {
                int nowFriend = queue.poll();

                for (int f = 0, num = member[nowFriend].size(); f < num; f++) {
                    int nextFriend = member[nowFriend].get(f);

                    if (!isVisited[nextFriend]) {
                        isVisited[nextFriend] = true;
                        queue.offer(nextFriend);
                    }
                }
            }

            score[start] += 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        member = new ArrayList[N + 1];
        for (int a = 0; a < N + 1; a++) {
            member[a] = new ArrayList<>();
        }

        StringTokenizer token;
        while (true) {
            token = new StringTokenizer(bf.readLine());
            int person = Integer.parseInt(token.nextToken());
            int friend = Integer.parseInt(token.nextToken());

            if ((person == -1) && (friend == -1)) {
                break;
            }

            member[person].add(friend);
            member[friend].add(person);
        }

        int minScore = Integer.MAX_VALUE; // 회원들의 점수 중 가장 작은 점수
        score = new int[N + 1];
        for (int m = 1; m < N + 1; m++) {
            isVisited = new boolean[N + 1];
            calculateScore(m);
            minScore = Math.min(minScore, score[m]);
        }

        int count = 0; // 회장 후보의 수
        StringBuilder sb = new StringBuilder();
        for (int c = 1; c < N + 1 ; c++) {
            if (score[c] == minScore) { // 해당 인덱스의 회원의 점수가 최소 점수와 같을 경우
                count++;
                sb.append(c);
                sb.append(" ");
            }
        }

        System.out.println(minScore + " " + count);
        System.out.println(sb);
    }
}
