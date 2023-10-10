class BOJ16918 {
    fun main() {
        val (r, c, n) = readln().split(" ").map { it.toInt() }
        val arr = Array(r) { IntArray(c) }
        repeat(r) { i ->
            val str = readln() // 입력받기
            repeat(c) { j ->
                arr[i][j] = when (str[j]) { // .과 O를 각각 숫자로 변환해 배열에 저장
                    '.' -> 3
                    'O' -> 1
                    else -> -1
                }
            }
        }
        fun printArray() { // 배열 출력함수
            repeat(r) { i ->
                repeat(c) { j ->
                    when (arr[i][j]) {
                        1 -> print("O") // 1일 경우 폭탄
                        else -> print(".") // 아닐경우 빈칸으로 출력
                    }
                }
                println()
            }
        }
        if (n == 1) {
            printArray() // N이 1일경우 행동하지 않으므로 바로 출력
            return // 이후 연산을 하지 않고 종료
        }
        fun change(from: Int, to: Int, flag: Boolean) { // from 에서 to로 숫자를 바꾸며 폭발 계산
            repeat(r) { i ->
                repeat(c) { j ->
                    if (arr[i][j] == from) { // 폭탄 위치에서 4방향을 돌며 폭발 위치 계산
                        for (v in arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))) {
                            if (i + v.first !in 0 until r || j + v.second !in 0 until c) continue
                            if (arr[i + v.first][j + v.second] == from) continue
                            arr[i + v.first][j + v.second] = to
                        }
                        if (flag) arr[i][j] = to // 입력받은 위치를 to값으로 바꿀지 여부
                    }
                }
            }
        }
        change(1, 0, true) // 폭발 연산
        change(3, -1, false) // 폭탄이 있는 위치에 폭발이 일어나는 경우를 계산
        repeat(r) { i ->
            repeat(c) { j ->
                when (n % 4) {
                    0, 2 -> {
                        arr[i][j] = 1 // 모든 칸이 폭탄
                    }

                    1 -> {
                        if (arr[i][j] == 0) {
                            arr[i][j] = 1 // 빈칸을 모두 폭탄으로 채운다
                        }

                    }

                    3 -> {
                        if (arr[i][j] == 3) {
                            arr[i][j] = 1 // 폭발에 휘말리는 칸을 제외한 나머지 칸을 폭탄으로 채운다
                        }
                    }
                }
            }
        }
        printArray()
    }
}