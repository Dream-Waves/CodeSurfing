package Jieun714;
/**
 * 문제: 한수는 크기가 2^N × 2^N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
 *      N > 1인 경우, 배열을 크기가 2^(N-1) × 2^(N-1)로 4등분 한 후에 재귀적으로 순서대로 방문한다. 다음 예는 2^2 × 2^2 크기의 배열을 방문한 순서이다.
 *      N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 정수 N, r, c가 주어진다.
 * 출력: r행 c열을 몇 번째로 방문했는지 출력한다.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
    public static int N, R, C;
    public static int cnt; //누적 방문 수
    public static  int mapSize;

    public static void recursion(int r, int c, int mapSize){
        if(mapSize == 1) { //종료조건
            return;
        }
        int num = (int)Math.pow(mapSize,2);

        //if 조건식 Z순서대로
        if(r<mapSize/2 && c<mapSize/2){
            recursion(r, c, mapSize/2);
        }
        else if(r<mapSize/2 && c>=mapSize/2){
            cnt += num/4;
            recursion(r, c-mapSize/2, mapSize/2);
        }
        else if(r>=mapSize/2 && c<mapSize/2){
            cnt += (num/4)*2;
            recursion(r-mapSize/2, c, mapSize/2);
        }
        else{
            cnt += (num/4)*3;
            recursion(r-mapSize/2, c-mapSize/2, mapSize/2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //배열의 크기를 파악하기 위한 수
        R = Integer.parseInt(st.nextToken()); //행
        C = Integer.parseInt(st.nextToken()); //열

        mapSize = (int)Math.pow(2, N);
        recursion(R, C, mapSize);
        System.out.println(cnt);
    }
}