package Jieun714;
/**
 * 문제: 지민이는 N개의 물병을 가지고 있다. 각 물병에는 물을 무한대로 부을 수 있다. 처음에 모든 물병에는 물이 1리터씩 들어있다. 지민이는 이 물병을 또 다른 장소로 옮기려고 한다. 지민이는 한 번에 K개의 물병을 옮길 수 있다.
 *      하지만, 지민이는 물을 낭비하기는 싫고, 이동을 한 번보다 많이 하기는 싫다. 따라서, 지민이는 물병의 물을 적절히 재분배해서, K개를 넘지 않는 비어있지 않은 물병을 만들려고 한다.
 *      물은 다음과 같이 재분배 한다.
 *      먼저 같은 양의 물이 들어있는 물병 두 개를 고른다. 그 다음에 한 개의 물병에 다른 한 쪽에 있는 물을 모두 붓는다. 이 방법을 필요한 만큼 계속 한다.
 *      이런 제약 때문에, N개로 K개를 넘지않는 비어있지 않은 물병을 만드는 것이 불가능할 수도 있다. 다행히도, 새로운 물병을 살 수 있다. 상점에서 사는 물병은 물이 1리터 들어있다.
 * 입력: 첫째 줄에 N과 K가 주어진다. N은 107보다 작거나 같은 자연수이고, K는 1,000보다 작거나 같은 자연수이다.
 * 출력: 첫째 줄에 상점에서 사야하는 물병의 최솟값을 출력한다. 만약 정답이 없을 경우에는 -1을 출력한다.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //물병의 수
        int K = Integer.parseInt(st.nextToken()); //한번에 옮길 수 있는 물병의 수
        int cnt = 0; //상점에서 사야하는 물병의 최솟값

        if (N > K) {
            int buy = 0; //구매한 물병의 수
            while (true) {
                int now = N + buy; //처음에 변수에 담지않고 N을 그대로 사용하니, while을 탈출하지 못함
                cnt = 0; //초기화 필수
                while(now > 0){ //기존 물병의 개수 + 상점에서 구매한 물병
                    if (now%2 != 0) { //나누어 떨어지지 않을 떄
                        cnt++;
                    }
                    now /= 2;
                }
                if(cnt <= K) {
                    cnt = buy;
                    break; //누적 물병의 개수가 k보다 작을 때 while 탈출
                }
                buy++; //물병 카운트 증가
            } //while end
        } //if end

        System.out.println(cnt); //결과 출력
    }
}