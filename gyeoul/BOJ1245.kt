class BOJ1245 {
    fun main() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val arr = Array(n) { IntArray(m) }
        val visited = Array(n) { BooleanArray(m) }
        var count = 0
        var peak: Boolean
        repeat(n) { i ->
            val st = java.util.StringTokenizer(readln())
            repeat(m) { j ->
                arr[i][j] = st.nextToken().toInt()
            }
        }

        fun checkPeak(i: Int, j: Int) {
            for (next in arrayOf(0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1)) {
                val ni = next.first + i // 다음 확인할 칸의 인덱스
                val nj = next.second + j
                if (ni !in 0 until n || nj !in 0 until m) continue // 다음 위치 유효성 검증
                if (arr[i][j] < arr[ni][nj]) peak = false // 입력받은 i, j에 위치한 값보다 높은값이 옆에 있을 경우 카운트 증가 x
                if (visited[ni][nj]) continue
                if (arr[i][j] != arr[ni][nj]) continue // 다음 위치의 값이 같은 경우에만 DFS실행
                visited[ni][nj] = true
                checkPeak(ni, nj)
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (visited[i][j]) continue
                peak = true // 초기화
                checkPeak(i, j) // 현재 위치의 값이 주변에서 가장 높은 값인지 확인
                if (peak) count++ // 현재 값이 가장 높은 값이라면 카운트 증가
            }
        }
        print(count)
    }
}