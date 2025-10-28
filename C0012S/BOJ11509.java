/*
11509. Gold 5 - 풍선 맞추기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    256 MB           8698	    3165      2367	         37.872%


    문제
        큰 방에 N개의 풍선이 떠있다. 풍선들은 왼쪽부터 오른쪽까지 일렬로 있다. 진솔이는 화살 가지고 노는 것과 사냥 연습하는 것을 좋아한다. 진솔이는 화살을 왼쪽에서 오른쪽으로 쏜다. 높이는 임의로 선택한다. 화살은 선택된 높이 H에서 풍선을 마주칠 때까지 왼쪽에서 오른쪽으로 이동한다. 화살이 풍선을 마주치는 순간, 풍선은 터지고 사라진다. 화살은 계속해서 가던길을 가는데 높이는 1 줄어든다. 그러므로 만약 화살이 높이 H에서 이동 중이었다면 풍선을 터트린 후에는 높이가 H-1이 된다.
        우리의 목표는 모든 풍선을 터트리되 가능한한 적은 화살을 사용하는 것이다.


    입력
        첫 번째 줄에는 정수 N(1 ≤ N ≤ 1 000 000)이 들어온다.
        두 번째 줄에는 배열 Hi가 N개 들어온다.
        각각의 Hi(1 ≤ Hi ≤ 1 000 000)는 i번째 풍선의 높이에 해당하며 왼쪽에서 오른쪽으로 나열되는 순서이다.


    출력
        첫 번째 줄 한줄에 최소한 필요한 화살의 개수를 출력한다.


    예제 입력 1
        5
        2 1 5 4 3
    예제 출력 1
        2

    예제 입력 2
        5
        1 2 3 4 5
    예제 출력 2
        5

    예제 입력 3
        5
        4 5 2 1 4
    예제 출력 3
        3


    힌트
        첫 번째 예제 에서 [5,4,3] 을 터트리고 [2,1]을 터트리면 모든 풍선을 터트릴 수 있으므로 최소한 2개의 화살을 필요로 한다.


    알고리즘 분류
        그리디 알고리즘
*/


// 메모리 : 77800KB
// 시간 : 384ms
// 코드 길이 : 1517B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11509 {
    static int N; // 풍선의 개수
    static int[] H; // 풍선이 위치한 높이를 순서대로 저장하는 배열
    static int[] arrow; // arrow[height] : height 높이에 위치한 화살의 개수

    public static void find() { // 모든 풍선을 터뜨릴 때, 가장 적게 드는 화살의 개수를 구하는 메서드
        int count = 0; // 모든 풍선을 터뜨릴 때, 가장 적게 드는 화살의 개수
        for (int n = 0; n < N; n++) {
            if (arrow[H[n]] > 0) { // H[n] 높이에 위치한 화살이 있을 경우
                arrow[H[n]] -= 1;
            }
            else { // H[n] 높이에 위치한 화살이 없을 경우
                count += 1;
            }

            arrow[H[n] - 1] += 1; // H[n] 높이에 위치한 풍선을 터뜨렸으므로 해당 화살은 (H[n] - 1) 높이의 위치로 이동
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        H = new int[N];
        arrow = new int[1000001];
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(token.nextToken());
        }

        find();
    }
}
