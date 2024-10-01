// 2011번 암호코드
// 18944KB
// 112ms
private val Char.n: Int get() = this.code - '0'.code // Char -> Int 변환 확장 프로퍼티
fun main() {
    val range = 1..26 // A~Z 범위
    val str = System.`in`.bufferedReader().use { it.readLine() } // 문자열 입력
    val arr = IntArray(str.length) // DP에 활용할 Int 배열
    val list = str.mapIndexed { i, _ ->
        // 25114와 같은 문자열을 2, 25, 51, 11, 14로 변환하여 리스트 생성
        if (i == 0) str[i].n
        else str[i - 1].n * 10 + str[i].n
    }

    fun update(i: Int, len: Int) { // DP 갱신 함수
        arr[i] += arr.getOrElse(i - len) { 1 } // 첫번째는 -1 의 인덱스를 가지므로 1로 초기값 할당
        arr[i] %= 1_000_000
    }

    list.forEachIndexed { i, v ->
        when (v % 10) { // 첫번째 자리만 계산
            in range -> update(i, 1) // 1~9 범위에 있는 경우 dp 갱신
        }
        when {
            v / 10 == 0 -> {} // 두번째 자리가 계산이 가능한지 판별
            v in range -> update(i, 2) // 10~26범위 내에 있는 경우 dp 갱신
        }
    }

    System.out.bufferedWriter().use { // BufferedWriter 활용
        it.write("${arr.last()}") // 버퍼 작성
        it.flush() // 출력
    }
}

