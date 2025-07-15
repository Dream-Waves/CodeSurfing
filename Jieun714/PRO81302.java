package Jieun714;
/**
 * 문제: 개발자를 희망하는 죠르디가 카카오에 면접을 보러 왔습니다.
 *      코로나 바이러스 감염 예방을 위해 응시자들은 거리를 둬서 대기를 해야하는데 개발 직군 면접인 만큼
 *      아래와 같은 규칙으로 대기실에 거리를 두고 앉도록 안내하고 있습니다.
 *
 *      1. 대기실은 5개이며, 각 대기실은 5x5 크기입니다.
 *      2. 거리두기를 위하여 응시자들 끼리는 맨해튼 거리1가 2 이하로 앉지 말아 주세요.
 *      3. 단 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용합니다.
 *      5개의 대기실을 본 죠르디는 각 대기실에서 응시자들이 거리두기를 잘 기키고 있는지 알고 싶어졌습니다. 자리에 앉아있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열 places가 매개변수로 주어집니다.
 *      각 대기실별로 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 * 해결: BFS
 * */
import java.util.*;
public class PRO81302 {
    class Solution {
        public char [][] map;
        public boolean [][] isVisited;
        public int [] dx = {0, 0, 1, -1};
        public int [] dy = {1, -1, 0, 0};

        public boolean bfs(int y, int x) {
            Queue<int []> que = new ArrayDeque<>();
            que.add(new int[]{y, x});
            isVisited[y][x] = true;

            while(!que.isEmpty()) {
                int [] now = que.poll();
                for(int i=0; i<4; i++) {
                    int nx = now[1] + dx[i];
                    int ny = now[0] + dy[i];

                    if(isChecked(ny, nx) ) { //유효한 위치인지 확인
                        int num = Math.abs(x-nx) + Math.abs(y-ny); //맨해튼 거리 계산
                        if(num <= 2) {
                            if(map[ny][nx] == 'P') return false; //거리두기 위반 시
                            isVisited[ny][nx] = true;
                            que.add(new int[]{ny, nx}); //다음 탐색을 위해 큐에 추가
                        }
                    }
                }
            }

            return true; //거리두기 위반 없다면 true
        }

        //현재 위치가 유효한 위치인지 판별하는 함수
        public boolean isChecked(int y, int x) {
            if(y < 0 || y >= 5 || x < 0 || x >= 5) return false;
            return !isVisited[y][x] && map[y][x] != 'X';
        }

        //현재 대기실이 거리두기를 잘 지키고 있는지 확인
        public int isSuccess() {
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    if(map[i][j] == 'P') { //사람(P)이 있는 위치에서 검사 시작
                        if(!bfs(i, j)) return 0; // 거리두기 위반 시 0 반환
                    }
                }
            }
            return 1; //모든 사람에 대해 거리두기 지켜졌다면 1
        }

        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            for(int i = 0; i<5; i++) {
                map = new char[5][5];
                isVisited = new boolean[5][5];
                for(int j=0; j<5; j++) map[j] = places[i][j].toCharArray();
                answer[i] = isSuccess(); //해당 대기실 결과 저장
            }

            return answer; //각 대기실의 결과 배열 반환
        }
    }
}