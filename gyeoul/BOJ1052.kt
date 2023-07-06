import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ1052 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        val p = br.readLine().split(" ").map { it.toInt() }
        bw.write("${calc(p[0], p[1])} ")
        bw.flush()
    }

    fun calc(n: Int, k: Int): Long {
        if (n <= k) {
            return 0
        }
        var next = n.toLong()
        var d: Long
        repeat(k - 1) {
            d = 2
            while (d < next) d *= 2
            next -= (d / 2)
            println("$next $d")
        }
        d = 2
        while (d < next) d *= 2
        return d - next
    }
}
