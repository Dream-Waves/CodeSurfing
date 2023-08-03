package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2023-08-03
 * BOJ 1495: 기타리스트
 * 첫 곡부터 해당 곡 시작 전에 바꿀 수 있는 볼륨을 더하거나 빼면서 접근 가능한 볼륨을 isVisited 배열에 기록하고,
 * 마지막 곡에서 접근 가능한 볼륨 중 최댓값을 출력한다
 * */

public class BOJ1495 {
    static int N;
    static int S;
    static int M;
    static int[] V;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(token.nextToken()); // 곡의 개수
        S = Integer.parseInt(token.nextToken()); // 시작 볼륨
        M = Integer.parseInt(token.nextToken()); // 볼륨의 최댓값

        V = new int[N+1];
        token = new StringTokenizer(bf.readLine());

        for(int i=1; i<N+1; i++) V[i] = Integer.parseInt(token.nextToken());

        isVisited = new boolean[N+1][M+1]; // 1번 곡 ~ N번 곡, 볼륨 0 ~ M

        checkVol(1, S); // 첫 번째 곡부터 체크, 시작 볼륨 S

        int ans = -1;

        for(int i=M; i>=0; i--){
            if(isVisited[N][i]==true){
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    static void checkVol(int i, int p){ // 몇 번째 곡인지 인덱스, 현재 볼륨 p
        if(i==N+1) return;

        int plus = p + V[i];
        if(plus<=M){
            if(!isVisited[i][plus]){ // 방문하지 않은 노드라면
                isVisited[i][plus] = true; // 방문 처리
                checkVol(i+1, plus); // 재귀
            }
        }

        int minus = p - V[i];
        if(minus>=0){
            if(!isVisited[i][minus]){ // 방문하지 않은 노드라면
                isVisited[i][minus] = true; // 방문 처리
                checkVol(i+1, minus); // 재귀
            }
        }
    }
}
