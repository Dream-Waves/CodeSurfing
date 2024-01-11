class BOJ1522 {
    fun main() = with(System.out.bufferedWriter()) {
        val raw = System.`in`.bufferedReader().readLine()
        val len = raw.count { it == 'a' } // 뭉쳐야 하는 a의 개수
        var ans = 0 // 정답을 저장할 변수
        for (i in raw.indices) {
            val cal = if (i + len > raw.lastIndex) {
                raw.substring(0, i + len - raw.length) + raw.substring(i, raw.length)
                // 인덱스를 벗어나는 경우 끝까지 읽은 뒤 남은 인덱스를 0부터 추가로 읽음
            } else {
                raw.substring(i, i + len) // 인덱스 내에서 해결될 경우 i부터 len 만큼 읽음
            }.count { it == 'a' }
            // 읽은 문자열에서 a의 개수를 추출
            ans = kotlin.math.max(cal, ans) // 최대값 갱신
        }
        write("${len - ans}") // a를 가진 문자열 최대값에서 뭉쳐야 하는 a의 개수의 차를 출력
        flush()
    }
}
