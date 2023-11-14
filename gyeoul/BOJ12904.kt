class BOJ12904 {
    fun main() {
        val raw = readln()
        var test = readln()
        while (test != raw) {
            if (test.isEmpty()) { // test 문자열이 비었을 경우
                print("0") // 더이상 진행 불가능하므로 0 출력
                return
            }
            test = if (test.last() == 'B') { // 마지막 글자가 B인 경우
                test.reversed().substring(1 until test.length) // B를 제거하고 문자열을 뒤집음
            } else {
                test.substring(0 until test.length - 1) // A일 경우 문자를 제거
            }
        }
        print("1") // test 문자열과 raw 문자열이 동일한 경우 1 출력
    }
}