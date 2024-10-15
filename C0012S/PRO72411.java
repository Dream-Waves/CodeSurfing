/*
Lv. 2 #72411 - 메뉴 리뉴얼

    문제 설명
        레스토랑을 운영하던 스카피는 코로나19로 인한 불경기를 극복하고자 메뉴를 새로 구성하려고 고민하고 있습니다.
        기존에는 단품으로만 제공하던 메뉴를 조합해서 코스요리 형태로 재구성해서 새로운 메뉴를 제공하기로 결정했습니다. 어떤 단품메뉴들을 조합해서 코스요리 메뉴로 구성하면 좋을 지 고민하던 "스카피"는 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.
        단, 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고 합니다. 또한, 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함하기로 했습니다.

        예를 들어, 손님 6명이 주문한 단품메뉴들의 조합이 다음과 같다면,
        (각 손님은 단품메뉴를 2개 이상 주문해야 하며, 각 단품메뉴는 A ~ Z의 알파벳 대문자로 표기합니다.)
            손님 번호	    주문한 단품메뉴 조합
            1번 손님	    A, B, C, F, G
            2번 손님	    A, C
            3번 손님	    C, D, E
            4번 손님	    A, C, D, E
            5번 손님	    B, C, F, G
            6번 손님	    A, C, D, E, H

        가장 많이 함께 주문된 단품메뉴 조합에 따라 "스카피"가 만들게 될 코스요리 메뉴 구성 후보는 다음과 같습니다.
            코스 종류	        메뉴 구성	        설명
            요리 2개 코스	    A, C	        1번, 2번, 4번, 6번 손님으로부터 총 4번 주문됐습니다.
            요리 3개 코스	    C, D, E	        3번, 4번, 6번 손님으로부터 총 3번 주문됐습니다.
            요리 4개 코스	    B, C, F, G	    1번, 5번 손님으로부터 총 2번 주문됐습니다.
            요리 4개 코스	    A, C, D, E	    4번, 6번 손님으로부터 총 2번 주문됐습니다.


    [문제]
        각 손님들이 주문한 단품메뉴들이 문자열 형식으로 담긴 배열 orders, "스카피"가 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수가 담긴 배열 course가 매개변수로 주어질 때, "스카피"가 새로 추가하게 될 코스요리의 메뉴 구성을 문자열 형태로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.


    [제한사항]
        · orders 배열의 크기는 2 이상 20 이하입니다.
        · orders 배열의 각 원소는 크기가 2 이상 10 이하인 문자열입니다.
            · 각 문자열은 알파벳 대문자로만 이루어져 있습니다.
            · 각 문자열에는 같은 알파벳이 중복해서 들어있지 않습니다.
        · course 배열의 크기는 1 이상 10 이하입니다.
            · course 배열의 각 원소는 2 이상 10 이하인 자연수가 오름차순으로 정렬되어 있습니다.
            · course 배열에는 같은 값이 중복해서 들어있지 않습니다.
        · 정답은 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 오름차순 정렬해서 return 해주세요.
            · 배열의 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬되어야 합니다.
            · 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다.
            · orders와 course 매개변수는 return 하는 배열의 길이가 1 이상이 되도록 주어집니다.


    [입출력 예]
        orders	                                            course	    result
        ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]	    [2,3,4]	    ["AC", "ACDE", "BCFG", "CDE"]
        ["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"]	[2,3,5]	    ["ACD", "AD", "ADE", "CD", "XYZ"]
        ["XYZ", "XWY", "WXA"]	                            [2,3,4]	    ["WX", "XY"]

        입출력 예에 대한 설명
            입출력 예 #1
                문제의 예시와 같습니다.

            입출력 예 #2
                AD가 세 번, CD가 세 번, ACD가 두 번, ADE가 두 번, XYZ 가 두 번 주문됐습니다.
                요리 5개를 주문한 손님이 1명 있지만, 최소 2명 이상의 손님에게서 주문된 구성만 코스요리 후보에 들어가므로, 요리 5개로 구성된 코스요리는 새로 추가하지 않습니다.

            입출력 예 #3
                WX가 두 번, XY가 두 번 주문됐습니다.
                3명의 손님 모두 단품메뉴를 3개씩 주문했지만, 최소 2명 이상의 손님에게서 주문된 구성만 코스요리 후보에 들어가므로, 요리 3개로 구성된 코스요리는 새로 추가하지 않습니다.
                또, 단품메뉴를 4개 이상 주문한 손님은 없으므로, 요리 4개로 구성된 코스요리 또한 새로 추가하지 않습니다.
*/


/*
    정확성  테스트
        테스트 1 〉	통과 (6.03ms, 66.8MB)
        테스트 2 〉	통과 (5.67ms, 75.2MB)
        테스트 3 〉	통과 (4.96ms, 73.4MB)
        테스트 4 〉	통과 (7.03ms, 89.1MB)
        테스트 5 〉	통과 (4.85ms, 75.8MB)
        테스트 6 〉	통과 (5.87ms, 82MB)
        테스트 7 〉	통과 (5.54ms, 79.3MB)
        테스트 8 〉	통과 (19.18ms, 87MB)
        테스트 9 〉	통과 (17.24ms, 92MB)
        테스트 10 〉	통과 (19.53ms, 93.3MB)
        테스트 11 〉	통과 (15.08ms, 89MB)
        테스트 12 〉	통과 (18.32ms, 87.7MB)
        테스트 13 〉	통과 (26.80ms, 75.4MB)
        테스트 14 〉	통과 (35.15ms, 79.8MB)
        테스트 15 〉	통과 (22.55ms, 80.3MB)
        테스트 16 〉	통과 (21.96ms, 82.9MB)
        테스트 17 〉	통과 (36.56ms, 80.4MB)
        테스트 18 〉	통과 (22.68ms, 85.8MB)
        테스트 19 〉	통과 (27.33ms, 101MB)
        테스트 20 〉	통과 (19.79ms, 90.4MB)

    채점 결과
        정확성: 100.0
        합계: 100.0 / 100.0
*/


// 정답

package C0012S;

import java.util.*;

class PRO72411 {
    static int orderNum; // 손님의 수
    static int orderLen; // 각 손님이 주문한 단품 메뉴들의 개수
    static boolean[] isSelected; // 각 손님이 주문한 단품 메뉴들의 메뉴 선택 여부
    static String selection; // 각 손님이 주문한 단품 메뉴들 중 선택한 메뉴 조합
    static Map<String, Integer> orderMap; // 각 손님이 주문한 단품 메뉴들로 만들 수 있는 메뉴 조합과 그 수를 저장한 Map
    static Map<String, Integer> courseMap; // 모든 손님이 주문한 단품 메뉴들로 만들 수 있는 메뉴 조합과 그 수를 저장한 Map

    public static void select(int selectedNum, int start, String[] order) { // 각 손님이 주문한 단품 메뉴들로 메뉴 조합을 만들고 그 조합의 수를 저장하는 메서드
        if (selectedNum > 0) {
            selection = "";

            for (int s = 0; s < orderLen; s++) {
                if (isSelected[s]) {
                    selection += order[s];
                }
            }

            orderMap.put(selection, 1);
        }

        for (int i = start; i < orderLen; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                select(selectedNum + 1, i + 1, order);
                isSelected[i] = false;
            }
        }
    }

    public static String[] find(String[] orders, int[] course) { // 스카피가 새로 추가하게 될 코스 요리의 메뉴 목록을 구하는 메서드
        courseMap = new HashMap<>();
        for (int o = 0; o < orderNum; o++) {
            String[] order = orders[o].split("");
            Arrays.sort(order); // 각 손님이 주문한 단품 메뉴 오름차순 정렬

            orderLen = order.length;
            isSelected = new boolean[orderLen];
            orderMap = new HashMap<>();
            select(0, 0, order);

            orderMap.forEach((key, value) -> courseMap.merge(key, value, (v1, v2) -> v1 + v2)); // 모든 손님이 주문한 단품 메뉴들로 만들 수 있는 메뉴 조합 갱신
        }

        List<String> resultList = new ArrayList<>(); // 스카피가 새로 추가하게 될 코스 요리의 메뉴 목록
        for (int count : course) {
            int maxCount = 2; // 해당 단품 메뉴 개수로 구성하는 코스 요리의 가장 많이 주문된 수
            Set<String> courseSet = new HashSet<>(); // 해당 단품 메뉴 개수로 구성하는 코스 요리

            for (String key : courseMap.keySet()) {
                if (key.length() == count) { // 해당 단품 메뉴 개수로 구성하는 코스 요리일 경우
                    if (courseMap.get(key) > maxCount) { // 해당 코스 요리의 주문된 수가 해당 단품 메뉴 개수로 구성하는 코스 요리의 가장 많이 주문된 수보다 클 경우
                        courseSet.clear();
                        courseSet.add(key);

                        maxCount = courseMap.get(key);
                    }
                    else if (courseMap.get(key) == maxCount) { // 해당 코스 요리의 주문된 수가 해당 단품 메뉴 개수로 구성하는 코스 요리의 가장 많이 주문된 수와 같을 경우
                        courseSet.add(key);
                    }
                }
            }

            resultList.addAll(courseSet); // 스카피가 새로 추가하게 될 코스 요리의 메뉴 목록에 해당 단품 메뉴 개수로 구성하는 코스 요리 추가
        }

        Collections.sort(resultList); // 스카피가 새로 추가하게 될 코스 요리의 메뉴 목록 오름차순 정렬

        return resultList.toArray(new String[resultList.size()]);
    }

    public String[] solution(String[] orders, int[] course) {
        orderNum = orders.length;

        return find(orders, course);
    }
}
