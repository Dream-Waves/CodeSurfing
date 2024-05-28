package Jieun714;
/**
 * 문제: 2차원 평면상에 N(3 ≤ N ≤ 10,000)개의 점으로 이루어진 다각형이 있다. 이 다각형의 면적을 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 N이 주어진다. 다음 N개의 줄에는 다각형을 이루는 순서대로 N개의 점의 x, y좌표가 주어진다. 좌표값은 절댓값이 100,000을 넘지 않는 정수이다.
 * 출력: 첫째 줄에 면적을 출력한다. 면적을 출력할 때에는 소수점 아래 둘째 자리에서 반올림하여 첫째 자리까지 출력한다.
 * 해결: 신발끈 공식
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //다각형의 꼭짓점의 좌표
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());

        long startX = x1;
        long startY = y1;
        double answer = 0; //
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            answer += ((x1 * y2) - (x2 * y1));

            x1 = x2;
            y1 = y2;
        }
        answer += ((x1 * startY) - (startX * y1));
        answer = Math.abs(answer) / 2; //계산된 결과에 절댓값을 취한 후 2로 나눔
        System.out.printf(String.format("%.1f", answer)); //소수점 아래 첫째 자리까지 출력
    }
}
