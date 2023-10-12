class BOJ7562 {
    fun main() {
        val testcase = readln().toInt() // 테스트 케이스 갯수
        val q = ArrayDeque<Pair<Int, Int>>() // 인덱스를 담을 큐
        var chess: Array<IntArray> // 나이트의 이동횟수를 저장하는 체스판(추후 재선언)
        repeat(testcase) {
            val l = readln().toInt() // 체스판 크기 입력
            val (sr, sc) = readln().split(" ").map { it.toInt() } // 시작 위치 입력
            val (dr, dc) = readln().split(" ").map { it.toInt() } // 도착 위치 입력
            chess = Array(l) { IntArray(l) } // 체스판 생성
            q.addLast(Pair(sr, sc)) // 시작 위치 부터 8방향 이동 시작
            while (q.isNotEmpty()) {
                val now = q.removeFirst() // 현재 위치
                if (now.first == dr && now.second == dc) { // 현재 위치가 도착 위치라면
                    q.clear() // 큐를 초기화하고
                    continue // while문에서 벗어남
                }
                for (next in arrayOf(1 to 2, 2 to 1, 2 to -1, 1 to -2, -1 to -2, -2 to -1, -2 to 1, -1 to 2)) {
                    // 현재위치에서 (1,2)를 시작으로 시계방향으로 돌며 나이트 이동
                    val (nr, nc) = arrayOf(now.first + next.first, now.second + next.second) // 다음 이동 위치
                    if (nr !in 0 until l || nc !in 0 until l) continue // 이동위치 유효성 검사
                    if (chess[nr][nc] > 0) continue // 처음 방문하는것이 아닐경우 건너뜀
                    chess[nr][nc] = chess[now.first][now.second] + 1 // 다음 위치에 현재위치 +1 을 기록
                    q.addLast(Pair(nr, nc)) // 큐에 다음위치를 추가
                }
            }
            println(chess[dr][dc]) // 체스판에서 목적지에 적힌 숫자를 출력
        }
    }
}