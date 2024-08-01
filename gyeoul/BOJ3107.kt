class BOJ3107 {
    fun main() = with(System.`in`.bufferedReader()) {
        val raw = readLine() // IP 입력
        val str = raw.indexOf("::").let { // IP 가 ::를 포함하는 경우
            if (it < 0) raw // 없으면 그대로
            else raw.replace("::", "".padStart(9 - raw.count(':'::equals), ':'))
            // 있다면 : 의 총 개수가 7 개가 되도록 ::를 :::: 처럼 늘려줌
        }
        with(System.out.bufferedWriter()) {
            write(str.split(':').joinToString(":") { it.padStart(4, '0') })
            // IP 를 : 기준으로 쪼개어 4 자리보다 부족하다면 앞에 0 을 4 자리가 될때까지 더하고 다시 : 를 포함하여 문자열 결합 후 출력
            flush() // 버퍼 출력
        }
    }
}

