package Jieun714;
/**
 * 문제: 크기가 N×M인 격자판의 각 칸에 정수가 하나씩 들어있다. 이 격자판에서 칸 K개를 선택할 것이고, 선택한 칸에 들어있는 수를 모두 더한 값의 최댓값을 구하려고 한다.
 *      단, 선택한 두 칸이 인접하면 안된다. r행 c열에 있는 칸을 (r, c)라고 했을 때, (r-1, c), (r+1, c), (r, c-1), (r, c+1)에 있는 칸이 인접한 칸이다.
 * 입력: 첫째 줄에 N, M, K가 주어진다. 둘째 줄부터 N개의 줄에 격자판에 들어있는 수가 주어진다.
 * 출력: 선택한 칸에 들어있는 수를 모두 더한 값의 최댓값을 출력한다.
 * 해결: 브루트 포스 알고리즘
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18290 {
    public static int [] arr;
    public static int [][] map;
    public static boolean [][] isVisited;
    public static int answer = Integer.MIN_VALUE; //선택한 칸에 들어있는 수를 모두 더한 값
    public static int [] dr = {-1, 1, 0, 0}; //(r-1, c), (r+1, c), (r, c-1), (r, c+1)
    public static int [] dc = {0, 0, -1, 1};

    public static boolean isCheck(int r, int c){
        boolean flag = true;
        for(int i=0; i<4; i++){ //인접행
            int nowR = r + dr[i];
            int nowC = c + dc[i];

            if(0<=nowR && nowR <arr[0] && 0<=nowC && nowC<arr[1]){ //범위안에 존재할때
                if(isVisited[nowR][nowC]) flag = false; //이미 방문했다면 false로
            }
        }
        return flag;
    }

    public static void find(int cnt, int sum, int r, int c){
        if(cnt == arr[2]){ //K과 동일하다면
            answer = Math.max(answer, sum); //선택한 칸에 들어있는 수를 모두 더한 값의 최대 구하기
            return;
        }

        for(int i=r; i<arr[0]; i++){
            for(int j=(r==i ? c:0); j<arr[1]; j++){
                if(isVisited[i][j]) continue; //방문했다면 다음으로
                if(isCheck(i, j)){ //인접한 수가 아니라면
                    isVisited[i][j] = true; //방문체크
                    dfs(cnt+1, sum + map[i][j], i, j);
                    isVisited[i][j] = false; //방문해제
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[3]; //N, M, K
        for(int i=0; i<3; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        map = new int[arr[0]][arr[1]];
        for(int i=0; i<arr[0]; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<arr[1]; j++){
                map[i][j] = Integer.parseInt(st.nextToken()); //격자판에 들어있는 수
            }
        }

        isVisited = new boolean[arr[0]][arr[1]];
        find(0, 0, 0, 0);

        System.out.println(answer); //결과 출력
    }
}