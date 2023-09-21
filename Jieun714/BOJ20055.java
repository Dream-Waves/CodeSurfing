package Jieun714;
/**
 * 문제: 길이가 N인 컨베이어 벨트가 있고, 길이가 2N인 벨트가 이 컨베이어 벨트를 위아래로 감싸며 돌고 있다. 벨트는 길이 1 간격으로 2N개의 칸으로 나뉘어져 있으며, 각 칸에는 아래 그림과 같이 1부터 2N까지의 번호가 매겨져 있다.
 *      벨트가 한 칸 회전하면 1번부터 2N-1번까지의 칸은 다음 번호의 칸이 있는 위치로 이동하고, 2N번 칸은 1번 칸의 위치로 이동한다. i번 칸의 내구도는 Ai이다. 위의 그림에서 1번 칸이 있는 위치를 "올리는 위치", N번 칸이 있는 위치를 "내리는 위치"라고 한다.
 *      컨베이어 벨트에 박스 모양 로봇을 하나씩 올리려고 한다. 로봇은 올리는 위치에만 올릴 수 있다. 언제든지 로봇이 내리는 위치에 도달하면 그 즉시 내린다. 로봇은 컨베이어 벨트 위에서 스스로 이동할 수 있다. 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 즉시 1만큼 감소한다.
 *      컨베이어 벨트를 이용해 로봇들을 건너편으로 옮기려고 한다. 로봇을 옮기는 과정에서는 아래와 같은 일이 순서대로 일어난다.
 *
 *      1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
 *      2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
 *          2-1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
 *      3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 *      4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 *
 *      종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자. 가장 처음 수행되는 단계는 1번째 단계이다.
 *
 * 입력: 첫째 줄에 N, K가 주어진다. 둘째 줄에는 A1, A2, ..., A2N이 주어진다.
 *
 * 출력: 몇 번째 단계가 진행 중일때 종료되었는지 출력한다.
 *
 * 해결: 구현. 문제의 1~4번 순서대로 실행
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055 {
    public static int N, K;
    public static int [] conveyor;
    public static boolean [] robot;
    public static int level = 1; //1 단계부터 시작

    public static void checkLevel(){
        while(true){
            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
            // 1-1. 벨트 이동
            int lastNum = conveyor[N*2-1]; //conveyor의 마지막 값
            for(int i=N*2-1; i>0; i--){ // 맨 앞부터 시작하기 위해 거꾸로
                conveyor[i] = conveyor[i-1];
            }
            conveyor[0] = lastNum;

            // 1-2. 로봇 이동
            for(int i=N-1; i>0; i--){
                robot[i] = robot[i-1];
            }
            robot[0] = false;
            robot[N-1] = false; //컨테이너 벨트에서 내려갈 때

            // 2. 로봇이 한칸 이동한다. 만약 이동할 수 없다면 가만히 있는다.
            // 단 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            for(int i=N-1; i>0; i--){ // 맨 앞부터 시작하기 위해 거꾸로
                if(!robot[i] && robot[i-1] && conveyor[i]>=1){
                    conveyor[i]--;
                    robot[i] = true;
                    robot[i-1] = false;
                }
            }

            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if(conveyor[0] > 0){
                robot[0] = true;
                conveyor[0]--;
            }

            // 4. 내구도가 0인 칸의 개수가 K개 이상일 때 종료
            int cnt = 0;
            for(int i=0; i<N*2; i++){
                if(conveyor[i] ==0) cnt++;
                if(cnt>=K) return;
            }

            level++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //컨테이너 벨트 길이
        K = Integer.parseInt(st.nextToken()); //내구도가 0인 칸의 개수
        conveyor = new int[N*2]; //칸의 내구도를 담는 배열
        robot = new boolean[N]; //로봇의 유무를 담는 배열. 로봇은 올리는 위치에만 올릴 수 있기 때문에 N으로 선언
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N*2; i++){
            conveyor[i] = Integer.parseInt(st.nextToken());
        }
        checkLevel();
        System.out.println(level); //결과 출력
    }
}