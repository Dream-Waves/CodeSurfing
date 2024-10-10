class BOJ1092 {
    fun main() {
        val (c, b) = System.`in`.bufferedReader().use { b -> // 입력
            fun r(): List<Int> {
                b.readLine().toInt() // 크레인 / 박스 개수 (미사용)
                return b.readLine().split(" ").map { it.toInt() }.sortedDescending() // 리스트 반환
            }
            r() to r().toMutableList() // 박스는 제거해야하므로 수정 가능하게
        }

        fun calc(): String { // 계산
            var m = 0 // 분
            while (b.isNotEmpty()) {
                var i = 0 // 박스 인덱스
                var w = 0 // 크레인 인덱스
                while (w in c.indices) { // 크레인 한개씩 들수 있는 박스 검사
                    when (b.getOrNull(i) ?: break) { // 현재 인덱스에 박스가 있는지 검사
                        in 0..c[w] -> { // 들수 있다면
                            b.removeAt(i) // 박스 제거
                            w++ // 다음 크레인
                        }

                        else -> i++ // 현재 크레인으로 못 옮기면 다음 박스 시도
                    }
                }
                m++ // 크레인 동작
            }
            return "$m" // 출력을 위해 텍스트로 반환
        }

        System.out.bufferedWriter().use { // 출력
            it.write(if (c[0] < b[0]) "-1" else calc()) // 들수 없는 박스가 있으면 -1
            it.flush() // 출력
        }
    }
}
