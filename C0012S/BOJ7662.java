/*
7662. Gold 4 - 이중 우선순위 큐

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    6 초	    256 MB           84071	    19510     14346	         22.377%


    문제
        이중 우선순위 큐(dual priority queue)는 전형적인 우선순위 큐처럼 데이터를 삽입, 삭제할 수 있는 자료 구조이다. 전형적인 큐와의 차이점은 데이터를 삭제할 때 연산(operation) 명령에 따라 우선순위가 가장 높은 데이터 또는 가장 낮은 데이터 중 하나를 삭제하는 점이다. 이중 우선순위 큐를 위해선 두 가지 연산이 사용되는데, 하나는 데이터를 삽입하는 연산이고 다른 하나는 데이터를 삭제하는 연산이다. 데이터를 삭제하는 연산은 또 두 가지로 구분되는데 하나는 우선순위가 가장 높은 것을 삭제하기 위한 것이고 다른 하나는 우선순위가 가장 낮은 것을 삭제하기 위한 것이다.
        정수만 저장하는 이중 우선순위 큐 Q가 있다고 가정하자. Q에 저장된 각 정수의 값 자체를 우선순위라고 간주하자.
        Q에 적용될 일련의 연산이 주어질 때 이를 처리한 후 최종적으로 Q에 저장된 데이터 중 최댓값과 최솟값을 출력하는 프로그램을 작성하라.


    입력
        입력 데이터는 표준입력을 사용한다. 입력은 T개의 테스트 데이터로 구성된다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 Q에 적용할 연산의 개수를 나타내는 정수 k (k ≤ 1,000,000)가 주어진다. 이어지는 k 줄 각각엔 연산을 나타내는 문자(‘D’ 또는 ‘I’)와 정수 n이 주어진다. ‘I n’은 정수 n을 Q에 삽입하는 연산을 의미한다. 동일한 정수가 삽입될 수 있음을 참고하기 바란다. ‘D 1’는 Q에서 최댓값을 삭제하는 연산을 의미하며, ‘D -1’는 Q 에서 최솟값을 삭제하는 연산을 의미한다. 최댓값(최솟값)을 삭제하는 연산에서 최댓값(최솟값)이 둘 이상인 경우, 하나만 삭제됨을 유념하기 바란다.
        만약 Q가 비어있는데 적용할 연산이 ‘D’라면 이 연산은 무시해도 좋다. Q에 저장될 모든 정수는 -2^31 이상 2^31 미만인 정수이다.


    출력
        출력은 표준출력을 사용한다. 각 테스트 데이터에 대해, 모든 연산을 처리한 후 Q에 남아 있는 값 중 최댓값과 최솟값을 출력하라. 두 값은 한 줄에 출력하되 하나의 공백으로 구분하라. 만약 Q가 비어있다면 ‘EMPTY’를 출력하라.


    예제 입력 1
        2
        7
        I 16
        I -5643
        D -1
        D 1
        D 1
        I 123
        D -1
        9
        I -45
        I 653
        D 1
        I -642
        I 45
        I 97
        D 1
        D -1
        I 333
    예제 출력 1
        EMPTY
        333 -45


    알고리즘 분류
        자료 구조
        트리를 사용한 집합과 맵
        우선순위 큐
*/


// 메모리 : 437256KB
// 시간 : 2384ms
// 코드 길이 : 4447B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7662 {
    static int T; // 입력 데이터의 수
    static int K; // 이중 우선 순위 큐에 적용할 연산의 개수를 나타내는 정수 (K ≤ 1000000)
    static Map<Integer, Integer> numberCountMap; // 각 수가 이중 우선 순위 큐에 저장되어 있는 개수를 저장하는 Map
    static PriorityQueue<Integer> minPriorityQueue; // 이중 우선 순위 큐의 최솟값을 저장하는 우선 순위 큐
    static PriorityQueue<Integer> maxPriorityQueue; // 이중 우선 순위 큐의 최댓값을 저장하는 우선 순위 큐
    static StringBuilder sb;

    public static void insert(int number) { // 이중 우선 순위 큐의 삽입 메서드
        numberCountMap.put(number, numberCountMap.getOrDefault(number, 0) + 1);
        minPriorityQueue.offer(number);
        maxPriorityQueue.offer(number);
    }

    public static int check(PriorityQueue<Integer> queue) { // 이중 우선 순위 큐의 수를 삭제하고, 삭제한 수를 반환하는 메서드
        int deleteNumber; // 이중 우선 순위 큐에서 삭제할 수

        while (true) {
            deleteNumber = queue.poll();
            int numberCount = numberCountMap.getOrDefault(deleteNumber, 0); // 해당 수가 이중 우선 순위 큐에 저장되어 있는 개수

            if (numberCount == 0) { // 해당 수가 이중 우선 순위 큐에 저장되어 있지 않을 경우
                continue; // 이중 우선 순위 큐에 들어 있는 수를 삭제해야 한다.
            }
            else if (numberCount == 1) { // 해당 수가 이중 우선 순위 큐에 저장되어 있는 개수가 1 개일 경우
                numberCountMap.remove(deleteNumber);
            }
            else { // 해당 수가 이중 우선 순위 큐에 저장되어 있는 개수가 1 개보다 많을 경우
                numberCountMap.put(deleteNumber, numberCount - 1);
            }

            break;
        }

        return deleteNumber;
    }

    public static void delete(int flag) { // 이중 우선 순위 큐의 삭제 메서드
        if (numberCountMap.size() == 0) { // 이중 우선 순위 큐에 저장되어 있는 수가 없을 경우
            return; // 이중 우선 순위 큐가 비어 있다.
        }

        if (flag == -1) { // 이중 우선 순위 큐의 최솟값을 삭제하는 연산일 경우
            check(minPriorityQueue);
        }
        else { // 이중 우선 순위 큐의 최댓값을 삭제하는 연산일 경우
            check(maxPriorityQueue);
        }
    }

    public static void decide() { // 이중 우선 순위 큐의 모든 연산을 처리한 후의 결과를 판별하는 메서드
        if (numberCountMap.size() == 0) { // 이중 우선 순위 큐에 저장되어 있는 수가 없을 경우
            sb.append("EMPTY"); // 이중 우선 순위 큐는 비어 있다.
        }
        else {
            int max = check(maxPriorityQueue); // 이중 우선 순위 큐의 최댓값

            sb.append(max);
            sb.append(" ");
            sb.append((numberCountMap.size() == 0) ? max : check(minPriorityQueue)); // 이중 우선 순위 큐의 최솟값
        }

        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());

        StringTokenizer token;
        sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            K = Integer.parseInt(bf.readLine());
            numberCountMap = new HashMap<>();
            minPriorityQueue = new PriorityQueue<>();
            maxPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

            for (int k = 0; k < K; k++) {
                token = new StringTokenizer(bf.readLine());

                String command = token.nextToken(); // 연산
                int number = Integer.parseInt(token.nextToken()); // 연산과 관련 있는 수

                if (command.equals("I")) { // 삽입 연산일 경우
                    insert(number);
                }
                else { // 삭제 연산일 경우
                    delete(number);
                }
            }

            decide();
        }

        System.out.println(sb);
    }
}
