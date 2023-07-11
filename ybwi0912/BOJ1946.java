package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1946 {
    public static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(bf.readLine());

            rank[] arr = new rank[N];
            for(int j=0; j<N; j++){
                StringTokenizer token = new StringTokenizer(bf.readLine());
                int s = Integer.parseInt(token.nextToken());
                int m = Integer.parseInt(token.nextToken());
                arr[j] = new rank(s, m);
            }

            Arrays.sort(arr);
            // s 기준 오름차순 정렬

            // 이후 m만 비교해 판단

            int count = 1; // 정렬 후 첫 번째 원소 포함
            int max_score = arr[0].m;

            for(int j=1; j<N; j++){
                if(max_score>arr[j].m){
                    count++;
                    max_score = arr[j].m;
                }
            }
            System.out.println(count);
        }
    }

    public static class rank implements Comparable<rank>{
        int s; // 서류심사 성적
        int m; // 면접 성적 순위

        public rank(int s, int m){
            this.s = s;
            this.m = m;
        }

        @Override
        public int compareTo(rank o) {
            if(this.s > o.s){
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
