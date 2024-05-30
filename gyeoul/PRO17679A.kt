class PRO17679A {
    fun solution(m: Int, n: Int, board: Array<String>): Int {
        fun rotate(str: List<String>): MutableList<MutableList<Char>> {
            val res = Array(n) { CharArray(m) }
            repeat(m) { r ->
                repeat(n) { c ->
                    res[c][r] = str[r][c]
                }
            }
            return res.map { it.toMutableList() }.toMutableList()
        }

        var answer = 0
        val arr = rotate(board.reversed())
        val set = HashSet<Pair<Int, Int>>()

        fun dfs(t: Pair<Int, Int>) {
            if (t.first in arr.indices
                && t.second in arr[t.first].indices
            ) {
                val delta = listOf(0 to 1, 1 to 0, 1 to 1)
                    .map { it.first + t.first to it.second + t.second }
                for ((ny, nx) in delta) {
                    if (
                        ny !in arr.indices ||
                        nx !in arr[ny].indices ||
                        arr[ny][nx] != arr[t.first][t.second]
                    ) return
                }
                set.addAll(delta)
                set.add(t)
            }
        }

        while (true) {
            for (i in arr.indices) {
                for (j in arr[i].indices) {
                    dfs(i to j)
                }
            }
            if (set.isEmpty()) break
            else {
                answer += set.count()
                set.toList().sortedWith { a, b ->
                    if (b.first == a.first) b.second - a.second
                    else b.first - a.first
                }.forEach {
                    arr[it.first].removeAt(it.second)
                }
                set.clear()
            }
            arr.forEach {
                println(it.toString())
            }
        }
        return answer
    }

    fun main() {
        println(solution(6, 6, arrayOf("TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ")))
        println(solution(4, 5, arrayOf("CCBDE", "AAADE", "AAABF", "CCBBF")))
        println(PRO17679B().solution(6, 6, arrayOf("TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ")))
        println(PRO17679B().solution(4, 5, arrayOf("CCBDE", "AAADE", "AAABF", "CCBBF")))
    }
}