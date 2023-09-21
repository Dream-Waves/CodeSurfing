import java.util.*

/*
[S1] 카드 합체 놀이
 - 우선순위 큐
 - 자료형
 */
class BOJ15903 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val q = PriorityQueue<Long>()
        val st = StringTokenizer(readLine())
        var ans = 0L
        repeat(n) {
            q.add(st.nextToken().toLong()) // Int의 범위보다 작은 수가 입력되지만 계산 후 Int를 넘어서는 경우가 발생한다
        }
        repeat(m) {
            val now: Long = q.remove() + q.remove() // 가장 작은 숫자의 카드 추출 후 덧셈
            q.add(now) // 더한 카드를 다시 카드 목록에 추가
            q.add(now)
        }
        while (q.isNotEmpty()) {
            ans += q.remove() // 모든 카드의 합 계산
        }
        bw.write("$ans")
        bw.flush()
        bw.close()
        close()
    }
}
