package Jieun714;
/**
 * 문제: 행복 유치원 원장인 태양이는 어느 날 N명의 원생들을 키 순서대로 일렬로 줄 세우고, 총 K개의 조로 나누려고 한다. 각 조에는 원생이 적어도 한 명 있어야 하며, 같은 조에 속한 원생들은 서로 인접해 있어야 한다. 조별로 인원수가 같을 필요는 없다.
 *      이렇게 나뉘어진 조들은 각자 단체 티셔츠를 맞추려고 한다. 조마다 티셔츠를 맞추는 비용은 조에서 가장 키가 큰 원생과 가장 키가 작은 원생의 키 차이만큼 든다. 최대한 비용을 아끼고 싶어 하는 태양이는 K개의 조에 대해 티셔츠 만드는 비용의 합을 최소로 하고 싶어한다. 태양이를 도와 최소의 비용을 구하자.
 * 입력: 입력의 첫 줄에는 유치원에 있는 원생의 수를 나타내는 자연수 N(1 ≤ N ≤ 300,000)과 나누려고 하는 조의 개수를 나타내는 자연수 K(1 ≤ K ≤ N)가 공백으로 구분되어 주어진다. 다음 줄에는 원생들의 키를 나타내는 N개의 자연수가 공백으로 구분되어 줄 서 있는 순서대로 주어진다.
 *      태양이는 원생들을 키 순서대로 줄 세웠으므로, 왼쪽에 있는 원생이 오른쪽에 있는 원생보다 크지 않다. 원생의 키는 109를 넘지 않는 자연수이다.
 * 출력: 티셔츠 만드는 비용이 최소가 되도록 K개의 조로 나누었을 때, 티셔츠 만드는 비용을 출력한다.
 * 해결: 그리디 알고리즘. 1.원생들의 키 차이를 담은 배열을 구하기  2.오름차순 정렬  3.차이를 담은 배열의 크기에서 조의 개수에서+1(N-K)한 만큼의 배열의 합 구하기
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //원생의 수
        int K = Integer.parseInt(st.nextToken()); //나누려고 하는 조의 개수
        ArrayList<Long> students = new ArrayList<>(); //원생들의 키를 입력 받을 리스트
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            students.add(Long.valueOf(st.nextToken()));
        }

        ArrayList<Long> minus = new ArrayList<>(); //원생들의 키 차이를 담을 리스트
        for(int i=0; i<N-1; i++){
            minus.add(students.get(i+1)-students.get(i));
        }
        Collections.sort(minus); //키 차이를 담은 리스트 오름차순 정렬

        int cost = 0; //티셔츠를 만드는 비용
        for(int i=0; i<N-K; i++){
            cost += minus.get(i);
        }

        System.out.println(cost); //티셔츠를 만드는 최소 비용 출력
    }
}
