class PRO72411 {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = mutableListOf<String>()
        val maked = mutableMapOf<String, Int>()
        var now = ""
        fun com(str: String = "", idx: Int = 0) {
            when (str.length) {
                in course -> {
                    when (str) {
                        in maked -> maked[str] = maked[str]!!.plus(1)
                        else -> maked[str] = 1
                    }
                }
            }
            for (i in idx..now.lastIndex) {
                com("$str${now[i]}", i + 1)
            }
        }
        orders.map { o ->
            now = o.toCharArray().sorted().joinToString("")
            com()
        }
        for (c in course) {
            val filter = maked.filter { it.key.length == c }
            if (filter.isNotEmpty()) {
                val max = filter.maxOf { it.value }
                answer.addAll(filter.filter { it.value == max }.map { it.key })
            }
        }
        return answer.sorted().toTypedArray()
    }
}