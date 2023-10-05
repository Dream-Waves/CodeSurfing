class BOJ1189 {
    fun main() {
        val (r, c, k) = readln().split(" ").map { it.toInt() }
        val arr = Array(r) { CharArray(c) } // . / T를 입력받아 저장하는 배열
        val visited = Array(r) { BooleanArray(c) } // 방문 처리 배열
        var count = 0 // 카운트가 K일때 도달하는 경우의 수
        repeat(r) {
            arr[it] = readln().toCharArray() // 배열 입력
        }
        fun dfs(y: Int, x: Int, cnt: Int) {
            if (y !in 0 until r || x !in 0 until c) return
            // y, x가 유효한 값인지 체크
            if (arr[y][x] == 'T' || visited[y][x] || cnt > k) return
            // 벽이거나, 방문했거나, 카운트가 K보다 클 경우 건너뜀
            if (cnt == k && y == 0 && x == c - 1) {
                count++ // K번째에 좌측 상단에 도달시 카운트 증가
                return
            }
            visited[y][x] = true // 방문처리
            dfs(y - 1, x, cnt + 1) // 현재 위치에서 위쪽 방문
            dfs(y, x + 1, cnt + 1) // 현재 위치에서 오른쪽 방문
            dfs(y, x - 1, cnt + 1) // 현재 위치에서 왼쪽쪽 방문
            dfs(y + 1, x, cnt + 1) // 현재 위치에서 아래쪽 방문
            visited[y][x] = false // 다른 루트를 찾기 위해 방문처리 취소
        }
        dfs(r - 1, 0, 1) // 좌측 하단부터 방문 시작
        print(count)
    }
}