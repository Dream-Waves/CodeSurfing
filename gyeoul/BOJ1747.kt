class BOJ1747 {
    fun main() {
        fun check(str: String): Boolean { // 팰린드롬(회문) 검사 함수
            var (l, r) = Pair(0, str.lastIndex) // 인덱스 선언
            while (l < r) if (str[l++] != str[r--]) return false // 회문 검사
            return true // 회문인 경우
        }

        fun calc(min: Int): Int { // 소수 검사 및 결과 계산 함수
            val num = BooleanArray(1_003_002) // 소수 검사 배열
            for (i in 2..1_003_001) {
                if (num[i]) continue // 이전에 계산된 값일 경우 건너뛰기
                else for (j in i * 2..1_003_001 step i) num[j] = true
                // 처음 만나는 경우 자신의 배수를 사용 불가능하게 만들기
            }
            num.forEachIndexed { idx, v ->
                if (!v && idx > 1 && idx >= min && check(idx.toString())) return idx
                // 처음 만나는 소수가 팰린드롬(회문)인 경우 소수 반환
            }
            return 0 // 오류 대비 반환값
        }

        val bw = System.out.bufferedWriter()
        val n = System.`in`.bufferedReader().readLine().toInt()
        bw.write("${calc(n)}") // 함수 실행 후 출력
        bw.flush()
    }
}
