package Jieun714;
/**
 * 문제: 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 *      만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *      수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고, 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
 * 입력: 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 * 출력: 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *      둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.
 * 해결: BFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {
    public static int N, K, cnt;
    public static int [] time = new int[100001];
    public static int [] move = {1, -1, 2}; //x-1, x+1, x*2 이동

    public static void find() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(N); //수빈이의 시작 위치 삽입

        while(!que.isEmpty()) {
            int now = que.poll(); //수빈이의 현재 위치
            for(int n : move) {
                int next = (n == 2) ? now * n : now + n; //1초 후 수빈이의 이동 위치

                //현재 위치가 범위가 벗어났거나, 이미 방문한 지점인데 기존에 걸렸던 시간보다 길다면 다른 이동 방법을 선택
                if(next < 0 || next > 100000 || (time[next] != 0 && time[next] < time[now]+1)) continue;
                if(next == K) cnt++; //동생을 찾은 경우 방법의 수 증가
                time[next] = time[now] + 1; //이동 시간 누적
                que.add(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //수빈이가 있는 위치
        K = Integer.parseInt(st.nextToken()); //동생이 있는 위치

        if(N == K) cnt = 1; //수빈이의 현재 위치와 수빈이 동생의 위치가 같을 때, 방법은 1가지
        else find();
        System.out.println(time[K] + "\n" + cnt); //가장 빠른 시간과 가장 빠른 시간으로 찾은 방법의 수 출력
    }
}