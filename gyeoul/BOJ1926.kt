class BOJ1926 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        // N, M 입력
        val map = Array(n) {
            val st = java.util.StringTokenizer(readLine(), " ")
            Array(m) { st.nextToken().toInt() == 1 }
            // 입력받은 0, 1에 따라 Boolean 2차원 배열에 저장
        }
        val visited = Array(n) { Array(m) { false } }
        var size = 0 // 넓이를 계산할 변수
        var count = 0 // 그림의 개수를 계산할 변수

        fun next(i: Int, j: Int) = listOf(i + 1 to j, i to j + 1, i - 1 to j, i to j - 1)
            .filter { it.first !in 0..<n || it.second !in 0..<m }
        // 다음 방문할 인덱스를 반환하는 함수, 유효한 값을 필터링 한다

        fun dfs(i: Int, j: Int, s: Int = 0): Int {
            visited[i][j] = true
            var calc = 1 // 넓이를 계산할 변수
            for ((ni, nj) in next(i, j)) {
                if (!visited[ni][nj] && map[ni][nj]) calc += dfs(ni, nj)
                // 유효한 위치일 경우 재귀를 통해 이후 넓이를 변수에 추가
            }
            return calc + s // 계산한 넓이를 반환
        }
        repeat(n) { i ->
            repeat(m) { j ->
                if (!visited[i][j] && map[i][j]) {
                    // 탐색하지 않은 그림을 만나면 탐색 진행
                    count++
                    size = maxOf(dfs(i, j), size)
                    // 저장된 크기와 탐색한 크기 중 큰 값을 저장
                }
            }
        }
        with(System.out.bufferedWriter()) {
            // 결과 출력
            write("$count\n$size")
            flush()
        }
    }
}
