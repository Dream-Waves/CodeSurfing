package Jieun714;
/**
 * 문제: 2차원 좌표 평면 위에 두 개의 박스(직사각형) P, Q가 놓여 있다. 각 박스의 변은 x축이나 y축에 평행하다. 박스를 연구하는 학수는 이 두 박스의 교차 상태를 파악하여 내부가 겹쳐 있는지 (FACE),
 *      그렇지 않고 선분에서 만나는 지(LINE), 그렇지 않고 한 점에서 만나는지(POINT), 아예 만나지 않는지 (NULL) 구별하려고 한다.
 *      다음 그림은 두 박스의 여러 가지 교차 상태의 예를 보여준다.
 *      FACE인 경우에는 (d)처럼 어느 한 박스가 다른 박스에 포함될 수도 있다는 점에 유의해야 한다. 두 박스의 정보가 주어졌을 때, 두 박스의 교차 상태를 출력하는 프로그램을 작성하시오.
 * 입력: 표준 입력으로 두 박스의 정보가 한 줄에 하나씩 주어진다. 각 박스의 정보는 왼쪽 아래 꼭짓점 좌표 (x1, y1)과 오른쪽 위 꼭짓점 좌표 (x2, y2)로 구성되는데 이들 좌푯값 x1, y1, x2, y2 (x1 < x2, y1 < y2)가 공백을 사이에 두고 주어진다.
 * 출력: 표준 출력으로 두 박스의 교차 상태를 POINT, LINE, FACE, NULL 중의 하나로 출력한다. 두 박스의 교차 상태는 모두 대문자로 출력한다.
 * 해결: 각각의 교차상태에 따른 분기 작성
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15973 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] box1 = new int[4];
        int [] box2 = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine()); //첫 번째 상자 입력
        for (int i = 0; i < 4; i++) {
            box1[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine()); //두 번째 상자 입력
        for (int i = 0; i < 4; i++) {
            box2[i] = Integer.parseInt(st.nextToken());
        }

        String answer;
        if((box2[0] > box1[2] || box2[2] < box1[0] || box2[1] > box1[3] || box2[3] < box1[1])) {
            answer = "NULL"; //두 개의 박스가 아예 만나지 않을 때
        } else if((box2[0] == box1[2] && box2[1] == box1[3]) || (box2[0] == box1[2] && box2[3] == box1[1]) || (box2[2] == box1[0] && box2[1] == box1[3]) || (box2[2] == box1[0] && box2[3] == box1[1])) {
            answer = "POINT"; //두 개의 박스가 한 점에서 만날 때
        } else if(box2[0] == box1[2] || box2[2] == box1[0] || box2[1] == box1[3] || box2[3] == box1[1]){
            answer = "LINE"; //두 개의 박스가 선분에서 만날 때
        } else {
            answer = "FACE"; //두 개의 박스 내부가 겹쳐 있을 때
        }

        System.out.println(answer); //결과 출력
    }
}