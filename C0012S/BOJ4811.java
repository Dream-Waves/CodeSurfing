/*
4811. Gold 5 - 알약

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           7739	    5093      3971	         68.067%


    문제
        70세 박종수 할아버지는 매일 매일 약 반알을 먹는다. 손녀 선영이는 종수 할아버지에게 약이 N개 담긴 병을 선물로 주었다.
        첫째 날에 종수는 병에서 약 하나를 꺼낸다. 그 다음, 그 약을 반으로 쪼개서 한 조각은 먹고, 다른 조각은 다시 병에 넣는다.
        다음 날부터 종수는 병에서 약을 하나 꺼낸다. (약은 한 조각 전체 일 수도 있고, 쪼갠 반 조각 일 수도 있다) 반 조각이라면 그 약을 먹고, 아니라면 반을 쪼개서 한 조각을 먹고, 다른 조각은 다시 병에 넣는다.
        종수는 손녀에게 한 조각을 꺼낸 날에는 W를, 반 조각을 꺼낸 날에는 H 보낸다. 손녀는 할아버지에게 받은 문자를 종이에 기록해 놓는다. 총 2N일이 지나면 길이가 2N인 문자열이 만들어지게 된다. 이때, 가능한 서로 다른 문자열의 개수는 총 몇 개일까?


    입력
        입력은 최대 1000개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄이며, 병에 들어있는 약의 개수 N ≤ 30 가 주어진다.
        입력의 마지막 줄에는 0이 하나 주어진다.


    출력
        각 테스트 케이스에 대해서 가능한 문자열의 개수를 출력한다.


    예제 입력 1
        6
        1
        4
        2
        3
        30
        0
    예제 출력 1
        132
        1
        14
        2
        5
        3814986502092304


    알고리즘 분류
        수학
        다이나믹 프로그래밍
*/


// 메모리 : 14244KB
// 시간 : 124ms
// 코드 길이 : 1603B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4811 {
    static long medicine[][]; // 행의 알약 한 조각의 개수와 열의 알약 반 조각의 개수의 약을 꺼내는 순서의 경우의 수를 저장하는 배열

    public static long takeMedicine(int one, int half) { // 병에 들어 있는 약을 꺼내는 순서의 경우의 수를 구하는 메서드
        if (one == 0) { // 알약 한 조각이 없어서 알약 반 조각만 있을 경우
            return 1;
        }

        if (medicine[one][half] != 0) { // 이미 구한 경우의 수일 경우
            return medicine[one][half];
        }

        long count = 0;
        if (one != 0) { // 알약 한 조각이 있을 경우
            count += takeMedicine(one - 1, half + 1);
        }

        if (half != 0) { // 알약 반 조각이 있을 경우
            count += takeMedicine(one, half - 1);
        }

        return medicine[one][half] = count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        medicine = new long[31][31];
        takeMedicine(30, 0);

        int N = 0; // 병에 들어 있는 약의 개수 (N ≤ 30)
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(bf.readLine());

            if (N == 0) {
                break;
            }

            sb.append(medicine[N][0]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
