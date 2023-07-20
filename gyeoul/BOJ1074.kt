class BOJ1074 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        var (n, r, c) = readLine().split(" ").map { it.toInt() }

        var ans = 0
        while (n > 0) { // n이 1이 될 때까지 r,c가 어느 사분면에 속해있는지 파악
            val p = Math.pow(2.0, n.toDouble() - 1).toInt() // 중간값으로 활용할 좌표 계산
            val v = r < p // 세로 판단 ture: 작음 false: 큼
            val h = c < p // 가로 판단 ture: 작음 false: 큼

            var quarter = if (v) 1 else 3
            if (h) quarter--

            // 속한 사분면 - 1 까지의 값을 ans에 추가
            ans += p * p * quarter

            // 다음 루프에서 사용할 좌표로 수정
            if (!v) r -= p
            if (!h) c -= p
            n--
        }
        bw.write("$ans")
        bw.flush()
    }
}
