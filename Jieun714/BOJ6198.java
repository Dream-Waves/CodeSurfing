package Jieun714;
/**
 * 문제: 도시에는 N개의 빌딩이 있다. 빌딩 관리인들은 매우 성실 하기 때문에, 다른 빌딩의 옥상 정원을 벤치마킹 하고 싶어한다.
 *     i번째 빌딩의 키가 hi이고, 모든 빌딩은 일렬로 서 있고 오른쪽으로만 볼 수 있다. i번째 빌딩 관리인이 볼 수 있는 다른 빌딩의 옥상 정원은 i+1, i+2, .... , N이다.
 *     그런데 자신이 위치한 빌딩보다 높거나 같은 빌딩이 있으면 그 다음에 있는 모든 빌딩의 옥상은 보지 못한다.
 * 입력: 첫 번째 줄에 빌딩의 개수 N이 입력된다.(1 ≤ N ≤ 80,000)
 *      두 번째 줄 부터 N+1번째 줄까지 각 빌딩의 높이가 hi 입력된다. (1 ≤ hi ≤ 1,000,000,000)
 * 출력: 각 관리인들이 벤치마킹이 가능한 빌딩의 수의 합을 출력한다.
 * 해결: 스택
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //빌딩의 수
        long total = 0; //각 관리인들이 벤치마킹이 가능한 빌딩의 수의 합
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++) {
            int high = Integer.parseInt(br.readLine()); //빌딩의 높이
            while(!stack.isEmpty() && stack.peek() <= high) { //큐가 비어있지 않고, 더이상 벤치마킹이 불가능할 때
                stack.pop();
                total += stack.size(); //벤치마킹이 가능한 빌딩의 수를 더함
            }
            stack.push(high); //스택에 빌딩의 높이 추가
        }

        while(!stack.isEmpty()) { //큐가 비어 있지 않을 때
            stack.pop();
            total += stack.size(); //벤치마킹이 가능한 빌딩의 수를 더함
        }

        System.out.println(total); //결과 출력
    }
}