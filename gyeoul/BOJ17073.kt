class BOJ17073 {
    fun main() {
        val (route, water) = System.`in`.bufferedReader().run {
            val (n, w) = readLine().split(" ").map { it.toInt() }
            val arr = List(n + 1) { mutableSetOf<Int>() }
            repeat(n - 1) {
                val (a, b) = readLine().split(" ").map { it.toInt() }
                arr[b].add(a)
                arr[a].add(b)
            }
            Pair(arr.map { it.toList() }, w)
        }
        System.out.bufferedWriter().run {
            write(String.format("%f", water.toDouble() / process(route)))
            flush()
        }
    }

    fun process(route: List<List<Int>>): Int {
        var count = 0
        val q = ArrayDeque(listOf(1))
        val visit = BooleanArray(route.size + 1)
        visit[1] = true

        while (q.isNotEmpty()) {
            val now = q.removeFirst()
            val list = route[now].filter { !visit[it] }
            when {
                list.isEmpty() -> count++
                else -> for (c in list) {
                    visit[c] = true
                    q.add(c)
                }
            }
        }

        return count
    }


    fun main2() {
        val ans = System.`in`.bufferedReader()
            .run {
                var leaf = 0
                val (n, w) = readLine().split(" ").map { it.toInt() }
                val arr = IntArray(n + 1)
                repeat(n - 1) {
                    readLine().split(" ").map { it.toInt() }
                        .let { (a, b) ->
                            arr[a]++
                            arr[b]++
                        }
                }
                for (i in 2..n) when (arr[i]) {
                    1 -> leaf++
                }
                w.toDouble() / leaf
            }
        System.out.bufferedWriter().run {
            write("$ans")
            flush()
        }
    }
}