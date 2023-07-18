package ybwi0912;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 2023-07-18
 * BOJ 2688: 줄어들지 않아
 * arr[i][j] = arr[i-1][j] + arr[i-1][j+1] + ... + arr[i-1][9]
 * */

public class BOJ2688 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[][] arr = new long[65][10]; // 1 <= n <= 64, 0~9로 시작하는 숫자

        for(int i=0; i<=9; i++){
            arr[1][i] = 1;
        } // 0000, 0001, 0002는 올바른 줄어들지 않는 4자리수

        for(int i=2; i<=64; i++){
            for(int j=0; j<=9; j++){
                for(int k=j; k<=9; k++){
                    //System.out.println("k = " + k);
                    arr[i][j] += arr[i-1][k];
                    //System.out.println("arr[" + i + "][" + j + "] = " + arr[i][j]);
                }
                // ex) arr[2][3] = arr[1][3] + arr[1][4] + arr[1][5] + arr[1][6] + arr[1][7] + arr[1][8] + arr[1][9]
            }
        }

        int T = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++){
            int n = sc.nextInt();
            // input
            long ans = 0; // int로 하지 않게 주의할 것!!!!!!!!!!!!

            for(int j=0; j<=9 ; j++){
                ans += arr[n][j]; // arr[n] = arr[n][0] + arr[n][1] + ... + arr[n][9]
            }
            sb.append(ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
