class PRO154540 {
    fun solution(maps: Array<String>): IntArray {
        val visited = Array(maps.size + 1) { BooleanArray(maps[0].length + 1) }
        val answer = mutableListOf<Int>()

        maps.forEachIndexed { i, r ->
            r.forEachIndexed { j, v ->
                when (v) {
                    'X' -> visited[i][j] = true
                }
            }
        }

        fun dfs(sr: Int, sc: Int): Int {
            if (visited[sr][sc]) return 0
            visited[sr][sc] = true
            var sum = "${maps[sr][sc]}".toInt()
            for ((nr, nc) in listOf(sr + 1 to sc, sr - 1 to sc, sr to sc + 1, sr to sc - 1)) {
                if (nr !in 0..maps.lastIndex || nc !in 0 until maps[0].length) continue
                sum += dfs(nr, nc)
            }
            return sum
        }

        for (i in maps.indices) {
            for (j in 0 until maps[0].length) {
                answer.add(dfs(i, j))
            }
        }

        if (answer.none { it > 0 }) return intArrayOf(-1)
        return answer.filter { it > 0 }.sorted().toIntArray()
    }
}