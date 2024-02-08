/*
5430. Gold 5 - AC

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    256 MB           140944	    31670     22381	         20.078%


    문제
        선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
        함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
        함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
        배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
        각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
        다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
        다음 줄에는 [x_1,...,x_n]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ x_i ≤ 100)
        전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.


    출력
        각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.


    예제 입력 1
        4
        RDD
        4
        [1,2,3,4]
        DD
        1
        [42]
        RRD
        6
        [1,1,2,3,5,8]
        D
        0
        []
    예제 출력 1
        [2,1]
        error
        [1,2,3,5,8]
        error


    알고리즘 분류
        구현
        자료 구조
        문자열
        파싱
        덱
*/


// 메모리 : 75924KB
// 시간 : 2280ms
// 코드 길이 : 4138B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ5430 {
    static String p; // 길이가 1보다 크거나 같고 100000보다 작거나 같은 수행할 함수
    static int n; // 배열에 들어 있는 수의 개수 (0 ≤ n ≤ 100000)
    static String x; // [x_1,...,x_n] 형태인 배열에 들어 있는 정수 (1 ≤ x_i ≤ 100)
    static boolean isReversed; // 배열의 뒤집힘 여부
    static StringBuilder sb; // 최종 결과를 출력하기 위한 StringBuilder

    public static void R() { // 뒤집기 함수
        if (isReversed) { // 이미 뒤집힌 상태일 경우
            isReversed = false;
        }
        else { // 뒤집힌 상태가 아닐 경우
            isReversed = true;
        }
    }

    public static boolean D(ArrayList<String> list) { // 버리기 함수
        if (list.isEmpty()) { // 리스트가 비어 있을 경우
            sb.append("error"); // 에러 발생
            sb.append("\n");

            return false;
        }
        else {
            if (isReversed) { // 리스트가 뒤집혔을 경우
                list.remove(list.size() - 1); // 마지막 수 버리기
            }
            else {
                list.remove(0); // 첫 번째 수 버리기
            }

            return true;
        }
    }

    public static void printList(ArrayList<String> list) { // 뒤집히지 않은 리스트의 값을 출력하기 위해 StringBuilder에 추가하는 메서드
        sb.append("[");
        for (int m = 0, size = list.size(); m < size; m ++) {
            sb.append(list.get(m));

            if (m != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        sb.append("\n");
    }

    public static void printReversedList(ArrayList<String> list) { // 뒤집힌 리스트의 값을 출력하기 위해 StringBuilder에 추가하는 메서드
        sb.append("[");
        for (int m = list.size() - 1; m >= 0; m--) {
            sb.append(list.get(m));

            if (m != 0) {
                sb.append(",");
            }
        }
        sb.append("]");
        sb.append("\n");
    }

    public static void calculate() { // 함수 p를 수행하고, 수행 결과를 StringBuilder에 추가하는 메서드
        // [x_1,...,x_n] 형태로 주어진 값에서 정수 x_i들만 리스트에 저장
        ArrayList<String> xList = new ArrayList<>(); // 정수 x_i를 저장하는 리스트
        for (String str : x.substring(1, x.length() - 1).split(",")) { // [x_1,...,x_n] 형태의 문자열에서 '[', ',', ']'를 제외한 배열의 원소만큼 반복
            if (!str.equals("")) { // 원소가 빈 문자열이 아닐 경우
                xList.add(str);
            }
        }

        // 함수 p 수행
        for (int f = 0, len = p.length(); f < len; f++) {
            char nowFunction = p.charAt(f); // 현재 수행해야 할 함수
            if (nowFunction == 'R') { // 현재 수행해야 할 함수가 R인 경우
                R();
            }
            else if (nowFunction == 'D') { // 현재 수행해야 할 함수가 D인 경우
                if (!D(xList)) { // 에러가 발생한 경우
                    return;
                }
            }
        }

        // 함수 p를 수행한 xList를 출력하기 위해 StringBuilder에 추가
        if (isReversed) {
            printReversedList(xList);
        }
        else {
            printList(xList);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine()); // 최대가 100인 테스트 케이스의 개수

        sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            p = bf.readLine();
            n = Integer.parseInt(bf.readLine());
            x = bf.readLine();

            isReversed = false;
            calculate();
        }

        System.out.println(sb); // 최종 결과 출력
    }
}
