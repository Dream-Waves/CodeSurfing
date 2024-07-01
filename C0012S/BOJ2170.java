/*
2170. Gold 5 - 선 긋기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    192 MB           26031	    9552      6878	         35.375%


    문제
        매우 큰 도화지에 자를 대고 선을 그으려고 한다. 선을 그을 때에는 자의 한 점에서 다른 한 점까지 긋게 된다. 선을 그을 때에는 이미 선이 있는 위치에 겹쳐서 그릴 수도 있는데, 여러 번 그은 곳과 한 번 그은 곳의 차이를 구별할 수 없다고 하자.
        이와 같은 식으로 선을 그었을 때, 그려진 선(들)의 총 길이를 구하는 프로그램을 작성하시오. 선이 여러 번 그려진 곳은 한 번씩만 계산한다.


    입력
        첫째 줄에 선을 그은 횟수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 다음 N개의 줄에는 선을 그을 때 선택한 두 점의 위치 x, y (-1,000,000,000 ≤ x < y ≤ 1,000,000,000)가 주어진다.


    출력
        첫째 줄에 그은 선의 총 길이를 출력한다.


    예제 입력 1
        4
        1 3
        2 5
        3 5
        6 7
    예제 출력 1
        5


    알고리즘 분류
        정렬
        스위핑
*/


// 메모리 : 275088KB
// 시간 : 2516ms
// 코드 길이 : 2042B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2170 {
    static int N; // 선을 그은 횟수 (1 ≤ N ≤ 1000000)
    static int coordinate[][]; // 선을 그을 때 선택한 두 점의 위치 x, y의 값을 담는 배열 (-1000000000 ≤ x < y ≤ 1000000000)

    public static void calculate() { // 그은 선의 총 길이를 구하는 메서드
        Arrays.sort(coordinate, (o1, o2) -> o1[0] - o2[0]); // 선의 시작 위치를 기준으로 오름차순 정렬

        int now[] = coordinate[0]; // 현재 선의 정보
        int lineLength = 0; // 그은 선의 총 길이
        for (int i = 0; i < N; i++) {
            int next[] = coordinate[i]; // 다음 선의 정보

            if (now[1] >= next[0]) { // 현재 선의 끝나는 위치가 다음 선의 시작 위치보다 크거나 같을 경우
                now[1] = Math.max(now[1], next[1]); // 현재 선의 끝나는 위치를 현재 선의 끝나는 위치와 다음 선의 끝나는 위치 중 더 큰 값으로 설정
            }
            else { // 다음 선이 현재 선과 연결되어 있지 않을 경우
                lineLength += (now[1] - now[0]); // 현재 선의 길이 누적
                now = next; // 현재 선의 정보 변경
            }
        }

        lineLength += (now[1] - now[0]); // 마지막 선의 길이 누적

        System.out.println(lineLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        coordinate = new int[N][2];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());
            coordinate[i][0] = Integer.parseInt(token.nextToken());
            coordinate[i][1] = Integer.parseInt(token.nextToken());
        }

        calculate();
    }
}
