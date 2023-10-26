package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2023-10-26
 * BOJ 2792번: 보석상자
 * */

public class BOJ2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        int[] input = new int[M];
        int result = 0;

        for(int i=0; i<M; i++){
            input[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(input); // 보석의 개수를 오름차순으로 정렬

        int left = 1;
        int right = input[M-1];

        while(left <= right){
            int count = 0; // 한 사람 당 mid개씩 가졌을 때
            int mid = (left + right) / 2; // 한 사람 당 가질 수 있는 개수의 최댓값

            for(int i = M-1; i>=0; i--){ // 개수가 많은 보석부터 비교
                if(input[i] % mid == 0){ // 보석의 개수가 mid개로 나누어 떨어진다면
                    count += input[i] / mid; // 해당 보석을 가질 수 있는 인원 카운트
                }
                else {
                    count += input[i] / mid + 1; // mid개로 나누어 떨어지지 않아서 다른 한 명에게 추가로 분배
                }
            }

            if(count <= N){ // mid개씩 나눠줬을 때 몇 명에게 보석을 나눠줄 수 있는지 count에 기록하고, 실제 인원수와 비교
                result = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }

        System.out.println(result);
    }
}
