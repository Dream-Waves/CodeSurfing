package ybwi0912;

import java.util.Scanner;

/**
 * 2023-07-06
 * BOJ 1052: 물병
 * N개의 물병을 한번씩 합쳐서 N/2개로 만들려면 N이 2의 배수여야 한다 ..?
 * */

public class BOJ1052 {
    public static int N, K;
    public static int min = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 가지고 있는 물병의 개수
        K = sc.nextInt(); // 한 번에 옮길 수 있는 물병의 개수
        // = N개의 물병을 같은 용량의 물병끼리 합쳐서 K개로 만들어야 한다
        // input

        if(N<=K){
            System.out.println(0);
            return;
        } // 굳이 물병을 추가적으로 구매하지 않아도 되는 경우

        while(true){
            char[] arr = Integer.toBinaryString(N).toCharArray();
            int count = 0; // 합칠 수 있는 만큼 합쳤을 때 물병의 개수
            for(int i=0; i<arr.length; i++){
                if(arr[i]=='1') count++;
            } // 물병의 개수 count

            if(count<=K){
                System.out.println(min);
                break;
                // output
                // 물병을 계속 살 수 있기 때문에 정답이 없는 경우는 없다!
                // 즉, -1 출력 처리를 해주지 않아도 된다
            }

            N++; // 물병 한 개 구매
            min++;
        }
        // operation
    }
}
