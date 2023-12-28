import java.util.PriorityQueue

class BOJ1927 {
    fun main() {
        val n = readln().toInt() // 라인 수 입력
        val heap = PriorityQueue<Int> { o1, o2 -> o1 - o2 } // 힙으로 사용할 우선순위 큐
        val str = System.out.bufferedWriter() // 출력 버퍼
        repeat(n) {
            when (val now = readln().toInt()) { // 라인마다 입력을 받으며
                0 -> str.write("${if (heap.isEmpty()) 0 else heap.remove()}\n")
                // 0 일 경우 제일 작은 값을 버퍼에 출력
                else -> heap.add(now)
                // 0이 아닐 경우 힙에 값을 추가
            }
        }
        str.flush() // 버퍼 출력
    }
}