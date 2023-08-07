/*
1495. Silver 1 - 기타리스트

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           19109	    7218      5635	         37.155%


    문제
        Day Of Mourning의 기타리스트 강토는 다가오는 공연에서 연주할 N개의 곡을 연주하고 있다. 지금까지 공연과는 다른 공연을 보여주기 위해서 이번 공연에서는 매번 곡이 시작하기 전에 볼륨을 바꾸고 연주하려고 한다.
        먼저, 공연이 시작하기 전에 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨의 리스트를 만들었다. 이 리스트를 V라고 했을 때, V[i]는 i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨을 의미한다. 항상 리스트에 적힌 차이로만 볼륨을 바꿀 수 있다. 즉, 현재 볼륨이 P이고 지금 i번째 곡을 연주하기 전이라면, i번 곡은 P+V[i]나 P-V[i] 로 연주해야 한다. 하지만, 0보다 작은 값으로 볼륨을 바꾸거나, M보다 큰 값으로 볼륨을 바꿀 수 없다.
        곡의 개수 N과 시작 볼륨 S, 그리고 M이 주어졌을 때, 마지막 곡을 연주할 수 있는 볼륨 중 최댓값을 구하는 프로그램을 작성하시오. 모든 곡은 리스트에 적힌 순서대로 연주해야 한다.


    입력
        첫째 줄에 N, S, M이 주어진다. (1 ≤ N ≤ 50, 1 ≤ M ≤ 1,000, 0 ≤ S ≤ M) 둘째 줄에는 각 곡이 시작하기 전에 줄 수 있는 볼륨의 차이가 주어진다. 이 값은 1보다 크거나 같고, M보다 작거나 같다.


    출력
        첫째 줄에 가능한 마지막 곡의 볼륨 중 최댓값을 출력한다. 만약 마지막 곡을 연주할 수 없다면 (중간에 볼륨 조절을 할 수 없다면) -1을 출력한다.


    예제 입력 1
        3 5 10
        5 3 7
    예제 출력 1
        10

    예제 입력 2
        4 8 20
        15 2 9 10
    예제 출력 2
        -1

    예제 입력 3
        14 40 243
        74 39 127 95 63 140 99 96 154 18 137 162 14 88
    예제 출력 3
        238


    알고리즘 분류
        다이나믹 프로그래밍
*/


// 메모리 : 15528KB
// 시간 : 144ms
// 코드 길이 : 3964B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken()); // 곡의 개수 N (1 ≤ N ≤ 50)
        int S = Integer.parseInt(token.nextToken()); // 시작 볼륨 S (0 ≤ S ≤ M)
        int M = Integer.parseInt(token.nextToken()); // 볼륨의 최댓값 M

        int V[] = new int[N + 1]; // 공연이 시작하기 전에 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨의 배열
        int d[] = new int[M + 1]; // 해당 볼륨으로 연주하는 곡의 순서를 담는 배열  // d[x] : x 볼륨으로 연주하는 N 번째 곡
        int smallVolume = -1; // i 번째 곡을 연주할 때, i 번째 볼륨 - V[i] (0 ≤ smallVolume ≤ M)
        int bigVolume = M + 1; // i 번째 곡을 연주할 때, i 번째 볼륨 + V[i] (0 ≤ bigVolume ≤ M)
        token = new StringTokenizer(bf.readLine());
        for (int n = 1; n <= N; n++) {
            V[n] = Integer.parseInt(token.nextToken());
            ArrayList<Integer> possibleVolumeList = new ArrayList<>(); // 해당 순서의 곡에서 연주할 수 있는 볼륨들을 담는 리스트

            if (n == 1) { // 첫 번째 곡에서 연주할 수 있는 볼륨 검사
                smallVolume = S - V[n];
                bigVolume = S + V[n];

                if (smallVolume >= 0) { // 차로 구한 바꿀 볼륨이 0보다 크거나 같을 경우
                    d[smallVolume] = n;
                }

                if (bigVolume <= M) { // 합으로 구한 바꿀 볼륨이 M보다 작거나 같을 경우
                    d[bigVolume] = n;
                }
            }
            else { // 첫 번째 이후의 n 번째 곡에서 연주할 수 있는 볼륨 검사
                for (int m = 0; m <= M; m++) {
                    if (d[m] == (n - 1)) { // 해당 볼륨으로 연주한 곡이 전 순서에서 연주한 곡일 경우
                        smallVolume = m - V[n];
                        bigVolume = m + V[n];

                        if (smallVolume >= 0) { // 차로 구한 바꿀 볼륨이 0보다 크거나 같을 경우
                            possibleVolumeList.add(smallVolume); // 해당 순서의 곡에서 연주할 수 있는 볼륨들을 담는 리스트에 추가
                        }

                        if (bigVolume <= M) { // 합으로 구한 바꿀 볼륨이 M보다 작거나 같을 경우
                            possibleVolumeList.add(bigVolume); // 해당 순서의 곡에서 연주할 수 있는 볼륨들을 담는 리스트에 추가
                        }
                    }
                }

                for (int possibleVolume : possibleVolumeList) {
                    d[possibleVolume] = n; // 해당 순서의 곡에서 연주할 수 있는 볼륨들로 n 번째 곡을 연주한다고 지정
                }
            }
        }

        int maxVolume = -1; // 마지막 곡을 연주할 수 있는 볼륨 중 최댓값 (0 ≤ maxVolume ≤ M)  // 마지막 곡을 연주할 수 없다면(중간에 볼륨 조절을 할 수 없다면) -1

        // 마지막 곡을 연주할 수 있는 볼륨 중 최댓값 구하기
        for (int m = M; m >= 0; m--) {
            if (d[m] == N) { // 해당 볼륨(m 볼륨)으로 연주하는 곡이 마지막 곡일 경우
                maxVolume = m; // 최대로 연주 가능한 볼륨의 크기부터 검사하므로 가장 먼저 해당 조건을 만족하는 값이 최댓값이다.
                break; // 최댓값을 찾았으므로 반복문 탈출
            }
        }

        System.out.println(maxVolume);
    }
}
