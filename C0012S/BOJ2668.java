/*
2668. Gold 5 - 숫자고르기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초	    128 MB           25303	    11636     8929	         46.462%


    문제
        세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다. 첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다. 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다. 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오. 예를 들어, N=7인 경우 아래와 같이 표가 주어졌다고 하자.
            [그림은 문제에서 참고]

        이 경우에는 첫째 줄에서 1, 3, 5를 뽑는 것이 답이다. 첫째 줄의 1, 3, 5밑에는 각각 3, 1, 5가 있으며 두 집합은 일치한다. 이때 집합의 크기는 3이다. 만약 첫째 줄에서 1과 3을 뽑으면, 이들 바로 밑에는 정수 3과 1이 있으므로 두 집합이 일치한다. 그러나, 이 경우에 뽑힌 정수의 개수는 최대가 아니므로 답이 될 수 없다.


    입력
        첫째 줄에는 N(1≤N≤100)을 나타내는 정수 하나가 주어진다. 그 다음 줄부터는 표의 둘째 줄에 들어가는 정수들이 순서대로 한 줄에 하나씩 입력된다.


    출력
        첫째 줄에 뽑힌 정수들의 개수를 출력하고, 그 다음 줄부터는 뽑힌 정수들을 작은 수부터 큰 수의 순서로 한 줄에 하나씩 출력한다.


    예제 입력 1
        7
        3
        1
        1
        5
        5
        4
        6
    예제 출력 1
        3
        1
        3
        5


    알고리즘 분류
        그래프 이론
        그래프 탐색
        깊이 우선 탐색
*/


// 메모리 : 14112KB
// 시간 : 100ms
// 코드 길이 : 2293B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2668 {
    static int N; // 가로의 크기 (1 ≤ N ≤ 100)
    static int[] number; // 표에 작성된 수를 저장하는 배열 (n : 첫 번째 행의 수, number[n] : 두 번째 행의 수)
    static boolean[] isVisited; // 방문 여부를 저장하는 배열
    static ArrayList<Integer> setList; // 뽑은 정수들을 저장하는 리스트

    public static void check(int start, int target) { // 사이클이 존재하는지 검사하는 메서드
        if (!isVisited[number[start]]) {
            isVisited[number[start]] = true;
            check(number[start], target);
            isVisited[number[start]] = false;
        }

        if (number[start] == target) { // 사이클이 존재할 경우
            setList.add(target);
        }
    }

    public static void print() { // 뽑은 집합의 정보를 출력하는 메서드
        Collections.sort(setList); // 순서대로 출력하기 위해 오름차순 정렬

        StringBuilder sb = new StringBuilder();

        sb.append(setList.size()); // 뽑힌 정수들의 개수
        sb.append("\n");

        // 두 번째 줄에 들어가는 정수들
        for (Integer setNum : setList) {
            sb.append(setNum);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void find() { // 첫 번째 줄에서 뽑은 정수들이 이루는 집합과 해당 수들의 바로 밑에 있는 두 번째 줄에서 뽑은 정수들이 이루는 집합이 일치하는 집합을 찾는 메서드
        isVisited = new boolean[N + 1];
        setList = new ArrayList<>();

        for (int n = 1; n <= N; n++) {
            isVisited[n] = true;
            check(n, n);
            isVisited[n] = false;
        }

        print();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        number = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            number[i] = Integer.parseInt(bf.readLine());
        }

        find();
    }
}
