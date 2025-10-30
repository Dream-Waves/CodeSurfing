package Jieun714;
/**
 * 문제: 큰 방에 N개의 풍선이 떠있다. 풍선들은 왼쪽부터 오른쪽까지 일렬로 있다. 진솔이는 화살 가지고 노는 것과 사냥 연습하는 것을 좋아한다.
 *      진솔이는 화살을 왼쪽에서 오른쪽으로 쏜다. 높이는 임의로 선택한다. 화살은 선택된 높이 H에서 풍선을 마주칠 때까지 왼쪽에서 오른쪽으로 이동한다.
 *      화살이 풍선을 마주치는 순간, 풍선은 터지고 사라진다. 화살은 계속해서 가던길을 가는데 높이는 1 줄어든다. 그러므로 만약 화살이 높이 H에서 이동 중이었다면 풍선을 터트린 후에는 높이가 H-1이 된다.
 *      우리의 목표는 모든 풍선을 터트리되 가능한한 적은 화살을 사용하는 것이다.
 * 입력: 첫 번째 줄에는 정수 N(1 ≤ N ≤ 1 000 000)이 들어온다.
 *      두 번째 줄에는 배열 Hi가 N개 들어온다.
 *      각각의 Hi(1 ≤ Hi ≤ 1 000 000)는 i번째 풍선의 높이에 해당하며 왼쪽에서 오른쪽으로 나열되는 순서이다.
 * 출력: 첫 번째 줄 한줄에 최소한 필요한 화살의 개수를 출력한다.
 * 해결: 그리디
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0; //한줄에 필요한 화살의 개수
        for(int i=0; i<N; i++) {
            int currentH = arr[i]; //현재 높이
            int nextH = currentH-1; //화실이 내려간 다음 높이

            //현재 높이에 화살이 있을 경우
            if(map.containsKey(currentH)){
                if(map.get(currentH) - 1 == 0) {
                    map.remove(currentH);
                } else {
                    map.put(currentH, map.get(currentH)-1);
                }

                if(nextH >= 1) map.put(nextH, map.getOrDefault(nextH, 0) + 1); //화살은 다음 높이로 이동
            } else{ //현재 높이에 화살이 없을 경우
                cnt++; //새로운 화살 사용
                if(nextH >= 1) map.put(nextH, map.getOrDefault(nextH, 0) + 1); //화살은 다음 높이로 이동
            }
        }
        System.out.println(cnt);
    }
}
