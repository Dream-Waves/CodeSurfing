import kotlin.math.abs

class BOJ2467 {
    fun main() {
        data class Store(var v: Long, var l: Int, var r: Int)

        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt() // 용액 개수 입력
        val arr = br.readLine().split(" ").map { it.toLong() } // 용액 입력

        var r = n - 1 //오른쪽 인덱스
        val s = Store(arr[0] + arr[r], 0, r) // 값 저장공간
        for (l in 0..<n) { // 왼쪽 인덱스 - 1씩 증가
            while (l < r - 1 && abs(arr[l] + arr[r]) > abs(arr[l] + arr[r - 1])) r--
            // 현재 l 값에 어떤 값을 더해야 0에 가까워지는지 r을 1씩 감소시키며 탐색
            if (r <= l) break // l이 r보다 크거나 같아진다면 반복 종료
            val v = abs(arr[l] + arr[r])
            if (v < s.v) { // 저장값 갱신
                s.v = v
                s.l = l
                s.r = r
            }
        }
        with(System.out.bufferedWriter()) {
            write("${arr[s.l]} ${arr[s.r]}") // 정답 출력
            flush()
        }
    }
}
