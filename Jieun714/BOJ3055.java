package Jieun714;
/**
 * 문제: 사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다. 이 숲에는 고슴도치가 한 마리 살고 있다. 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.
 *      티떱숲의 지도는 R행 C열로 이루어져 있다. 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
 *      매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 *      티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 *      고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
 * 입력: 첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다. 다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.
 * 출력: 첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.
 * 해결: BFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3055 {
    public static int R, C; //지도의 행, 열
    public static char [][] map;
    public static boolean [][] isVisited;
    public static int [] start; //고슴도치 위치
    public static int [] end; //비버 위치
    public static int [] dx = {0, 0, 1, -1};
    public static int [] dy = {1, -1, 0, 0};
    public static int min = Integer.MAX_VALUE; //최소 이동 시간 저장

    //시간당 물이 차는 곳을 증가시키는 함수
    public static void increase() {
        char[][] copy = new char[R][C]; //원본 맵을 복사해서 현재 시점의 물의 위치를 기준으로 물 확산 처리
        for (int i = 0; i <R; i++) copy[i] = Arrays.copyOf(map[i], C);
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(copy[i][j] == '*') {
                    if(isNextWater(i-1, j)) map[i-1][j] = '*';
                    if(isNextWater(i+1, j)) map[i+1][j] = '*';
                    if(isNextWater(i, j-1)) map[i][j-1] = '*';
                    if(isNextWater(i, j+1)) map[i][j+1] = '*';
                }
            }
        }
    }

    //물이 찰 수 있는 구역인지 체크하는 함수
    public static boolean isNextWater(int y, int x) {
        if(y < 0 || y >= R || x < 0 || x >= C) return false;
        return map[y][x] == '.';
    }

    //고슴도치가 이동할 수 있는 위치인지 체크하는 함수
    public static boolean isChecked(int y, int x) {
        if(y < 0 || y >= R || x < 0 || x >= C) return false;
        return !isVisited[y][x] && map[y][x] != '*' && map[y][x] != 'X';
    }

    //해당 위치에 다음 시간에 물이 찰 예정인지 체크하는 함수
    public static boolean isWater(int y, int x) {
        if(y >= 0 && y < R && x >= 0 && x < C)  return map[y][x] != '*';
        return true;
    }

    public static void bfs() {
        int cnt = 0; //현재 시간
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[] {start[0], start[1], 0});
        isVisited[start[0]][start[1]] = true;

        while(!que.isEmpty()) {
            int [] now = que.poll();
            for(int i=0; i<4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if(isChecked(ny, nx)) { //이동 가능한 위치 확인
                    if(ny == end[0] && nx == end[1]) { //비버 굴 탐색
                        min = Math.min(min, now[2]+1);
                        break;
                    }
                    // 다음 위치가 현재 시간에 물이 차지 않을 곳이면 이동
                    if(isWater(ny-1, nx) && isWater(ny+1, nx) && isWater(ny, nx-1) && isWater(ny, nx+1)) {
                        que.add(new int[]{ny, nx, now[2]+1});
                        isVisited[ny][nx] = true;
                    }
                }
            }
            if(cnt == now[2]) { //현재 시간의 고슴도치 이동이 끝났다면 물 확산
                increase();
                cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        isVisited = new boolean[R][C];
        start = new int[2];
        end = new int[2];

        for(int i=0; i<R; i++) {
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = str.charAt(j);
                if(str.charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (str.charAt(j) == 'D') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        bfs();
        System.out.println(min == Integer.MAX_VALUE ? "KAKTUS" : min); //결과 출력
    }
}