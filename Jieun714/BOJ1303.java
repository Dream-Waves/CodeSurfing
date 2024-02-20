package Jieun714;
/**
 * 문제: 전쟁은 어느덧 전면전이 시작되었다. 결국 전투는 난전이 되었고, 우리 병사와 적국 병사가 섞여 싸우게 되었다. 그러나 당신의 병사들은 흰색 옷을 입고, 적국의 병사들은 파란색 옷을 입었기 때문에 서로가 적인지 아군인지는 구분할 수 있다. 문제는 같은 팀의 병사들은 모이면 모일수록 강해진다는 사실이다.
 *      N명이 뭉쳐있을 때는 N2의 위력을 낼 수 있다. 과연 지금 난전의 상황에서는 누가 승리할 것인가? 단, 같은 팀의 병사들이 대각선으로만 인접한 경우는 뭉쳐 있다고 보지 않는다.
 * 입력: 첫째 줄에는 전쟁터의 가로 크기 N, 세로 크기 M(1 ≤ N, M ≤ 100)이 주어진다. 그 다음 두 번째 줄에서 M+1번째 줄에는 각각 (X, Y)에 있는 병사들의 옷색이 띄어쓰기 없이 주어진다. 모든 자리에는 병사가 한 명 있다. B는 파란색, W는 흰색이다. 당신의 병사와 적국의 병사는 한 명 이상 존재한다.
 * 출력: 첫 번째 줄에 당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력한다.
 * 해결: BFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1303 {
    public static int N, M;
    public static char [][] color;
    public static int [] dx = {0, 0, -1, 1};
    public static int [] dy = {1, -1, 0, 0};
    public static boolean [][] isVisited;
    public static int ourSum = 0; //우리 병사의 위력의 합
    public static int anotherSum = 0; //적국 병사의 위력의 합

    public static void bfs(int y, int x, char c){
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[]{y, x});
        isVisited[y][x] = true;
        int cnt = 1; //같은 팀 병사의 수

        while(!que.isEmpty()){
            int [] now = que.poll();
            for(int i=0; i<4; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if(0<=ny && ny <M && 0<=nx && nx<N) { //이동 가능한 위치일 때
                    if(!isVisited[ny][nx] && color[ny][nx] == c){ //현재 병사와 동일한 병사일 때
                        cnt++; //병사 수 증가
                        isVisited[ny][nx] = true; //방문체크
                        que.add(new int[]{ny, nx}); //큐에 삽입
                    }
                }
            }
        }
        if(c == 'W'){ //우리 병사일 때
            ourSum += Math.pow(cnt, 2);
        } else { //적국의 병사일 때
            anotherSum += Math.pow(cnt, 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //전쟁터의 가로 크기
        M = Integer.parseInt(st.nextToken()); //전쟁터의 세로 크기
        color = new char[M][N]; //병사들의 옷색을 담는 배열

        for(int i=0; i<M; i++){
            color[i] = br.readLine().toCharArray();
        }

        isVisited = new boolean[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!isVisited[i][j]) //방문하지 않은 곳일 때만
                    bfs(i, j, color[i][j]);
            }
        }

        System.out.println(ourSum + " " + anotherSum); //당신의 병사의 위력의 합과 적국의 병사의 위력의 합을 출력
    }
}