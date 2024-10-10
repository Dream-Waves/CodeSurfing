/*
1092. Gold 5 - 배

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           32542	    8489      5999	         25.203%


    문제
        지민이는 항구에서 일한다. 그리고 화물을 배에 실어야 한다. 모든 화물은 박스에 안에 넣어져 있다. 항구에는 크레인이 N대 있고, 1분에 박스를 하나씩 배에 실을 수 있다. 모든 크레인은 동시에 움직인다.
        각 크레인은 무게 제한이 있다. 이 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다. 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 각 크레인의 무게 제한이 주어진다. 이 값은 1,000,000보다 작거나 같다. 셋째 줄에는 박스의 수 M이 주어진다. M은 10,000보다 작거나 같은 자연수이다. 넷째 줄에는 각 박스의 무게가 주어진다. 이 값도 1,000,000보다 작거나 같은 자연수이다.


    출력
        첫째 줄에 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 출력한다. 만약 모든 박스를 배로 옮길 수 없으면 -1을 출력한다.


    예제 입력 1
        3
        6 8 9
        5
        2 5 2 4 7
    예제 출력 1
        2

    예제 입력 2
        2
        19 20
        7
        14 12 16 19 16 1 5
    예제 출력 2
        4

    예제 입력 3
        4
        23 32 25 28
        10
        5 27 10 16 24 20 2 32 18 7
    예제 출력 3
        3

    예제 입력 4
        10
        11 17 5 2 20 7 5 5 20 7
        5
        18 18 15 15 17
    예제 출력 4
        2


    알고리즘 분류
        그리디 알고리즘
        정렬
*/


// 메모리 : 16456KB
// 시간 : 268ms
// 코드 길이 : 2640B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1092 {
    static int N; // 크레인의 개수
    static Integer craneWeight[]; // 각 크레인의 무게 제한을 저장하는 배열
    static int M; // 박스의 수
    static ArrayList<Integer> boxWeight; // 각 박스의 무게를 저장하는 리스트

    public static void take() { // 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하고 출력하는 메서드
        Arrays.sort(craneWeight, Comparator.reverseOrder()); // 크레인의 무게 제한 배열 내림차순 정렬
        boxWeight.sort(Comparator.reverseOrder()); // 박스의 무게 리스트 내림차순 정렬

        if (craneWeight[0] < boxWeight.get(0)) { // 모든 박스를 배로 옮길 수 없을 경우 : 크레인의 무게 제한 최댓값이 박스의 무게 최댓값보다 작을 경우
            System.out.println(-1);

            return;
        }

        int time = 0; // 모든 박스를 배로 옮기는데 드는 시간의 최솟값
        while (!boxWeight.isEmpty()) {
            int index = 0; // 체크할 박스의 인덱스
            for (int n = 0; n < N;) {
                if (index == boxWeight.size()) {
                    break;
                }
                else if (craneWeight[n] >= boxWeight.get(index)) { // 해당 박스의 무게가 해당 크레인의 무게 제한 이내일 경우
                    boxWeight.remove(index);
                    n += 1; // 체크할 크레인의 인덱스의 값 증가
                }
                else { // 해당 박스의 무게가 해당 크레인의 무게 제한 이내가 아닐 경우
                    index += 1; // 다음 박스의 무게를 체크할 수 있도록 박스의 인덱스의 값 증가
                }
            }

            time += 1;
        }

        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        craneWeight = new Integer[N];
        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            craneWeight[i] = Integer.parseInt(token.nextToken());
        }

        M = Integer.parseInt(bf.readLine());
        boxWeight = new ArrayList<>();
        token = new StringTokenizer(bf.readLine());
        for (int j = 0; j < M; j++) {
            boxWeight.add(Integer.parseInt(token.nextToken()));
        }

        take();
    }
}
