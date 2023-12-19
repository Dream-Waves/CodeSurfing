class BOJ10844 {
    fun main() {
        val n = readln().toInt()
        val arr = Array(n) { LongArray(10) }
        var ans = 0L // 정답을 계산할 변수
        for (i in 1..9) { // 시작점 설정
            arr[0][i] = 1 // 0으로 시작할 수 없으므로 0을 제외한 나머지 공간에 1 할당
        }
        for (i in 1 until n) {
            for (j in 0..9) {
                arr[i][j] = (arr[i - 1].getOrElse(j - 1) { 0L } + arr[i - 1].getOrElse(j + 1) { 0L }) % 1_000_000_000
                // 각 자리마다 올 수 있는 경우의 수 계산
                // [i-1][j+1]과 [i-1][j-1]의 경우의 수를 더하고
                // 0과 9의 경우에는 각각 [i-1][1], [i-1][8]만을 참조한다
                // 너무 값이 커지는 것을 막기 위해 각 계산마다 모듈러 계산을 추가로 진행한다
            }
        }
        arr.last().map { ans += it } // 정답 계산
        print(ans % 1_000_000_000) // 모듈러 계산 후 정답 출력
    }
}
