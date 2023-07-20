package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023-07-20
 * BOJ 1074: Z
 * 배열 전체를 사분면으로 나눴을 때 (r, c)가 몇 사분면에 위치해 있는지 파악하는 과정을 반복
 * - 제 1사분면일 때 : 탐색 범위를 줄인다
 * - 제 2사분면일 때 : 제 1사분면을 탐색했다고 간주하고 해당 사분면의 크기만큼 카운트해준 뒤(ans++) 탐색 범위를 줄인다
 * - 제 3사분면일 때 : 제 1, 2사분면을 탐색했다고 간주하고 해당 사분면의 크기만큼 카운트해준 뒤 탐색 범위를 줄인다
 * - 제 4사분면일 때 : 제 1, 2, 3사분면을 탐색했다고 간주하고 해당 사분면의 크기만큼 카운트해준 뒤 탐색 범위를 줄인다
 * */

public class BOJ1074 {
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(token.nextToken()); // 2^N * 2^N의 배열
        int r = Integer.parseInt(token.nextToken()); // r행(y좌표)
        int c = Integer.parseInt(token.nextToken()); // c열(x좌표)
        // input

        //int arr[][] = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];

        int x = (int) Math.pow(2, N);
        search(r, c, x);
        // operation

        System.out.println(ans);
        // output
    }

    static void search(int r, int c, int x){
        if(x<2) return;

        if(r < x/2 && c < x/2) {
            search(r, c, x/2); // 제 1사분면
        }
        else if(r < x/2 && c >= x/2) {
            ans += (x * x) / 4;
            search(r, c - x/2, x/2); // 탐색 범위를 좁히면서 각 좌표의 상대적인 위치를 반영해줘야 한다
        } // 제 2사분면
        else if(r >= x/2 && c < x/2){
            ans += ((x * x) / 4) * 2;
            search(r - x/2, c, x/2);
        } // 제 3사분면
        else{
            ans += ((x * x) / 4) * 3;
            search(r - x/2, c - x/2, x/2);
        }
    }
}
