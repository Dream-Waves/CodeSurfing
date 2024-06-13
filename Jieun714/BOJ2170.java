package Jieun714;
/**
 * 문제: 매우 큰 도화지에 자를 대고 선을 그으려고 한다. 선을 그을 때에는 자의 한 점에서 다른 한 점까지 긋게 된다. 선을 그을 때에는 이미 선이 있는 위치에 겹쳐서 그릴 수도 있는데, 여러 번 그은 곳과 한 번 그은 곳의 차이를 구별할 수 없다고 하자.
 *      이와 같은 식으로 선을 그었을 때, 그려진 선(들)의 총 길이를 구하는 프로그램을 작성하시오. 선이 여러 번 그려진 곳은 한 번씩만 계산한다.
 * 입력: 첫째 줄에 선을 그은 횟수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 다음 N개의 줄에는 선을 그을 때 선택한 두 점의 위치 x, y (-1,000,000,000 ≤ x < y ≤ 1,000,000,000)가 주어진다.
 * 출력: 첫째 줄에 그은 선의 총 길이를 출력한다.
 * 해결: 정렬 및
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2170 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //선을 그은 횟수
        ArrayList<int []> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //x의 위치
            int y = Integer.parseInt(st.nextToken()); //y의 위치
            list.add(new int[]{x, y});
        }
        list.sort(Comparator.comparingInt(o -> o[0])); //x를 기준으로 정렬

        int total = 0; //선의 총 길이
        int nowX = list.get(0)[0]; //현재 x
        int nowY = list.get(0)[1]; //현재 y
        for(int i=1; i<list.size(); i++) {
            int nextX  = list.get(i)[0];
            int nextY  = list.get(i)[1];
            if(nowX <= nextX && nextX <= nowY) { //다음 x의 위치가 현재 x와 현재 y의 위치 사이일 때
                if(nowY < nextY) nowY = nextY; //다음 y의 위치가 현재 y보다 크다면 현재 y의 위치 변경
            } else {
                total += (nowY-nowX); //현재 x부터 현재 y까지 길이 누적
                nowX = nextX; //현재 x값 변경
                nowY = nextY; //현재 y값 변경
            }
        }
        total += nowY-nowX; //현재 x부터 현재 y까지 길이 누적
        System.out.println(total); //결과 출력
    }
}