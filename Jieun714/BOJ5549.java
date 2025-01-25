package Jieun714;
/**
 * 문제: 상근이는 우주선을 타고 인간이 거주할 수 있는 행성을 찾고 있다. 마침내, 전 세계 최초로 인간이 거주할 수 있는 행성을 찾았다. 이 행성은 정글, 바다, 얼음이 뒤얽힌 행성이다. 상근이는 이 행성에서 거주 할 수 있는 구역의 지도를 만들어 지구로 보냈다.
 *      상근이가 보내온 지도는 가로 Ncm, 세로 Mcm 직사각형 모양이다. 지도는 1cm 크기의 정사각형으로 나누어져 있고, 각 구역의 지형이 알파벳으로 표시되어 있다. 지형은 정글, 바다, 얼음 중 하나이며, 정글은 J, 바다는 O, 얼음은 I로 표시되어 있다.
 *      지구에 있는 정인이는 조사 대상 영역을 K개 만들었다. 이때, 각 영역에 정글, 바다, 얼음이 각각 몇 개씩 있는지 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 지도의 크기 M과 N이 주어진다. (1 ≤ M, N ≤ 1000)
 *      둘째 줄에 정인이가 만든 조사 대상 영역의 개수 K가 주어진다. (1 ≤ K ≤ 100000)
 *      셋째 줄부터 M개 줄에는 상근이가 보낸 지도의 내용이 주어진다.
 *      다음 K개 줄에는 조사 대상 영역의 정보가 주어진다. 정보는 네 정수 a b c d로 이루어져 있다. 구역은 직사각형 모양 이며, 왼쪽 위 모서리의 좌표가 (a, b) 오른쪽 아래 모서리의 좌표가 (c, d)이다.
 * 출력: 각 조사 대상 영역에 포함되어 있는 정글, 바다, 얼음의 수를 공백으로 구분해 한 줄에 한 정보씩 출력한다.
 * 해결: 누적합
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5549 {
    public static int M, N, K;
    public static int [][] jungle;
    public static  int [][] ocean;
    public static int [][] ice;
    public static StringBuilder sb = new StringBuilder();

    public static void find(int a, int b, int c, int d) {
        int jCnt = jungle[c][d] - jungle[c][b-1] - jungle[a-1][d] + jungle[a-1][b-1]; //조사 대상 영역에 포함된 정글의 수
        int oCnt = ocean[c][d] - ocean[c][b-1] - ocean[a-1][d] + ocean[a-1][b-1]; //조사 대상 영역에 포함된 바다의 수
        int iCnt = ice[c][d] - ice[c][b-1] - ice[a-1][d] + ice[a-1][b-1]; //조사 대상 영역의 포함된 얼음의 수
        sb.append(jCnt +" ").append(oCnt + " ").append(iCnt + " \n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //지도의 세로 길이
        N = Integer.parseInt(st.nextToken()); //지도의 가로 길이
        K = Integer.parseInt(br.readLine()); //조사 대상 영역의 수
        jungle = new int[M+1][N+1]; //정글의 수르 담는 배열
        ocean = new int[M+1][N+1]; //바다의 수를 담는 배열
        ice = new int[M+1][N+1]; //얼음의 수를 담는 배열

        for(int i=1; i<=M; i++) {
            String str = br.readLine();
            for(int j=1; j<=N; j++) {
                jungle[i][j] = jungle[i-1][j] + jungle[i][j-1] - jungle[i-1][j-1];
                ocean[i][j] = ocean[i-1][j] + ocean[i][j-1] - ocean[i-1][j-1];
                ice[i][j] = ice[i-1][j] + ice[i][j-1] - ice[i-1][j-1];

                if(str.charAt(j-1) == 'J') jungle[i][j] += 1;
                else if(str.charAt(j-1) == 'O') ocean[i][j] += 1;
                else ice[i][j] += 1;
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            find(a, b, c, d); //조사 대상 영역에 포함되어 있는 정글, 바다, 얼음의 수 구하기
        }

        System.out.println(sb); //결과 출력
    }
}