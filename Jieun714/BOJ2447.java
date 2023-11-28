package Jieun714;
/**
 * 문제: 재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.
 *      크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 *      ***
 *      * *
 *      ***
 *      N이 3보다 클 경우, 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이다. 예를 들어 크기 27의 패턴은 예제 출력 1과 같다.
 * 입력: 첫째 줄에 N이 주어진다. N은 3의 거듭제곱이다. 즉 어떤 정수 k에 대해 N=3k이며, 이때 1 ≤ k < 8이다.
 * 출력: 첫째 줄부터 N번째 줄까지 별을 출력한다.
 *
 * 해결: 구현 + 재귀 사용. 3의 패턴에서 가운데 공백이 있는 것을 고려
 * */
import java.util.Scanner;

public class BOJ2447 {
    public static StringBuilder sb = new StringBuilder();
    public static void checkStar(int n1, int n2) {
        if((n1%3)==1 && (n2%3)==1) { //3의 패턴에서 가운데에 해당할 때
            sb.append(" "); //공백 출력
        } else {
            if(n1 == 0 || n2 == 0) sb.append("*"); //3의 패턴에서 바깥에 해당할 때
            else checkStar(n1/3, n2/3); // 어느 조건에도 만족하지 않으면, 3으로 나눴을 때 몫을 가지고 다시 계산
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i=0; i<N; i++){ // 입력받은 n만큼
            for(int j=0; j<N; j++){
                checkStar(i, j);
            }
            sb.append("\n"); //n개의 별 or 공백 출력 후, 다음 줄로 이동
        }
        System.out.println(sb); //결과 출력
    }
}
