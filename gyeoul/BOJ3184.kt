class BOJ3184 {
    fun main() {
        val ans = intArrayOf(0, 0) // 정답을 저장할 배열
        val animal = IntArray(2) // 한 울타리 내의 양과 늑대의 수를 계산할 배열

        val (r, c) = readln().split(" ").map { it.toInt() }
        val arr = Array(r) { CharArray(c) }
        val visited = Array(r) { BooleanArray(c) }
        repeat(r) {
            arr[it] = readln().toCharArray()
        } // 입력 부분 - 지도와 방문 체크 배열을 생성

        fun dfs(i: Int, j: Int) {
            if (i !in 0 until r || j !in 0 until c) return // i, j의 유효성 검사
            if (visited[i][j]) return
            visited[i][j] = true // 방문 체크
            when (arr[i][j]) {
                'o' -> animal[0]++
                'v' -> animal[1]++
                '#' -> return // 벽일경우 dfs 진행하지 않음
            } // 문자열 체크
            for (delta in arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
                val (di, dj) = delta
                dfs(i + di, j + dj)
            } // 4방향으로 재귀 수행
        } // 각 지역의 양, 늑대의 숫자를 계산하는 함수

        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (arr[i][j] == '#' || visited[i][j]) continue
                animal.fill(0) // animal 배열 초기화
                dfs(i, j)
                if (animal[0] > animal[1]) ans[0] += animal[0] else ans[1] += animal[1] // 정답 배열에 값 입력
            } // 배열을 순회하며 방문하지 않은 벽이 아닌 구역에 대해 dfs 수행
        }

        println("${ans[0]} ${ans[1]}")
    }
}