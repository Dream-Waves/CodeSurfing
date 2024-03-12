class BOJ1527 {
    fun main() {
        val (a, b) = readln().split(" ")
        val range = a.toLong()..b.toLong() // A와 B 사이의 범위 (숫자)
        val lenRange = a.length..b.length // A와 B의 자릿수 범위
        val set = HashSet<Long>() // 만든 수를 저장할 set
        fun rec(str: String) { // 수를 만들어낼 재귀 함수
            if (str.length in lenRange && str.toLong() in range) set.add(str.toLong())
            // 만든 숫자의 길이가 범위 내라면 range 범위 체크 후 set에 저장
            if (str.length >= b.length) return // 재귀 종료
            rec(str + '4') // 현재 만든 숫자에 4를 붙여 재귀
            rec(str + '7') // 현재 만든 숫자에 7을 붙여 재귀
        }
        rec("") // 숫자 생성
        with(System.out.bufferedWriter()) {
            write("${set.size}") // set에 몇개가 있는지 확인해 버퍼에 작성
            flush() // 버퍼 출력
            close()
        }
    }
}
