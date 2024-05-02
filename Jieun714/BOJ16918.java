package Jieun714;
/**
 * 문제: 봄버맨은 크기가 R×C인 직사각형 격자판 위에서 살고 있다. 격자의 각 칸은 비어있거나 폭탄이 들어있다.
 *      폭탄이 있는 칸은 3초가 지난 후에 폭발하고, 폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다. 즉, 폭탄이 있던 칸이 (i, j)인 경우에 (i+1, j), (i-1, j), (i, j+1), (i, j-1)도 함께 파괴된다. 만약, 폭탄이 폭발했을 때, 인접한 칸에 폭탄이 있는 경우에는 인접한 폭탄은 폭발 없이 파괴된다. 따라서, 연쇄 반응은 없다.
 *      봄버맨은 폭탄에 면역력을 가지고 있어서, 격자판의 모든 칸을 자유롭게 이동할 수 있다. 봄버맨은 다음과 같이 행동한다.
 *
 *      - 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
 *      - 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
 *      - 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
 *      - 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
 *      - 3과 4를 반복한다.
 *      폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하려고 한다.
 * 입력: 첫째 줄에 R, C, N (1 ≤ R, C, N ≤ 200)이 주어진다. 둘째 줄부터 R개의 줄에 격자판의 초기 상태가 주어진다. 빈 칸은 '.'로, 폭탄은 'O'로 주어진다.
 * 출력: 총 R개의 줄에 N초가 지난 후의 격자판 상태를 출력한다.
 * 해결: 구맨
 *
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918 {
    public static int R, C, N;
    public static char [][] map;
    public static int [][] bombCnt;

    public static void bombArea(int r, int c) {
        if(0<=r && r<R && 0<=c && c<C) {
            if(bombCnt[r][c] != 3) {
                map[r][c] = '.';
                bombCnt[r][c] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); //세로
        C = Integer.parseInt(st.nextToken()); //가로
        N = Integer.parseInt(st.nextToken()); //흘러가는 시간
        map = new char[R][C];
        bombCnt= new int[R][C];

        for(int i=0; i<R; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                bombCnt[i][j] = (map[i][j] == 'O') ? 1 : 0;
            }
        }

        while(N>1) {
            //폭탄이 설치되지 않은 곳은 폭탄 설치, 설치되어 있다면 폭탄 카운트 증가
            for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    if(map[r][c] == '.') { //폭탄이 아닐 때
                        map[r][c] = 'O'; //폭탄 설치
                    } else { //폭탄일 때
                        bombCnt[r][c] += 1; //폭탄 카운트 증가
                    }
                }
            }

            for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    if(bombCnt[r][c] == 3) { //설치된 폭탄이 폭발할 때
                        bombArea(r+1,c); //상
                        bombArea(r-1,c); //하
                        bombArea(r,c+1); //좌
                        bombArea(r,c-1); //우

                        //현재 위치도 초기화
                        map[r][c] = '.';
                        bombCnt[r][c] = 0;
                    }
                }
            }
            N--;
        }

        StringBuilder sb = new StringBuilder();
        for(char [] cArr : map) {
            for(char c : cArr) sb.append(c);
            sb.append("\n");
        }
        System.out.println(sb); //결과 출력
    }
}