class BOJ15900 {
    fun main() {
        val n = readln().toInt()
        val arr = ArrayList<ArrayList<Int>>()
        var sum = 0
        val visited = BooleanArray(n)
        repeat(n) { arr.add(ArrayList()) }
        repeat(n - 1) {
            val (a, b) = readln().split(" ").map { it.toInt() - 1 }
            arr[a].add(b)
            arr[b].add(a)
        }
        fun dfs(node: Int, depth: Int) {
            for (next in arr[node]) {
                if (!visited[next]) {
                    visited[next] = true
                    dfs(next, depth + 1)
                }
            }
            if (arr[node].size == 1 && node != 0) {
                sum += depth
            }
        }
        visited[0] = true
        dfs(0, 0)
        print(if (sum % 2 == 0) "No" else "Yes")
    }
}