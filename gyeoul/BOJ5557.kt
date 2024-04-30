class BOJ5557 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val list = br.readLine().split(" ").map { it.toInt() }
        val arr = Array(n) { LongArray(21) } // 0~20 내의 값을 저장하기 위해 20칸의 배열 생성
        repeat(n) { i ->
            when (i) {
                0 -> arr[i][list[i]] = 1 // 0일때 값 초기화
                else -> for (j in arr[i].indices) {
                    for (next in listOf(1, -1).map { it * list[i] + j }) { // list의 값을 이전값에 +, - 후 검사
                        if (next in arr[i].indices && arr[i - 1][j] != 0L) { // 이전 값이 0이 아니라면
                            arr[i][next] += arr[i - 1][j] // 현재위치에 이전 값을 추가
                        }
                    }
                }
            }
        }
        print(arr[n - 2][list[n - 1]]) // 저장된 값을 출력 / 마지막 값은 영향을 끼치지 않기 때문에 n-1 인덱스에서 추가적으로 -1
    }
}
