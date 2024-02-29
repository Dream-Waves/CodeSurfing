package Jieun714;
/**
 * 문제: 남규는 통나무를 세워 놓고 건너뛰기를 좋아한다. 그래서 N개의 통나무를 원형으로 세워 놓고 뛰어놀려고 한다. 남규는 원형으로 인접한 옆 통나무로 건너뛰는데, 이때 각 인접한 통나무의 높이 차가 최소가 되게 하려 한다.
 *      통나무 건너뛰기의 난이도는 인접한 두 통나무 간의 높이의 차의 최댓값으로 결정된다. 높이가 {2, 4, 5, 7, 9}인 통나무들을 세우려 한다고 가정하자. 이를 [2, 9, 7, 4, 5]의 순서로 세웠다면, 가장 첫 통나무와 가장 마지막 통나무 역시 인접해 있다.
 *      즉, 높이가 2인 것과 높이가 5인 것도 서로 인접해 있다. 배열 [2, 9, 7, 4, 5]의 난이도는 |2-9| = 7이다. 우리는 더 나은 배열 [2, 5, 9, 7, 4]를 만들 수 있으며 이 배열의 난이도는 |5-9| = 4이다. 이 배열보다 난이도가 낮은 배열은 만들 수 없으므로 이 배열이 남규가 찾는 답이 된다.
 * 입력: 입력은 T개의 테스트 케이스로 이루어져 있다. 첫 줄에 T가 주어진다.
 *      이어지는 각 줄마다 첫 줄에 통나무의 개수를 나타내는 정수 N(5 ≤ N ≤ 10,000), 둘째 줄에 각 통나무의 높이를 나타내는 정수 Li가 주어진다. (1 ≤ Li ≤ 100,000)
 * 출력: 각 테스트 케이스마다 한 줄에 주어진 통나무들로 만들 수 있는 최소 난이도를 출력하시오.
 * 해결: 그리디 알고리즘. 정규분포도 형식으로 배열의 값을 배치 후 차이 값 계산
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); //테스트 케이스
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine()); //통나무의 개수
            StringTokenizer st = new StringTokenizer(br.readLine());
            int [] L = new int[N]; //통나무의 높이를 담는 배열
            for(int j=0; j<N; j++) {
                L[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(L); //배열 오름차순 정렬
            int [] newArr = new int[N];
            //작은 값들부터 새로운 배열에 삽입
            for(int j=0; j<N; j+=2){
                if(N%2 == 1 && j == N-1){ //통나무의 수가 홀수이면서 for문의 마지막일 때
                    newArr[N/2] = L[j]; //정중앙에 값 삽입
                    break; //for문 종료
                }
                newArr[j/2] = L[j]; //왼쪽에 값 삽입
                newArr[N-1-j/2] = L[j+1]; //오른쪽에 값 삽입
            }
            int answer = 0; //통나무들로 만들 수 있는 최소 난이도
            for(int j=0; j<N; j++){
                if(j != N-1)
                    answer = Math.max(answer, Math.abs(newArr[j]-newArr[j+1]));
                else
                    answer = Math.max(answer, Math.abs(newArr[j]-newArr[0]));
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb); //결과 출력
    }
}