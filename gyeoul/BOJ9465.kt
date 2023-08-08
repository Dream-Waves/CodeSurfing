import java.util.StringTokenizer

class BOJ9465 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        val t = readLine().toInt()
        var arr: Array<IntArray>

        repeat(t) {
            val n = readLine().toInt()
            val st = arrayOf(StringTokenizer(readLine()), StringTokenizer(readLine()))
            arr = Array(n + 2) { IntArray(2) }

            // 입력 로직
            repeat(n) { i ->
                repeat(2) { j ->
                    arr[i + 1][j] = st[j].nextToken().toInt()
                }
            }

            // 계산 로직
            // 1, 2, 미선택 세가지 선택지 중 하나를 선택하여 각 위치에서
            // 현재 위치에 올 수 있는 가장 큰 값을 배열에 다시 넣는다.
            // 이전에 1을 선택했다면 2, 미선택 2를 선택한다면 1, 미선택이
            // 올 수 있으므로 두가지 경우의 수만 계산하였다.
            for (i in 2..n + 1) {
                arr[i][0] += Math.max(arr[i - 1][1], arr[i - 2][1])
                arr[i][1] += Math.max(arr[i - 1][0], arr[i - 2][0])
            }

            // 버퍼에 결과값 쓰기
            bw.write("${arr[n + 1].max()}\n")
        }
        bw.flush()
    }
}
