package Jieun714;
/**
 * 문제: 노드가 N개인 이진 트리가 있다. 트리를 중위 순회와 유사하게 순회하려고 한다. 이를 유사 중위 순회라고 하자.
 *      순회의 시작은 트리의 루트이고 순회의 끝은 중위 순회할 때 마지막 노드이다. 이때 루트 노드는 항상 1번 노드이다. 유사 중위 순회는 루트 노드에서 시작하며, 다음과 같이 진행된다.
 *
 *        1. 현재 위치한 노드의 왼쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 왼쪽 자식 노드로 이동한다.
 *        2. 그렇지 않고 현재 위치한 노드의 오른쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 오른쪽 자식 노드로 이동한다.
 *        3. 그렇지 않고 현재 노드가 유사 중위 순회의 끝이라면, 유사 중위 순회를 종료한다.
 *        4. 그렇지 않고 부모 노드가 존재한다면, 부모 노드로 이동한다.
 *        5. 유사 중위 순회를 종료할 때까지 1 ~ 4를 반복한다.
 *
 *      여기서 이동이라는 것은 하나의 노드에서 다른 노드로 한번 움직이는 것을 의미한다. 예를 들면, 노드 1에서 노드 2로 가는 것을 한번 이동하였다고 한다.
 *      유사 중위 순회를 하면서 이동한 횟수를 구하려고 한다.
 * 입력: 첫 번째 줄에 트리를 구성하는 노드의 개수 N이 주어진다.
 *      두 번째 줄부터 N + 1 번째 줄까지 현재 노드 a, 현재 노드의 왼쪽 자식 노드 b, 현재 노드의 오른쪽 자식 노드 c가 공백으로 구분되어 주어진다.
 *      만약 자식 노드의 번호가 -1인 경우 자식 노드가 없다는 것을 의미한다.
 * 출력: 유사 중위 순회를 하면서 이동한 총 횟수를 출력한다.
 * 해결: 그래프(중위순회)
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ22856 {
    public static int N, lastInOrder, cnt; //노드 개수, 중위 순회의 마지막 노드 번호, 이동 횟수
    public static ArrayList<Integer> [] graph; //각 노드의 왼쪽 자식노드와 오른쪽 자식노드를 저장하는 그래프
    public static boolean [] isVisited; //방문 여부 체크 배열
    public static boolean flag = false; //마지막 노드에 도달했는지 여부

    public static void inOrder(int now) {
        int left = graph[now].get(0);
        int right = graph[now].get(1);
        if(left != -1) inOrder(left); //왼쪽 자식노드가 존재하면 재귀 호출
        lastInOrder = now ;//현재 노드를 일단 마지막으로 저장
        if(right != -1) inOrder(right); //오른쪽 자식노드가 존재하면 재귀 호출
    }

    public static void similarityInOrder(int now){
        isVisited[now] = true; //방문처리
        int left = graph[now].get(0);
        int right = graph[now].get(1);

        if(left != -1 && !isVisited[left]){
            cnt++; //왼쪽으로 이동
            similarityInOrder(left);
        }
        if(right != -1 && !isVisited[right]){
            cnt++; //오른쪽으로 이동
            similarityInOrder(right);
        }

        if(lastInOrder == now) flag = true; //마지막 노드 도착 시 flag 설정
        if(flag) return; //마지막 노드 이후로는 이동 횟수 카운트 중단
        cnt++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //현재 노드
            int b = Integer.parseInt(st.nextToken()); //왼쪽 자식 노드
            int c = Integer.parseInt(st.nextToken()); //오른쪽 자식 노드
            graph[a].add(b);
            graph[a].add(c);
        }

        isVisited = new boolean[N+1];
        inOrder(1); //중위순회로 마지막 노드 찾기
        similarityInOrder(1); //이동 횟수 계산
        System.out.println(cnt);
    }
}
