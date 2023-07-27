import java.util.StringTokenizer

class BOJ1446 {
    fun main() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        val map = IntArray(d + 1) { Int.MAX_VALUE }
        val loc = Array<MutableList<Pair<Int, Int>>>(d + 1) { mutableListOf() }

        for (i in 0 until n) {
            st = StringTokenizer(readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            val cost = st.nextToken().toInt()
            if (a !in 0..d || b !in 0..d) continue
            loc[b].add(Pair(a, cost))
        }
        map[0] = 0
        for (i in 1..d) {
            if (loc[i].isEmpty()) map[i] = map[i - 1] + 1
            else {
                for (v in loc[i]) {
                    map[i] = map[i].coerceAtMost((map[i - 1] + 1).coerceAtMost(map[v.first] + v.second))
                }
            }
        }
        print(map[d])
    }
}
