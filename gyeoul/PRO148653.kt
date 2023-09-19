import kotlin.math.*

class PRO148653 {
    fun solution(storey: Int): Int {
        var num = storey // 변수로 입력받은 값 저장
        var count = 0 // 버튼 입력 횟수 저장 변수
        while (num > 0) { // 입력받은 값이 0이 될때까지
            val now = num % 10 // 입력값을 10으로 모듈러 계산
            count += min(now, 10 - now) // 가까운 10의 자리수까지 이동(카운트 증가)
            if ((now == 5 && num % 100 / 10 >= 5) || now > 5) num += now
            // now 값이 5이며 % 100의 계산값이 50이 넘는경우 혹은 now가 6이 넘는 경우
            // 위쪽에서 내려오는것이 빠르므로 값 추가
            num /= 10 // 입력값을 1/10으로 줄여 다음자리 계산
        }
        return count // 버튼 입력 횟수 반환
    }
    /* BFS 시간초과
    fun solution(storey: Int): Int {
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(Pair(0, 0))
        val size = min(max(storey * 2, 10), 200_000_000) + 1
        val visited = BooleanArray(size)
        var ans = 0
        val list = ArrayList<Int>()
        repeat(9) {
            val now = 10.0.pow(it).toInt()
            list.add(now)
            list.add(now * -1)
        }
        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            if (now.first == storey) {
                ans = now.second
                q.clear()
                continue
            }
            for (i in list) {
                var next = now.first + i
                if (next in 0 until size && !visited[next]) {
                    visited[next] = true
                    q.add(Pair(next, now.second + 1))
                }
            }
        }
        return ans
    }
    */
}