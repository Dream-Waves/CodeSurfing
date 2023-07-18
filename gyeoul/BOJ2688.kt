class BOJ2688 {
    fun main() = with(System.`in`.bufferedReader()) {
        val arr = Array(65) { LongArray(10) }
        val bw = System.out.bufferedWriter()
        arr[1] = LongArray(10) { 1L }

        for (i in 2..64) {
            for (j in 0..9) {
                for (k in 0..j) {
                    arr[i][j] += arr[i - 1][k]
                }
            }
        }

        val t = readLine().toInt()
        repeat(t) {
            val n = readLine().toInt()
            bw.write("${arr[n].sum()}\n")
        }
        bw.flush()
    }
}
