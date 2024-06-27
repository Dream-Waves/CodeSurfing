package Jieun714;
/**
 * 문제: 트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
 *      다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.
 *      solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다.
 *      이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.
 * 예시 입력: bridge_length - 2,  weight -10,  truck_weights - [7,4,5,6]
 * 예시 출력: 8
 * 해결: 스택
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class PRO42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0; //걸리는 시간
        int[] times = new int[truck_weights.length]; //트럭마다 경과 시간을 담을 배열
        int totalWeight = 0; //누적 무게를 담을 배열
        Queue<Integer> que = new ArrayDeque<>();
        int startIdx = 0; //다리를 건너는 트럭의 맨 앞 인덱스
        int nowIdx = 0; //현재 트럭의 인덱스

        while (nowIdx != truck_weights.length) {
            int nextTotal = totalWeight + truck_weights[nowIdx]; //다리 위에 전체 트럭 무게 + 새로운 트럭
            if (nextTotal <= weight) { //다리를 지날 수 있다면
                que.add(truck_weights[nowIdx]); //큐에 새로운 트럭 추가
                totalWeight += truck_weights[nowIdx]; //전체 무게에 누적
                nowIdx++;
            }
            for (int i = startIdx; i < nowIdx; i++) times[i] += 1;
            if (times[startIdx] == bridge_length) { //만약 다리를 벗어났다면
                if (!que.isEmpty()) {
                    totalWeight -= que.poll(); //다리 위에 전체 트럭 무게 - 맨 앞 트럭
                    startIdx++;  //다리를 건너는 트럭의 인덱스 +1
                }
            }
            answer++; //걸리는 시간 누적
        }
        if (times[truck_weights.length - 1] != bridge_length) //마지막에 남은 트럭 시간 계산
            answer += (bridge_length - times[truck_weights.length - 1]);

        return answer + 1;
    }
}