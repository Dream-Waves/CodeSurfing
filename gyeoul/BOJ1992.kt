class BOJ1992 {
    fun main() {
        val n = readln().toInt()
        val arr = Array(n) { CharArray(n) }
        repeat(n) {
            arr[it] = readln().toCharArray()
        }

        fun check(s: String): String { // 모든 칸이 1111 혹은 0000 일 경우 변환하는 함수
            var value = s
            value = value.replace("(1111)", "1")
            value = value.replace("(0000)", "0")
            return value
        }

        fun recursive(y: Int, x: Int, count: Int): String {
            var res = ""
            val c = count / 2
            for (next in arrayOf(
                intArrayOf(y - c, x - c, c),
                intArrayOf(y - c, x, c),
                intArrayOf(y, x - c, c),
                intArrayOf(y, x, c),
            )) {
                res += if (count > 3) {
                    check(recursive(next[0], next[1], next[2]))
                } else {
                    "${arr[next[0]][next[1]]}"
                }
            }
            return "($res)"
        }

        println(check(recursive(n - 1, n - 1, n)))
    }
}