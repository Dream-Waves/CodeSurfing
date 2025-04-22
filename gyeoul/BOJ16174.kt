/*
‘쩰리’는 가로와 세로의 칸 수가 같은 정사각형의 구역 내부에서만 움직일 수 있다. ‘쩰리’가 정사각형 구역의 외부로 나가는 경우엔 바닥으로 떨어져 즉시 게임에서 패배하게 된다.
‘쩰리’의 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸이다. 다른 출발점에서는 출발하지 않는다.
‘쩰리’가 이동 가능한 방향은 오른쪽과 아래 뿐이다. 위쪽과 왼쪽으로는 이동할 수 없다.
‘쩰리’가 가장 오른쪽, 가장 아래 칸에 도달하는 순간, 그 즉시 ‘쩰리’의 승리로 게임은 종료된다.
‘쩰리’가 한 번에 이동할 수 있는 칸의 수는, 현재 밟고 있는 칸에 쓰여 있는 수 만큼이다. 칸에 쓰여 있는 수 초과나 그 미만으로 이동할 수 없다.
 */

class BOJ16174 {
fun main() {
    val arr = System.`in`.bufferedReader() // 배열에 입력 받기
        .run { List(readLine().toInt()) { readLine().split(" ").map { it.toInt() } } }
    val n = arr.size
    val visited = Array(n) { BooleanArray(n) } // 방문 배열
    val q = ArrayDeque<Pair<Int, Int>>() // 방문 큐

    q.addLast(Pair(0, 0)) // 출발칸 추가
    while (q.isNotEmpty()) { // 큐가 빌 때까지
        val (r, c) = q.removeFirst() // 현재 좌표
        val m = arr[r][c] // 이동 칸 수
        if (m == -1) break // 마지막 칸인 경우 종료
        if (r + m < n && !visited[r + m][c]) { // 오른쪽으로 이동한 경우
            visited[r + m][c] = true
            q.addLast(Pair(r + m, c))
        }
        if (c + m < n && !visited[r][c + m]) { // 아래쪽으로 이동한 경우
            visited[r][c + m] = true
            q.addLast(Pair(r, c + m))
        }
    }

    System.out.bufferedWriter().run {
        write(if (visited[n - 1][n - 1]) "HaruHaru" else "Hing") // 방문 후 종료 되었다면 정답 출력
        flush()
    }
}
}
