class BOJ15973 {
    fun main() = with(System.out.bufferedWriter()) {
        val br = System.`in`.bufferedReader()

        data class Square(val l: Int, val d: Int, val r: Int, val u: Int)

        fun getSquare(): Square {
            // 호출시 입력을 받아 Square 객체를 반환
            return java.util.StringTokenizer(br.readLine()).run { // this = StringTokenizer
                Square(
                    this.nextToken().toInt(),
                    this.nextToken().toInt(),
                    this.nextToken().toInt(),
                    this.nextToken().toInt(),
                )
            }
        }

        val p = getSquare() // P 사각형 생성
        val q = getSquare() // Q 사각형 생성
        val h1 = p.l == q.r // p와 q의 세로선 일치 여부 확인
        val h2 = p.r == q.l
        val v1 = p.d == q.u // p와 q의 가로선 일치 여부 확인
        val v2 = p.u == q.d
        write( // 조건에 맞추어 분기 처리 후 출력
            when {
                p.l > q.r || p.r < q.l || p.d > q.u || p.u < q.d -> "NULL"
                // p와 q가 겹치지 않는 경우
                h1 && v1 || h1 && v2 || h2 && v1 || h2 && v2 -> "POINT"
                // p와 q의 선이 모두 일치하는 경우
                h1 || h2 || v1 || v2 -> "LINE"
                // p와 q의 선이 일치하는 경우
                else -> "FACE"
                // 이외의 경우
            }
        )
        flush()
    }
}
