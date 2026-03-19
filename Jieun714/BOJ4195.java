package Jieun714;
/**
 * 문제: 민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다. 우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.
 *      어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
 *      친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.
 * 입력: 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다.
 *      다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다. 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.
 * 출력: 친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
 * 해결: 유니온-파인드
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195 {
    public static int idx;
    public static int [] parent, depth;
    public static Map<String, Integer> map;

    //새로운 사람이 나오면 map에 등록
    public static void checkFriend(String str) {
        if(!map.containsKey(str)) {
            map.put(str, idx++);
        }
    }

    //루트 노드 찾는 함수
    public static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    //노드를 합치는 함수
    public static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if(x != y) { //서로 다른 집합일 때만 합침
            parent[y] = x;
            depth[x] += depth[y]; //네트워크 크기 합치기
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++) {
            int F = Integer.parseInt(br.readLine()); //친구 관계의 수
            map = new HashMap<>();
            idx = 0;
            parent = new int[F*2]; //최대 인원 수
            depth = new int[F*2]; //친구 네트워크 크기(int)

            for(int k=0; k<F*2; k++) { //초기화
                parent[k] = k;
                depth[k] = 1;
            }

            for(int k=0; k<F; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String id1 = st.nextToken(); //친구 아이디 1
                String id2 = st.nextToken(); //친구 아이디 2
                checkFriend(id1);
                checkFriend(id2);

                int a = map.get(id1);
                int b = map.get(id2);
                union(a, b); //친구 관계 연결
                sb.append(depth[find(a)]).append("\n");
            }
        }

        System.out.println(sb); //결과 출력
    }
}
