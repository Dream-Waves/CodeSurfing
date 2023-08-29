package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 2023-08-29
* BOJ 15903: 카드 합체 놀이
* 배열을 정렬한 뒤 최솟값 두 개를 더한 값을 반영하는 과정을 m번 반복한다. 모두 반복한 후 배열에 저장된 모든 값을 더한다.
* 로직이 간단하고 직관적이지만, 매번 배열을 정렬해야 해서 시간이 오래 걸린다는 단점이 있다
 * */

public class BOJ15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        long[] cards = new long[n];

        token = new StringTokenizer(bf.readLine());

        for(int i=0; i<n; i++){
            cards[i] = Integer.parseInt(token.nextToken());
        }

        for(int i=0; i<m; i++){
            Arrays.sort(cards);
            long add = cards[0] + cards[1];
            cards[0] = add;
            cards[1] = add;
        }

        long sum = 0;
        for(long i : cards){
            sum += i;
        }

        System.out.println(sum);
    }
}
