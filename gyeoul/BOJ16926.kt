class BOJ16926 {
    fun main() {
        val (n, m, r) = readln().split(" ").map { it.toInt() } // N, M, R 입력
        val arr = Array(n) { Array(m) { 0 } } // 숫자를 저장할 배열
        val q = ArrayDeque<IntArray>() // 배열을 순회하기 위한 큐
        val list = ArrayDeque<Int>() // 현재 순회한 값을 저장하기 위한 큐
        repeat(n) { i -> arr[i] = readln().split(" ").map { it.toInt() }.toTypedArray() } // 배열에 숫자 입력
        q.add(intArrayOf(0, n - 1, 0, m - 1)) // i의 시작, 끝 & j의 시작, 끝 인덱스를 큐에 삽입
        while (q.isNotEmpty()) {
            val (si, ei, sj, ej) = q.removeFirst() // 큐에 저장된 값을 각 변수에 할당
            if (si >= ei || sj >= ej) continue // 모든 칸을 회전시켰을 경우 루프 종료
            list.clear() // 오류 방지를 위한 리스트 초기화
            val range = arrayOf(sj until ej, si until ei, ej downTo sj + 1, ei downTo si + 1)
            // 범위 선언 / 각 for 에서 범위를 다시 계산하는것보다 미리 계산하여 사용하는것이 메모리 대비 시간효율이 좋다
            for (j in range[0]) list.add(arr[si][j]) // 좌측 상단부터 가로로 우측 상단 - 1 까지 리스트에 삽입
            for (i in range[1]) list.add(arr[i][ej]) // 우측 상단부터 세로로 우측 하단 - 1 까지 리스트에 삽입
            for (j in range[2]) list.add(arr[ei][j]) // 우측 하단부터 가로로 좌측 하단 - 1 까지 리스트에 삽입
            for (i in range[3]) list.add(arr[i][sj]) // 좌측 하단부터 세로로 우측 상단 - 1 까지 리스트에 삽입
            repeat(r) { list.addLast(list.removeFirst()) } // R값 만큼 리스트의 앞부분을 뒤에 추가하며 회전 구현
            for (j in range[0]) arr[si][j] = list.removeFirst() // 리스트의 값을 꺼내 배열에 할당
            for (i in range[1]) arr[i][ej] = list.removeFirst()
            for (j in range[2]) arr[ei][j] = list.removeFirst()
            for (i in range[3]) arr[i][sj] = list.removeFirst()
            q.addLast(intArrayOf(si + 1, ei - 1, sj + 1, ej - 1)) // 시작 끝 인덱스를 1씩 좁혀 다음 큐 실행
        }
        for (v in arr) println(v.joinToString(" ")) // 각 숫자 사이에 공백을 넣어 줄별로 출력
    }
}
