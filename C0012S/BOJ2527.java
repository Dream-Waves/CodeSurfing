/*
2527. Silver 1 - 직사각형

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           11043	    2921      2423	         29.781%


    문제
        2차원 격자공간에 두 개의 꼭짓점 좌표로 표현되는 직사각형이 있다. 직사각형은 아래와 같이 왼쪽 아래 꼭짓점 좌표 (x, y)와 오른쪽 위 꼭짓점 좌표 (p, q)로 주어진다.
            [그림은 문제에서 참고]

        이 문제에서 모든 직사각형은 두 꼭짓점의 좌표를 나타내는 4개의 정수 x y p q 로 표현된다. 단 항상 x<p, y<q 이다. 예를 들어 위 그림에 제시된 직사각형이라면 아래와 같이 표현된다.
            3 2 9 8

        두 개의 직사각형은 그 겹치는 부분의 특성에 따라 다음 4가지 경우로 분류될 수 있다.
            먼저 두 직사각형의 겹치는 부분이 직사각형인 경우이다. 아래 그림(a)는 공통부분이 직사각형인 경우의 3가지 예를 보여준다,
                [그림 a의 그림은 문제에서 참고]
                    그림 (a)

            또는 겹치는 부분이 아래 그림 (b)와 같이 선분이 될 수도 있고, 그림 (c)와 같이 점도 될 수 있다.
                [그림 b의 그림은 문제에서 참고]
                    그림 (b)
                [그림 c의 그림은 문제에서 참고]
                    그림 (c)

            마지막으로 아래 그림 (d)와 같이 공통부분 없이 두 직사각형이 완전히 분리된 경우도 있다.
                [그림 d의 그림은 문제에서 참고]
                    그림 (d)

        여러분은 두 직사각형의 겹치는 부분이 직사각형인지, 선분인지, 점인지, 아니면 전혀 없는 지를 판별해서 해당되는 코드 문자를 출력해야 한다.
            공통부분의 특성	코드 문자
            직사각형	            a
            선분	                b
            점	                c
            공통부분이 없음	    d


    입력
        4개의 줄로 이루어져 있다. 각 줄에는 8개의 정수가 하나의 공백을 두고 나타나는데, 첫 4개의 정수는 첫 번째 직사각형을, 나머지 4개의 정수는 두 번째 직사각형을 각각 나타낸다. 단 입력 직사각형의 좌표 값은 1이상 50,000 이하의 정수로 제한된다.


    출력
        4개의 각 줄에 주어진 두 직사각형의 공통부분을 조사해서 해당하는 코드 문자를 출력파일의 첫 4개의 줄에 각각 차례대로 출력해야 한다.


    예제 입력 1
        3 10 50 60 100 100 200 300
        45 50 600 600 400 450 500 543
        11 120 120 230 50 40 60 440
        35 56 67 90 67 80 500 600
    예제 출력 1
        d
        a
        a
        b


    알고리즘 분류
        기하학
        많은 조건 분기
*/


// 메모리 : 14164KB
// 시간 : 128ms
// 코드 길이 : 1888B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2527 {
    public static void calculate(int x1, int y1, int p1, int q1, int x2, int y2, int p2, int q2) { // 두 직사각형의 공통 부분의 특성에 해당하는 코드 문자를 구하고 출력하는 메서드  // calculate(첫 번째 직사각형의 좌표((x, y), (p, q)), 두 번째 직사각형의 좌표((x, y), (p, q)))
        if (q1 < y2 || y1 > q2 || x1 > p2 || p1 < x2) { // 두 직사각형의 겹치는 부분이 없을 경우
            System.out.println("d");
        }
        else if ((x1 == p2 && q1 == y2) || (p1 == x2 && q1 == y2) || (x1 == p2 && y1 == q2) || (p1 == x2 && y1 == q2)) { // 두 직사각형의 겹치는 부분이 점일 경우
            System.out.println("c");
        }
        else if (q1 == y2 || y1 == q2 || x1 == p2 || p1 == x2) { // 두 직사각형의 겹치는 부분이 선분일 경우
            System.out.println("b");
        }
        else { // 두 직사각형의 겹치는 부분이 직사각형일 경우
            System.out.println("a");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token;
        for (int t = 0; t < 4; t++) {
            token = new StringTokenizer(bf.readLine());

            calculate(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), // 첫 번째 직사각형의 (x, y), (p, q)
                    Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())); // 두 번째 직사각형의 (x, y), (p, q)
        }
    }
}
