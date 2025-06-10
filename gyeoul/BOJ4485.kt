class BOJ4485 {
    fun solver(n: Int): Long {
        val arr = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val minimum = Array(n) { LongArray(n) { Long.MAX_VALUE } }
        val q = ArrayDeque<Triple<Int, Int, Long>>() // dfs 실패 bfs 성공
        q.add(Triple(0, 0, 0L)) // but priorityqueue를 사용하여 v값을 정렬하여 사용하는것이 훨씬 효과적 1912ms -> 488ms
        while (q.isNotEmpty()) {
            val (r, c, v) = q.removeFirst()
            val nv = v + arr[r][c]
            for ((nr, nc) in listOf(r + 1 to c, r - 1 to c, r to c + 1, r to c - 1)) {
                if (nr in 0 until n && nc in 0 until n && minimum[nr][nc] > nv) {
                    minimum[nr][nc] = nv // 선 반영
                    q.addLast(Triple(nr, nc, nv))
                }
            }
        }
        return minimum[n - 1][n - 1] + arr[n - 1][n - 1] // 0으로 시작했으므로 마지막값 더해서 반환
    }

    fun main() {
        var ans = ""
        var i = 0
        while (true) {
            i++
            when (val n = readln().toInt()) {
                0 -> break
                else -> ans += "Problem $i: ${solver(n)}\n" // 값 기록
            }
        }
        System.out.bufferedWriter().use {
            it.write(ans)
            it.flush()
        }
    }
}
