import java.util.LinkedHashSet;
import java.util.Set;

public class PRO49994 {
    public int solution(String dirs) {
        int r = 0; // 현재 y 좌표
        int c = 0; // 현재 x 좌표
        Set<String> record = new LinkedHashSet<>(); // 지나간 경로 저장
        char[] chars = dirs.toCharArray();
        for (char k : chars) { // 각 글자별 계산
            int pr = r; // 이전 값 저장
            int pc = c;
            switch (k) {
                case 'U': // U 일때
                    if (r + 1 <= 5) r++; // 5 미만이라면 +1
                    break;
                case 'D': // D 일때
                    if (r - 1 >= -5) r--; // -5 초과라면 -1
                    break;
                case 'R': // R 일때
                    if (c + 1 <= 5) c++; // 5 미만이라면 +1
                    break;
                case 'L': // L 일때
                    if (c - 1 >= -5) c--; // -5 초과라면 -1
                    break;
            }
            if (pr == r && pc == c) continue; // 이동하지 못했다면 건너뜀
            var a = String.format("%d%d%d%d", pr, pc, r, c); // 경로 문자열 1
            var b = String.format("%d%d%d%d", r, c, pr, pc); // 경로 문자열 2
            if (record.contains(a) || record.contains(b)) continue; // 문자열이 중복될경우 건너뜀
            record.add(a); // 경로 추가
        }
        return record.size(); // 지나온 경로 갯수 반환
    }

    public static void main(String[] args) {
        System.out.println(new PRO49994().solution("ULURRDLLU"));
        System.out.println(new PRO49994().solution("LULLLLLLU"));
    }
}
