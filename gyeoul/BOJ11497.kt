class BOJ11497 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter() // 출력용 버퍼
        val t = readLine().toInt() // T 입력
        repeat(t) {
            var ans = 0 // 크기 차이를 저장할 변수
            val n = readLine().toInt() // 입력받을 통나무 개수
            val st = java.util.StringTokenizer(readLine())
            val arr = ArrayDeque<Int>(n) // 통나무를 세울 공간
            val raw = IntArray(n) {
                st.nextToken().toInt() // 통나무를 입력받아 저장
            }.sortedDescending() // 역순으로 정렬
            raw.forEach { // 역순으로 정렬된 입력받은 통나무를 세운다
                if (arr.isEmpty()) arr.add(it) // 가장 큰것은 중앙에
                else if (arr.first() - it > arr.last() - it) arr.addFirst(it)
                else arr.addLast(it)
                // 점점 작은것이 들어오므로 더 차이가 벌어지지 않도록
                // 현재 통나무를 양 끝에 세운 통나무 중 크기차이가 더 많이 나는 곳 옆에 세움
            }
            var priv = arr.removeFirst() // 비교대상 통나무 선택
            while (arr.isNotEmpty()) {
                val now = arr.removeFirst() // 다음 통나무 선택
                ans = maxOf(ans, kotlin.math.abs(priv - now)) // 이전 통나무와 크기차이 비교
                priv = now // 비교대상 통나무 갱신
            }
            bw.write("$ans\n")
        }
        bw.flush()
    }
}
