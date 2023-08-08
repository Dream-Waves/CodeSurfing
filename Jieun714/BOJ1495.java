package Jieun714;
/**
 * 문제: Day Of Mourning의 기타리스트 강토는 다가오는 공연에서 연주할 N개의 곡을 연주하고 있다. 지금까지 공연과는 다른 공연을 보여주기 위해서 이번 공연에서는 매번 곡이 시작하기 전에 볼륨을 바꾸고 연주하려고 한다.
 *      먼저, 공연이 시작하기 전에 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨의 리스트를 만들었다. 이 리스트를 V라고 했을 때, V[i]는 i번째 곡을 연주하기 전에 바꿀 수 있는 볼륨을 의미한다.
 *      항상 리스트에 적힌 차이로만 볼륨을 바꿀 수 있다. 즉, 현재 볼륨이 P이고 지금 i번째 곡을 연주하기 전이라면, i번 곡은 P+V[i]나 P-V[i] 로 연주해야 한다. 하지만, 0보다 작은 값으로 볼륨을 바꾸거나, M보다 큰 값으로 볼륨을 바꿀 수 없다.
 *      곡의 개수 N과 시작 볼륨 S, 그리고 M이 주어졌을 때, 마지막 곡을 연주할 수 있는 볼륨 중 최댓값을 구하는 프로그램을 작성하시오. 모든 곡은 리스트에 적힌 순서대로 연주해야 한다.
 * 압력: 첫째 줄에 N, S, M이 주어진다. (1 ≤ N ≤ 50, 1 ≤ M ≤ 1,000, 0 ≤ S ≤ M) 둘째 줄에는 각 곡이 시작하기 전에 줄 수 있는 볼륨의 차이가 주어진다. 이 값은 1보다 크거나 같고, M보다 작거나 같다.
 * 출력: 첫째 줄에 가능한 마지막 곡의 볼륨 중 최댓값을 출력한다. 만약 마지막 곡을 연주할 수 없다면 (중간에 볼륨 조절을 할 수 없다면) -1을 출력한다.
 * 해결: 재귀 사용
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1495 {
    public static int N, S, M;
    public static int [] V; //볼륨 리스트를 담을 배열
    public static int max = -1; //마지막 곡의 볼륨 중 최댓값. default = -1
    public static boolean [][] isVisited; //방문한 곳을 체크하기 위한 배열

    public static void check(int P, int idx){
        if(idx==N){ //마지막 곡 연주까지 끝냈을 때
            max = Math.max(max, P); //최대값 변경
            return;
        }
        int up = P + V[idx]; //볼륨 업
        int down = P - V[idx]; //볼륨 다운

        if(up<=M && !isVisited[idx][up]){ //최대 볼륨을 넘지 않고, 방문하지 않았을 떄
            isVisited[idx][up] = true;
            check(up, idx+1);
        }
        if(down>=0 && !isVisited[idx][down]){ //0보다 작지 않고, 방문하지 않았을 떄
            isVisited[idx][down] = true;
            check(down, idx+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //곡의 개수
        S = Integer.parseInt(st.nextToken()); //시작 볼륨
        M = Integer.parseInt(st.nextToken()); //볼륨 최대 값

        V = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            V[i] = Integer.parseInt(st.nextToken());
        }
        isVisited = new boolean[N+1][M+1]; //방문배열 초기화
        check(S, 0);

        System.out.println(max); //마지막 곡의 볼륨 중 최댓값 출력
    }
}
