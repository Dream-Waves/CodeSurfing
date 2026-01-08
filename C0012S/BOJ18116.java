/*
18116. Gold 4 - 로봇 조립

    시간 제한	                메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    4 초 (추가 시간 없음)	    1024 MB          5508	    1793      1319	         29.521%


    문제
        성규는 로봇을 조립해야 한다. 상자 안에는 여러 로봇의 부품들이 섞여 있다. 그런데 어떤 부품이 어느 로봇의 부품인지 표시가 되어있지 않다. 호재는 전자과라서 두 부품을 보면 같은 로봇의 부품인지 알 수 있다. 그래서 성규는 호재의 지시에 따라 부품들을 정리하기로 하였다.
        부품들은 1부터 10^6까지의 정수로 표현된다. 그리고 부품 i가 속한 로봇은 robot(i)라고도 표현한다. 예를 들어, 부품 11과 부품 22가 로봇 A의 부품이라고 알고 있는 경우, robot(11)은 로봇 A를 의미하고, robot(22)도 로봇 A를 의미한다.
        서로 다른 로봇은 공통 부품을 가지지 않는다. 즉 어떤 부품이 로봇 A의 부품이라면, 로봇 B의 부품은 될 수 없다.

        호재는 2가지 지시를 한다.
            · 서로 다른 부품 2개를 말해주며, 두 부품은 같은 로봇의 부품이라는 정보를 알려준다.
            · 부품 i에 대해서, 지금까지 알아낸 robot(i)의 부품이 몇 개냐고 물어본다.

        초기에는 부품에 대한 정보가 존재하지 않는다.


    입력
        첫 번째 줄에 호재의 지시 횟수 N이 들어온다. (1 ≤ N ≤ 10^6)
        다음 줄부터 N개의 지시가 들어온다.
        부품 2개가 같은 로봇의 부품인지 알려줄 때에는 I a b 의 형태로 들어온다. 부품 a와 부품 b는 같은 로봇의 부품이라는 의미이다. (1 ≤ a, b ≤ 10^6, a ≠ b, a, b는 정수)
        어떤 로봇의 부품이 몇 개인지 물어볼 때에는 Q c 의 형태로 들어온다. 지금까지 알아낸 robot(c)의 부품이 몇 개냐는 의미이다. (1 ≤ c ≤ 10^6, c는 정수)
        입력으로 Q c의 형태가 적어도 한 번 들어온다.


    출력
        Q로 시작하는 입력에 대해서 한 줄에 하나씩, 지금까지 알아낸 해당 로봇의 부품 개수를 출력한다.


    예제 입력 1
        4
        I 1 2
        I 3 2
        Q 1
        Q 4
    예제 출력 1
        3
        1


    알고리즘 분류
        자료 구조
        분리 집합
*/


// 메모리 : 253936KB
// 시간 : 1100ms
// 코드 길이 : 2878B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18116 {
    static int N; // 지시의 개수
    static int[] robotComponent; // 로봇 정보(각 로봇의 대표 부품)와 부품 개수를 동시에 관리하는 Union-Find 배열 (양수 : 로봇 정보, 음수 : 부품 개수)
    static StringBuilder sb;

    public static void inform(int componentA, int componentB) { // 두 부품이 같은 로봇의 부품인지 검사하고, 로봇의 부품 정보를 관리하는 메서드
        int robotA = question(componentA); // 부품 A의 로봇 정보
        int robotB = question(componentB); // 부품 B의 로봇 정보

        if (robotA == robotB) { // 같은 로봇의 부품으로 표현되어 있을 경우
            return;
        }

        // 부품의 개수가 더 많은 쪽을 로봇의 대표 부품으로 지정하고, 부품의 개수 갱신
        if (robotComponent[robotA] <= robotComponent[robotB]) {
            robotComponent[robotA] += robotComponent[robotB];
            robotComponent[robotB] = robotA;
        }
        else {
            robotComponent[robotB] += robotComponent[robotA];
            robotComponent[robotA] = robotB;
        }
    }

    public static int question(int component) { // 해당 부품의 로봇 정보를 찾는 메서드
        if (robotComponent[component] < 0) { // 해당 부품의 로봇 정보가 음수일 경우
            return component; // 해당 부품이 로봇의 대표 부품
        }

        return robotComponent[component] = question(robotComponent[component]); // 해당 부품의 로봇의 대표 부품의 정보로 갱신
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        robotComponent = new int[1000001];
        Arrays.fill(robotComponent, -1); // 각 부품의 로봇은 해당 부품을 하나씩 부품으로 가지고 있으므로 -1로 초기화

        sb = new StringBuilder();
        StringTokenizer token;

        for (int j = 0; j < N; j++) {
            token = new StringTokenizer(bf.readLine());

            String command = token.nextToken();
            int component = Integer.parseInt(token.nextToken());

            if (command.equals("I")) {
                inform(component, Integer.parseInt(token.nextToken()));
            }
            else if (command.equals("Q")) {
                int number = question(component); // 해당 부품의 로봇 정보(로봇의 대표 부품)

                sb.append(-robotComponent[number]); // 해당 부품의 로봇의 부품 개수
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
