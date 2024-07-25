class BOJ6198 {
    fun main() {
        val stack = ArrayDeque<Int>()
        var ans = 0L // 정답 저장 변수
        fun action(tmp: Int = Int.MAX_VALUE) { // 입력을 하지 않는경우 스택을 모두 비운다
            while (stack.isNotEmpty() && stack.last() <= tmp) {
                stack.removeLast() // 입력받은 값에 따라 해당 값보다 작은 수를 스택에서 제거하고
                ans += stack.size // 스택의 크기를 정답에 더함
            }
        }
        with(System.`in`.bufferedReader()) { // 입력버퍼 사용
            for (i in 0..<readLine().toInt()) { // N 만큼 반복
                with(readLine().toInt()) { // this = 이번 입력
                    action(this) // 함수 실행
                    stack.addLast(this) // 현재 값 추가
                }
            }
            close() // 입력버퍼 닫기
        }
        with(System.out.bufferedWriter()) { // 출력버퍼 사용
            action() // 스택 비우기
            write("$ans") // 정답 버퍼 작성
            flush() // 버퍼 출력
            close() // 출력 버퍼 닫기
        }
    }
}
