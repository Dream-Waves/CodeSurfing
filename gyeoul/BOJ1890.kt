class BOJ1890 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = Array(n + 1) { IntArray(n + 1) } // 숫자를 입력받을 공간
        for (i in 1..n) {
            val st = java.util.StringTokenizer(readLine())
            for (j in 1..n) {
                arr[i][j] = st.nextToken().toInt() // 입력
            }
        }
        close()
        val map = hashMapOf<Pair<Int, Int>, Long>()
        // 좌표값을 키값으로 가지는 메모이제이션을 실행할 공간
        fun jump(r: Int, c: Int): Long {
            return when {
                r < 1 -> 0 // 범위를 벗어나면 0 반환
                c < 1 -> 0
                r == 1 && c == 1 -> 1 // 출발 지점에 도착한 경우 1 반환
                map.containsKey(Pair(r, c)) -> map.getValue(Pair(r, c)) // r,c 좌표에 값이 존재한다면 값 반환
                else -> {
                    val value = arr[n - r + 1][n - c + 1]
                    // 현재 좌표가 출발 지점까지 도달할 수 있는지 확인
                    if (value < 1) map[Pair(r, c)] = 0
                    // 도달할 수 없다면 0 반환
                    else map[Pair(r, c)] = jump(r - value, c) + jump(r, c - value)
                    // 도달할 수 있다면 현재위치에 도달 가능한 위치의 값을 합산하여 좌표에 메모이제이션
                    map.getValue(Pair(r, c)) // 현재 좌표의 값을 계산한 뒤 현재 좌표의 값을 반환
                }
            }
        }
        with(System.out.bufferedWriter()) {
            write("${jump(n, n)}") // n, n 위치로 jump 함수 연산 실행 후 반환 값 출력
            flush()
            close()
        }
    }
}