class BOJ7490 {
    private fun eval(str: String): Int { // 수식 평가 함수
        val num = StringBuilder() // 문자열 숫자 -> Int 로 변환할 변수
        var symbol = '+' // 마지막 숫자 계산을 위해 +- 저장
        var result = 0 // 수식 결과값 반환 변수
        fun calc() { // 덧셈, 뺄셈
            when (symbol) {
                '+' -> result += num.toString().toInt()
                '-' -> result -= num.toString().toInt()
            }
        }
        str.forEach { // 입력받은 문자열 한자씩 순회
            if (it.isDigit()) num.append(it) // 현재 Char 가 숫자라면 변수에 저장
            else if (it != ' ') { // + 혹은 - 라면
                calc() // 계산
                num.clear() // 저장했던 숫자들 초기화
                symbol = it // 현재 기호 저장
            }
        }
        calc() // 마지막 숫자 계산
        return result // 수식 결과값 반환
    }

    fun main() {
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
        val tc = br.readLine().toInt() // 테스트 케이스 횟수 입력
        val list = mutableListOf<String>() // 0 이 되는 수식을 저장할 변수
        fun gen(cnt: Int, n: Int = cnt, str: String = "") { // cnt = 반복횟수, n = 입력받은 값, str = 생성한 기호들
            if (cnt <= 1) { // 마지막자리에 도달할때
                val expr = (1..n).zip("$str ".toList()).flatMap { it.toList() }.joinToString("").trim()
                // 생성한 기호들을 숫자와 병합
                if (eval(expr) == 0) list.add(expr) // 수식 평가 후 0 이라면 리스트에 저장
                return
            }
            for (c in "+- ") {
                gen(cnt - 1, n, "$str$c") // 카운트를 1 씩 줄이며 '+','-',' '을 덧붙여 기호 문자열 생성
            }
        }
        repeat(tc) {
            gen(br.readLine().toInt()) // 현재 테스트 케이스를 입력받아 gen 함수 호출
            list.sorted().forEach { // 정렬 후
                bw.write("$it\n") // 0 이 되는 모든 문자열을 한줄씩 버퍼에 작성
            }
            bw.write("\n") // 테스트 케이스 구분
            list.clear()
        }
        bw.flush() // 버퍼 출력
    }
}
