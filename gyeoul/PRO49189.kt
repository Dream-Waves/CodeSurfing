/*
문제 설명
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다.
1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
 */
class PRO49189 {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        val arr = MutableList(n + 1) { -1 } // 최단 경로로 도달할 수 있는 각 노드의 값
        val route = Array(n + 1) { BooleanArray(n + 1) } // 간선 지도
        // 다음 경로 목록을 찾아주는 함수
        fun getRoute(n: Int): List<Int> = route[n].mapIndexed { idx, bool -> if (bool) idx else null }.filterNotNull()

        edge.forEach { (a, b) -> // 입력받은 경로로 지도 초기화
            route[a][b] = true
            route[b][a] = true
        }

        val q = ArrayDeque<Pair<Int, Int>>() // BFS
        arr[1] = 0 // 루트 노드 초기화
        q.add(Pair(1, 0))

        while (q.isNotEmpty()) {
            val (now, sum) = q.removeFirst()
            getRoute(now).forEach { i ->
                if (arr[i] > sum + 1 || arr[i] < 0) { // 더 빨리 도달할 수 있거나 아직 한번도 가지 않은 경우 갱신
                    arr[i] = sum + 1 //
                    q.add(Pair(i, sum + 1))
                }
            }
        }

        val max = java.util.Collections.max(arr) // 최대값 = 루트에서 가장 먼 노드
        return arr.count { it == max } // 최대값을 가지는 노드의 갯수 반환
    }
}
