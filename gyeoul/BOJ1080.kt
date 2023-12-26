class BOJ1080 {
    fun main() {
        val dir =
            arrayOf(0 to 0, 0 to 1, 0 to 2, 1 to 0, 1 to 1, 1 to 2, 2 to 0, 2 to 1, 2 to 2) // 3x3 공간을 뒤집기 위한 인덱스 오프셋
        var count = 0 // 정답을 저장할 변수
        val (n, m) = readln().split(' ').map { it.toInt() } // n,m 입력
        val check = n < 3 || m < 3 // 입력받은 공간이 3x3보다 작을 경우
        var flag = false // 뒤집는것이 가능하지 않을 경우 사용할 변수
        fun setDiff(): Array<BooleanArray> {
            val arr = Array(n) { Array(m) { BooleanArray(2) } } // 행렬 A,B를 저장할 배열
            val res = Array(n) { BooleanArray(m) } // A,B를 xor 계산하여 저장할 배열
            repeat(2) { d ->
                repeat(n) { i ->
                    readln().mapIndexed { j, c ->
                        arr[i][j][d] = c == '1' // 입력값이 1일경우 true / 0일경우 false
                    }
                }
            }
            for (i in 0 until n) {
                for (j in 0 until m) {
                    val now = arr[i][j].reduce { a, b -> a.xor(b) } // A,B xor 계산
                    if (check && now) flag = true // 3x3 공간보다 작을경우 + A,B의 값이 다를 경우
                    res[i][j] = now // xor 계산값 저장
                }
            }
            return res
        }

        val diff = setDiff()
        for (i in 0..n - 3) { // xor계산된 배열을 순회하며 true값을 만날때마다
            for (j in 0..m - 3) { // true값의 위치에서 3x3 크기만큼을 뒤집는다
                if (diff[i][j]) { // true일 경우
                    for (next in dir) { // 3x3 뒤집기
                        val ni = next.first + i
                        val nj = next.second + j
                        diff[ni][nj] = diff[ni][nj].xor(true)
                    }
                    count++ // 정답 횟수 증가
                }
            }
        }
        repeat(n) { if (!flag) flag = diff[it].reduce { a, b -> a.or(b) } } // 각 줄에 true가 남아있는지 검사
        println(if (flag) -1 else count) // true가 존재하면 -1 출력 A에서 B로 완벽히 바꿀수 있다면 정답 출력
    }
}
