class BOJ1389 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        // N, M 입력
        val user = Array(n) { i -> IntArray(n) { j -> if (i == j) 0 else 10000 } }
        // 인덱스가 동일한 곳은 0 아닌곳은 큰 숫자로 2차원 배열 생성 및 초기화
        repeat(m) {
            val st = java.util.StringTokenizer(readLine())
            val a = st.nextToken().toInt() - 1 // 유저 번호와 인덱스를 동일하게 하기 위해 - 1로 입력
            val b = st.nextToken().toInt() - 1
            user[a][b] = 1 // 친구 사이의 거리를 1로 설정
            user[b][a] = 1
        }
        close() // bufferedReader 입력 닫기
        val r = 0..<n // 반복적인 범위값 변수
        for (c in r) { // 경유하는 친구 (중간값)
            for (s in r) { // 계산할 친구
                for (e in r) { // 친구 S와 얼마나 떨어져 있는지 계산할 친구
                    val path = user[s][c] + user[c][e] // 중간값을 경유하는 경로
                    if (user[s][e] > path) user[s][e] = path // 현재보다 중간을 거쳐가는게 빠를때 값 갱신
                }
            }
        }
        with(System.out.bufferedWriter()) { // bufferedWriter 포함 코드블럭
            val process = user.map { it.sum() } // 각 인원마다 케빈베이컨 스코어 합산
            val min = process.min() // 케빈베이컨 최소값 도출
            val idx = process.indexOf(min) // 처음 만나는 (가장 작은) 최소값 인원의 인덱스 확인
            write("${idx + 1}") // 유저번호를 출력해야 하기 때문에 +1
            flush() // 버퍼 출력
            close() // bufferedWriter 닫기
        }
    }
}
