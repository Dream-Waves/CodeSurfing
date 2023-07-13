class BOJ9934_3 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        val k = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        var size = arr.size + 1
        repeat(k) {
            for (i in size / 2 - 1 until arr.size step size) {
                bw.write("${arr[i]} ")
            }
            size /= 2
            bw.newLine()
        }
        bw.flush()
    }
}
