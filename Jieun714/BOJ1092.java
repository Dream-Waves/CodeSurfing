package Jieun714;
/**
 * 문제: 지민이는 항구에서 일한다. 그리고 화물을 배에 실어야 한다. 모든 화물은 박스에 안에 넣어져 있다. 항구에는 크레인이 N대 있고, 1분에 박스를 하나씩 배에 실을 수 있다. 모든 크레인은 동시에 움직인다.
 *      각 크레인은 무게 제한이 있다. 이 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다. 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 각 크레인의 무게 제한이 주어진다. 이 값은 1,000,000보다 작거나 같다. 셋째 줄에는 박스의 수 M이 주어진다.
 *      M은 10,000보다 작거나 같은 자연수이다. 넷째 줄에는 각 박스의 무게가 주어진다. 이 값도 1,000,000보다 작거나 같은 자연수이다.
 * 출력: 첫째 줄에 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 출력한다. 만약 모든 박스를 배로 옮길 수 없으면 -1을 출력한다.
 * 해결: 그리디
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> crane = new ArrayList<>();
        ArrayList<Integer> box = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) crane.add(Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) box.add(Integer.parseInt(st.nextToken()));

        crane.sort(Collections.reverseOrder()); //내림차순 정렬
        box.sort(Collections.reverseOrder()); //내림차순 정렬

        if(crane.get(0) < box.get(0)) { //크레인의 무게 제한 보다 박스의 무게가 클 때
            System.out.println(-1);
            System.exit(0);
        }

        int time = 0; //모든 박스를 배로 옮기는데 드는 시간의 최솟값을 담는 변수
        while(!box.isEmpty()) { //더 이상 옮길 박스가 없을 때까지
            int boxIdx = 0, craneIdx = 0;
            while(craneIdx < N) {
                if(boxIdx == box.size()) break; // 현재 크레인에 더이상 박스를 실을 수가 없을 때
                if(crane.get(craneIdx) >= box.get(boxIdx)) { //현재 크레인에 박스를 실을 수 있을 때
                    box.remove(boxIdx); //적재한 박스는 리스트에서 삭제
                    craneIdx++;
                } else boxIdx++;
            }
            time++;
        }
        System.out.println(time); //결과 출력
    }
}