package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2023-07-25
 * BOJ 17615: 볼 모으기
 * 빨간 공을 움직여 왼쪽으로 모으는 경우 | 빨간 공을 움직여 오른쪽으로 모으는 경우
 * 파란 공을 움직여 왼쪽으로 모으는 경우 | 파란 공을 움직여 오른쪽으로 모으는 경우
 * 네 가지 경우 공을 움직이는 횟수를 각각 구해서 최솟값을 찾는다
 * */

public class BOJ17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine()); // 볼의 총 개수
        String input = bf.readLine();
        char[] arr = new char[N];

        for(int i=0; i<N; i++){
            char c = input.charAt(i);
            arr[i] = c;
        }
        // input

        boolean isBlue = false;
        boolean isRed = false;
        int countR = 0;
        int countB = 0;
        for(char i : arr){
            if(i=='B') isBlue = true;
            if(i=='R') isRed = true;

            if(isBlue && i == 'R') countR++;
            if(isRed && i == 'B') countB++;

        }
        int min = Integer.min(countR, countB);
        // 빨간 공을 움직여 왼쪽으로 모으는 경우 : 처음으로 파란 공이 나온 이후부터 나오는 빨간 공들의 수를 센다
        // 파란 공을 움직여 왼쪽으로 모으는 경우 : 처음으로 빨간 공이 나온 이후부터 나오는 파란 공들의 수를 센다

        isBlue = false;
        isRed = false;
        countR = 0;
        countB = 0;
        for(int i=N-1; i>=0; i--){
            if(arr[i]=='B') isBlue = true;
            if(arr[i]=='R') isRed = true;

            if(isBlue && arr[i] == 'R') countR++;
            if(isRed && arr[i] == 'B') countB++;
        }
        min = Integer.min(Integer.min(min, countR), countB);
        // 빨간 공을 움직여 오른쪽으로 모으는 경우 : 배열의 맨 뒤부터 시작, 처음으로 파란 공이 나온 이후부터 나오는 빨간 공들의 수를 센다
        // 파란 공을 움직여 오른쪽으로 모으는 경우 : 배열의 맨 뒤부터 시작, 처음으로 빨간 공이 나온 이후부터 나오는 파란 공들의 수를 센다
        // operation

        System.out.println(min);
        // output
    }
}
