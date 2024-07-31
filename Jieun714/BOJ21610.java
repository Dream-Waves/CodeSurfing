package Jieun714;
/**
 * 문제: 마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그 마법을 할 수 있다. 오늘 새로 배운 마법은 비바라기이다. 비바라기를 시전하면 하늘에 비구름을 만들 수 있다. 오늘은 비바라기를 크기가 N×N인 격자에서 연습하려고 한다. 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지한다. 바구니에 저장할 수 있는 물의 양에는 제한이 없다. (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.
 *      격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다. 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.
 *      비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 구름은 칸 전체를 차지한다. 이제 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 이동을 명령하면 다음이 순서대로 진행된다.
 *
 *      1. 모든 구름이 di 방향으로 si칸 이동한다.
 *      2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 *      3. 구름이 모두 사라진다.
 *      4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
 *         - 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 *         - 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 *      5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
 *      M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
 * 입력: 첫째 줄에 N, M이 주어진다.
 *      둘째 줄부터 N개의 줄에는 N개의 정수가 주어진다. r번째 행의 c번째 정수는 A[r][c]를 의미한다.
 *      다음 M개의 줄에는 이동의 정보 di, si가 순서대로 한 줄에 하나씩 주어진다.
 * 출력: 첫째 줄에 M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력한다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21610 {
    public static int N, M;
    public static int [][] A;
    public static boolean [][] status;
    public static int [] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int [] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static int checkCross(int y, int x) {
        int cnt = 0;
        if(0<=x-1 && x-1<N && 0<=y-1 && y-1<N) if(A[y-1][x-1] != 0) cnt += 1; //좌측 상단 대각선
        if(0<=x+1 && x+1<N && 0<=y-1 && y-1<N) if(A[y-1][x+1] != 0) cnt += 1; //우측 상단 대각선
        if(0<=x-1 && x-1<N && 0<=y+1 && y+1<N) if(A[y+1][x-1] != 0) cnt += 1; //좌측 하단 대각선
        if(0<=x+1 && x+1<N && 0<=y+1 && y+1<N) if(A[y+1][x+1] != 0) cnt += 1; //우측 하단 대각선
        return cnt;
    }

    public static void goorm(int d, int s) {
        boolean [][] newStatus = new boolean[N][N]; //위치를 이동한 구름의 좌표를 파악하기 위한 배열
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (status[i][j]) { //구름이 있는 칸일 때
                    int nx = (j + dx[d-1]*s)%N;
                    int ny = (i + dy[d-1]*s)%N;
                    if (nx < 0) nx += N;
                    if (ny < 0) ny += N;
                    newStatus[ny][nx] = true; //구름 상태 표시
                    A[ny][nx] += 1; //저장된 물 증가
                }
            }
        }
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if(newStatus[i][j]) A[i][j] += checkCross(i, j); //물복사 버그
            }
        }
        boolean [][] newCheck = new boolean[N][N]; //새로운 구름 상태를 담는 배열
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!newStatus[i][j] && A[i][j] >= 2) { //구름이 될 수 있는 칸일 때
                    newCheck[i][j] = true; //새로운 구름 상태 표시
                    A[i][j] -= 2; //물의 양 2 감소
                }
            }
        }
        status = newCheck; //새로운 구름 상태로 전환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //비바라기 크기
        M = Integer.parseInt(st.nextToken()); //이동의 정보
        A = new int[N][N];
        status = new boolean[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }

        //시작 구름 초기화
        status[N-1][0] = true;
        status[N-1][1] = true;
        status[N-2][0] = true;
        status[N-2][1] = true;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); //방향
            int s = Integer.parseInt(st.nextToken()); //거리
            goorm(d, s);
        }

        int total = 0; //물의 양의 합
        for(int [] arr: A){
            for(int n: arr) total += n;
        }
        System.out.println(total); //결과 출력
    }
}