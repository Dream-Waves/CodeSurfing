package Jieun714;
/**
 * 문제: 강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다. 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다.
 *      순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
 *      강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에, 블루레이의 개수를 가급적 줄이려고 한다. 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다.
 *      이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다. 강토의 각 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.
 *
 * 입력: 첫째 줄에 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다. 다음 줄에는 강토의 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다. 각 강의의 길이는 10,000분을 넘지 않는다.
 *
 * 출력: 첫째 줄에 가능한 블루레이 크기중 최소를 출력한다.
 *
 * 해결: 이분탐색 사용. 최대값과 강의 배열의 누적 값을 start와 end 초기값으로 줌
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2343 {
    public static int N, M;
    public static int [] arr;
    public static int min; //가능한 블루레이 크기 중 최소

    public static void binarySearch(int start, int end){
        while(start < end){ //start가 end보다 작을 동안
            int cnt = 1;
            int mid = (start+end)/2; //중간 값
            int target = mid; //타켓 설정

            // 블루레이 갯수 체크
            for(int i=0; i<N; i++){
                if(arr[i] > target){
                    cnt++;
                    target = mid;
                }
                target -= arr[i]; //타겟 위치 변경
            }

            if(cnt > M){ //모든 강의를 저장할 수 없을 때
                start = mid +1; //시작 위치 조정
            } else{
                end = mid;
            }
        }
        min = Math.min(min, start); //최소의 길이 찾기
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //강의의 수
        M = Integer.parseInt(st.nextToken()); //블루레이의 수
        arr = new int[N];  //기타 강의의 길이를 담는 배열

        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            min += arr[i]; //강의 누적 길이
            max = Math.max(max, arr[i]); //최대값
        }
        binarySearch(max, min);
        System.out.println(min); //결과 출력
    }
}