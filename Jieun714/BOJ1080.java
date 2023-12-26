package Jieun714;
/**
 * 문제: 0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 *      행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)
 * 입력: 첫째 줄에 행렬의 크기 N M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 행렬 A가 주어지고, 그 다음줄부터 N개의 줄에는 행렬 B가 주어진다.
 * 출력: 첫째 줄에 문제의 정답을 출력한다. 만약 A를 B로 바꿀 수 없다면 -1을 출력한다.
 * 해결: 그리디 알고리즘
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1080 {
    public static int N, M;
    public static int [][] A, B;
    public static boolean flag;
    public static int cnt = 0; //연산의 횟수

    public static void reverse(int x, int y){
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                int nowY = y+i;
                int nowX = x+j;
                A[nowY][nowX] = (A[nowY][nowX] == 1 ? 0 : 1);
            }
        }
        cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행의 크기
        M = Integer.parseInt(st.nextToken()); //열의 크기
        A = new int [N][M]; //바꾸기 전
        B = new int [N][M]; //바꾼 후

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                A[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                B[i][j] = str.charAt(j) - '0';
            }
        }

        if (Arrays.deepEquals(A, B)) //처음부터 같다면
            flag = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(A[i][j] != B[i][j] && i+2 <N && j+2 <M) { //행렬의 값이 같지 않고, 3x3이 행렬 범위 안에 있을 때만
                    reverse(j, i);
                    if (Arrays.deepEquals(A, B)) {
                        flag = true;
                        break;
                    }
                }
            }
        }

        System.out.println(flag ? cnt : -1); //flag가 true일 때 갯수 출력, false라면 -1
    }
}
