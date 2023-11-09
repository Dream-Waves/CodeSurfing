package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 23-11-09
 * BOJ 15565번: 귀여운 라이언
 * [ 문제 ]
 * 꿀귀 라이언 인형과, 마찬가지로 꿀귀인 어피치 인형이 N개 일렬로 놓여 있다.
 * 라이언 인형은 1, 어피치 인형은 2로 표현하자.
 * 라이언 인형이 K개 이상 있는 가장 작은 연속된 인형들의 집합의 크기를 구하여라.
 *
 * [ 입력 ]
 * 첫 줄에 N과 K가 주어진다. (1 <= K <= N <= 10^6)
 * 둘째 줄에 N개의 인형의 정보가 주어진다. (1 또는 2)
 *
 * [ 출력 ]
 * K개 이상의 라이언 인형을 포함하는 가장 작은 연속된 인형들의 집합의 크기를 출력한다.
 * 그런 집합이 없다면 -1을 출력한다.
 *
 * [ 예제 입력 1 ]
 * 10 3
 * 1 2 2 2 1 2 1 2 2 1
 *
 * [ 예제 출력 1 ]
 * 6
 * */

public class BOJ15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken());
        int min = Integer.MAX_VALUE;
        List<Integer> d = new ArrayList<>();
        token = new StringTokenizer(bf.readLine());

        for(int i=0; i<N; i++) {
            if (Integer.parseInt(token.nextToken())==1){
                d.add(i); // 라이언 인형이 위치한 인덱스만 ArrayList에 저장한다
            }
        }

        for (int i=0; i<d.size()-K+1; i++){
            int s = d.get(i+K-1) - d.get(i) + 1;  
            min = Integer.min(min, s);
            // 인덱스 K개씩 확인하면서 첫 인덱스와 마지막 인덱스의 차이를 구한 뒤 최소값과 비교한다
        }

        if (min==Integer.MAX_VALUE) min = -1; // 만약 min 값이 갱신이 된 적 없다면 그런 집합이 없다고 판단하고 문제의 조건에 따라 -1을 저장한다
        System.out.println(min);
    }
}
