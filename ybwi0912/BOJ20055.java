package ybwi0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2023-08-22
 * BOJ 20055번: 컨베이어 벨트 위의 로봇
 * 단계를 나타내는 변수 cnt를 증가시키며 벨트를 회전시키고 모든 로봇을 한 칸씩 이동하길 반복한다
 * (고려할 점 : 로봇은 컨베이어 벨트의 1번 칸에 올리고, N번 칸에서 내린다 | 로봇을 올리거나 이동하면 해당 칸의 내구도가 1 감소한다)
 * 내구도가 0인 칸의 개수 zero가 K 이상이 되면 모든 과정을 종료하고 단계를 나타내는 변수 cnt를 출력한다
 * */

public class BOJ20055 {
    static int N, K; // 컨베이어 벨트의 길이 N, 내구도가 0인 칸의 개수가 K개 이상일 때 과정 종료
    static int[] conveyor; // 컨베이어 벨트 칸의 내구도
    static boolean[] isExist; // 컨베이어 벨트의 해당 칸에 로봇이 존재하는지 체크하기 위한 배열
    static int cnt, zero; // 단계 cnt, 내구도가 0인 칸의 개수 zero

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(token.nextToken()); // 컨베이어 벨트의 길이
        K = Integer.parseInt(token.nextToken()); // 과정 종료 조건

        conveyor = new int[2*N + 1]; // 1 ~ 2N 칸의 컨베이어 벨트
        isExist = new boolean[2*N + 1];
        token = new StringTokenizer(bf.readLine());

        for(int i=1; i<2*N + 1; i++){ // 1부터 입력할 것
            conveyor[i] = Integer.parseInt(token.nextToken());
        }

        cnt = 0; // cnt번째 단계
        zero = 0; // 내구도가 0인 칸의 개수 zero

        while(true){
            cnt++; // 단계 1 증가
            rotate(); // 벨트 회전
            move(); // 로봇 이동

            if(zero >= K) break; // 회전, 이동 후 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
        }

        System.out.println(cnt); // 진행 중이던 단계 cnt를 출력
    }

    /*
     * 벨트 회전: 컨베이어 벨트와 로봇들을 우측으로 한 칸씩 이동
     * */
    static void rotate(){
        // 컨베이어 벨트 한 칸씩 이동
        conveyor[0] = conveyor[2*N]; // 배열의 0번째 칸은 사용하지 않기 때문에 값 이동 위한 temp의 역할로 활용
        for(int i=2*N; i>1; i--){
            conveyor[i] = conveyor[i-1]; // 2N-1번째 칸부터 우측으로 한 칸씩 이동
        }
        conveyor[1] = conveyor[0]; // 미리 저장해뒀던 값을 첫 번째 칸에 저장

        // 컨베이어 벨트 위에 존재하는 로봇들도 한 칸씩 이동 - 위와 같은 과정으로 실행
        isExist[0] = isExist[2*N];
        for(int i=2*N; i>1; i--){
            isExist[i] = isExist[i-1];
        }
        isExist[1] = isExist[0];
    }

    /*
     * 로봇 이동
     * 1) 벨트 회전 이후 N번 칸에 로봇이 존재한다면 로봇을 내린다
     * 2) 벨트 위의 모든 로봇을 이동한다 - N-1번째 칸부터 거꾸로 체크한다.
     *    로봇의 다음 칸이 비어 있고, 다음 칸의 내구도가 0 이상이라면
     *    로봇을 다음 칸으로 옮기고 이동한 칸의 내구도를 1 감소시킨다
     *    만약 이동한 칸의 내구도가 0이 되었다면 zero를 1 더한다
     * 3) 1번 위치에 로봇이 존재하지 않고 내구도가 남아 있다면 로봇을 올리고 내구도를 감소시킨다.
     * 4) 로봇을 이동한 결과 N번 칸에 로봇이 존재한다면 로봇을 내린다(이 과정을 2번 과정 바로 다음에 실행해도 괜찮을듯)
     * */
    static void move(){
        if(isExist[N]) isExist[N] = false;
        // 내리는 위치(N번 칸)에 로봇이 존재한다면 로봇을 내린다

        for(int i=N-1; i>0; i--){ // 내리는 위치와 가까운 칸부터 1번 칸까지 체크
            if(isExist[i]) { // 만약 해당 위치에 로봇이 존재한다면
                if (!isExist[i + 1] && conveyor[i + 1] > 0) {
                    // 다음 칸에 로봇이 없고, 다음 칸의 내구도가 0 이상이라면
                    isExist[i] = false; // 해당 위치에서 로봇을 들어서
                    isExist[i + 1] = true; // 다음 칸으로 옮기고
                    if (--conveyor[i + 1] <= 0) zero++; // 다음 칸의 내구도를 1 감소시킨다
                    // 만약 이동한 칸의 내구도가 0이 되었다면 zero를 1 증가시킨다
                }
            }
        }

        if(!isExist[1] && conveyor[1] > 0){
            isExist[1] = true;
            if(--conveyor[1] <= 0) zero++;
        }

        if(isExist[N]){
            isExist[N] = false;
            // 내리는 위치(N번 칸)에 로봇이 존재한다면 로봇을 내린다
        }
    }
}