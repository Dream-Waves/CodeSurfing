/*
4195. Gold 2 - 친구 네트워크

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    3 초	    256 MB           57969	    17939     11470	         28.981%


    문제
        민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다. 우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.
        어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
        친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.


    입력
        첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다. 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다. 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.


    출력
        친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.


    예제 입력 1
        2
        3
        Fred Barney
        Barney Betty
        Betty Wilma
        3
        Fred Barney
        Betty Wilma
        Barney Betty
    예제 출력 1
        2
        3
        4
        2
        2
        4


    알고리즘 분류
        자료 구조
        집합과 맵
        해시를 사용한 집합과 맵
        분리 집합
*/


// 메모리 : 71496KB
// 시간 : 600ms
// 코드 길이 : 3034B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ4195 {
    static int T; // 테스트 케이스의 개수
    static int F; // 친구 관계의 수
    static Map<String, Integer> friendMap; // 사람 이름별 고유 번호를 저장하는 Map
    static int[] friendInfo; // 각 사람이 속한 친구 네트워크의 루트 번호를 저장하는 배열
    static int[] friendNum; // 친구 네트워크 인원 수를 저장하는 배열

    public static void init() { // 각 정보를 초기화하는 메서드
        friendMap = new HashMap<>();
        friendInfo = new int[2 * F]; // 최대 사람의 수는 2 * F
        friendNum = new int[2 * F];

        for (int i = 0, size = 2 * F; i < size; i++) {
            friendInfo[i] = i;
            friendNum[i] = 1;
        }
    }

    public static int find(int u) { // 친구 네트워크의 루트 번호를 구하는 메서드
        if (friendInfo[u] == u) {
            return u;
        }

        return friendInfo[u] = find(friendInfo[u]);
    }

    public static int union(int u, int v) { // 친구 네트워크를 합치고, 친구 네트워크 인원 수를 구하는 메서드
        u = find(u); // u의 친구 네트워크 속 루트 번호
        v = find(v); // v의 친구 네트워크 속 루트 번호

        if (u == v) { // 이미 같은 친구 네트워크에 있을 경우
            return friendNum[u];
        }

        if (u < v) { // u의 번호가 v의 번호보다 작을 경우
            friendInfo[v] = u; // v의 친구 네트워크 속 루트 번호를 u로 설정
            friendNum[u] += friendNum[v];

            return friendNum[u];
        }
        else { // u의 번호가 v의 번호보다 작지 않을 경우
            friendInfo[u] = v; // u의 친구 네트워크 속 루트 번호를 v로 설정
            friendNum[v] += friendNum[u];

            return friendNum[v];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) { // 테스트 케이스 수만큼 반복
            F = Integer.parseInt(bf.readLine());

            init();

            int num = 0; // 각 사람의 고유 번호
            for (int f = 0; f < F; f++) {
                String[] nameArray = bf.readLine().split(" ");

                for (String name : nameArray) {
                    if (!friendMap.containsKey(name)) { // 처음 입력되는 이름일 경우
                        friendMap.put(name, num);
                        num += 1;
                    }
                }

                sb.append(union(friendMap.get(nameArray[0]), friendMap.get(nameArray[1])));
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
