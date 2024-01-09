/*
2583. Silver 1 - 영역 구하기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           43998	    25139     19667	         57.551%


    문제
        눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
        예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.
            0 1 0 0 0 0 0
            1 1 1 1 0 0 0
            1 1 1 1 0 0 0
            0 1 0 0 1 1 0
            0 0 0 0 1 1 0

        <그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.
        M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.


    입력
        첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다. 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.


    출력
        첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.


    예제 입력 1
        5 7 3
        0 2 4 4
        1 1 2 5
        4 0 6 2
    예제 출력 1
        3
        1 7 13


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        깊이 우선 탐색
*/


// 메모리 : 15420KB
// 시간 : 132ms
// 코드 길이 : 3351B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2583 {
    static int M; // 세로 길이  // 100 이하의 자연수
    static int N; // 가로 길이  // 100 이하의 자연수
    static boolean paper[][]; // 모눈종이
    static int startX; // 왼쪽 아래 꼭짓점의 x 좌표
    static int startY; // 왼쪽 아래 꼭짓점의 y 좌표
    static int endX; // 오른쪽 위 꼭짓점의 x 좌표
    static int endY; // 오른쪽 위 꼭짓점의 y 좌표
    static int dr[] = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int dc[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int size; // 영역의 넓이

    public static void fillArea() { // 직사각형을 그리는 메서드
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                paper[j][i] = true;
            }
        }
    }

    public static boolean check(int r, int c) { // 모눈종이 범위 안의 좌표인지 검사하는 메서드
        if (r >= 0 && r < M && c >= 0 && c < N) {
            return true;
        }

        return false;
    }

    public static void DFS(int r, int c) { // 해당 좌표가 속한 영역의 넓이를 구하는 메서드
        size += 1;

        paper[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (check(nr, nc) && !paper[nr][nc]) {
                DFS(nr, nc);
            }
        }
    }

    public static void calculateResult() { // 분리되어 나누어지는 영역의 개수와 각 영역의 넓이를 구하는 메서드
        ArrayList<Integer> area = new ArrayList<>(); // 각 영역의 넓이를 저장하는 리스트
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                if (!paper[m][n]) {
                    size = 0; // 영역의 넓이 초기화
                    DFS(m, n);
                    area.add(size);
                }
            }
        }

        Collections.sort(area); // 영역의 넓이 오름차순 정렬
        StringBuilder sb = new StringBuilder();
        sb.append(area.size()).append("\n"); // 분리되어 나누어지는 영역의 개수
        for (int a : area) {
            sb.append(a);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken()); // 그릴 직사각형의 개수  // 100 이하의 자연수

        paper = new boolean[M][N];
        for (int k = 0; k < K; k++) {
            token = new StringTokenizer(bf.readLine());
            startX = Integer.parseInt(token.nextToken());
            startY = Integer.parseInt(token.nextToken());
            endX = Integer.parseInt(token.nextToken());
            endY = Integer.parseInt(token.nextToken());

            fillArea();
        }

        calculateResult();
    }
}
