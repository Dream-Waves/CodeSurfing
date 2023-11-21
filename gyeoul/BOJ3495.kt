class BOJ3495 {
    fun main() {
        val (h, w) = readln().split(" ").map { it.toInt() }
        var ans = 0 // 도형의 넓이
        repeat(h) {
            val now = readln()
            var flag = false // 외부, 내부 판별 변수
            repeat(w) { // 입력받은 줄의 각 칸에 위치한 문자가 외곽선인지 검사
                when (now[it]) {
                    '/', '\\' -> flag = !flag // 현재 칸이 외곽선일 경우 내,외부 변수 토글
                }
                if (flag) ans++ // 내부인 경우 넓이를 1 증가
            }
        }
        print(ans) // 넓이 출력
    }
}
