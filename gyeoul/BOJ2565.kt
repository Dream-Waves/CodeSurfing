class BOJ2565 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val arr = mutableListOf<Int>() // 계산을 진행할 리스트
        val n = br.readLine().toInt()
        val line = List(n) {
            br.readLine().split(" ").map { it.toInt() }.zipWithNext().first()
        }.sortedBy { it.first } // List<Pair<Int,Int>> 형태에서 첫번째 값을 기준으로 정렬해 저장

        arr.add(line.first().second) // 정렬된 첫번째 값을 리스트에 삽입
        for ((_, dst) in line) { // 저장한 Pair<Int,Int> 에서 뒤의 값만 선택
            if (dst > arr.last()) { // 리스트의 마지막 값이 dst보다 작으면
                arr.add(dst) // 리스트 마지막에 추가
            } else {
                var s = 0 // 시작 인덱스
                var e = arr.lastIndex // 끝 인덱스
                while (s < e) { // 리스트를 이분 탐색
                    val m = s + (e - s) / 2 // 중간값 계산
                    if (arr[m] < dst) {
                        s = m + 1 // 중간값 위로 탐색
                    } else {
                        e = m  // 중간값 아래로 탐색
                    }
                }
                arr[s] = dst // 값 갱신
            }
        }

        with(System.out.bufferedWriter()) {
            write("${n - arr.size}") // 버퍼 작성
            flush() // 버퍼 출력
        }
    }
}
