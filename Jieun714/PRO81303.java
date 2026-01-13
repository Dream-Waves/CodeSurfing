package Jieun714;
/**
 * 문제: 업무용 소프트웨어를 개발하는 니니즈웍스의 인턴인 앙몬드는 명령어 기반으로 표의 행을 선택, 삭제, 복구하는 프로그램을 작성하는 과제를 맡았습니다. 세부 요구 사항은 다음과 같습니다.
 *
 *      - "U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
 *      - "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
 *      - "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
 *      - "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
 *
 *      처음 표의 행 개수를 나타내는 정수 n, 처음에 선택된 행의 위치를 나타내는 정수 k, 수행한 명령어들이 담긴 문자열 배열 cmd가 매개변수로 주어질 때,
 *      모든 명령어를 수행한 후 표의 상태와 처음 주어진 표의 상태를 비교하여 삭제되지 않은 행은 O, 삭제된 행은 X로 표시하여 문자열 형태로 return 하도록 solution 함수를 완성해주세요.
 *
 * 입력: n=8, k=2, cmd=["D 2","C","U 3","C","D 4","C","U 2","Z","Z"]
 * 출력: "OOOOXOOO"
 * 해결: TreeSet과 Stack을 활용한 구현
 * */
import java.util.*;
public class PRO81303 {
    public class Solution {
        public String solution(int n, int k, String[] cmd) {
            TreeSet<Integer> set = new TreeSet<>(); //현재 존재하는 행 번호들을 관리하기 위한 TreeSet
            Stack<Integer> stack = new Stack<>(); //삭제된 행을 저장하기 위한 Stack
            StringBuilder sb = new StringBuilder();

            //초기화
            for(int i=0; i<n; i++) {
                set.add(i);
                sb.append("O");
            }

            for(String str : cmd) {
                String [] sArr = str.split(" ");

                switch(sArr[0]) {
                    case "C": //현재 선택된 행 삭제
                        set.remove(k);
                        stack.push(k);

                        // 아래 행이 있으면 아래로, 없으면 위로 이동
                        if(set.higher(k) != null) k = set.higher(k);
                        else k = set.lower(k);

                        break;
                    case "Z": //가장 최근에 삭제된 행 복구
                        if(!stack.isEmpty()) set.add(stack.pop());
                        break;
                    default:
                        int num = Integer.parseInt(sArr[1]);

                        if(sArr[0].equals("U")) { //현재 선택된 행에서 X칸 위에 있는 행 선택
                            for(int i=0; i<num; i++) k = set.lower(k);
                        } else { //현재 선택된 행에서 X칸 아래에 있는 행 선택
                            for(int i=0; i<num; i++) k = set.higher(k);
                        }

                        break;
                }
            }

            while(!stack.isEmpty()) { //최종적으로 삭제된 행은 'X' 표기
                sb.setCharAt(stack.pop(), 'X');
            }

            return sb.toString();
        }
    }
}