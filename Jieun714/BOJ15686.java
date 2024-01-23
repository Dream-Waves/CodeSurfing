package Jieun714;
/**
 * 문제: 크기가 N×N인 도시가 있다. 도시는 1×1크기의 칸으로 나누어져 있다. 도시의 각 칸은 빈 칸, 치킨집, 집 중 하나이다. 도시의 칸은 (r, c)와 같은 형태로 나타내고, r행 c열 또는 위에서부터 r번째 칸, 왼쪽에서부터 c번째 칸을 의미한다. r과 c는 1부터 시작한다.
 *      이 도시에 사는 사람들은 치킨을 매우 좋아한다. 따라서, 사람들은 "치킨 거리"라는 말을 주로 사용한다. 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 즉, 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
 *      이 도시에 있는 치킨집은 모두 같은 프랜차이즈이다. 프렌차이즈 본사에서는 수익을 증가시키기 위해 일부 치킨집을 폐업시키려고 한다. 오랜 연구 끝에 이 도시에서 가장 수익을 많이 낼 수 있는  치킨집의 개수는 최대 M개라는 사실을 알아내었다.
 *      도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 어떻게 고르면, 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.
 *      둘째 줄부터 N개의 줄에는 도시의 정보가 주어진다.
 *      도시의 정보는 0, 1, 2로 이루어져 있고, 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다. 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다. 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
 * 출력: 첫째 줄에 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력한다.
 * 해결: 조합 + 구현
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {
    public static int N, M;
    public static List<int []> houseList, checkenList;
    public static boolean isSelected[];
    public static int select[];
    public static List<int []> chooseCheckenList;
    public static int result = Integer.MAX_VALUE;

    //최대 M개의 치킨집 고르기 - 조합
    public static void combination(int r, int start) {
        if(r == M) { //M개를 골랐을 때
            chooseCheckenList = new ArrayList<>(); //리스트 초기화
            for(int i=0; i<M; i++) {
                int [] c = checkenList.get(select[i]); //선택한 치킨집 좌표
                chooseCheckenList.add(new int[]{c[0] , c[1]});
            }
            result = Math.min(result, calc(chooseCheckenList)); //도시의 치킨 거리의 최솟값 저장
            return;
        }

        for(int i=start; i<checkenList.size(); i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            select[r] = i;
            combination(r+1, i);
            isSelected[i] = false;
        }
    }

    //도시의 치킨 거리의 최솟값 계산
    public static int calc(List<int []> list) {
        int total = 0;
        for(int [] h : houseList){
            int min = Integer.MAX_VALUE;
            for(int [] c : list){
                min = Math.min(min, Math.abs(c[0]-h[0]) + Math.abs(c[1]-h[1])); //최솟값 구하기
            }
            total += min; //최소 거리 누적
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //도시 정보의 크기(NxN)
        M = Integer.parseInt(st.nextToken()); //폐업 시키지 않을 치킨집

        houseList = new ArrayList<>(); //집의 좌표
        checkenList = new ArrayList<>(); //치킨집의 좌표
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) houseList.add(new int[]{i, j}); //집일때
                if(num == 2) checkenList.add(new int[]{i, j}); //치킨집일때
            }
        }

        select = new int[M];
        isSelected = new boolean[checkenList.size()];
        if(checkenList.size()>M){ //치킨집의 개수가 M보다 크면
            combination(0, 0);
            System.out.println(result); //도시의 치킨 거리의 최솟값 출력
        } else {
            System.out.println(calc(checkenList)); //도시의 치킨 거리의 최솟값 출력
        }
    }
}