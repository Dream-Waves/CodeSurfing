package Jieun714;
/**
 * 문제: 서준이는 아빠로부터 N개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하라는 미션을 받았다. 각 회의는 시작 시간과 끝나는 시간이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다.
 *      단, 회의는 한번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작 시간은 끝나는 시간보다 항상 작다. N이 너무 커서 괴로워 하는 우리 서준이를 도와주자.
 * 입력: 첫째 줄에 배열의 크기 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N+1 줄까지 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 2^31−1보다 작거나 같은 자연수 또는 0이다.
 * 출력: 첫째 줄에 최소 회의실 개수를 출력한다.
 * 해결: 정렬 + 우선순위 큐
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ19598 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> room = new PriorityQueue<>();
        room.offer(arr[0][1]);
        for(int i=1; i<arr.length; i++) {
            if(!room.isEmpty()) { //사용중인 회의실 있을 경우
                if (room.peek() <= arr[i][0]) { //가장 빨리 끝나는 회의보다 늦게 시작할 경우
                    room.poll(); //진행 중인 회의를 뺌
                }
            }
            room.offer(arr[i][1]); //새로운 회의를 추가
        }

        System.out.println(room.size()); //최소 회의실 개수 출력
    }
}