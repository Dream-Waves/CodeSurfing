package Jieun714;
/**
 * 문제: 네모는 뿌××× 게임에 깊은 감명을 받아, 직사각형 모양의 격자판과 “넴모”라는 수수께끼의 생물을 이용하는 “넴모넴모”라는 게임을 만들었다. 이 게임의 규칙은 아주 간단하다.
 *      격자판의 비어 있는 칸을 임의로 골라 “넴모”를 하나 올려놓거나, “넴모”가 올라간 칸 네 개가 2 × 2 사각형을 이루는 부분을 찾아 그 위에 있는 “넴모”들을 모두 없애는 것을 질릴 때까지 반복하면 된다.
 *      하지만 안타깝게도 게임은 정말 재미가 없었고, 네모는 아주 빨리 질려 버리고 말았다. 실망한 네모는 게임을 적당히 플레이하다가, “넴모”를 없애고 싶은데 격자판 위에 없앨 수 있는 “넴모”가 없으면 게임을 그만두기로 했다.
 *      네모가 게임을 그만두었을 때 나올 수 있는 “넴모”의 배치의 가짓수를 구하여라.
 * 입력: 첫 번째 줄에 격자판의 행의 개수 N, 열의 개수 M(1 ≤ N, M ≤ 25, 1 ≤ N × M ≤ 25)이 공백으로 구분되어 주어진다.
 * 출력: 첫 번째 줄에 주어진 격자판에서 나올 수 있는, “넴모”들이 올라간 칸이 2 × 2 사각형을 이루지 않는 모든 배치의 가짓수를 출력한다.
 * 해결: 백트래킹
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14712 {
    public static int N, M;
    public static boolean [][] isVisited;
    public static int cnt = 0; //2 × 2 사각형을 이루지 않는 모든 배치의 가짓수

    public static void dfs(int depth) {
        if(depth == N*M) { //격자판이 꽉 찰 경우
            cnt++; //배치의 수 증가
            return;
        }
        int x = depth/M + 1;
        int y = depth%M + 1;

        if(isVisited[x-1][y] && isVisited[x][y-1] && isVisited[x-1][y-1]) { //왼쪽 위, 오른쪽 위, 왼쪽아래 모두 넴모가 있을 경우
            dfs(depth + 1); //오른쪽 아레에 넴모를 배치하지 않고 진행
        } else { //그 외의 경우
            dfs(depth + 1); //넴모 배치
            isVisited[x][y] = true;
            dfs(depth + 1); //넴모 배치하지 않기
            isVisited[x][y] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //격자판 행의 개수
        M = Integer.parseInt(st.nextToken()); //격자판 열의 개수
        isVisited = new boolean[N+1][M+1];
        dfs(0);
        System.out.println(cnt);
    }
}