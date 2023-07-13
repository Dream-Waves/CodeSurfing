package ybwi0912;

/*
 * 2023-07-13
 * BOJ 9934: 완전 이진 트리
 * 1. 가장 처음에 상근이는 트리의 루트에 있는 빌딩 앞에 서있다.
 * 2. 현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동한다.
 * 3. 현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 현재 노드에 있는 빌딩을 들어가고 종이에 번호를 적는다.
 * 4. 현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동한다.
 * 5. 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동한다.
 * -> 해당 방문 순서는 중위 순회
 * -> 문제에서 주어진 트리는 가장 마지막 레벨을 제외한 모든 노드가 왼쪽 자식과 오른쪽 자식을 갖는 완전 이진 트리이다
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9934 {
    static int K; // 완전 이진 트리의 깊이
    static int[] input;
    static List<ArrayList<Integer>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(bf.readLine());
        StringTokenizer token = new StringTokenizer(bf.readLine());

        input = new int[(int)Math.pow(2, K) - 1];

        for(int i=0; i<input.length; i++){
            input[i] = Integer.parseInt(token.nextToken());
        }
        // input

        tree = new ArrayList<>();
        for(int i=0; i<K; i++){
            tree.add(new ArrayList<>());
        } // ArrayList를 담고 있는 ArrayList 생성

        search(0, input.length - 1, 0); // 루트 노드부터 한 노드씩 내려가면서 탐색
        // operation

        for(ArrayList<Integer> arr : tree){
            for(int i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        // output
    }

    static void search(int start, int end, int depth){ // 시작 노드, 끝 노드, 깊이
        if(depth == K) return; // 탐색 종료

        int mid = (start + end) / 2;
        tree.get(depth).add(input[mid]);
        // 중앙값을 찾아 트리에 넣는다

        search(start, mid - 1, depth + 1); // 왼쪽 노드
        search(mid + 1, end, depth + 1); // 오른쪽 노드
    }
}