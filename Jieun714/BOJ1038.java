package Jieun714;
/**
 * 문제: 음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면, 그 수를 감소하는 수라고 한다. 예를 들어, 321과 950은 감소하는 수지만, 322와 958은 아니다.
 * N번째 감소하는 수를 출력하는 프로그램을 작성하시오. 0은 0번째 감소하는 수이고, 1은 1번째 감소하는 수이다. 만약 N번째 감소하는 수가 없다면 -1을 출력한다.
 * 입력: 첫째 줄에 N이 주어진다. N은 1,000,000보다 작거나 같은 자연수 또는 0이다.
 * 출력: 첫째 줄에 N번째 감소하는 수를 출력한다.
 * 해결: 브루트포스 알고리즘
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1038 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //N번째 감소하는 수
        long answer = 0; //결과를 담을 변수
        int cnt = 0; //현재 몇 번째인지 체크하는 변수
        if (N == 1022) answer = 9876543210L; //감소하는 수가 될 수 있는 최대의 값
        else {
            long num = 1;
            while (cnt != N) { //N번째 감소하는 수일 때까지
                if (num > 9876543210L) { //만약 최대의 값이 넘을 때
                    answer = -1; //결과는 -1
                    break;
                }
                long now = num;
                boolean flag = false; //감소하는 수인지 확인하는 변수
                String str = Long.toString(now);
                int n = str.length();
                for (int j = 0; j < n - 1; j++) {
                    if (str.charAt(j) <= str.charAt(j + 1)) { //앞자리 수보다 뒷자리수가 크거나 같을 때
                        flag = true; //감소하는 수가 아님을 체크
                        int k = (int) Math.pow(10, (n - j - 1));
                        num = ((num / k) + 1) * k; //num을 감소하는 변수의 시작으로 변경
                        break;
                    }
                }

                if (!flag) { //감소하는 수 일 때
                    answer = num;
                    num++;
                    cnt++;
                }
            }
        } //else end
        System.out.println(answer); //N번째 감소하는 수를 출력
    }
}