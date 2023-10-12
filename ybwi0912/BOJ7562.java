package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023-10-12
 * BOJ 7562번: 나이트의 이동
 * [ 문제 ]
 * 체스판 위에 한 나이트가 놓여져 있다.
 * 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 나이트가 이동하려고 하는 칸이 주어진다.
 * 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 *
 * [ 입력 ]
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 * 각 테스트 케이스는 세 줄로 이루어져 있다.
 * 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
 * 체스판의 크기는 l × l이다.
 * 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다.
 * 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 *
 * [ 출력 ] 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 *
 * [ 예제 입력 1 ]
 * 3
 * 8
 * 0 0
 * 7 0
 * 100
 * 0 0
 * 30 50
 * 10
 * 1 1
 * 1 1
 *
 * [ 예제 출력 1 ]
 * 5
 * 28
 * 0
 * */

public class BOJ7562 {
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[][] board;
    static boolean[][] isVisited;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 테스트 케이스의 개수
        StringTokenizer token;

        for(int i=0; i<T; i++){
            L = Integer.parseInt(bf.readLine()); // 체스판의 한 변의 길이
            board = new int[L][L];
            isVisited = new boolean[L][L];

            token = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            token = new StringTokenizer(bf.readLine());
            int ex = Integer.parseInt(token.nextToken());
            int ey = Integer.parseInt(token.nextToken());

            bfs(x, y, ex, ey);

            System.out.println(board[ex][ey]);
        }
    }

    static void bfs(int x, int y, int ex, int ey){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while(!q.isEmpty()){
            int[] n = q.poll();

            if(n[0] == ex && n[1] == ey) break;

            for(int i=0; i<8; i++){
                int nx = n[0] + dx[i];
                int ny = n[1] + dy[i];

                if(nx>=0 && nx<L && ny>=0 && ny<L && board[nx][ny]==0) {
                    board[nx][ny] = board[n[0]][n[1]] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}
