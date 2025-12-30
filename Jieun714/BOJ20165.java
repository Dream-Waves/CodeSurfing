package Jieun714;
/**
 * 문제: 사람을 화나게 하는 법은 다양하다. 그 중에서도 악질은 바로 열심히 세워놓은 도미노를 넘어뜨리는 것이다.
 *      이번에 출시된 보드 게임인 "너 죽고 나 살자 게임"은 바로 이 점을 이용해서 2명이 공격과 수비를 하는 게임이다. 공격수는 도미노를 계속 넘어뜨리고 수비수는 도미노를 계속 세우려고 한다. 본 게임은 다음과 같이 진행된다.
 *        1. N 행 M 열의 2차원 격자 모양의 게임판의 각 격자에 도미노를 세운다. 각 도미노는 1 이상 5 이하의 높이를 가진다.
 *        2. 매 라운드는 공격수가 먼저 공격하고, 수비수는 공격이 끝난 뒤에 수비를 한다.
 *        3. 공격수는 특정 격자에 놓인 도미노를 동, 서, 남, 북 중 원하는 방향으로 넘어뜨린다. 길이가 K인 도미노가 특정 방향으로 넘어진다면, 그 방향으로 K-1 개의 도미노들 중에 아직 넘어지지 않은 것들이 같은 방향으로 연달아 넘어진다.
 *           이 때, 당연히 도미노의 특성상, 연쇄적으로 도미노가 넘어질 수 있다. 이미 넘어진 도미노가 있는 칸을 공격한 경우에는 아무 일이 일어나지 않는다.
 *        4. 수비수는 넘어져 있는 도미노들 중에 원하는 것 하나를 다시 세울 수 있다. 넘어지지 않은 도미노를 세우려고 하면 아무 일이 일어나지 않는다.
 *        5. 총 R 번의 라운드동안 3, 4번 과정이 반복된다. 매 라운드마다 공격수가 해당 라운드에 넘어뜨린 도미노의 개수를 세고, R 라운드에 걸친 총합이 곧 공격수의 점수가 된다.
 *      인내를 빼면 시체라고 자부하는 호석이는 당신의 공격을 이겨내고자 수비수를 자처했다. 초기 도미노 판의 상태와, 각 라운드에서 둘의 행동의 기록을 받아서 공격수의 점수와 게임판의 최종 상태를 출력하는 프로그램을 작성하라.
 * 입력: 첫 번째 줄에는 게임판의 행 개수 N, 열 개수 M, 라운드 횟수 R 이 주어진다.
 *      이어서 N개의 줄에 게임판의 상태가 주어진다. 1행부터 주어지며, M 개의 숫자는 각 격자에 놓인 도미노의 길이를 의미한다.
 *      이어서 R×2 개의 줄에 걸쳐서 공격수와 수비수의 행동이 주어진다. 각 라운드는 두 줄로 이뤄지며, 첫 줄은 공격수, 두번째 줄은 수비수의 행동이 주어진다. 공격수의 행동은 "X Y D"로 주어진다. 이는 X행 Y열의 도미노를 D 방향으로 민다는 뜻이다.
 *      D는 E, W, S, N 중 하나이며 각각 동, 서, 남, 북 방향을 의미한다. 수비수의 행동은 "X Y"로 주어진다. 이는 X행 Y열의 도미노를 다시 세운다는 뜻이다.
 *      만약 이미 넘어진 격자의 도미노를 공격수가 넘어뜨리려 할 때에는 아무 일도 일어나지 않는다. 또한 만약 넘어지지 않은 도미노를 수비수가 세우려고 할 때에도 아무 일도 일어나지 않는다.
 * 출력: 첫 줄에 공격수의 점수를 출력한다.
 *      이어서 게임판의 상태를 N 줄에 걸쳐서 출력한다. 각 격자마다 넘어진 것은 F, 넘어지지 않은 것은 S 를 공백으로 구분하여 출력한다.
 * 해결: 구현
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20165 {
    public static int N, M, R, cnt;
    public static int [][] map;
    public static char [][] answer;

    //
    public static void attack(int x, int y, char d) {
        if (answer[x][y] == 'F') return; //넘어져있으면 아무일도 일어나지 않음

        int dx = 0, dy = 0; //이동 방향
        if (d == 'E') dy = 1;
        else if (d == 'W') dy = -1;
        else if (d == 'S') dx = 1;
        else if (d == 'N') dx = -1;

        int now = map[x][y]; //현재 넘어뜨릴 수 있는 도미노의 수
        while (now > 0){
            if(x < 0 || x >= N || y < 0 || y >= M) break; //격자를 벗어나면 종료

            if (answer[x][y] == 'S') { //도미노가 서 있을 경우
                answer[x][y] = 'F'; //도미노 넘어짐 처리
                cnt++; //공격수 점수 증가
                if(map[x][y] > now) now = map[x][y]; //더 큰 도미노를 만나면 갯수 갱신
            }
            now--;
            x += dx;
            y += dy;
        }
    }

    public static void defense(int x, int y) {
        answer[x][y] = 'S'; //도미노 세우기
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행 개수
        M = Integer.parseInt(st.nextToken()); //열 개수
        R = Integer.parseInt(st.nextToken()); //라운드 횟수

        map = new int[N][M]; //도미노 크기를 담는 배열
        answer = new char[N][M]; //게임판 상태를 담는 배열

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer[i][j] = 'S'; //넘어지지 않은 것
            }
        }

        cnt = 0;
        for(int i=0; i<R*2; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken())-1; //X행
            int Y = Integer.parseInt(st.nextToken())-1; //Y열

            if(i%2 == 0) {
                char D = st.nextToken().charAt(0); //방향 - E W S N (동 서 남 북)
                attack(X, Y, D);
            } else {
                defense(X, Y);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n"); //공격수 점수
        for(char [] cArr : answer) {
            for(char c : cArr) sb.append(c).append(" ");
            sb.append("\n");
        }

        System.out.println(sb); //결과 출력
    }
}