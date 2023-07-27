/*
1446. Silver 1 - 지름길

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           7110	    3800      2900	         53.328%


    문제
        매일 아침, 세준이는 학교에 가기 위해서 차를 타고 D킬로미터 길이의 고속도로를 지난다. 이 고속도로는 심각하게 커브가 많아서 정말 운전하기도 힘들다. 어느 날, 세준이는 이 고속도로에 지름길이 존재한다는 것을 알게 되었다. 모든 지름길은 일방통행이고, 고속도로를 역주행할 수는 없다.
        세준이가 운전해야 하는 거리의 최솟값을 출력하시오.


    입력
        첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다. 다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다. 모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.


    출력
        세준이가 운전해야하는 거리의 최솟값을 출력하시오.


    예제 입력 1
        5 150
        0 50 10
        0 50 20
        50 100 10
        100 151 10
        110 140 90
    예제 출력 1
        70

    예제 입력 2
        2 100
        10 60 40
        50 90 20
    예제 출력 2
        80

    예제 입력 3
        8 900
        0 10 9
        20 60 45
        80 190 100
        50 70 15
        160 180 14
        140 160 14
        420 901 5
        450 900 0
    예제 출력 3
        432


    알고리즘 분류
        다이나믹 프로그래밍
        그래프 이론
        데이크스트라
*/


// 메모리 : 14356KB
// 시간 : 132ms
// 코드 길이 : 3462B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ1446 {
    static int N; // 지름길의 개수 N
    static int D; // 고속도로의 길이 D
    static HashMap<Integer, ArrayList<Integer[]>> shortCut; // key : 지름길 도착 위치, value : 도착 위치까지 갈 수 있는 지름길의 시작 위치와 길이를 담고 있는 배열의 리스트
    static int d[]; // d[도착 위치] : 도착 위치까지 운전해야 하는 거리의 최솟값

    public static void findShortCut() {
        // d[도착 위치] = min(도착 위치까지 지름길 사용해서 걸리는 길이, 도착 위치까지 지름길 사용하지 않고 걸리는 길이)
        // d[도착 위치] = min((지름길 시작 위치까지 걸리는 길이 + 지름길 시작 위치부터 도착 위치까지 지름길 사용해서 걸리는 길이), (지름길 시작 위치까지 걸리는 길이 + 지름길 시작 위치부터 도착 위치까지 지름길 사용하지 않고 걸리는 길이))
        // d[도착 위치] = min(지름길 시작 위치부터 도착 위치까지 지름길 사용해서 걸리는 길이, 지름길 시작 위치부터 도착 위치까지 지름길 사용하지 않고 걸리는 길이) + 지름길 시작 위치까지 걸리는 길이
        // d[도착 위치] = Math.min((d[지름길 시작 위치] + 지름길 시작 위치부터 도착 위치까지 지름길 사용해서 걸리는 길이), d[도착 위치])
        for (int m = 1; m <= D; m++) {
            d[m] = d[m - 1] + 1; // 지름길을 사용하지 않고 해당 위치까지 운전해야 하는 거리

            if (shortCut.containsKey(m)) { // 해당 위치가 지름길의 도착 위치라면
                for (int w = 0; w < shortCut.get(m).size(); w++) { // 해당 도착 위치까지 올 수 있는 지름길의 개수만큼 반복
                    d[m] = Math.min(d[shortCut.get(m).get(w)[0]] + shortCut.get(m).get(w)[1], d[m]);
                }
            }
        }

        System.out.println(d[D]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        D = Integer.parseInt(token.nextToken());

        shortCut = new HashMap<>();
        for (int n = 0; n < N; n++) {
            token = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(token.nextToken()); // 지름길의 시작 위치
            int end = Integer.parseInt(token.nextToken()); // 지름길의 도착 위치
            int roadLength = Integer.parseInt(token.nextToken()); // 지름길의 길이

            if (!shortCut.containsKey(end)) { // 해당 지름길의 도착 위치가 포함되어 있지 않다면
                shortCut.put(end, new ArrayList<>()); // 해당 지름길의 도착 위치의 지름길에 대한 정보를 담을 수 있도록 HashMap의 value ArrayList를 초기화
            }
            shortCut.get(end).add(new Integer[] {start, roadLength}); // 해당 지름길의 도착 위치의 지름길에 대한 지름길의 시작 위치와 길이 배열을 ArrayList에 추가
        }

        d = new int[D + 1];
        findShortCut();
    }
}
