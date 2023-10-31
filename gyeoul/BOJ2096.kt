class BOJ2096 {
    fun main() {
        val n = readln().toInt()
        val arr = Array(n) { Array(3) { 0 } } // 입력값 받을 배열
        val min = Array(n) { Array(3) { 0 } } // 최소값을 계산할 배열
        val max = Array(n) { Array(3) { 0 } } // 최대값을 계산할 배열
        repeat(n) { r ->
            arr[r] = readln().split(" ").map { it.toInt() }.toTypedArray()
        }
        repeat(3) { // 배열의 첫째줄을 복사
            min[0][it] = arr[0][it]
            max[0][it] = arr[0][it]
        }
        val tmp = ArrayList<Int>() // 계산한 최대 / 최소값을 임시 저장
        for (i in 1 until n) {
            for (j in 0..2) {
                tmp.clear() // 임시배열 초기화
                for (it in j - 1..j + 1) {
                    if (it !in 0..2) continue // 배열 유효성 검증
                    tmp.add(min[i - 1][it] + arr[i][j]) // 한줄 윗 값을 계산하고
                    tmp.add(max[i - 1][it] + arr[i][j]) // 임시배열에 저장
                }
                max[i][j] = tmp.max() // 최소값 저장
                min[i][j] = tmp.min() // 최대값 저장
            }
        }
        println("${max.last().max()} ${min.last().min()}") // 최종적인 최대 / 최소값 출력
    }
}
