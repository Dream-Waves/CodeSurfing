package Jieun714;
/**
 * 문제: (생략) 문제를 소개하기 전, 계란으로 계란을 치게 될 경우 어떤 일이 벌어지는지를 먼저 이해하고 가자. 각 계란에는 내구도와 무게가 정해져있다. 계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
 *      그리고 내구도가 0 이하가 되는 순간 계란은 깨지게 된다. 예를 들어 계란 1의 내구도가 7, 무게가 5이고 계란 2의 내구도가 3, 무게가 4라고 해보자. 계란 1으로 계란 2를 치게 되면 계란 1의 내구도는 4만큼 감소해 3이 되고 계란 2의 내구도는 5만큼 감소해 -2가 된다.
 *      충돌 결과 계란 1은 아직 깨지지 않았고 계란 2는 깨졌다. 유현이가 인범이에게 알려준 퍼즐은 일렬로 놓여있는 계란에 대해 왼쪽부터 차례로 들어서 한 번씩만 다른 계란을 쳐 최대한 많은 계란을 깨는 문제였다. 구체적으로 계란을 치는 과정을 설명하면 아래와 같다.
 *      1. 가장 왼쪽의 계란을 든다.
 *      2. 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다. 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다. 이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다.
 *      3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행한다. 단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
 *      이 과정을 통해 최대한 많은 계란을 깨는 것이 앞으로 인범이가 매일 아침마다 풀게 될 퍼즐이다. 그리고 유현이는 인범이가 찾은 답이 정답이 맞는지 확인해주려고 한다. 일렬로 놓인 계란들의 내구도와 무게가 차례대로 주어졌을 때 최대 몇 개의 계란을 깰 수 있는지 알아맞춰보자.
 * 입력: 첫째 줄에 계란의 수를 나타내는 N(1 ≤ N ≤ 8)가 주어진다. 그 다음 N개의 줄에는 계란의 내구도와 무게에 대한 정보가 주어진다. i+1번째 줄에는 왼쪽에서 i번째에 위치한 계란의 내구도 Si(1 ≤ Si ≤ 300)와 무게 Wi(1 ≤ Wi ≤ 300)가 한 칸의 빈칸을 사이에 두고 주어진다.
 * 출력: 첫째 줄에 인범이가 깰 수 있는 계란의 최대 개수를 출력한다.
 * 해결: 백트래킹
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
    public static int N;
    public static int max = Integer.MIN_VALUE;

    public static void maxEgg(int [] S, int [] W, int now) {
        if(now == N) {
            int cnt = 0; //깨진 계란 수
            for(int n : S)
                if(n<=0) cnt++;
            max = Math.max(max, cnt);
            return;
        }

        if(S[now] <= 0) { //현재 들고 있는 계란이 깨져 있을 때
            maxEgg(S, W, now+1);
            return;
        }

        boolean flag = false; //계란 치기 유무
        for(int i=0; i<N; i++) { //계란치기
            if(i == now || S[i] <= 0) continue; //들고있는 계란이거나 이미 깨져있는 계란이면 다음으로
            flag = true;
            S[now] -= W[i]; //현재 들고 있는 계란의 내구도 감소
            S[i] -= W[now]; //상대 계란의 내구도 감소
            maxEgg(S, W, now+1);
            S[now] += W[i]; //원래 내구도로
            S[i] += W[now]; //원래 내구도로
        }
        if(!flag) maxEgg(S, W, now+1); //만약 칠 계란이 없었다면 다음으로
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //계란의 수
        int [] S = new int[N]; //내구도
        int [] W = new int[N]; //무게

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
        }
        maxEgg(S, W, 0);
        System.out.println(max); //최대로 깰 수 있는 계란
    }
}