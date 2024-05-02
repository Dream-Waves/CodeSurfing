class BOJ3020 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val (n, h) = br.readLine().split(" ").map { it.toInt() }
        val top = IntArray(h) // 종유석을 저장할 배열
        val bottom = IntArray(h) // 석순을 저장할 배열
        repeat(n) { i ->
            val m = br.readLine().toInt() - 1 // 인덱스를 기반으로 계산하기 위해 - 1
            if (i % 2 == 0) { // 석순과 종유석을 번갈아가며 입력
                bottom[m]++ // 입력받은 위치에 +1
            } else {
                top[m]++
            }
        }
        for (i in h - 1 downTo 1) { // 부분 합 계산을 위해 높이-1부터 1까지 순회
            top[i - 1] += top[i] // 가장 긴 종유석부터 부분합 계산
            bottom[i - 1] += bottom[i] // 가장 긴 석순부터 부분합 계산
        }
        val ans = top.reversed().mapIndexed { i: Int, v: Int ->
            bottom[i] + v // 종유석 배열을 뒤집어 석순 배열에 합산
        }
        val min = ans.min()
        println("$min ${ans.count { it == min }}") // 최소값과 최소값 개수를 출력
    }
}
