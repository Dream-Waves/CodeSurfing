package Jieun714;
/**
 * 문제: 이진 트리 모양의 땅으로 이루어진 꽉꽉마을에는 오리들이 살고 있다. 땅 번호는 다음과 같이 매겨진다.
 *      1. 루트 땅의 번호는 1이다.
 *      2. 어떤 땅의 번호가 K라면, 왼쪽 자식 땅의 번호는 2 × K, 오른쪽 자식 땅의 번호는 2 × K + 1이다.
 *      어느날 오리들끼리 부동산 다툼이 일어나서 꽉꽉마을 촌장 경완이가 해결책을 가져왔고, 그 내용은 다음과 같다.
 *      오리들을 한 줄로 대기시킨다. 맨 처음 오리들은 1번 땅에 위치해 있다. 오리들이 서있는 순서대로 원하는 땅을 가지도록 한다.
 *      만약, 한 오리가 원하는 땅까지 가는 길에 이미 다른 오리가 점유한 땅이 있다면 막대한 세금을 내야 하는 이유로 해당 땅을 지나가지 못해 그 오리는 땅을 가지지 못한다. 오리가 원하는 땅까지 가는 길에는 오리가 원하는 땅도 포함된다.
 *      경완이의 해결책대로 땅 분배를 했을 때 각 오리별로 원하는 땅을 가질 수 있는지, 가질 수 없다면 처음 마주치는 점유된 땅의 번호를 구해보자.
 *
 * 입력: 첫 번째 줄에 땅 개수 N과 꽉꽉나라에 사는 오리 수 Q가 공백으로 구분되어 주어진다. (2 ≤ N < 220, 1 ≤ Q ≤ 200,000)
 *      두 번째 줄부터 차례로 Q개의 줄에 걸쳐 i+1번째 줄에는 i번째 오리가 원하는 땅 번호 xi가 주어진다. (2 ≤ xi ≤ N)
 * 출력: Q개의 줄에 원하는 땅에 갈 수 있다면 0을, 갈 수 없다면 처음 마주치는 점유된 땅의 번호를 출력한다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20364 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //땅 개수
        int Q = Integer.parseInt(st.nextToken()); //오리 수

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> tree = new ArrayList<>();
        for(int i=1; i<=N; i++){ //땅 개수만큼
            tree.add(i);
        }

        for(int i=0; i<Q; i++){
            int num = Integer.parseInt(br.readLine());
            int idx = num;
            int ans = 0; //갈 수 있다면 0, 갈 수 없다면 처음 마주치는 점유된 땅의 번호
            while(idx != 0){
                if(tree.get(idx-1) == 0) //점유되었다면
                    ans = idx; //점유된 땅의 번호
                idx /=2;
            }
            tree.set(num-1, 0); //점유 체크
            sb.append(ans).append("\n");
        }
        System.out.println(sb); //결과 출력
    }
}
