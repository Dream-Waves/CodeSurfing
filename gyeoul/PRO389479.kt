@file:Suppress("SpellCheckingInspection")

class PRO389479 {
    /*
    당신은 온라인 게임을 운영하고 있습니다.
    같은 시간대에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요합니다.
    어느 시간대의 이용자가 m명 미만이라면, 서버 증설이 필요하지 않습니다.
    어느 시간대의 이용자가 n x m명 이상 (n + 1) x m명 미만이라면 최소 n대의 증설된 서버가 운영 중이어야 합니다.
    한 번 증설한 서버는 k시간 동안 운영하고 그 이후에는 반납합니다.
    예를 들어, k = 5 일 때 10시에 증설한 서버는 10 ~ 15시에만 운영됩니다.

    하루 동안 모든 게임 이용자가 게임을 하기 위해 서버를 최소 몇 번 증설해야 하는지 알고 싶습니다.
    같은 시간대에 서버를 x대 증설했다면 해당 시간대의 증설 횟수는 x회입니다.
     */

    fun solution(players: IntArray, m: Int, k: Int): Int {
        var answer = 0
        // 시간별 증설된 서버 수
        val server = IntArray(players.size) { 0 }

        // 각 시간대별 플레이어 수 순회
        players.forEachIndexed { i, n ->
            // 현재 필요한 최소 서버 수
            val needed = n / m

            // max(0, i - k + 1) 부터 i - 1 까지 만료되지 않은 과거 시간대(t) 확인
            var current = 0
            for (t in maxOf(0, i - k + 1)..<i) {
                current += server[t]
            }

            // current가 needed보다 적으면 증설
            if (current < needed) {
                val added = needed - current
                answer += added
                server[i] = added
            }
        }

        // 계산된 총 증설 횟수 반환
        return answer
    }
}