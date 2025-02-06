/*
Lv. 3 #12927 - 야근 지수

    문제 설명
        회사원 Demi는 가끔은 야근을 하는데요, 야근을 하면 야근 피로도가 쌓입니다. 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값입니다. Demi는 N시간 동안 야근 피로도를 최소화하도록 일할 겁니다.Demi가 1시간 동안 작업량 1만큼을 처리할 수 있다고 할 때, 퇴근까지 남은 N 시간과 각 일에 대한 작업량 works에 대해 야근 피로도를 최소화한 값을 리턴하는 함수 solution을 완성해주세요.


    제한 사항
        · works는 길이 1 이상, 20,000 이하인 배열입니다.
        · works의 원소는 50000 이하인 자연수입니다.
        · n은 1,000,000 이하인 자연수입니다.


    입출력 예
        works	    n	result
        [4, 3, 3]	4	12
        [2, 1, 2]	1	6
        [1,1]	    3	0


    입출력 예 설명
        입출력 예 #1
            n=4 일 때, 남은 일의 작업량이 [4, 3, 3] 이라면 야근 지수를 최소화하기 위해 4시간동안 일을 한 결과는 [2, 2, 2]입니다. 이 때 야근 지수는 22 + 22 + 22 = 12 입니다.

        입출력 예 #2
            n=1일 때, 남은 일의 작업량이 [2,1,2]라면 야근 지수를 최소화하기 위해 1시간동안 일을 한 결과는 [1,1,2]입니다. 야근지수는 12 + 12 + 22 = 6입니다.

        입출력 예 #3
            남은 작업량이 없으므로 피로도는 0입니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (0.50ms, 85MB)
        테스트 2 〉	통과 (0.49ms, 77MB)
        테스트 3 〉	통과 (0.49ms, 85.2MB)
        테스트 4 〉	통과 (0.75ms, 84.4MB)
        테스트 5 〉	통과 (0.53ms, 85.8MB)
        테스트 6 〉	통과 (0.47ms, 77.4MB)
        테스트 7 〉	통과 (0.48ms, 76.5MB)
        테스트 8 〉	통과 (1.68ms, 93.2MB)
        테스트 9 〉	통과 (1.19ms, 89.9MB)
        테스트 10 〉	통과 (0.51ms, 88.3MB)
        테스트 11 〉	통과 (0.47ms, 73.3MB)
        테스트 12 〉	통과 (0.54ms, 85.8MB)
        테스트 13 〉	통과 (0.71ms, 85.3MB)

    효율성  테스트
        테스트 1 〉	통과 (131.83ms, 70.3MB)
        테스트 2 〉	통과 (114.64ms, 70.2MB)

    채점 결과
        정확성: 86.7
        효율성: 13.3
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO12927 {
    static PriorityQueue<Integer> queue; // 각 일에 대한 작업량을 저장하는 우선 순위 큐
    static long fatigue; // 야근 피로도

    public static void init(int[] works) { // 각 일에 대한 작업량이 내림차순으로 정렬된 우선 순위 큐와 초기 야근 피로도를 구하는 메서드
        queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int p = 0, num = works.length; p < num; p++) {
            queue.offer(works[p]);
            fatigue += Math.pow(works[p], 2);
        }
    }

    public static void find(int n, int[] works) { // 야근 피로도의 최솟값을 구하는 메서드
        init(works);

        for (int w = 0; w < n; w++) {
            int work = queue.poll(); // 현재 작업량이 가장 많이 남은 작업량의 작업

            if (work <= 0) { // 남은 작업량이 없을 경우
                return;
            }

            fatigue -= Math.pow(work, 2); // 기존의 야근 피로도에서 해당 작업의 야근 피로도 제외
            work -= 1; // 1 시간 동안 작업량 1만큼 처리
            queue.offer(work); // 해당 작업의 남은 작업량 추가
            fatigue += Math.pow(work, 2); // 남은 작업의 야근 피로도 추가
        }
    }

    public long solution(int n, int[] works) {
        find(n, works);

        return fatigue;
    }
}
