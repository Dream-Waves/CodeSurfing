package Jieun714;
/**
 * 문제: 선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 *      함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 *      함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
 *      배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다. 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
 *      다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
 *      다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
 *      전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.
 * 출력: 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.
 * 해결: 덱 사용. 리버스함수대신 boolean 변수로 덱의 값을 삭제
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<T; i++){
            char [] p = br.readLine().toCharArray(); //수행할 함수
            int n = Integer.parseInt(br.readLine()); //배열에 들어 있는 수
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],"); //구분문자로 괄호와 콤마 선언
            Deque<Integer> deque = new ArrayDeque<>(); //덱 선언
            for(int j=0; j<n; j++){
                deque.add(Integer.parseInt(st.nextToken())); //덱에 입력받은 수 삽입
            }

            boolean flag = false; //해당 테스트에 대한 결과를 저장했는 지 구분하는 불린 변수
            boolean isReversed = false; //리버스 상태를 나타내는 불린 변수
            //입력 받은 함수 수행
            for (char c : p) {
                if (c == 'R') { // R : 뒤집기
                    isReversed = (!isReversed);
                } else { // D : 버리기
                    if (!deque.isEmpty()) {
                        if (isReversed) { //reverse 상태일 때
                            deque.removeLast(); //마지막 수를 버리기
                        } else {
                            deque.removeFirst(); //첫번째 수를 버리기
                        }
                    } else { //데크가 비어있다면
                        sb.append("error").append("\n"); //에러 출력
                        flag = true; //상태 변경
                        break;
                    }
                }
            }

            if(!flag) {
                StringBuilder answer = new StringBuilder("[");
                while(!deque.isEmpty()){ //덱이 빌때까지
                    if(deque.size() == 1){ //덱의 마지막 변수일 때
                        answer.append(isReversed ? deque.removeLast() : deque.removeFirst());
                    } else {
                        answer.append((isReversed ? deque.removeLast() : deque.removeFirst())).append(",");
                    }
                }
                answer.append("]");
                sb.append(answer).append("\n"); //StringBuffer에 저장
            }
        }
        System.out.println(sb); //결과 출력
    }
}