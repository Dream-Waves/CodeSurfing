/*
3495. Silver 1 - 아스키 도형

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           1291	    682       568	         54.511%


    문제
        창영이는 메모장에 '.', '\', '/'을 이용해서 도형을 그렸다. 각 문자는 그림에서 1*1크기의 단위 정사각형을 나타낸다.
        '.'은 빈 칸을 나타내며, '/'는 정사각형의 왼쪽 아래 꼭짓점과 오른쪽 위 꼭짓점이 연결된 선분을, '\'은 왼쪽 위 꼭짓점과 오른쪽 아래 꼭짓점이 연결된 선분을 나타낸다.

        창영이가 그린 도형의 넓이를 출력하는 프로그램을 작성하시오.
            [그림은 문제에서 참고]


    입력
        첫째 줄에 h와 w가 주어진다. h는 그림의 높이, w는 너비이다. (2 ≤ h,w ≤ 100)
        다음 h개 줄에는 창영이가 메모장에 그린 다각형이 주어진다.
        창영이가 그린 다각형은 1개이고, 변과 변이 서로 교차하는 경우는 없고, 자기 자신과 접하는 경우도 없다.


    출력
        첫째 줄에 다각형의 넓이를 출력한다.


    예제 입력 1
        4 4
        /\/\
        \../
        .\.\
        ..\/
    예제 출력 1
        8


    알고리즘 분류
        구현
        기하학
*/


// 메모리 : 14432KB
// 시간 : 104ms
// 코드 길이 : 1585B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3495 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int H = Integer.parseInt(token.nextToken()); // 그림의 높이 (2 ≤ H ≤ 100)
        int W = Integer.parseInt(token.nextToken()); // 그림의 너비 (2 ≤ W ≤ 100)

        double area = 0; // 다각형의 넓이
        boolean flag; // 다각형의 내부와 외부 구분  // true : 내부, false : 외부
        for (int h = 0; h < H; h++) {
            flag = false;
            String str = bf.readLine();

            for (int w = 0; w < W; w++) {
                if (str.charAt(w) == '.') { // 빈 칸일 경우
                    if (flag) { // 다각형의 내부에 있는 빈 칸일 경우
                        area += 1;
                    }
                }
                else { // 다각형의 변일 경우
                    if (!flag) { // 다각형의 영역이 시작되지 않았을 경우
                        flag = true; // 다각형의 영역 시작
                    }
                    else { // 다각형의 영역이 시작되어 있을 경우
                        flag = false; // 다각형의 영역 종료
                    }

                    area += 0.5;
                }
            }
        }

        System.out.println((int) area);
    }
}
