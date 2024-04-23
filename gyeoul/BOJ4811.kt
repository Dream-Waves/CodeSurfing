class BOJ4811 {
    /*
     * 1->1
     * 2->2
     * 3->5
     * 4->14
     * 6->132
     * 30->3814986502092304
     */
    fun main() {
        val br = System.`in`.bufferedReader() // 버퍼 입력
        val q = ArrayDeque<Int>() // 입력받은 숫자를 저장할 큐
        while (true) {
            val n = br.readLine().toInt() // 입력
            if (n == 0) break // 0이라면 반복 종료
            q.add(n) // 0이 아니면 큐에 추가
        }
        val catalan = LongArray(31) {
            if (it <= 1) 1 else 0 // 0~1 배열 초기화
        }
        for (i in 2..30) { // 카탈란 수를 계산하기 위한 반복문
            repeat(i) { j ->
                catalan[i] += catalan[j] * catalan[i - j - 1] // 2~30 배열 초기화
            }
        }
        with(System.out.bufferedWriter()) {
            while (q.isNotEmpty()) {
                write("${catalan[q.removeFirst()]}\n") // 큐에서 값을 꺼내 해당하는 값을 배열에서 찾아 버퍼 작성
            }
            flush() // 버퍼 출력
        }
    }
}
