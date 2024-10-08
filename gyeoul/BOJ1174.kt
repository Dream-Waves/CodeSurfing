class BOJ1174 {
    fun main() {
        val n = System.`in`.bufferedReader().use { it.readLine().toInt() - 1 }
        val set = (0L..10L).toMutableSet()
        fun gen(prev: Long, max: Long) {
            if (prev !in 0L..9876543210L) return
            for (i in 0..max) {
                set.add(prev * 10 + i)
            }
            if (max == 0L) return
            for (i in 1..max) {
                gen(prev * 10 + i, i - 1)
            }
        }
        (1L..<10L).forEach { gen(0, it) }
        println(set.sorted())
        System.out.bufferedWriter().use { bw ->
            set.sorted().getOrElse(n) { -1 }.let { bw.write("$it") }
            bw.flush()
        }
    }
}
