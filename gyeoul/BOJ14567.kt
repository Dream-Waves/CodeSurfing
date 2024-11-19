fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() } // 과목수 N 선수조건 M 입력
    val pre = Array<MutableList<Int>>(n + 1) { mutableListOf() } // 선수과목 리스트
    val cnt = MutableList(n + 1) { 1 } // 정답 배열

    repeat(m) { // 선수과목 입력
        val (a, b) = readLine().split(" ").map { it.toInt() }
        pre[b].add(a) // 선수과목 등록
    }
    for (i in 1..n) {
        var p = 0 // 현재 과목의 선수과목 중 가장 나중에 듣는 학기
        pre[i].forEach {
            p = if (p < cnt[it]) cnt[it] else p
        }
        cnt[i] += p
    }

    System.out.bufferedWriter().use { bw ->
        cnt.removeAt(0) // 0번 과목은 없으므로 제거하고 출력
        bw.write(cnt.joinToString(" "))
        bw.flush()
    }
}