class BOJ11052 {
    fun main() {
        val n = readln().toInt() // 구매할 카드 갯수
        val dp = IntArray(n + 1)
        val p = listOf(0) + readln().split(" ").map { it.toInt() }
        // 입력, 인덱스를 벗어날때 0을 제공하기 위해 0번째 인덱스에 0 삽입
        for (i in 1..n) {
            for (j in 1..i) {
                dp[i] = maxOf(dp[i], dp[i - j] + p[j])
                // i - j + j = i 이므로
                // 1부터 현재값 i까지 순회하며 i개의 카드일때 최대값 계산
            }
        }
        print(dp[n]) // n번째 값 출력
    }
}
