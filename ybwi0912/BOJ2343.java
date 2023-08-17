package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2023-08-17
 * BOJ 2343번: 기타 레슨
 * 이분 탐색. 단, 입력받은 배열을 정렬하면 안된다 (강의의 순서가 바뀌면 안되기 때문)
 * 최솟값 left와 최댓값 right를 두고 이분 탐색을 통해 가능한 블루레이의 크기를 찾는다
 * 이때 블루레이 하나는 최소한 강의 하나씩은 꼭 담을 수 있는 크기여야 하기 때문에, 길이가 가장 긴 강의 하나의 길이가 블루레이 크기의 최솟값 left가 된다
 * 한 블루레이에 모든 강의를 담을 때 블루레이 하나의 크기가 최대가 되기 때문에, 최댓값 right는 모든 강의의 길이의 합으로 두었다
 * 블루레이의 크기의 최솟값과 최댓값의 중앙값을 블루레이 하나의 크기라고 가정하고, 직접 강의를 배분하면서 블루레이의 개수를 cnt 변수에 체크한다.
 * 강의를 모두 나눴을 때 소모된 블루레이의 개수가 가지고 있는 블루레이 M개보다 크다면 블루레이의 용량의 최솟값(min)을 늘려서 다시 배분을 시도한다. min을 늘리기 위해 최솟값 left를 min + 1로 증가시킨다
 * 강의를 모두 나눴을 때 소모된 블루레이의 개수가 가지고 있는 블루레이 M개보다 적거나 같다면, 즉 모든 조건을 충족하면서 강의 배분을 끝냈다면 그때의 블루레이의 용량 mid를 답 answer에 넣고,
 * 가능한 블루레이의 크기 중 최소를 찾기 위해 블루레이의 용량을 줄여서 다시 배분을 시도한다. 용량 mid를 줄이기 위해 최댓값 right를 mid - 1로 감소시킨다.
 * 최솟값 left와 최댓값 right가 반전되면, 즉 이분 탐색을 끝냈다면 while문을 종료하고 최종적인 answer를 출력한다.
 * */

public class BOJ2343 {
    static int N, M; // 강의의 수, 블루레이의 개수
    static int[] lessons; // 각 강의의 길이를 담는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int left = 0, right = 0;
        int mid, cnt, sum;
        int answer = 0;

        N = Integer.parseInt(token.nextToken()); // 강의의 수
        M = Integer.parseInt(token.nextToken()); // 블루레이의 개수

        lessons = new int[N];
        token = new StringTokenizer(bf.readLine());

        for(int i=0; i<N; i++){
            lessons[i] = Integer.parseInt(token.nextToken());
            left = Integer.max(left, lessons[i]); // 한 블루레이 내에 최소한 강의 하나는 들어갈 수 있어야 한다. 즉, 가장 긴 강의 하나의 길이를 최솟값으로 잡을 수 있다.
            right += lessons[i]; // 블루레이 하나에 모든 강의를 담는 경우를 최댓값 right로 볼 수 있다
        }
        // input

        while(left <= right){ // 강의들을 블루레이 내에 직접 나눠담으면서 블루레이의 최대 용량을 찾는다
            mid = (left + right) / 2; // 한 블루레이에 채울 수 있는 최대 용량
            sum = 0; // 블루레이에 강의가 얼마나 차 있는지 확인하기 위한 합 변수
            cnt = 1; // 블루레이의 개수

            for(int i=0; i<N; i++){
                sum += lessons[i]; // 블루레이에 강의를 순서대로 담는다
                if(sum > mid){ // 블루레이에 강의를 최대한 채운 상황
                    cnt++;   // 블루레이의 개수를 늘리고
                    sum = lessons[i]; // 해당 강의를 새 블루레이에 담기 시작한다
                }
            } // 모든 강의를 블루레이에 나눠 담고, 블루레이 개수가 유효한지 체크한다

            if(cnt > M) left = mid + 1; // 만약 주어진 블루레이의 개수보다 블루레이가 더 필요하다면 한 블루레이에 채울 수 있는 최대 용량을 늘리기 위해 용량의 최솟값을 수정한다
            else {
                right = mid - 1; // 만약 주어진 블루레이의 개수 내에서 강의를 배분할 수 있다면 블루레이의 최소 크기를 찾기 위해 용량을 줄인다. 즉, 용량의 최댓값을 수정한다
                answer = mid; // 블루레이 하나의 최대 용량을 답으로 지정
            }
        }
        // operation

        System.out.println(answer);
        // output
    }
}
