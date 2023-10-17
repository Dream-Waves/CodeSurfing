class BOJ17609 {
    fun main() {
        fun check(text: String, a: Int, b: Int): Boolean {
            return text[a] == text[b] // 입력받은 텍스트가 일치하는지 여부를 반환
        }
        repeat(readln().toInt()) {
            val text = readln() // 검사할 글자
            var l = -1 // 왼쪽 포인터
            var r = text.length // 오른쪽 포인터
            var flag = true // 유사회문일 경우 false
            var status = true // 두번 이상 일치하지 않을경우 false
            var left = true // 유사회문일때 방향성 설정
            var right = true
            while (++l < --r && status) {
                if (flag) { // 유사회문이 아닐경우 검사
                    flag = check(text, l, r) // 유사회문일 경우를 대비하여 flag에 대입
                }
                if (!flag) { // 유사회문일 경우 검사
                    if (left) left = check(text, l + 1, r) // 좌측을 한칸 더 전진시켜 검사
                    if (right) right = check(text, l, r - 1) // 우측을 한칸 더 전진시켜 검사
                    if (!left && !right) status = false // 양쪽 모두 검사를 실패한 경우 상태값 변경
                }
            }
            println(
                when {
                    !status -> 2 // 검사 실패
                    !flag -> 1 // 검사 성공 & 유사 회문
                    else -> 0 // 검사 성공 & 완전 회문
                }
            )
        }
    }
}