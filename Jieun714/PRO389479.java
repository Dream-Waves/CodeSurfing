package Jieun714;
/**
 * 문제: 당신은 온라인 게임을 운영하고 있습니다. 같은 시간대에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요합니다. 어느 시간대의 이용자가 m명 미만이라면, 서버 증설이 필요하지 않습니다.
 *      어느 시간대의 이용자가 n x m명 이상 (n + 1) x m명 미만이라면 최소 n대의 증설된 서버가 운영 중이어야 합니다. 한 번 증설한 서버는 k시간 동안 운영하고 그 이후에는 반납합니다. 예를 들어, k = 5 일 때 10시에 증설한 서버는 10 ~ 15시에만 운영됩니다.
 *      하루 동안 모든 게임 이용자가 게임을 하기 위해 서버를 최소 몇 번 증설해야 하는지 알고 싶습니다. 같은 시간대에 서버를 x대 증설했다면 해당 시간대의 증설 횟수는 x회입니다.
 *      0시에서 23시까지의 시간대별 게임 이용자의 수를 나타내는 1차원 정수 배열 players, 서버 한 대로 감당할 수 있는 최대 이용자의 수를 나타내는 정수 m, 서버 한 대가 운영 가능한 시간을 나타내는 정수 k가 주어집니다.
 *      이때, 모든 게임 이용자를 감당하기 위한 최소 서버 증설 횟수를 return 하도록 solution을 완성해 주세요.
 * 제한사항:
 *       - players의 길이 = 24
 *          - 0 ≤ players의 원소 ≤ 1,000
 *          - players[i]는 i시 ~ i+1시 사이의 게임 이용자의 수를 나타냅니다.
 *       - 1 ≤ m ≤ 1,000
 *       - 1 ≤ k ≤ 24
 * */
import java.util.*;

public class PRO389479 {
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 추가된 서버의 수
        ArrayList<Integer> list = new ArrayList<>(); // 각 서버의 남은 처리 시간을 저장할 리스트

        for(int p : players) {
            int server = p/m; // 현재 사용자가 요청한 시점의 서버 인덱스를 계산
            int need = server-list.size(); // 필요한 서버 수
            if(need > 0) {
                for(int j=0; j<need; j++) list.add(k); // 새 서버는 k만큼의 처리 시간으로 시작
                answer += need; // 필요 서버 증설
            }

            if(list.size() != 0) {
                // 모든 서버의 남은 처리 시간 1씩 감소
                for(int j=list.size()-1; j>=0; j--) {
                    int now = list.get(j);
                    if(now-1 == 0) list.remove(j); // 남은 시간이 0이 되면 운영 서버 제거
                    else list.set(j, now-1);
                }
            }
        }
        return answer;
    }
}
