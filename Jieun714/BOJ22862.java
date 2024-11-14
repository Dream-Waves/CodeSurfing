package Jieun714;
/**
 * 문제: 길이가 N인 수열 S가 있다. 수열 S는 1 이상인 정수로 이루어져 있다.
 *      수열 S에서 원하는 위치에 있는 수를 골라 최대 K번 삭제를 할 수 있다.
 *      수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 구해보자.
 * 입력: 수열 S의 길이 N와 삭제할 수 있는 최대 횟수인 K가 공백으로 구분되어 주어진다.
 *      두 번째 줄에는 수열 S를 구성하고 있는 N개의 수가 공백으로 구분되어 주어진다.
 * 출력: 수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 출력한다.
 * 해결: 투 포인트
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //수열 S의 길이
        int K = Integer.parseInt(st.nextToken()); //삭제 최대 횟수
        int [] arr = new int[N]; //수열을 담을 배열
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int max = 0; //연속한 부분 수열 중 가장 긴 길이
        int start = 0, end = 0;
        int kCnt = K; //삭제 횟수 파악
        while(end < N) {
            while(end < N && kCnt >= 0) {
                if(arr[end]%2 != 0) kCnt--; //다음 지점이 홀수라면 삭제 횟수 차감
                end++; //end 포인트 이동
            }
            max = Math.max(max, end - start - (K-kCnt));
            if(arr[start]%2 != 0) kCnt++; //시작 지점이 홀수하면 삭제 횟수 더함
            start++; //시작 포인트
        }

        System.out.println(max);
    }
}
