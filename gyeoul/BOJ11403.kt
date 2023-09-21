import java.util.StringTokenizer

class BOJ11403 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        var st: StringTokenizer
        val n = readLine().toInt()
        val arr = Array(n) { IntArray(n) }
        val zeroValue = 100 * 100 + 1
        repeat(n) { i ->
            st = StringTokenizer(readLine())
            repeat(n) { j ->
                var now = st.nextToken().toInt()
                if (now < 1) now = zeroValue
                arr[i][j] = now
            }
        }
        repeat(n) { now ->
            repeat(n) { from ->
                repeat(n) { to ->
                    val edge = arr[from][now] + arr[now][to]
                    if (arr[from][to] > edge) arr[from][to] = edge
                }
            }
        }
        repeat(n) { i ->
            repeat(n) { j ->
                bw.write("${if (arr[i][j] == zeroValue) 0 else 1} ")
            }
            bw.newLine()
        }
        bw.flush()
    }
}