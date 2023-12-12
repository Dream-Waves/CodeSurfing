package Jieun714;
/**
 * 문제: 자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
 * 출력: 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
 *
 * 해결: 재귀를 사용. 그냥 제곱 연산을 사용하게 되면 수가 커지기 때문에 연산 횟수를 줄이는 방식으로 접근
 * 참고: https://www.acmicpc.net/board/view/70289
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    public static long pow(int A, int B, int C){
        if(B==0) return 1;

        long tmp = pow(A, B/2, C);
        tmp = (tmp*tmp)%C; // A^(B/2)의 제곱을 C로 나눈 나머지
        if(B%2==1){ //B가 홀수일 때
            return (A*tmp)%C; // A^(B/2)의 제곱에 A를 한 번 더 곱하고 C로 나눈 나머지 반환
        } else{ //짝수일 때
            return tmp; // A^(B/2)의 제곱 반환
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long result = pow(A, B, C);
        System.out.println(result); //결과 출력
    }
}
