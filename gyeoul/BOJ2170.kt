class BOJ2170 {
    fun main() {
        val list = mutableListOf<Pair<Int, Int>>()
        repeat(readln().toInt()) {
            list.add(readln().split(" ").map { it.toInt() }.zipWithNext().first()) // x to y 형태로 저장
        }
        list.sortBy { it.first } // x 를 기준으로 정렬
        var l = 0 // 왼쪽 인덱스
        var r = 0 // 오른쪽 인덱스
        var ans = 0 // 합산
        for (i in list.indices) {
            val now = list[i]
            when {
                i == 0 -> { // 첫번째 값으로 인덱스 초기화
                    l = now.first
                    r = now.second
                }

                now.second >= r -> { // r 보다 현재 y 값이 더 클때
                    if (now.first > r) { // 범위를 벗어나면
                        ans += r - l // 합산
                        l = now.first // 왼쪽 인덱스 갱신
                    }
                    r = now.second // 오른쪽 인덱스 갱신
                }
            }
        }
        ans += r - l // 마지막 계산결과 합산
        with(System.out.bufferedWriter()) {
            write("$ans") // 정답 출력
            flush()
        }
    }
}
