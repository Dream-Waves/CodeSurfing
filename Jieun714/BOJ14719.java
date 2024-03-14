package Jieun714;
/**
 * 문제: 2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다. 비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?
 * 입력: 첫 번째 줄에는 2차원 세계의 세로 길이 H과 2차원 세계의 가로 길이 W가 주어진다. (1 ≤ H, W ≤ 500)
 *      두 번째 줄에는 블록이 쌓인 높이를 의미하는 0이상 H이하의 정수가 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.
 *      따라서 블록 내부의 빈 공간이 생길 수 없다. 또 2차원 세계의 바닥은 항상 막혀있다고 가정하여도 좋다.
 * 출력: 2차원 세계에서는 한 칸의 용량은 1이다. 고이는 빗물의 총량을 출력하여라. 빗물이 전혀 고이지 않을 경우 0을 출력하여라.
 * 해결: 구현
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken()); //2차원 세계의 세로 길이
        int W = Integer.parseInt(st.nextToken()); //2차원 세계의 가로 길이
        boolean [][] blocks = new boolean[H][W]; //HxW 사이즈의 블록
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            int high = Integer.parseInt(st.nextToken());
            for(int j=H-1; j>H-high-1; j--){ //왼쪽하단부터 채우기위해 j의 시작을 H-1로 둠
                blocks[j][i] = true; //채워지면 true로
            }
        }

        int answer = 0; //빗물의 총량
        for(int i=0; i<H; i++) {
            int cnt = 0; //고인 빗물의 용량
            boolean status = false; //블록으로 막혀있는 지 체크하는 변수
            for(int j=0; j<W; j++){
                if(!status && blocks[i][j]) { //블록이 있다면
                    status = true; //상태 변경
                } else if(status && blocks[i][j]) { //블록에서 또다른 블록에 다다랐다면
                    answer += cnt; //빗물에 총량에 더해줌
                    cnt = 0; //현재 빗물의 용량 초기화
                } else if(status && !blocks[i][j]){ //막혀있는 공간에 빗물이 고여있을 때
                    cnt += 1; //현재 빗물의 용량 더하기
                }
            }
        }
        System.out.println(answer); //고이는 빗물의 총량 출력
    }
}