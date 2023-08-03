class BOJ1495 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (n, s, m) = readLine().split(" ").map { it.toInt() }
        val limit = 0..m
        val arr = readLine().split(" ").map { it.toInt() }.toTypedArray()
        val ans = Array(n + 1) { BooleanArray(m + 1) { false } }
        // n+1 * m+1 의 불리언 배열을 생성

        fun calc(idx: Int, vol: Int) {
            ans[idx][vol] = true
            val next = idx + 1
            if (next > n) return // n+1칸에 값을 채우고 재귀 종료
            for (v in intArrayOf(vol - arr[idx], vol + arr[idx])) {
                if (v !in limit || ans[next][v]) continue // next 인덱스가 유효한지, 다음 값이 유효한지 검사
                calc(next, v) // 재귀
            }
        }
        calc(0, s)

        for (i in m downTo 0) {
            if (ans[n][i]) { // 뒤에서부터 검사하여 가장 큰 인덱스의 true값 탐색
                print(i)
                return
            }
        }
        print(-1)
    }
}

// 큐로 시도 - 메모리 초과
//fun main() = with(System.`in`.bufferedReader()) {
//    val q = ArrayDeque<Pair<Int, Int>>() // value,index
//    var st = StringTokenizer(readLine())
//
//    val n = st.nextToken().toInt()
//    val s = st.nextToken().toInt()
//    val limit = 0..st.nextToken().toInt()
//
//    val arr = IntArray(n)
//    var ans = -1
//    st = StringTokenizer(readLine())
//    repeat(n) {
//        arr[it] = st.nextToken().toInt()
//    }
//
//    q.add(Pair(s - arr[0], 0))
//    q.add(Pair(s + arr[0], 0))
//
//    while (q.isNotEmpty()) {
//        val now = q.removeFirst()
//        if (now.first !in limit) continue
//        val next = now.second + 1
//        if (next == n) {
//            ans = ans.coerceAtLeast(now.first)
//            continue
//        }
//        q.add(Pair(now.first + arr[next], next))
//        q.add(Pair(now.first - arr[next], next))
//    }
//
//    print(ans)
//}
