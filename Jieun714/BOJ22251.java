package Jieun714;
/**
 * 문제: 치르보기 빌딩은 1층부터 N층까지 이용이 가능한 엘리베이터가 있다. 엘리베이터의 층수를 보여주는 디스플레이에는 K 자리의 수가 보인다. 수는 0으로 시작할 수도 있다.
 *      0부터 9까지의 각 숫자가 디스플레이에 보이는 방식은 아래와 같다. 각 숫자는 7개의 표시등 중의 일부에 불이 들어오면서 표현된다.
 *      빌런 호석은 치르보기 빌딩의 엘리베이터 디스플레이의 LED 중에서 최소 1개, 최대 P개를 반전시킬 계획을 세우고 있다. 반전이란 켜진 부분은 끄고, 꺼진 부분은 켜는 것을 의미한다.
 *      예를 들어 숫자 1을 2로 바꾸려면 총 5개의 LED를 반전시켜야 한다. 또한 반전 이후에 디스플레이에 올바른 수가 보여지면서 1 이상 N 이하가 되도록 바꿔서 사람들을 헷갈리게 할 예정이다.
 *      치르보기를 사랑하는 모임의 회원인 당신은 호석 빌런의 행동을 미리 파악해서 혼쭐을 내주고자 한다. 현재 엘리베이터가 실제로는 X층에 멈춰있을 때, 호석이가 반전시킬 LED를 고를 수 있는 경우의 수를 계산해보자.
 * 입력: N, K, P, X 가 공백으로 구분되어 첫째 줄에 주어진다.
 * 출력: 호석 빌런이 엘리베이터 LED를 올바르게 반전시킬 수 있는 경우의 수를 계산해보자.
 * 해결: 구현
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22251 {
    public static int N, K, P, X;
    public static int [][] number;

    //각 숫자 간 LED 반전 개수 초기화하는 함수
    public static void init() {
        number = new int[10][10];
        number[0] = new int[]{0, 4, 3, 3, 4, 3, 2, 3, 1, 2};
        number[1] = new int[]{4, 0, 5, 3, 2, 5, 6, 1, 5, 4};
        number[2] = new int[]{3, 5, 0, 2, 5, 4, 3, 4, 2, 3};
        number[3] = new int[]{3, 3, 2, 0, 3, 2, 3, 2, 2, 1};
        number[4] = new int[]{4, 2, 5, 3, 0, 3, 4, 3, 3, 2};
        number[5] = new int[]{3, 5, 4, 2, 3, 0, 1, 4, 2, 1};
        number[6] = new int[]{2, 6, 3, 3, 4, 1, 0, 5, 1, 2};
        number[7] = new int[]{3, 1, 4, 2, 3, 4, 5, 0, 4, 3};
        number[8] = new int[]{1, 5, 2, 2, 3, 2, 1, 4, 0, 1};
        number[9] = new int[]{2, 4, 3, 1, 2, 1, 2, 3, 1, 0};
    }

    //숫자를 자리 수에 맞는 배열로 변환하는 함수
    public static int [] setting(int n) {
        int[] numArr = new int[K];
        for (int i = K - 1; i >= 0; i--) {
            numArr[i] = n % 10;
            n /= 10;
        }
        return numArr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //최대 층 수
        K = Integer.parseInt(st.nextToken()); //디스플레이 자리 수
        P = Integer.parseInt(st.nextToken()); //최대 반전 횟수
        X = Integer.parseInt(st.nextToken()); //현재 위치

        init();
        int [] now = setting(X); //현재 층 숫자를 자리별 배열로 변환
        int answer = 0;

        for(int i=1; i<=N; i++) { //1층부터 N층까지 비교
            if(i == X) continue; //현재 층은 제외

            int [] next = setting(i);
            int total = 0;
            for(int j=0; j<K; j++) {
                total += number[now[j]][next[j]];  //자리별 반전 횟수 더하기
                if(total > P) break; //P 초과시 다음 층으로
            }

            if(total <= P) answer++;
        }

        System.out.println(answer); //경우의 수 출력
    }
}
