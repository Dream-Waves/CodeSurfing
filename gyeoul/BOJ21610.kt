//class BOJ21610 {
//}
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val priv = Array(n) { BooleanArray(n) }
    val arr = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    val delta = arrayOf(0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1, 1 to 0, 1 to -1)
    val cloud = mutableListOf(n - 1 to 0, n - 1 to 1, n - 2 to 0, n - 2 to 1)

    fun idx(): Pair<Int, Int> = readLine().split(" ")
        .map { it.toInt() }.let { (d, x) ->
            delta[d - 1].let { (r, c) ->
                r * x to c * x
            }
        }

    fun copy(list: List<Pair<Int, Int>>) = list.forEach { (r, c) ->
        arr[r][c] += delta.count { (y, x) ->
            y != 0 && x != 0 && r + y in 0..<n && c + x in 0..<n && arr[r + y][c + x] > 0
        }
    }

    fun rain(cloud: List<Pair<Int, Int>>) = cloud.forEach { (r, c) ->
        priv[r][c] = true
        arr[r][c]++
    }

    fun move(d: Pair<Int, Int>): List<Pair<Int, Int>> = cloud.map { (r, c) ->
        (r + d.first + n * 50) % n to (c + d.second + n * 50) % n
    }

    fun gen() {
        cloud.clear()
        for (i in 0..<n) {
            for (j in 0..<n) {
                if (!priv[i][j] && arr[i][j] >= 2) {
                    arr[i][j] -= 2
                    cloud.add(i to j)
                }
            }
            priv[i] = BooleanArray(n)
        }
    }

    repeat(m) {
        move(idx()).let {
            rain(it)
            copy(it)
        }
        gen()
    }

    with(System.out.bufferedWriter()) {
        write("${arr.sumOf { it.sum() }}")
        flush()
    }
}
