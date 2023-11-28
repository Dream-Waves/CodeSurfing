class BOJ2447 {
    fun main() {
        val n = readln().toInt() // 입력
        val arr = Array(n) { CharArray(n) { '*' } } // N * N 의 캐릭터 배열 생성
        var calc = 1
        for (k in 1..8) {
            if (calc >= n) break // 만약 n보다 계산할 값이 크면 루프 탈출
            val calc2 = calc + calc // 계산값 선 계산
            val calc3 = calc2 + calc
            for (i in 0 until n step calc3) { // 배열을 순회하며 알맞은 위치의 *을 공백으로 변경
                for (j in 0 until n step calc3) {
                    val ri = i + calc until i + calc2 // k에 따라 3^(k-1)칸 오프셋을 주고,
                    val rj = j + calc until j + calc2 // 3^(k-1) * 3^(k-1) 만큼의 영역을 범위로 생성
                    for (ni in ri) for (nj in rj) arr[ni][nj] = ' ' // 생성한 범위를 순회하며 *을 빈 문자로 변경
                }
            }
            calc *= 3 // 다음 계산할 값 갱신
        }
        repeat(n) {
            println(arr[it].concatToString()) // 각 줄의 캐릭터 배열을 join해 출력
        }
    }
}
