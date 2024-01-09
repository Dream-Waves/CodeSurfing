fun main() = with(System.out.bufferedWriter()) {
    val br = System.`in`.bufferedReader()
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { BooleanArray(m) }
    // 계산을 수행할 배열
    repeat(k) {
        val (sj, si, ej, ei) = br.readLine().split(" ").map { it.toInt() }
        for (i in si..<ei) for (j in sj..<ej) arr[i][j] = true
        // 시작 인덱스부터 끝 인덱스를 순회하며 방문(dfs 불가능) 처리
    }
    fun dfs(i: Int, j: Int): Int {
        arr[i][j] = true // 방문 처리
        var size = 1 // 현재 위치 + 4방향 dfs 값을 저장할 변수
        for ((di, dj) in arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)) {
            val ni = di + i
            val nj = dj + j
            if (ni !in 0..<n || nj !in 0..<m || arr[ni][nj]) continue
            size += dfs(ni, nj) // 방문하지 않은 곳이 있다면 dfs를 실행하고 변수 값 업데이트
        }
        return size
    }
    val ans = mutableListOf<Int>()
    arr.forEachIndexed { i, v -> // i는 인덱스를, v는 각 줄의 배열 할당
        v.indices.forEach { j -> if (!arr[i][j]) ans.add(dfs(i, j)) }
        // 한 줄의 모든 인덱스를 순회
        // 순회하는 칸의 인덱스를 j에 할당하고 i,j칸을 방문하지 않았을때 해당 칸을 기준으로 dfs 실행
        // dfs의 결과를 ans 리스트에 저장
    }
    write("${ans.size}\n")
    ans.sorted().forEach { // ans의 요소를 정렬한 뒤 각 값을 it에 할당
        write("$it ")
    }
    flush()
}
