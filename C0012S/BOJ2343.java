/*
2343. Silver 1 - 기타 레슨

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    128 MB           26677	    8890      6160	         31.260%


    문제
        강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다. 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다. 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
        강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다. 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다. 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다.
        강토의 각 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.


    입력
        첫째 줄에 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다. 다음 줄에는 강토의 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다. 각 강의의 길이는 10,000분을 넘지 않는다.


    출력
        첫째 줄에 가능한 블루레이 크기중 최소를 출력한다.


    예제 입력 1
        9 3
        1 2 3 4 5 6 7 8 9
    예제 출력 1
        17


    힌트
        강의는 총 9개이고, 블루레이는 총 3개 가지고 있다.
        1번 블루레이에 1, 2, 3, 4, 5, 2번 블루레이에 6, 7, 3번 블루레이에 8, 9 를 넣으면 각 블루레이의 크기는 15, 13, 17이 된다. 블루레이의 크기는 모두 같아야 하기 때문에, 블루레이의 크기는 17이 된다. 17보다 더 작은 크기를 가지는 블루레이를 만들 수 없다.


    알고리즘 분류
        이분 탐색
        매개 변수 탐색
*/


// 메모리 : 24248KB
// 시간 : 344ms
// 코드 길이 : 3190B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2343 {
    static int N; // 강의의 수 N (1 ≤ N ≤ 100,000)
    static int M; // 블루레이의 개수 M (1 ≤ M ≤ N)
    static int guitarLessonMinutes[]; // 강토의 기타 강의의 길이를 순서대로 저장하는 배열
    static int lowMinutes; // 가장 작은 기타 강의의 길이
    static int highMinutes; // 가장 큰 기타 강의의 길이

    public static void calculateBlurayMinSize() { // 가능한 블루레이의 크기 중 최소를 출력하는 함수
        int midMinutes; // 가장 작은 기타 강의들을 담은 블루레이의 크기와 가장 큰 기타 강의들을 담은 블루레이의 크기의 중간값
        int nowBluraySum; // 해당 블루레이에 녹화한 길이
        int blurayNum; // 블루레이의 개수

        while (lowMinutes <= highMinutes) {
            midMinutes = (lowMinutes + highMinutes) / 2;
            nowBluraySum = 0;
            blurayNum = 0;

            for (int l = 0; l < N; l++) {
                if (nowBluraySum + guitarLessonMinutes[l] > midMinutes) { // 해당 강의 전까지 녹화한 블루레이의 크기와 해당 강의의 길이를 합친 값이 기타 강의들을 담은 중간 블루레이의 크기보다 클 경우
                    nowBluraySum = 0; // 해당 블루레이에 녹화한 길이 초기화
                    blurayNum += 1; // 블루레이의 개수 추가
                }

                nowBluraySum += guitarLessonMinutes[l]; // 해당 블루레이에 녹화 (해당 기타 강의의 길이만큼 녹화했으므로 해당 기타 강의의 길이 추가)
            }

            if (nowBluraySum > 0) { // 블루레이에 녹화할 수 있는 크기를 초과하여 아직 녹화되지 않은 기타 강의가 있을 경우
                blurayNum += 1; // 블루레이의 개수 추가
            }

            if (blurayNum <= M) { // 계산한 블루레이의 개수가 주어진 블루레이의 개수보다 작거나 같을 경우
                highMinutes = midMinutes - 1; // 가장 큰 기타 강의의 길이 - 1
            }
            else {
                lowMinutes = midMinutes + 1; // 가장 작은 기타 강의의 길이 + 1
            }
        }

        System.out.println(lowMinutes);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken()); // 강의의 수 N (1 ≤ N ≤ 100,000)
        M = Integer.parseInt(token.nextToken()); // 블루레이의 개수 M (1 ≤ M ≤ N)

        token = new StringTokenizer(bf.readLine());
        guitarLessonMinutes = new int[N];
        for (int n = 0; n < N; n++) {
            guitarLessonMinutes[n] = Integer.parseInt(token.nextToken());

            lowMinutes = Math.max(lowMinutes, guitarLessonMinutes[n]);
            highMinutes += guitarLessonMinutes[n];
        }

        calculateBlurayMinSize();
    }
}
