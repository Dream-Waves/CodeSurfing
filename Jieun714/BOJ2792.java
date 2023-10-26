package Jieun714;
/**
 * 문제 : 보석 공장에서 보석 상자를 유치원에 기증했다. 각각의 보석은 M가지 서로 다른 색상 중 한 색상이다. 원장 선생님은 모든 보석을 N명의 학생들에게 나누어 주려고 한다. 이때, 보석을 받지 못하는 학생이 있어도 된다. 하지만, 학생은 항상 같은 색상의 보석만 가져간다.
 *       한 아이가 너무 많은 보석을 가져가게 되면, 다른 아이들이 질투를 한다. 원장 선생님은 이런 질투심을 수치화하는데 성공했는데, 질투심은 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수이다. 원장 선생님은 질투심이 최소가 되게 보석을 나누어 주려고 한다.
 *       상자에 빨간 보석이 4개 (RRRR), 파란 보석이 7개 (BBBBBBB) 있었고, 이 보석을 5명의 아이들에게 나누어 주는 경우를 생각해보자. RR, RR, BB, BB, BBB로 보석을 나누어주면 질투심은 3이 되고, 이 값보다 작게 나누어 줄 수 없다.
 *       상자 안의 보석 정보와 학생의 수가 주어졌을 때, 질투심이 최소가 되게 보석을 나누어주는 방법을 알아내는 프로그램을 작성하시오.
 *
 * 입력: 첫째 줄에 아이들의 수 N과 색상의 수 M이 주어진다. (1 ≤ N ≤ 109, 1 ≤ M ≤ 300,000, M ≤ N)
 *      다음 M개 줄에는 구간 [1, 109]에 포함되는 양의 정수가 하나씩 주어진다. K번째 줄에 주어지는 숫자는 K번 색상 보석의 개수이다.
 * 출력: 첫째 줄에 질투심의 최솟값을 출력한다.
 *
 * 해결: 이분탐색
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2792 {
    public static int N, M;
    public static List<Integer> list;
    public static int max = Integer.MIN_VALUE;

    public static int binarySearch(int low, int high) {
        int min = 0;
        while(low <= high){
            int mid = (low+high)/2; //중간 값
            int sum = 0;
            for(int i=0; i<M; i++){
                sum += list.get(i)/mid;
                if(list.get(i) % mid == 0){ //나누어 떨어질 때
                    sum++;
                }
            }

            if(sum > N){ //더 이상 나눠줄 수 없을 떄
                low = mid+1;
            }else{ //나눠 줄 수 있을 때
                high = mid-1;
                min = mid; //최솟값
            }
        }
        return min; //질투심 최솟값 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //아이들의 수
        M = Integer.parseInt(st.nextToken()); //색상의 수
        list = new ArrayList<>();

        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(br.readLine());
            list.add(num);
            max = Math.max(max, num); //최대값
        }

        int result = binarySearch(1, max); //이분 탐색 결과
        System.out.println(result); //결과 출력
    }
}
