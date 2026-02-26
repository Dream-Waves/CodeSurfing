/*
2138. Gold 4 - 전구와 스위치

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           18172	    7092      5469	         39.436%


    문제
        N개의 스위치와 N개의 전구가 있다. 각각의 전구는 켜져 있는 상태와 꺼져 있는 상태 중 하나의 상태를 가진다. i(1 < i < N)번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다. 즉, 꺼져 있는 전구는 켜지고, 켜져 있는 전구는 꺼지게 된다. 1번 스위치를 눌렀을 경우에는 1, 2번 전구의 상태가 바뀌고, N번 스위치를 눌렀을 경우에는 N-1, N번 전구의 상태가 바뀐다.
        N개의 전구들의 현재 상태와 우리가 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위치를 최소 몇 번 누르면 되는지 알아내는 프로그램을 작성하시오.


    입력
        첫째 줄에 자연수 N(2 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 전구들의 현재 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 그 다음 줄에는 우리가 만들고자 하는 전구들의 상태를 나타내는 숫자 N개가 공백 없이 주어진다. 0은 켜져 있는 상태, 1은 꺼져 있는 상태를 의미한다.


    출력
        첫째 줄에 답을 출력한다. 불가능한 경우에는 -1을 출력한다.


    예제 입력 1
        3
        000
        010
    예제 출력 1
        3


    알고리즘 분류
        그리디 알고리즘
*/


// 메모리 : 19484KB
// 시간 : 196ms
// 코드 길이 : 3076B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2138 {
    static int N; // 스위치와 전구의 개수
    static int[] nowStatus; // 전구들의 현재 상태
    static int[] wishStatus; // 만들고자 하는 전구들의 상태

    public static void on(int[] status, int index) { // 규칙에 따라 전구의 상태를 바꾸는 메서드 : i 번 스위치를 눌렀을 때, (i - 1) 번, i 번, (i + 1) 번 전구의 상태를 바꾼다.
        for (int b = index - 1; b <= index + 1; b++) {
            if (b >= 0 && b < N) { // 배열 범위 체크
                status[b] = status[b] ^ 1; // XOR 연산 수행
            }
        }
    }

    public static int check(int[] status, boolean pressZero) { // 0 번 스위치의 눌림 여부에 따라 만들고자 하는 전구들의 상태를 만드는 스위치 눌림 횟수를 구하는 메서드
        int count = 0; // 스위치를 누른 횟수

        if (pressZero) { // 0 번 스위치를 눌렀을 경우
            on(status, 0); // 0 번 스위치 누르기
            count += 1;
        }

        for (int i = 1; i < N; i++) { // 전구 상태에 따라 1 번 스위치부터 (N - 1) 번 스위치 누르기
            if (status[i - 1] != wishStatus[i - 1]) { // (i - 1) 번의 전구에 대해, 현재 상태가 만들고자 하는 상태와 다를 경우
                on(status, i);
                count += 1;
            }
        }

        for (int i = 0; i < N; i++) { // 스위치를 눌러 만든 전구의 상태와 만들고자 하는 상태가 같은지 확인
            if (status[i] != wishStatus[i]) { // 스위치를 눌러 만든 전구의 상태와 만들고자 하는 상태가 다를 경우
                return Integer.MAX_VALUE;
            }
        }

        return count;
    }

    public static void find() { // 현재 전구들의 상태에서 만들고자 하는 전구들의 상태를 만들기 위해 필요한 스위치 누름 횟수의 최솟값을 구하는 메서드
        int notPressZeroCase = check(nowStatus.clone(), false); // 0 번 스위치를 누르지 않았을 경우 필요한 스위치 누름 횟수
        int pressZeroCase = check(nowStatus.clone(), true); // 0 번 스위치를 눌렀을 경우 필요한 스위치 누름 횟수

        int answer = Math.min(notPressZeroCase, pressZeroCase); // 스위치 누름 횟수의 최솟값

        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer); // 스위치 누름 횟수의 최솟값이 Integer.MAX_VALUE일 경우, 만들고자 하는 전구들의 상태로 만드는 것이 불가능하므로 -1 출력
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nowStatus = bf.readLine().chars().map(character -> character - '0').toArray();
        wishStatus = bf.readLine().chars().map(character -> character - '0').toArray();

        find();
    }
}
