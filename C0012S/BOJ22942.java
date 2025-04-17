/*
22942. Gold 4 - 데이터 체커

    시간 제한	            메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (하단 참고)	    1024 MB          3320	    1089      777	         32.094%


    문제
        원 이동하기 2 문제를 만들고 만든 데이터가 문제의 조건에 맞는지 확인하는 코드를 작성해야한다.

        해당 문제의 데이터는 아래 조건들을 만족해야한다.
            1. 모든 원의 중심 좌표는 x축 위에 존재해야 한다.
            2. N개의 원 중 임의의 두 원을 선택했을 때, 교점이 존재하지 않아야 한다. 즉, 하나의 원이 다른 원 안에 존재하거나 외부에 존재한다.

        데이터 형식은 원의 개수 N이랑 각 원의 중심 x좌표, 원의 반지름 r만 주어진다. 따라서, 2번 조건을 만족하는지만 확인하면 된다.
        주어진 데이터가 해당 조건을 만족하는지 확인해보자.


    입력
        첫 번째 줄에는 원의 개수 N이 주어진다.
        두 번째 줄부터 N+1번째 줄까지 원의 중심 x좌표, 원의 반지름 r이 공백으로 구분되어 주어진다.


    출력
        데이터가 조건에 맞는다면 YES, 조건에 만족하지 않는다면 NO를 출력한다.


    제한
        · 2 ≤ N ≤ 200,000
        · -1,000,000 ≤ x ≤ 1,000,000
        · 1 ≤ r ≤ 10,000
        · x, r은 정수


    예제 입력 1
        4
        5 4
        3 1
        6 1
        13 3
    예제 출력 1
        YES

    예제 입력 2
        4
        3 1
        4 1
        5 1
        6 5
    예제 출력 2
        NO


    힌트
        두 원의 위치관계
            두 원의 위치관계를 파악할 때 아래를 이용하면 된다.

            원 A의 반지름은 r_A, 원 B의 반지름은 r_B, 원 A와 원 B의 중심 사이의 거리를 d라고 가정하자.
                두 점에서 만난다.	        한 점에서 만난다.              만나지 않는다.
                                        외접	        내접	            외부에 있는 경우	내부에 있는 경우	동심원
                |r_A-r_B|<d<r_A+r_B     r_A+r_B=d   |r_A-r_B|=d     r_A+r_B<d       d<|r_A-r_B|     d=0

        두 점 사이의 거리
            (x_1, y_1)와 (x_2, y_2) 사이의 거리 d를 구하는 식은 아래와 같다.
                d = sqrt{(x_1-x_2)^2+(y_1-y_2)^2}


    알고리즘 분류
        자료 구조
        정렬
        기하학
        스위핑
        스택


    시간 제한
        Java 8: 2 초
        Python 3: 2 초
        PyPy3: 2 초
        Java 8 (OpenJDK): 2 초
        Java 11: 2 초
        Kotlin (JVM): 2 초
        Java 15: 2 초
*/


// 메모리 : 67236KB
// 시간 : 744ms
// 코드 길이 : 3089B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ22942 {
    static int N; // 원의 개수 (2 ≤ N ≤ 200000)
    static ArrayList<Circle> circleList; // 원의 정보들을 저장하는 리스트

    static class Circle { // 원의 정보를 저장하는 클래스
        int start; // 원이 시작하는 x 좌표
        int end; // 원이 끝나는 x 좌표

        public Circle(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void prove() { // 데이터가 조건에 만족하는지에 따라 YES와 NO를 출력하는 메서드
        circleList.sort((o1, o2) -> (o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start));

        Stack<Circle> stack = new Stack<>();
        stack.push(circleList.get(0));
        for (int n = 1; n < N; n++) {
            Circle nowCircle = circleList.get(n); // 현재 검사하고 있는 원
            Circle nearCircle = stack.peek(); // 가장 안 쪽에 존재하는 원

            if (nowCircle.end < nearCircle.end) { // 가장 안 쪽에 존재하는 원이 현재 원을 포함할 경우
                stack.push(nowCircle);
            }
            else if (nowCircle.start > nearCircle.end) { // 가장 안 쪽에 존재하는 원이 현재 원을 포함하지 않고, 만나지 않을 경우 (현재 원이 가장 안 쪽에 존재하는 원의 외부에 있을 경우)
                while (!stack.isEmpty() && (nowCircle.start > stack.peek().end)) { // 스택이 비지 않았고, 현재 원이 스택에 존재하는 원의 외부에 있을 경우
                    stack.pop();
                }

                if (stack.isEmpty() || nowCircle.end < stack.peek().end) { // 스택이 비거나, 현재 원이 스택에 존재하는 원의 내부에 있을 경우
                    stack.push(nowCircle);
                }
                else { // 현재 원이 스택에 존재하는 원과 만날 경우
                    System.out.println("NO");
                    return;
                }
            }
            else { // 현재 원과 가장 안 쪽에 존재하는 원이 만날 경우
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        circleList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(token.nextToken()); // 원의 중심의 x 좌표 (-1000000 ≤ x ≤ 1000000)
            int r = Integer.parseInt(token.nextToken()); // 원의 반지름 (1 ≤ r ≤ 10000)

            circleList.add(new Circle(x - r, x + r));
        }

        prove();
    }
}
