class BOJ2212 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt() // 센서 개수 입력
        val k = readLine().toInt() - 1 // 집중국 개수 입력, 사용편의를 위해 - 1
        val st = java.util.StringTokenizer(readLine())
        val arr = IntArray(n) { st.nextToken().toInt() } // 센서 위치 입력
        val dist = mutableListOf<Int>() // 거리를 계산할 리스트
        arr.sort() // 센서 위치 정렬
        close() // 입력버퍼 닫음
        for (i in 1..<n) dist.add(arr[i] - arr[i - 1]) // 각 센서별 거리 계산
        dist.sort() // 거리순 정렬
        var ans: Long = 0
        for (i in 0..<dist.size - k) ans += dist[i]
        // 센서별 거리의 최소값을 구하기 위해 가장 멀리 떨어진 K 개를 제외한 나머지를 합산
        //    val ans = dist.subList(0, dist.size - k).sum()
        // sublist 사용시 인덱스 underflow 발생
        with(System.out.bufferedWriter()) {
            write("$ans")
            flush() // 버퍼 출력
            close() // 입력버퍼 닫음
        }
    }
}
