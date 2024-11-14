/*
22862. Gold 5 - 가장 긴 짝수 연속한 부분 수열 (large)

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    1024 MB          5629	    2429      1800	         41.484%


    문제
        길이가 N인 수열 S가 있다. 수열 S는 1 이상인 정수로 이루어져 있다.
        수열 S에서 원하는 위치에 있는 수를 골라 최대 K번 삭제를 할 수 있다.

        예를 들어, 수열 S가 다음과 같이 구성되어 있다고 가정하자.
            수열 S : 1 2 3 4 5 6 7 8

        수열 S에서 4번째에 있는 4를 지운다고 하면 아래와 같다.
            수열 S : 1 2 3 5 6 7 8

        수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 구해보자.


    입력
        수열 S의 길이 N와 삭제할 수 있는 최대 횟수인 K가 공백으로 구분되어 주어진다.
        두 번째 줄에는 수열 S를 구성하고 있는 N개의 수가 공백으로 구분되어 주어진다.


    출력
        수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 출력한다.


    제한
        · 1 ≤ N ≤ 1,000,000
        · 1 ≤ K ≤ 100,000
        · 1 ≤ 원소의 값 ≤ 10^6


    예제 입력 1
        8 2
        1 2 3 4 5 6 7 8
    예제 출력 1
        3


    알고리즘 분류
        두 포인터
*/


// 메모리 : 96468KB
// 시간 : 472ms
// 코드 길이 : 2702B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken()); // 수열 S의 길이 (1 ≤ N ≤ 1000000)
        int K = Integer.parseInt(token.nextToken()); // 삭제할 수 있는 최대 횟수 (1 ≤ K ≤ 100000)

        Queue<Integer> indexQueue = new ArrayDeque<>(); // 수열 S에 존재하고 있는 홀수의 인덱스를 저장하는 큐
        int startIndex = -1; // 현재 짝수로 이루어져 있는 수열의 시작 인덱스
        int nowSequenceLength = 0; // 현재 짝수로 이루어져 있는 수열의 길이
        int maxSequenceLength = 0; // 수열 S에서 최대 K 번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이

        token = new StringTokenizer(bf.readLine());
        for (int n = 0; n < N; n++) {
            int nowNumber = Integer.parseInt(token.nextToken()); // 수열 S를 구성하고 있는 수

            if (nowNumber % 2 == 0) { // 현재 수가 짝수일 경우
                if (startIndex == -1) { // 현재 짝수로 이루어져 있는 수열의 시작을 찾지 못했을 경우
                    startIndex = n;
                }

                nowSequenceLength += 1;
            }
            else { // 현재 수가 홀수일 경우
                if (startIndex == -1) { // 현재 짝수로 이루어져 있는 수열의 시작을 찾지 못했을 경우
                    continue;
                }

                if (K == 0) { // 원소를 K 번 삭제했을 경우
                    int oddIndex = indexQueue.poll();
                    nowSequenceLength -= (oddIndex - startIndex); // 큐에 저장된 순서대로 홀수의 인덱스 다음부터 짝수로 이루어져 있는 수열의 길이 설정
                    startIndex = oddIndex + 1;
                }
                else { // 원소를 K 번보다 적게 삭제했을 경우
                    K -= 1; // 해당 원소를 삭제하여 짝수로 이루어져 있는 수열의 길이를 지속
                }

                indexQueue.offer(n); // 수열 S에 존재하고 있는 홀수의 인덱스를 큐에 저장
            }

            maxSequenceLength = Math.max(maxSequenceLength, nowSequenceLength);
        }

        System.out.println(maxSequenceLength);
    }
}
