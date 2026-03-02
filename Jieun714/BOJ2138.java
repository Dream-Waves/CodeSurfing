package Jieun714;
/**
 * 문제: N개의 스위치와 N개의 전구가 있다. 각각의 전구는 켜져 있는 상태와 꺼져 있는 상태 중 하나의 상태를 가진다. i(1 < i < N)번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다.
 *      즉, 꺼져 있는 전구는 켜지고, 켜져 있는 전구는 꺼지게 된다. 1번 스위치를 눌렀을 경우에는 1, 2번 전구의 상태가 바뀌고, N번 스위치를 눌렀을 경우에는 N-1, N번 전구의 상태가 바뀐다.
 *      N개의 전구들의 현재 상태와 우리가 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위치를 최소 몇 번 누르면 되는지 알아내는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 자연수 N(2 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 전구들의 현재 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 그 다음 줄에는 우리가 만들고자 하는 전구들의 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 0은 켜져 있는 상태, 1은 꺼져 있는 상태를 의미한다.
 * 출력: 첫째 줄에 답을 출력한다. 불가능한 경우에는 -1을 출력한다.
 * 해결: 그리디 알고리즘
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138 {
    public static int N; //전구 개수
    public static char [] begin, goal; //초기 상태와 목표 상태

    public static int calc(boolean flag) {
        char[] now = begin.clone(); //시작 배열 카피 후 사용
        int cnt = 0; //누적 스위치 누른 횟수

        if (flag) { //첫번째 스위치 누르눈 경우
            push(now, 0);
            cnt++;
        }

        for (int i=1; i<N; i++) { //왼쪽부터 눌러야하는 스위치 파악 후 로직 실행
            if (now[i-1] != goal[i-1]) { //'i-1' 현재와 목표상태가 다를 경우
                push(now, i);
                cnt++;
            }
        }

        boolean isSame = true; //최종 상태 동일 여부
        for (int i=0; i<N; i++) {
            if (now[i] != goal[i]) {
                isSame = false;
                break;
            }
        }

        //동일하면 카운트 횟수 반환, 다르다면 가장 큰 값 반환
        return isSame ? cnt : Integer.MAX_VALUE;
    }

    static void push(char [] now, int idx) { //idx번째 스위치를 눌렀을 때
        for (int i=idx-1; i<=idx+1; i++) { //idx-1, idx, idx+1 위치의 전구 상태 변경
            if (i >= 0 && i < N) {
                now[i] = (now[i] == '0') ? '1' : '0';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        begin = br.readLine().toCharArray();
        goal = br.readLine().toCharArray();

        int answer = Math.min(calc(true), calc(false));
        System.out.println(answer != Integer.MAX_VALUE ? answer : -1); //스위치 누르는 최소 횟수 출력, 불가능한 경우에는 -1
    }
}
