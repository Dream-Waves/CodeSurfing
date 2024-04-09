class BOJ5972 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() } // N, M 입력
        val map = Array(n + 1) { mutableListOf<Pair<Int, Int>>() } // 노드를 저장할 배열
        repeat(m) {
            val (s, e, c) = br.readLine().split(" ").map { it.toInt() } // 간선 추가
            map[s].add(Pair(e, c)) // s -> e
            map[e].add(Pair(s, c)) // e -> s
        }
        fun delivery(s: Int): Int { // 다익스트라 실행
            val arr = IntArray(n + 1) { if (it == s) 0 else Int.MAX_VALUE } // 출발지점은 0 나머지는 Int의 최대값
            val q = java.util.PriorityQueue<Pair<Int, Int>> { a, b -> a.first - b.first } // 우선순위 큐, 노드 번호순 정렬
            q.add(Pair(s, 0)) // 출발 노드 추가
            while (q.isNotEmpty()) {
                val now = q.remove() // 현재 노드
                if (arr[now.first] < now.second) continue // 갱신하지 못하는 곳은 건너뜀
                for (next in map[now.first]) { // 현재 노드에서 도달할 수 있는 노드마다
                    val cost = now.second + next.second
                    if (arr[next.first] > cost) { // 더 저렴한 비용으로 다음 노드를 접근 가능할 때
                        arr[next.first] = cost // 값 갱신
                        q.add(Pair(next.first, cost)) // 갱신을 위해 방문한 노드 큐 추가
                    }
                }
            }
            return arr.last() // 도착 지점의 비용 출력
        }
        with(System.out.bufferedWriter()) {
            write("${delivery(1)}") // 다익스트라 알고리즘 결과 버퍼 작성
            flush() // 버퍼 출력
        }
    }
}
