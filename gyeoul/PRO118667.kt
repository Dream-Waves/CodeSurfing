class PRO118667 {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val limit = queue1.size * 4
        val q1 = queue1.toCollection(ArrayDeque()) // queue1을 큐 형식으로 변환
        val q2 = queue2.toCollection(ArrayDeque()) // queue2를 큐 형식으로 변환
        var s1: Long = queue1.sumOf { it.toLong() } // queue1의 합을 계산
        var s2: Long = queue2.sumOf { it.toLong() } // queue2의 합을 계산
        var answer = 0
        while (s1 != s2) {
            if (limit < answer) { // 계산횟수를 넘었을 경우
                answer = -1 // -1로 answer를 변경하고
                break // 루프 종료
            }
            if (s1 > s2) { // queue1의 합이 큰 경우
                val m = q1.removeFirst() // q1에서 원소 1개를 제거 후
                s1 -= m // queue1 합 재계산
                s2 += m // queue2 합 재계산
                q2.add(m) // q2에 원소 추가
            } else { // queue2의 합이 큰 경우
                val m = q2.removeFirst()
                s1 += m
                s2 -= m
                q1.add(m)
            }
            answer++ // 작업 횟수 증가
        }
        return answer
    }
}
