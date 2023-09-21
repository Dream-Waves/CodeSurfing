import java.util.PriorityQueue

class PRO155651 {
    fun solution(book_time: Array<Array<String>>): Int {
        // 시간순 정렬을 위해 PriorityQueue 활용
        val inQ = PriorityQueue<Int>() // 입실 큐
        val outQ = PriorityQueue<Int>() // 퇴실 큐
        var count = 0 // 현재 입실 인원
        var answer = 0 // 최대 입실 인원
        for (t in book_time) { // 시간을 분으로 환산하여 큐에 추가
            val i = t[0].split(":").map { it.toInt() }
            val o = t[1].split(":").map { it.toInt() }
            inQ.add(i[0] * 60 + i[1]) // 입실 큐에 추가
            outQ.add(o[0] * 60 + o[1] + 10) // 퇴실 큐에 추가 (+ 청소 시간)
        }
        while (inQ.isNotEmpty() && outQ.isNotEmpty()) {
            if (inQ.first() >= outQ.first()) { // 두 큐중 가장 빠른 시간의 값을 계산
                outQ.poll()
                count-- // 퇴실 큐에서 꺼내온 값일 경우 현재 입실 인원 감소
            } else {
                inQ.poll()
                count++ // 입실 큐에서 꺼내온 값일 경우 현재 입실 인원 증가
            }
            if (count > answer) answer = count // 최대값 갱신
        } // 최대값이 필요한 것 이므로 두 큐중 하나라도 비었을 경우 계산 종료
        return answer
    }
}