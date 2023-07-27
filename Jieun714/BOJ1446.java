package Jieun714;
/**
 * 문제: 매일 아침, 세준이는 학교에 가기 위해서 차를 타고 D킬로미터 길이의 고속도로를 지난다. 이 고속도로는 심각하게 커브가 많아서 정말 운전하기도 힘들다.
 *      어느 날, 세준이는 이 고속도로에 지름길이 존재한다는 것을 알게 되었다. 모든 지름길은 일방통행이고, 고속도로를 역주행할 수는 없다. 세준이가 운전해야 하는 거리의 최솟값을 출력하시오.
 * 입력: 첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다. 다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다.
 *      모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.
 * 출력: 세준이가 운전해야하는 거리의 최솟값을 출력하시오.
 * 참조: https://velog.io/@sehhe161/%EB%B0%B1%EC%A4%80-1446%EB%B2%88-%EC%A7%80%EB%A6%84%EA%B8%B8-JAVA
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1446 {
    public static class Point{
        int start;
        int distance;
        Point(int start, int distance){
            this.start = start;
            this.distance = distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //지름길의 갯수
        int D = Integer.parseInt(st.nextToken()); //고속도로의 길이

        ArrayList<Point> [] graph = new ArrayList[10001]; //지름길의 정보를 담는 리스트
        for(int i=0; i<10001; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); //지름길 시작 위치
            int end = Integer.parseInt(st.nextToken()); //지름길 도착 위치
            int distance = Integer.parseInt(st.nextToken()); //지름길 길이

            //지름길이 무조건 최단거리는 아니기 때문에, 최단거리일 때만 값을 넣도록
            if(end-start > distance){
                graph[end].add(new Point(start, distance));
            }
        }

        int[] distance = new int[D+1]; //거리는 담는 배열
        Arrays.fill(distance,Integer.MAX_VALUE); // 거리배열 최댓값으로 초기화
        distance[0] = 0;
        for(int i=1; i<=D; i++){
            if(graph[i].size()>0){ //그래프가 존재할 때
                for(Point p : graph[i]){
                    if(distance[p.start]+p.distance > distance[i]) continue; // 갱신되었다면
                    distance[i] = Math.min(distance[i-1]+1, distance[p.start]+p.distance);
                }
                continue;
            }
            distance[i] = distance[i-1]+1;
        }
        System.out.println(distance[D]); //최단 거리 출력
    }
}