//class BOJ18290 {
//}
fun main() = with(System.`in`.bufferedReader()) {
    val delta = arrayOf(0 to 0, 1 to 0, 0 to 1, -1 to 0, 0 to -1)
    // 4방향 탐색을 위한 델타 배열
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { IntArray(m) }
    val visited = Array(n) { IntArray(m) }
    val neighbor = IntArray(k)
    // 선택한 칸의 각각의 값을 저장할 배열
    var max = Int.MIN_VALUE // 최대값을 저장할 변수
    var st: java.util.StringTokenizer
    repeat(n) { i ->
        st = java.util.StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt() // 배열에 값 할당
        }
    }

    fun calc(start: Int, depth: Int) {
        if (depth >= k) { // 최대 깊이 초과 시
            max = maxOf(neighbor.sum(), max) // 선택한 값의 합으로 최대값 갱신
            return
        }
        for (i in 0..<n) {
            for (j in start..<m) {
                if (visited[i][j] <= 0) {
                    val d = delta.map { Pair(it.first + i, it.second + j) }
                        .filter { it.first in 0..<n && it.second in 0..<m }
                    // 선택한 칸의 주변 칸을 탐색하는 델타 배열 생성
                    d.forEach { visited[it.first][it.second]++ }
                    // 델타 배열에 있는 칸들을 순회하며 방문수 증가
                    neighbor[depth] = arr[i][j]
                    // 선택한 칸의 값을 배열에 저장
                    calc(i, depth + 1)
                    // 재귀 호출
                    d.forEach { visited[it.first][it.second]-- }
                    // 방문수 감소
                }
            }
        }
    }
    calc(0, 0)
    print(max)
}