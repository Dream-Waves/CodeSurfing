package Jieun714;
/**
 * 문제: 나3곱2 게임은 정수 하나를 이용한다. 가장 먼저, 정수 x로 시작하고, 연산을 N-1번 적용한다. 적용할 수 있는 연산은 두 가지 있고, 아래와 같다.
 *      - 나3: x를 3으로 나눈다. x는 3으로 나누어 떨어져야 한다.
 *      - 곱2: x에 2를 곱한다.
 *      나3곱2 게임을 진행하면서, 만든 수를 모두 기록하면 수열 A를 만들 수 있다. 예를 들어, x = 9, N = 6이고, 적용한 연산이 곱2, 곱2, 나3, 곱2, 나3인 경우에 A = [9, 18, 36, 12, 24, 8] 이다.
 *      수열 A의 순서를 섞은 수열 B가 주어졌을 때, 수열 A를 구해보자.
 * 입력: 첫째 줄에 수열의 크기 N(2 ≤ N ≤ 100)이 주어진다. 둘째 줄에는 수열 B가 주어진다. B에 포함된 원소는 1018 보다 작거나 같은 자연수이다.
 * 출력: 나3곱2 게임의 결과 수열 A를 출력한다. 항상 정답이 존재하는 경우에만 입력으로 주어지며, 가능한 정답이 여러가지인 경우에는 아무거나 출력한다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import static java.lang.System.exit;

public class BOJ16936 {
    public static ArrayList<Long> list = new ArrayList<>();
    public static boolean [] isChecked; // 숫자 사용 여부를 체크하는 배열
    public static Map<Long, Integer> map = new HashMap<>(); //값 - 인덱스

    public static void calc(Long num, int time, String str) {
        StringBuilder sb = new StringBuilder();
        if(time == list.size()-1) {
            System.out.println(str);
            exit(0); //프로그램 종료
        }

        if(num%3 == 0 && map.containsKey(num/3)) { // 현재 수에서 3으로 나누는 경우 탐색
            int idx = map.get(num / 3);
            if (!isChecked[idx]) {
                sb.append(str).append(" ").append(num / 3);
                isChecked[idx] = true;
                calc(num / 3, time + 1, sb.toString());
                isChecked[idx] = false;
                sb.setLength(0); // StringBuilder 초기화
            }
        }

        if(map.containsKey(num * 2)) { // 현재 수에서 2를 곱하는 경우 탐색
            int idx = map.get(num * 2);
            if (!isChecked[idx]) {
                sb.append(str).append(" ").append(num * 2);
                isChecked[idx] = true;
                calc(num * 2, time + 1, sb.toString());
                isChecked[idx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()); //수열의 크기

        for(int i=0; i<N; i++) {
            Long num = Long.parseLong(st.nextToken());
            list.add(num);
            map.put(num, i); //값 -> 인덱스 매핑
        }

        for(int i=0; i<N; i++) {
            isChecked = new boolean[N]; // 방문 여부 배열 초기화
            isChecked[i] = true;
            calc(list.get(i), 0, String.valueOf(list.get(i)));
        }
    }
}