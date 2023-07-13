class BOJ9934 {
    data class Node(val left: List<Int>, val mid: Int, val right: List<Int>, var count: Int = 1)

    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        val k = readLine().toInt() // 사용하지 않음
        val arr = readLine().split(" ").map { it.toInt() }

        val q = ArrayDeque<Node>()
        q.add(
            Node(
                arr.subList(0, arr.size / 2), arr[arr.size / 2], arr.subList((arr.size / 2) + 1, arr.size)
            )
        )
        var ln = 1

        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            if (now.count > ln) {
                bw.appendLine()
                ln++
            }
            bw.write("${now.mid} ")
            if (now.left.isNotEmpty() || now.right.isNotEmpty()) {
                q.add(
                    Node(
                        now.left.subList(0, now.left.size / 2),
                        now.left[now.left.size / 2],
                        now.left.subList((now.left.size / 2) + 1, now.left.size),
                        now.count + 1,
                    )
                )
                q.add(
                    Node(
                        now.right.subList(0, now.right.size / 2),
                        now.right[now.right.size / 2],
                        now.right.subList((now.right.size / 2) + 1, now.right.size),
                        now.count + 1,
                    )
                )
            }
        }
        bw.flush()
    }
}
