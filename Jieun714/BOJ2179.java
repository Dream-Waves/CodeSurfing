package Jieun714;
/**
 * 문제: N개의 영단어들이 주어졌을 때, 가장 비슷한 두 단어를 구해내는 프로그램을 작성하시오.
 *      두 단어의 비슷한 정도는 두 단어의 접두사의 길이로 측정한다. 접두사란 두 단어의 앞부분에서 공통적으로 나타나는 부분문자열을 말한다. 즉, 두 단어의 앞에서부터 M개의 글자들이 같으면서 M이 최대인 경우를 구하는 것이다. "AHEHHEH", "AHAHEH"의 접두사는 "AH"가 되고, "AB", "CD"의 접두사는 ""(길이가 0)이 된다.
 *      접두사의 길이가 최대인 경우가 여러 개일 때에는 입력되는 순서대로 제일 앞쪽에 있는 단어를 답으로 한다. 즉, 답으로 S라는 문자열과 T라는 문자열을 출력한다고 했을 때, 우선 S가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력하고, 그런 경우도 여러 개 있을 때에는 그 중에서 T가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력한다.
 * 입력: 첫째 줄에 N(2 ≤ N ≤ 20,000)이 주어진다. 다음 N개의 줄에 알파벳 소문자로만 이루어진 길이 100자 이하의 서로 다른 영단어가 주어진다.
 * 출력: 첫째 줄에 S를, 둘째 줄에 T를 출력한다. 단, 이 두 단어는 서로 달라야 한다. 즉, 가장 비슷한 두 단어를 구할 때 같은 단어는 제외하는 것이다.
 * 해결: HashMap
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2179 {
    public static Map<String, Integer> idxMap; //(단어, 인덱스) 저장
    public static Map<String, String> map; //(접두사, 원본 단어) 저장
    public static int bestFirstIdx; //현재 정답 쌍의 첫 번째 단어 인덱스
    public static int bestSecondIdx; //현재 정답 쌍의 두 번째 단어 인덱스
    public static int max; //현재까지 발견한 가장 긴 공통 접두사 길이
    public static StringBuilder answer;

    public static void add(String now, int nowIdx) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < now.length(); i++) {
            sb.append(now.charAt(i)); //접두사를 1글자씩 증가시키며 검사
            String same = sb.toString();

            if (!map.containsKey(same)) { //해당 접두사가 처음 등장한 경우
                map.put(same, now);
            } else {
                int firstIdx = idxMap.get(map.get(same));

                if (same.length() > max) { //공통 접두사 길이가 더 긴 경우
                    max = same.length();
                    change(firstIdx, nowIdx, map.get(same), now);
                } else if (same.length() == max) { //공통 접두사 길이가 같은 경우
                    //첫 번째 단어 인덱스가 더 빠르거나 같다면 두 번째 단어 인덱스가 더 빠른 쌍 선택
                    if (firstIdx < bestFirstIdx || (bestFirstIdx == firstIdx && nowIdx < bestSecondIdx)) {
                        change(firstIdx, nowIdx, map.get(same), now);
                    }
                }
            }
        }
    }

    public static void change(int firstIdx, int secondIdx, String first, String second) {
        bestFirstIdx = firstIdx;
        bestSecondIdx = secondIdx;
        answer.setLength(0);
        answer.append(first).append("\n").append(second);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //입력 영단어 수
        idxMap = new LinkedHashMap<>();
        map = new HashMap<>();
        bestFirstIdx = Integer.MAX_VALUE;
        bestSecondIdx = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        answer = new StringBuilder();

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            idxMap.put(str, i); //단어 입력 순서 저장
            add(str, i); //접두사 추가 및 비교
        }

        if(max == Integer.MIN_VALUE) { //공통 접두사가 하나도 없는 경우
            //answer에 입력 순서대로 처음 두 단어 저장
            Iterator<String> it = idxMap.keySet().iterator();
            for (int i = 0; i < 2 && it.hasNext(); i++) {
                if(i == 1) answer.append("\n");
                answer.append(it.next());
            }
        }

        System.out.println(answer);
    }
}

