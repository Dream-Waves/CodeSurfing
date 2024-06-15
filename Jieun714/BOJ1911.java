package Jieun714;
/**
 * 문제: 어젯밤 겨울 캠프 장소에서 월드 본원까지 이어지는, 흙으로 된 비밀길 위에 폭우가 내려서 N(1 ≤ N ≤ 10,000)개의 물웅덩이가 생겼다.
 *      월드학원은 물웅덩이를 덮을 수 있는 길이가 L(1 ≤ L ≤ 1,000,000)인 널빤지들을 충분히 가지고 있어서, 이들로 다리를 만들어 물웅덩이들을 모두 덮으려고 한다.
 *      물웅덩이들의 위치와 크기에 대한 정보가 주어질 때, 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 구하여라.
 * 입력: 첫째 줄에 두 정수 N과 L이 들어온다.
 *      둘째 줄부터 N+1번째 줄까지 총 N개의 줄에 각각의 웅덩이들의 정보가 주어진다. 웅덩이의 정보는 웅덩이의 시작 위치와 끝 위치로 이루어진다. 각 위치는 0 이상 1,000,000,000 이하의 정수이다. 입력으로 주어지는 웅덩이는 겹치지 않는다.
 * 출력: 첫째 줄에 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 출력한다.
 * 해결: 그리디 알고리즘 + 정렬
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //물웅덩이 개수
        int L = Integer.parseInt(st.nextToken()); //물웅덩이를 덮을 수 있는 길이
        ArrayList<long []> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new long[] {Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())});
        }
        Collections.sort(list, Comparator.comparingLong(o -> o[0])); //웅덩이의 시작 위치를 기준으로 정렬

        long now = list.get(0)[0]; //웅덩이의 시작 위치
        int cnt = 0; //널빤지 개수
        for(long [] l : list) {
            if(l[1]<now) continue; //현재 널빤지 위치가 웅덩이의 끝보다 크다면 continue
            if(now<l[0]) now = l[0]; //만약 현재 널빤지 위치보다 웅덩이의 시작 크다면, 현재 위치를 웅덩이의 시작으로 바꿔줌
            long n = l[1] - now; //웅덩이의 끝 - 현재 널빤지 위치
            cnt += (n/L);
            long remain = n%L; //나머지
            if(remain != 0) { //나머지가 있다면
                now = l[1] + (L-remain); //현재 널빤지 위치 수정
                cnt++; //널빤지 추가
            }
        }
        System.out.println(cnt); //결과 출력
    }
}