class BOJ2660 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val bw = System.`out`.bufferedWriter()
        val m = br.readLine().toInt()
        val arr = Array(m + 1) { IntArray(m + 1) } // 친구관계 배열
        val relation = IntArray(m + 1) // 관계 순위 배열
        val visited = BooleanArray(m + 1) // 방문체크 배열
        val q = ArrayDeque<Pair<Int, Int>>() // 최단 친구거리 계산 큐
        var minval = Int.MAX_VALUE // 최소값 저장 변수
        while (true) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() } // 친구관계 입력
            if (a == -1 && b == -1) break // -1을 만나면 종료
            arr[a][b] = b // 배열에 입력
            arr[b][a] = a
        }
        for (i in 1..m) { // 1번부터 관계 최소값 구하기
            visited.fill(false)
            visited[i] = true
            q.addAll(arr[i].filter { it > 0 }.map { Pair(it, 1) }) // 친구인 사람만을 큐에 추가
            while (q.isNotEmpty()) {
                val now = q.removeFirst()
                if (visited[now.first]) continue
                visited[now.first] = true
                relation[i] = relation[i].coerceAtLeast(now.second) // 친구 거리 측정
                for (j in arr[now.first].filter { it > 0 }) q.add(Pair(j, now.second + 1))
            }
            minval = minval.coerceAtMost(relation[i]) // 최소값 갱신
        }
        val ans = relation.mapIndexed { i, r -> Pair(i, r) }.filter { it.second == minval }.sortedBy { it.first }
        // 거리가 제일 가까운 사람들을 찾고 배열에 저장
        bw.write("$minval ${ans.size}\n")
        for (i in ans.indices) bw.write("${ans[i].first} ")
        bw.flush()
    }
}
