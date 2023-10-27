import kotlin.math.*

class BOJ2792 {
    fun main() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val arr = ArrayList<Int>()
        repeat(m) {
            arr.add(readln().toInt())
        }
        fun calc(median: Int): Int {
            var sum = 0
            repeat(m) {
                sum += arr[it] / median
                // 모든 인원은 한종류의 보석만을 가질 수 있으므로
                // 각 보석을 인원수로 나누었을때 모든 보석을 총 몇명이 나누어 가지는지 계산
            }
            return sum
        }
        var (less, more) = arrayOf(1, arr.max()) // 인원수의 최대, 최소
        var ans = more
        while (less <= more) {
            val median = (less + more) / 2
            if (calc(median) > n) {
                less = median + 1 // 결과값이 인원수보다 많을경우 최소 인원 증가
            } else {
                more = median - 1 // 적거나 같을 경우 최대 인원 감소
                ans = min(ans, median) // 같을 경우를 대비해 답을 갱신
            }
        }
        println(ans)
    }
}