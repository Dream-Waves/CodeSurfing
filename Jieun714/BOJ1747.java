package Jieun714;
/**
 * 문제: 어떤 수와 그 수의 숫자 순서를 뒤집은 수가 일치하는 수를 팰린드롬이라 부른다. 예를 들어 79,197과 324,423 등이 팰린드롬 수이다.
 *      어떤 수 N (1 ≤ N ≤ 1,000,000)이 주어졌을 때, N보다 크거나 같고, 소수이면서 팰린드롬인 수 중에서, 가장 작은 수를 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 N이 주어진다.
 * 출력: 첫째 줄에 조건을 만족하는 수를 출력한다.
 * 문제: 먼저 에라토스테네스의 체로 범위 안의 수를 소수판별하고, 팰린드롬 체크
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1747 {
    //팰린드롬인지 판별하는 함수
    public static boolean checkPalindrome(int n){
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        sb.reverse(); //문자 정렬 반대로
        String str = sb.toString();
        return String.valueOf(n).equals(str);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean [] checkArr = new boolean[1003002];
        checkArr[1] = true; //1은 소수가 아님

        //에라토스테네스의 체 계산
        for(int i=2; i<1003002; i++){
            if(!checkArr[i]){
                for(int j = i*2; j<1003002; j += i){
                    checkArr[j] = true;
                }
            }
        }

        while (true) {
            if(checkPalindrome(N)){ //팰린드롬인지 판별
                if(!checkArr[N]){ //소수가 아니라면
                    break; //반복문 탈출
                }
            }
            N++;
        }
        System.out.println(N); //결과 출력
    }
}