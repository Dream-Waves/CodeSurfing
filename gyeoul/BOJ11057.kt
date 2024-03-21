class BOJ11057 {
    fun main() {
        val n = readln().toInt()
        val m = 10
        val d = 10007
        val arr = Array(n + 1) { IntArray(m + 1) } // 10 * n 크기의 배열 생성
        for (i in 1..n) { // i = 자리수
            for (j in 1..m) { // j = 들어갈 숫자
                if (i == 1) arr[i][j] = j // 첫번째 자리일 경우 기본값 설정
                else arr[i][j] = (arr[i][j - 1] + arr[i - 1][j]) % d // 위치에 올 수 있는 경우의 수 계산
                // 계산값을 모듈러 연산 후 저장
            }
        }
        print(arr[n][m] % d)
    }
}