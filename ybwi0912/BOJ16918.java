package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023-10-10
 * BOJ 16918번: 봄버맨
 * [ 문제 ]
 * 봄버맨은 크기가 R×C인 직사각형 격자판 위에서 살고 있다. 격자의 각 칸은 비어있거나 폭탄이 들어있다.
 *
 * 폭탄이 있는 칸은 3초가 지난 후에 폭발하고, 폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다. 즉, 폭탄이 있던 칸이 (i, j)인 경우에 (i+1, j), (i-1, j), (i, j+1), (i, j-1)도 함께 파괴된다. 만약, 폭탄이 폭발했을 때, 인접한 칸에 폭탄이 있는 경우에는 인접한 폭탄은 폭발 없이 파괴된다. 따라서, 연쇄 반응은 없다.
 *
 * 봄버맨은 폭탄에 면역력을 가지고 있어서, 격자판의 모든 칸을 자유롭게 이동할 수 있다. 봄버맨은 다음과 같이 행동한다.
 *
 * 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
 * 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
 * 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
 * 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
 * 3과 4를 반복한다.
 * 폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하려고 한다.
 *
 * [ 입력 ]
 * 첫째 줄에 R, C, N(1 ≤ R, C, N ≤ 200)이 주어진다. 둘째 줄부터 R개의 줄에 격자판의 초기 상태가 주어진다. 빈 칸은 '.'로, 폭탄은 'O'로 주어진다.
 *
 * [ 출력 ]
 * 총 R개의 줄에 N초가 지난 후의 격자판 상태를 출력한다.
 *
 * [ 예제 입력 1 ]
 * 6 7 3
 * .......
 * ...O...
 * ....O..
 * .......
 * OO.....
 * OO.....
 *
 * [ 예제 출력 1 ]
 * OOO.OOO
 * OO...OO
 * OOO...O
 * ..OO.OO
 * ...OOOO
 * ...OOOO
 *
 * [ 예제 입력 2 ]
 * 6 7 4
 * .......
 * ...O...
 * ....O..
 * .......
 * OO.....
 * OO.....
 *
 * [ 예제 출력 2 ]
 * OOOOOOO
 * OOOOOOO
 * OOOOOOO
 * OOOOOOO
 * OOOOOOO
 * OOOOOOO
 *
 * [ 예제 입력 3 ]
 * 6 7 5
 * .......
 * ...O...
 * ....O..
 * .......
 * OO.....
 * OO.....
 *
 * [ 예제 출력 3 ]
 * .......
 * ...O...
 * ....O..
 * .......
 * OO.....
 * OO.....
 * */

public class BOJ16918 {
    public static void main(String[] args) throws IOException {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        int R = Integer.parseInt(token.nextToken());
        int C = Integer.parseInt(token.nextToken());
        int N = Integer.parseInt(token.nextToken());

        int[][] board = new int[R][C]; // 해당 칸에 위치한 폭탄이 몇 초에 터지는지 저장하기 위한 int 배열
        char[][] caseA = new char[R][C]; // N % 4 == 1
        char[][] caseB = new char[R][C]; // N % 4 == 3
        boolean[][] check;

        for(int i=0; i<R; i++){
            String s = bf.readLine();
            for(int j=0; j<C; j++){
                char c = s.charAt(j);
                caseA[i][j] = c; // 초기 상태 저장
                if(c=='.') board[i][j] = 0;
                else if(c=='O') board[i][j] = 3;
            }
        }
        // input

        if(N==1){
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(caseA[i][j]);
                }
                System.out.println();
            }
            return;
        } // 예외 처리 : N = 1일 때는 연산 없이 초기 상태 출력 후 프로그램 종료

        for(int n=2; n<=5; n++){ // 1초부터 N초까지, 첫 1초 동안은 아무것도 하지 않기 때문에 2초부터 시작
            check = new boolean[R][C];

            // ① 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(board[i][j]==0) board[i][j]=n+3;
                }
            }

            // ② 폭발의 영향권에 들어있는 칸을 체크한다
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(board[i][j] == n){
                        check[i][j] = true;
                        for(int k=0; k<4; k++){
                            int nr = i + dr[k];
                            int nc = j + dc[k];

                            if(nr<0 || nc < 0 || nr>=R || nc>=C) continue;
                            check[nr][nc] = true;
                        }
                    }
                }
            }

            // ③ 체크한 결과를 격자판 배열에 반영한다
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(check[i][j]) {
                        board[i][j] = 0;
                        if(n==3) caseB[i][j] = '.'; // N % 4 == 3일 때
                        else if(n==5) caseA[i][j] = '.'; // N % 4 == 1일 때
                    } else {
                        if(n==3) caseB[i][j] = 'O';
                        else if(n==5) caseA[i][j] = 'O';
                    }
                }
            }
        }
        // opeartion

        if(N%2==0){ // N이 짝수일 때는 항상 모든 칸에 폭탄이 들어 있다
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    System.out.print('O');
                }
                System.out.println();
            }
        }
        else if(N%4==1){ // N != 1이면서 N % 4 == 1일 경우 : caseA
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(caseA[i][j]);
                }
                System.out.println();
            }
        }
        else if(N%4==3){ // N != 1이면서 N % 4 == 3일 경우 : caseB
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(caseB[i][j]);
                }
                System.out.println();
            }
        }
        // output
    }
}
