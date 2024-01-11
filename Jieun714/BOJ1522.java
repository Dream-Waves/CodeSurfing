package Jieun714;
/**
 * 문제: a와 b로만 이루어진 문자열이 주어질 때,  a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를 최소로 하는 프로그램을 작성하시오.
 *      이 문자열은 원형이기 때문에, 처음과 끝은 서로 인접해 있는 것이다.
 *      예를 들어,  aabbaaabaaba이 주어졌을 때, 2번의 교환이면 a를 모두 연속으로 만들 수 있다.
 * 입력: 첫째 줄에 문자열이 주어진다. 문자열의 길이는 최대 1,000이다.
 * 출력: 첫째 줄에 필요한 교환의 회수의 최솟값을 출력한다.
 * 해결: 슬라이딩 원도우
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1522 {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char [] arr = br.readLine().toCharArray(); //문자열
        int aCnt = 0; //a의 개수
        int result = Integer.MAX_VALUE; //교환의 개수

        //윈도우 크기 계산
        for(char c : arr){
            if(c == 'a') aCnt++; //a의 개수 up
        }

        for(int i=0; i<arr.length; i++){
            int end = i + aCnt -1;
            int bCnt = 0;
            for(int j=i; j<=end; j++){
                int idx = j;
                if(j >= arr.length){ //배열의 길이보다 크다면
                    idx = j - arr.length; //인덱스의 위치 조정 - 문자열이 원형이기 때문
                }
                if(arr[idx] == 'b') bCnt++; //b의 개수 up
            }
            result = Math.min(result, bCnt); //윈도우 내에서 b의 최솟값
        }

        System.out.println(result); //결과 출력
    }
}