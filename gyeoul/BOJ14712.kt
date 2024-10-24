fun main() {
    val (n, m) = System.`in`.bufferedReader().use { br ->
        br.readLine().split(" ").map { it.toInt() }
    }
    val arr = Array(n + 1) { BooleanArray(m + 1) }
    var ans = 0

    fun dfs(y: Int, x: Int) {
        when {
            y == n + 1 && x == 1 -> ans += 1 // 모든 행을 돌았다면 가능한것으로 간주
            else -> {
                val (r, c) = when { // 다음번 넴모의 위치 계산
                    x == m -> y + 1 to 1
                    else -> y to x + 1
                }
                if (!(arr[y - 1][x] && arr[y - 1][x - 1] && arr[y][x - 1])) { // 현재 위치에 넴모를 놓을 수 있는지 검사
                    arr[y][x] = true // 가능하다면 배치
                    dfs(r, c)
                    arr[y][x] = false // 놓지 않았을때 계산을 위해 배치 해제
                }
                dfs(r, c) // 놓지 않았을때 계산
            }
        }
    }

    System.out.bufferedWriter().use { bw ->
        dfs(1, 1)
        bw.write("$ans")
        bw.flush()
    }
}
