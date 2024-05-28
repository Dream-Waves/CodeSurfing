class BOJ2166 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val points = mutableListOf<Pair<Long, Long>>()
        var sum = 0L
        repeat(n) {
            points.add(br.readLine().split(" ").map { it.toLong() }
                .zipWithNext { a, b -> a to b }.first())
        }
        repeat(n) {
            val next = (it + 1) % n
            sum += points[it].first * points[next].second - points[it].second * points[next].first
        }
        print("%.1f".format(kotlin.math.abs(sum) / 2.0))
    }
}
