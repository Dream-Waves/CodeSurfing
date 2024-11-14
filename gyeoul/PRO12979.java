public class PRO12979 {
    // n : 아파트 개수
    // stations : 기지국 현재 위치
    // w : 전파 도달 거리
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int priv = 1; // 이전 계산까지만을 포함해 커버리지를 벗어난 마지막 아파트
        double cover = (w * 2) + 1.0; // 기지국의 커버리지
        for (var station : stations) {
            int min = station - w;
            int max = station + w;
            if (priv < min) { // 효율성을 위해 겹치는 범위 제외 (시간초과 발생)
                int gap = min - priv; // 현재 위치에 기지국을 세웠을때 커버하지 못하는 범위
                answer += (int) Math.ceil(gap / cover); // 커버하지 못하는 범위를 커버리지로 나누어 정답에 합산
            }
            priv = max + 1; // 현재 기지국의 커버리지 까지로 갱신
        }
        if (priv <= n) { // 마지막 아파트까지 커버리지에 들어가지 못한 경우 추가 계산
            int gap = n + 1 - priv;
            answer += (int) Math.ceil(gap / cover);
        }
        return answer;
    }
}
