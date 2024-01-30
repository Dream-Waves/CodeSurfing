class BOJ2527 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
        repeat(4) {
            val sq = br.readLine().split(" ").map { it.toInt() }
            val h1 = sq[0] == sq[6] // 각 사각형의 바깥쪽 세로선 체크
            val h2 = sq[2] == sq[4] // 각 사각형의 안쪽 세로선 체크
            val v1 = sq[1] == sq[7] // 각 사각형의 바깥쪽 가로선 체크
            val v2 = sq[3] == sq[5] // 각 사각형의 안쪽 가로선 체크
            val c = when {
                sq[0] > sq[6] -> "d" // 두번째 사각형이 첫 사각형 좌측 바깥에 있는 경우
                sq[2] < sq[4] -> "d" // 두번째 사각형이 첫 사각형 우측 바깥에 있는 경우
                sq[1] > sq[7] -> "d" // 두번째 사각형이 첫 사각형 상단 바깥에 있는 경우
                sq[3] < sq[5] -> "d" // 두번째 사각형이 첫 사각형 하단 바깥에 있는 경우
                // D 출력
                h1 && v1 -> "c"
                h1 && v2 -> "c"
                h2 && v1 -> "c"
                h2 && v2 -> "c"
                // 꼭지점이 만나는 경우 c 출력
                h1 -> "b"
                h2 -> "b"
                v1 -> "b"
                v2 -> "b"
                // 선분이 만나는 경우 b 출력
                else -> "a"
                // 이외의 경우 a 출력
            }
            bw.write("$c\n")
        }
        bw.flush()
    }
}
