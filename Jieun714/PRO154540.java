package Jieun714;
/**
 * 문제: 메리는 여름을 맞아 무인도로 여행을 가기 위해 지도를 보고 있습니다. 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다. 지도는 1 x 1크기의 사각형들로 이루어진 직사각형 격자 형태이며, 격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가 적혀있습니다.
 *      지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다. 이때, 상, 하, 좌, 우로 연결되는 땅들은 하나의 무인도를 이룹니다. 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상, 하, 좌, 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다.
 *      어떤 섬으로 놀러 갈지 못 정한 메리는 우선 각 섬에서 최대 며칠씩 머물 수 있는지 알아본 후 놀러갈 섬을 결정하려 합니다.
 *      지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때, 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return 하는 solution 함수를 완성해주세요. 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.
 * 제한사항:
 *   - 3 ≤ maps의 길이 ≤ 100
 *      - 3 ≤ maps[i]의 길이 ≤ 100
 *      - maps[i]는 'X' 또는 1 과 9 사이의 자연수로 이루어진 문자열입니다.
 *      - 지도는 직사각형 형태입니다.
 * 해결: BFS
 */
import java.util.*;
public class PRO154540 {
    class Solution {
        public int [] dx = {0, 0, -1, 1};
        public int [] dy = {1, -1, 0, 0};
        public boolean [][] isVisited; //방문 여부를 체크하기 위한 배열

        //유효한 좌표인지, 아직 방문하지 않았고 바다가 아닌지 확인하는 함수
        public boolean isCheck(int y, int x, String [] maps) {
            if(0 <= y && y < isVisited.length && 0 <= x && x < isVisited[0].length) {
                return !isVisited[y][x] && maps[y].charAt(x) != 'X';
            }
            return false;
        }

        public int bfs(int y, int x, String [] maps) {
            int total = Integer.parseInt(String.valueOf(maps[y].charAt(x))); //섬에 머무르는 기간
            Queue<int []> que = new ArrayDeque<>();
            que.add(new int[] {y, x});
            isVisited[y][x] = true; //방문 체크

            while(!que.isEmpty()) {
                int [] now = que.poll();
                for(int i=0; i<4; i++) {
                    int nx = now[1] + dx[i];
                    int ny = now[0] + dy[i];

                    if(isCheck(ny, nx, maps)) {
                        total += Integer.parseInt(String.valueOf(maps[ny].charAt(nx))); //섬에 머무리는 기간 누적
                        isVisited[ny][nx] = true; //방문 체크
                        que.add(new int[] {ny, nx});
                    }
                }
            }

            return total;
        }

        public int[] solution(String[] maps) {
            int[] answer = {-1};
            isVisited = new boolean[maps.length][maps[0].length()];
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<maps.length; i++) {
                for(int j=0; j<maps[0].length(); j++) {
                    String s = String.valueOf(maps[i].charAt(j));
                    if(!isVisited[i][j] && !s.equals("X")) list.add(bfs(i, j, maps));
                }
            }

            if(list.size() != 0) { //섬이 최소 1개 이상일 경우
                Collections.sort(list); //섬에 머무르는 기간 정렬
                answer = new int[list.size()];
                for(int i = 0; i<list.size(); i++) answer[i] = list.get(i);
            }
            return answer;
        }
    }
}
