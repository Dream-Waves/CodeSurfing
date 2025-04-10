package Jieun714;
/**
 * 문제: 개발자 출신으로 세계 최고의 갑부가 된 어피치는 스트레스를 받을 때면 이를 풀기 위해 오프라인 매장에 쇼핑을 하러 가곤 합니다.
 *      어피치는 쇼핑을 할 때면 매장 진열대의 특정 범위의 물건들을 모두 싹쓸이 구매하는 습관이 있습니다.
 *      어느 날 스트레스를 풀기 위해 보석 매장에 쇼핑을 하러 간 어피치는 이전처럼 진열대의 특정 범위의 보석을 모두 구매하되 특별히 아래 목적을 달성하고 싶었습니다.
 *      진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
 *      진열대 번호 순서대로 보석들의 이름이 저장된 배열 gems가 매개변수로 주어집니다. 이때 모든 보석을 하나 이상 포함하는 가장 짧은 구간을 찾아서 return 하도록 solution 함수를 완성해주세요.
 *      가장 짧은 구간의 시작 진열대 번호와 끝 진열대 번호를 차례대로 배열에 담아서 return 하도록 하며, 만약 가장 짧은 구간이 여러 개라면 시작 진열대 번호가 가장 작은 구간을 return 합니다.
 * 제한사항
 *   - gems 배열의 크기는 1 이상 100,000 이하입니다.
 *   - gems 배열의 각 원소는 진열대에 나열된 보석을 나타냅니다.
 *   - gems 배열에는 1번 진열대부터 진열대 번호 순서대로 보석이름이 차례대로 저장되어 있습니다.
 *   - gems 배열의 각 원소는 길이가 1 이상 10 이하인 알파벳 대문자로만 구성된 문자열입니다.
 * 풀이: 투포인터
 * */
import java.util.*;

public class PRO67258 {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        for(String s : gems) set.add(s);

        int left = 0, right = 0; // 투포인트 초기화
        int min = Integer.MAX_VALUE; // 최소 구간 길이
        Map<String, Integer> map = new HashMap<>();
        while (true) {
            if (map.size() == set.size()) { // 모든 보석을 포함한 경우
                if (right - left < min) { // 현재 구간이 기존 최소 구간보다 짧으면 갱신
                    min = right - left;
                    answer[0] = left + 1;
                    answer[1] = right;
                }

                String leftGem = gems[left];
                map.put(leftGem, map.get(leftGem) - 1); // 왼쪽 구간 보석 하나 제거
                if (map.get(leftGem) == 0) map.remove(leftGem); // 해당 보석이 구간 내에 더 이상 없으면 map에서 제거
                left++;
            } else {
                if (right == gems.length) break; // 끝에 도달하면 종료

                String rightGem = gems[right];
                // getOrDefault : 찾는 키가 존재한다면 찾는 키의 값을 반환하고 없다면 기본 값을 반환
                map.put(rightGem, map.getOrDefault(rightGem, 0) + 1); // 보석 개수 증가
                right++;
            }
        }

        return answer;
    }
}