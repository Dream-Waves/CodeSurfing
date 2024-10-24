/*
14712. Gold 5 - 넴모넴모 (Easy)

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    512 MB           2385	    1253      882	         53.879%


    문제
        네모는 뿌××× 게임에 깊은 감명을 받아, 직사각형 모양의 격자판과 “넴모”라는 수수께끼의 생물을 이용하는 “넴모넴모”라는 게임을 만들었다. 이 게임의 규칙은 아주 간단하다. 격자판의 비어 있는 칸을 임의로 골라 “넴모”를 하나 올려놓거나, “넴모”가 올라간 칸 네 개가 2 × 2 사각형을 이루는 부분을 찾아 그 위에 있는 “넴모”들을 모두 없애는 것을 질릴 때까지 반복하면 된다.
            ■ □ ■         ■ ■ ■         ■ ▣ ▣
            □ ■ ■    →    □ ■ ■    →    □ ▣ ▣

        하지만 안타깝게도 게임은 정말 재미가 없었고, 네모는 아주 빨리 질려 버리고 말았다. 실망한 네모는 게임을 적당히 플레이하다가, “넴모”를 없애고 싶은데 격자판 위에 없앨 수 있는 “넴모”가 없으면 게임을 그만두기로 했다. 네모가 게임을 그만두었을 때 나올 수 있는 “넴모”의 배치의 가짓수를 구하여라.


    입력
        첫 번째 줄에 격자판의 행의 개수 N, 열의 개수 M(1 ≤ N, M ≤ 25, 1 ≤ N × M ≤ 25)이 공백으로 구분되어 주어진다.


    출력
        첫 번째 줄에 주어진 격자판에서 나올 수 있는, “넴모”들이 올라간 칸이 2 × 2 사각형을 이루지 않는 모든 배치의 가짓수를 출력한다.


    예제 입력 1
        2 2
    예제 출력 1
        15

    예제 입력 2
        2 3
    예제 출력 2
        57

    예제 입력 3
        3 5
    예제 출력 3
        22077


    힌트
        2×2 격자판에 2×2 사각형을 이루지 않도록 “넴모”들을 배치하는 방법은 모든 경우(24 = 16) 중 네 칸 모두에 “넴모”가 올라가 있는 경우를 제외한 15가지가 있다.


    알고리즘 분류
        브루트포스 알고리즘
        백트래킹
*/


// 메모리 : 17136KB
// 시간 : 568ms
// 코드 길이 : 2012B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14712 {
    static int N; // 행의 개수 (1 ≤ N ≤ 25, 1 ≤ N × M ≤ 25)
    static int M; // 열의 개수 (1 ≤ M ≤ 25, 1 ≤ N × M ≤ 25)
    static boolean map[][]; // 격자판의 넴모 배치 정보를 저장하는 배열
    static int count; // 네모가 게임을 그만 두었을 때 나올 수 있는 넴모의 배치의 가짓수(격자판 위에 없앨 수 있는 넴모가 없는 넴모의 배치의 가짓수)

    public static boolean check(int x, int y) { // 해당 좌표가 격자판 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        }

        return false;
    }

    public static void find(int num) { // 격자판 위에 없앨 수 있는 넴모가 없는 넴모의 배치의 가짓수를 구하는 메서드
        if (num == N * M) {
            count += 1;

            return;
        }

        int x = num / M; // 현재 행
        int y = num % M; // 현재 열
        if (check(x - 1, y - 1)
            && map[x - 1][y] && map[x][y - 1] && map[x - 1][y - 1]) { // 해당 격자판의 칸을 포함하여 2 × 2 사각형을 이룰 경우
            find(num + 1);
        }
        else {
            map[x][y] = true; // 해당 격자판의 칸에 넴모 올려 놓기
            find(num + 1);

            map[x][y] = false; // 해당 격자판의 칸에 넴모 올려 놓지 않기
            find(num + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new boolean[N][M];
        find(0);

        System.out.println(count);
    }
}
