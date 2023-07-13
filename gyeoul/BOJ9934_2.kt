class BOJ9934_2 {
    data class Node(val left: Int, val right: Int, var count: Int = 1)

    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        readLine().toInt() // 사용하지 않음
        val arr = readLine().split(" ").map { it.toInt() }

        var ln = 1
        val q = ArrayDeque<Node>()
        q.add(Node(0, arr.size - 1))

        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            val mid = (now.left + now.right) / 2
            if (now.count > ln) {
                bw.appendLine()
                ln++
            }
            bw.write("${arr[mid]} ")
            if (now.left != now.right) {
                q.add(Node(now.left, mid - 1, now.count + 1))
                q.add(Node(mid + 1, now.right, now.count + 1))
            }
        }
        bw.flush()
    }
}
