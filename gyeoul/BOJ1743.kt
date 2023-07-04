import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

//class BOJ1743 {

lateinit var arr: Array<BooleanArray>
lateinit var visited: Array<BooleanArray>
lateinit var data: List<Int>
var maxValue = 0
private fun check(y: Int, x: Int, count: Int = 1) {
    for (next in arrayOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))) {
        val nextY = next.first + y
        val nextX = next.second + x
        if (nextY !in 0 until data[0] || nextX !in 0 until data[1]) continue

    }
}

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    data = br.readLine().split(" ").map { it.toInt() }

    arr = Array(data[0]) { BooleanArray(data[1]) { false } }
    visited = Array(data[0]) { BooleanArray(data[1]) { false } }
    var ans = 0

    repeat(data[2]) {
        val now = br.readLine().split(" ").map { it.toInt() }
        arr[now[0] - 1][now[1] - 1] = true
    }

    repeat(data[0]) { y ->
        repeat(data[1]) { x ->
            if (arr[y][x] && !visited[y][x]) {
                check(y, x)
            }
        }
    }

    bw.write("$ans")
    bw.flush()
}
//}

