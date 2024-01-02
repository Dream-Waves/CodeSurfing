package Jieun714;
/**
 * 문제: 월드컵 축구의 응원을 위한 모임에서 회장을 선출하려고 한다. 이 모임은 만들어진지 얼마 되지 않았기 때문에 회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다. 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.
 *      예를 들어 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다. 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말한다. 또한 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말한다.
 *      4점, 5점 등은 같은 방법으로 정해진다. 각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이라고 본다.
 *      회장은 회원들 중에서 점수가 가장 작은 사람이 된다. 회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성하시오.
 * 입력: 입력의 첫째 줄에는 회원의 수가 있다. 단, 회원의 수는 50명을 넘지 않는다. 둘째 줄 이후로는 한 줄에 두 개의 회원번호가 있는데, 이것은 두 회원이 서로 친구임을 나타낸다. 회원번호는 1부터 회원의 수만큼 붙어 있다. 마지막 줄에는 -1이 두 개 들어있다.
 * 출력: 첫째 줄에는 회장 후보의 점수와 후보의 수를 출력하고, 두 번째 줄에는 회장 후보를 오름차순으로 모두 출력한다.
 * 해결: 너비우선탐색(BFS) 사용
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2660 {
    public static int N;
    public static ArrayList<Integer> graph[]; //친구 정보를 담는 그래프
    public static boolean [] isVisited; //방문체크 배열
    public static int score = Integer.MAX_VALUE; //회장 후보의 점수
    public static int [] scoreList; //누적 점수 리스트
    public static List<Integer> candidateList = new ArrayList<>(); //회장 후보

    public static int bfs(int num){
        Queue<int []> que = new LinkedList<>();
        que.offer(new int[]{num, 0}); //현재 노드, 경로의 길이
        int cnt = 0;

        while(!que.isEmpty()){ //큐가 빌때까지
            int [] now = que.poll(); //현재 노드, 경로의 길이
            for(int i=0; i<graph[now[0]].size(); i++){
                int next = graph[now[0]].get(i); //다음 이동 노드
                if(isVisited[next]) continue; //이미 방문했다면 다음으로
                isVisited[next] = true; //방문체크
                que.offer(new int[]{next, now[1]+1}); //다음 노드, 현재 경로의 길이 + 1
            }
            cnt = now[1]; //현재 경로의 길이
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //회원의 수

        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) graph[i] = new ArrayList<>();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1) break; //while문 종료 조건

            graph[a].add(b);
            graph[b].add(a);
        }

        scoreList = new int[N+1];
        for(int i=1; i<=N; i++){
            isVisited = new boolean[N+1];
            isVisited[i] = true; //자기 자신은 방문처리
            int num = bfs(i);
            scoreList[i] = num; //후보 점수 저장
            score = Math.min(score, num); //회장 후보 점수
        }

        for(int i=1; i<=N; i++){
            if(score == scoreList[i]){ //같다면
                candidateList.add(i); //회장 후보 리스트에 담기
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score).append(" ").append(candidateList.size()).append("\n"); //회장 후보의 점수와 후보의 수
        for (Integer i : candidateList) { //회장 후보
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}