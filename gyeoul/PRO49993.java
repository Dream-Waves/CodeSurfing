public class PRO49993 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0; // 정답 변수
        for (var s : skill_trees) { // 각 스킬트리를 검사
            var x = s.chars()
                    // String -> IntStream 변환 (각 글자의 코드값으로 스트림 생성)
                    .mapToObj(c -> skill.indexOf((char) c))
                    // 각 글자 별로 skill 문자열의 어디에 위치하는지 인덱스 추출
                    .filter(i -> i >= 0)
                    // skill 에 존재하지 않는 글자는 제거
                    .toList();
                    // 스트림 종료 후 리스트 변환, 버전에 따라 .collect(Collectors.toList()); 라고 써야할 수도 있음
            int i = 0; // 검사에 필요한 인덱스
            while (i < x.size() && i == x.get(i)) i++;
            // 각 글자들이 0 - 1 - 2 - 3 ... 순으로 위치하는지 인덱스를 증가시키며 검사
            if (i == x.size()) answer++; // 인덱스가 스킬트리 조건을 만족한다면 정답 변수 증가
        }
        return answer; // 정답 변수 반환
    }
}

/*
// 코틀린 :
var answer = 0
skill_trees.map { s ->
    val x = s.map { skill.indexOf(it) }.filter { it != -1 }
    val t = x.filterIndexed { i, v -> i == v }
    if (x == t) answer++
}
return answer
 */
