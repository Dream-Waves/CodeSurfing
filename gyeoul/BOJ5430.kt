class BOJ5430 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        val t = readLine().toInt()
        var rev: Boolean // 배열이 뒤집혀져 있는지 체크할 변수
        repeat(t) {
            rev = false // 초기화
            val func = readLine() // 입력
            val n = readLine().toInt()
            val arr = readLine()
            val nums = arr.substring(1, arr.length - 1).split(",")
            // 입력받은 배열을 리스트로 변환
            var l = 0
            var r = n
            // 함수 계산을 위한 인덱스 기반 변수
            for (p in func) {
                when (p) { // 각 함수 처리
                    'R' -> rev = !rev // R일 경우 rev의 값 뒤집기
                    'D' -> if (rev) r-- else l++ // D일 경우 rev의 값에 따라 인덱스 축소
                }
            }
            bw.write(
                if (l > r || l !in 0..n || r !in 0..n) "error\n" // 인덱스가 정상 범위가 아닐 경우
                else "[${nums.subList(l, r).let { (if (rev) it.reversed() else it).joinToString(",") }}]\n"
                // 인덱스가 정상일 경우 뒤집혀진 경우와 아닌 경우를 구별하여 출력
            )
        }
        bw.flush()
    }
}
