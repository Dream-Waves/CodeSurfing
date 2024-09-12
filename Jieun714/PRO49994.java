package Jieun714;
/**
 * 문제: 게임 캐릭터를 4가지 명령어를 통해 움직이려 합니다. 명령어는 다음과 같습니다.
 *      U: 위쪽으로 한 칸 가기
 *      D: 아래쪽으로 한 칸 가기
 *      R: 오른쪽으로 한 칸 가기
 *      L: 왼쪽으로 한 칸 가기
 *      캐릭터는 좌표평면의 (0, 0) 위치에서 시작합니다. 좌표평면의 경계는 왼쪽 위(-5, 5), 왼쪽 아래(-5, -5), 오른쪽 위(5, 5), 오른쪽 아래(5, -5)로 이루어져 있습니다.
 *      명령어가 매개변수 dirs로 주어질 때, 게임 캐릭터가 처음 걸어본 길의 길이를 구하여 return 하는 solution 함수를 완성해 주세요.
 * 입출력 예시:
 * dirs	          answer
 * "ULURRDLLU"	  7
 * "LULLLLLLU"	  7
 * */
import java.util.*;
public class PRO49994 {
    public class Solution {
        public boolean check(int x, int y) { //좌표평면의 경계 체크
            if(x < 0 || x > 10 || y < 0 || y > 10) return false;
            return true;
        }

        public int solution(String dirs) {
            int answer = 0;
            Set<String> set = new HashSet<>();
            int x = 5;
            int y = 5;

            for(int i = 0; i<dirs.length(); i++) {
                char c = dirs.charAt(i);
                int nx = x;
                int ny = y;

                if(c == 'U') ny += 1; //위쪽으로 한 칸 가기
                else if(c == 'D') ny -= 1; //아래쪽으로 한 칸 가기
                else if(c == 'R') nx += 1; //오른쪽으로 한 칸 가기
                else nx -= 1; //왼쪽으로 한 칸 가기

                if(check(nx, ny)) { //좌표평면의 경계를 넘어가지 않을 경우
                    String s1 = x + "," + y + ">>" + nx + "," + ny; // A -> B
                    String s2 = nx + "," + ny + ">>" + x + "," + y; // B -> A
                    if(!set.contains(s1) && !set.contains(s2)) { //양방향에서 확인
                        answer++; //처음 걸어본 길의 길이 카운트
                        set.add(s1);
                    }
                    x = nx;
                    y = ny;
                }
            }
            return answer; //결과 출력
        }
    }
}
