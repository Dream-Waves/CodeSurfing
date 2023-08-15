import java.util.StringTokenizer

class BOJ1012 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        val t = readLine().toInt() // 테스트 케이스 입력
        var st: StringTokenizer
        repeat(t) {
            st = StringTokenizer(readLine()) // M, N, K 입력
            val m = st.nextToken().toInt()
            val n = st.nextToken().toInt()
            val k = st.nextToken().toInt()
            var ans = 0 // 정답 출력으로 사용할 변수
            val arr = Array(m) { IntArray(n) } // 배추의 위치를 저장할 배열
            repeat(k) {
                st = StringTokenizer(readLine())
                arr[st.nextToken().toInt()][st.nextToken().toInt()] = 1 // 배추 위치 입력
            }
            fun dfs(x: Int, y: Int) { // 인접한 배추를 확인하는 함수
                arr[x][y] = 0
                for (d in arrayOf(intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0))) {
                    val now = intArrayOf(x + d[0], y + d[1])
                    // 땅 바깥으로 벗어나는지, 빈 땅인지 확인
                    if (now[0] !in 0 until m || now[1] !in 0 until n || arr[now[0]][now[1]] <= 0) continue
                    dfs(now[0], now[1]) // 다음 인접한 배추 확인
                }
            }
            repeat(m) { i ->
                repeat(n) { j ->
                    if (arr[i][j] > 0) {
                        dfs(i, j) // 배추가 있고, 아직 검사하지 않은 경우 인접 배추 확인
                        ans++ // 답 증가
                    }
                }
            }
            bw.write("$ans\n") // 버퍼에 정답 출력
        }
        bw.flush() // 버퍼 출력
    }
}
