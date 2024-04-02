class BOJ12026 {
    fun main() {
        val n = readln().toInt()
        val arr = IntArray(n + 1) { 1000001 }
        val str = (" " + readln()).toCharArray()

        fun isNext(a: Int, b: Int): Boolean = str[a] == when (str[b]) {
            // 인덱스 b가 a의 다음 값이 맞는지 반환
            'B' -> 'O'
            'O' -> 'J'
            'J' -> 'B'
            else -> '-'
        }

        fun dp(idx: Int) {
            if (arr[idx] > 1000000) return
            var i = 1 // 현재 인덱스 다음 칸부터 확인
            while (idx + i <= n) {
                if (isNext(idx + i, idx)) arr[idx + i] = kotlin.math.min(arr[idx + i], i * i + arr[idx])
                // 다음 칸이 idx의 다음 값이라면 현재 에너지 값과 계산된 에너지 값 중 작은 값 삽입
                i++
            }
        }

        arr[1] = 0 // 첫칸 값 초기화
        for (i in 1..n) dp(i) // dp 실행
        print(if (arr[n] > 1000000) -1 else arr[n]) // 계산하지 못했을 경우 -1 반환
    }
}
