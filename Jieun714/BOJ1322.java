package Jieun714;
/**
 * 문제: 두 자연수 X와 K가 주어진다. 그러면, 다음 식을 만족하는 K번째로 작은 자연수 Y를 찾아야 한다.
 *      X + Y = X | Y
 *      |는 비트 연산 OR이다.
 * 입력: 첫째 줄에 X와 K가 주어진다. X와 K는 2,000,000,000보다 작거나 같은 자연수이다.
 * 출력: 첫째 줄에 X + Y = X | Y를 만족하는 K번째 작은 Y를 출력한다. 정답은 231-1 보다 클 수도 있다.
 * 해결: 비트마스킹
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1322 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long Y = 0;
        int N = 0; //K의 몇번째 비트를 사용할지 나타낼 인덱스
        for (int i = 0; i < 63; i++) {
            if ((X & (1L << i)) == 0) { //X의 i번째 비트가 0일 경우
                if ((K & (1L << N)) != 0) { //K의 N번째 비트가 1일 경우
                    Y |= (1L << i);  //Y의 i번째 비트를 1로 설정
                }
                N++; //다음 비트로 이동
            }
        }

        System.out.println(Y);
    }
}