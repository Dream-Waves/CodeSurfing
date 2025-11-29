package Jieun714;
/**
 * 문재: 이진수 게임은 주어진 ‘시작 이진수’를 몇 가지 동작으로 ‘목표 이진수’로 바꾸는 게임이다.
 *      이 게임에서 가능한 동작들은 다음과 같다.
 *      1. 한 자리 숫자를 보수로 바꾸기. 단, 맨 앞 숫자(Most Significant Digit)는 바꿀 수 없다.
 *      2. 현재 수에 1 더하기.
 *      3. 현재 수에서 1 빼기. 단, 현재 수가 0이라면 빼기가 불가능하다.
 * ‘시작 이진수’와 ‘목표 이진수’가 주어질 때, ‘시작 이진수’를 ‘목표 이진수’로 만들기 위한 최소 동작 횟수를 출력하라. 주어지는 이진수들의 맨 앞 숫자는 항상 1이다.
 * 입력: 첫 번째 줄에 길이 L의 ‘시작 이진수’가 주어진다. 두 번째 줄에 길이 K의 ‘목표 이진수’가 주어진다. (1 ≤ L, K ≤ 10)
 * 출력: ‘시작 이진수’를 ‘목표 이진수’로 만들기 위한 최소 동작 횟수를 출력한다.
 * 해결: 비트마스킹+그래프
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BOJ18112 {
    public static String L, K;
    public static Map<Integer, Integer> isVisited;
    public static int answer;

    public static void bfs(int start, int end) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(start);
        isVisited.put(start, 0);

        while(!que.isEmpty()) {
            int now = que.poll();
            if(now == end) { //종료 조건
                answer = isVisited.get(now);
                return;
            }

            //1번 - 한 자리 숫자를 보수로 바꾸기(비트 반전)
            for (int i = 1; i <= (now >> 1); i = i << 1) { //맨 앞 숫자 제외 조건으로 1부터 시작
                if (!isVisited.containsKey(now^i)) {
                    que.add(now^i);
                    isVisited.put(now^i, isVisited.get(now) + 1);
                }
            }

            //2번 - 현재 수에 1 더하기
            if(!isVisited.containsKey(now+1)) {
                que.add(now+1);
                isVisited.put(now+1, isVisited.get(now) + 1);
            }

            //3번 - 현재 수에서 1 빼기
            if (now != 0) {
                if (!isVisited.containsKey(now-1)) {
                    que.add(now-1);
                    isVisited.put(now-1, isVisited.get(now) + 1);
                }
            }
        }
    }

    //2진수 계산하는 함수
    public static int toBinary(String str) {
        int number = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                number += Math.pow(2, str.length() - 1 - i);
            }
        }
        return number;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = br.readLine(); //시작 이진수
        K = br.readLine(); //목표 이진수
        isVisited = new HashMap<>(); //방문 여부 관리
        bfs(toBinary(L), toBinary(K));
        System.out.println(answer);
    }
}
