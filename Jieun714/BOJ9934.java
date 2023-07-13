package Jieun714;
/**
 * 문제: 이 도시의 도로는 깊이가 K인 완전 이진 트리를 이루고 있다. 깊이가 K인 완전 이진 트리는 총 2K-1개의 노드로 이루어져 있다. (아래 그림) 각 노드에는 그 곳에 위치한 빌딩의 번호가 붙여져 있다.
 *      또, 가장 마지막 레벨을 제외한 모든 집은 왼쪽 자식과 오른쪽 자식을 갖는다.
 * 입력: 첫째 줄에 K (1 ≤ K ≤ 10)가 주어진다. 둘째 줄에는 상근이가 방문한 빌딩의 번호가 들어간 순서대로 주어진다. 모든 빌딩의 번호는 중복되지 않으며, 구간 [1,2^K]에 포함된다.
 * 출력: 총 K개의 줄에 걸쳐서 정답을 출력한다. i번째 줄에는 레벨이 i인 빌딩의 번호를 출력한다. 출력은 왼쪽에서부터 오른쪽 순서대로 출력한다.
 * 메모리: 15280 KB,  걸린 시간: 216 ms
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9934 {
    static int K; //트리의 깊이
    static ArrayList<Integer> list = new ArrayList<>(); //입력 받은 노드를 담는 배열
    static ArrayList<Integer> tree[]; //결과를 담을 리스트 배열

    //재귀 함수
    public static void check(int start, int last, int depth){
        if(depth == K) { //종료 조건
            return;
        }
        int mid = (start+last)/2;
        tree[depth].add(list.get(mid)); //결과에 추가
        check(start, mid-1, depth+1);
        check(mid+1, last, depth+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); //트리의 깊이
        StringTokenizer st = new StringTokenizer(br.readLine());
        int last = (int) Math.pow(2, K)-1; //2^K-1개의 노드
        for(int i=0; i<last; i++){
            list.add(Integer.parseInt(st.nextToken())); //방문한 빌딩의 번호(노드)
        }

        tree = new ArrayList[K];
        for(int i=0; i<K; i++){
            tree[i] = new ArrayList<>();
        }

        check(0, last, 0);

        //결과 출력
        for(int i=0; i<K; i++){
            for(int s : tree[i]) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}