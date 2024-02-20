import kotlin.math.pow

class BOJ1303 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() } // 세로길이 m, 가로길이 n 입력
        val arr = Array(m) { // 세로길이 M 배열 생성
            readLine().toCharArray().map { it == 'B' }.toTypedArray() // 각각의 글자마다 B 인지 비교하고 배열로 바꾸어
            // 2차원 불리언 배열 생성
        }
        val visited = Array(m) { BooleanArray(n) } // 방문을 체크할 2차원 불리언 배열
        val score = IntArray(2) // 각 팀의 점수를 저장할 배열
        val q = ArrayDeque<Pair<Int, Int>>() // BFS를 실행할 큐
        for (i in 0..<m) {
            for (j in 0..<n) { // 배열의 각 칸을 순회
                if (visited[i][j]) continue // 방문했을 경우 로직 건너 뜀
                visited[i][j] = true // 방문 체크
                q.add(Pair(i, j)) // 큐에 시작 위치 추가
                var s = 0.0 // 현재 칸이 몇개까지 이어져있는지 저장할 변수를 Double로 선언 (pow 를 사용하기 위해)
                while (q.isNotEmpty()) { // BFS 실행
                    val (r, c) = q.removeFirst() // 큐에서 하나를 꺼내 r과 c에 값 대입
                    s += 1 // 현재 칸을 처리하므로 변수 카운트 증가
                    for ((nr, nc) in listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1) // delta값을 이용해
                        // 다음 방문할 인덱스를 nr, nc에 저장
                        .map { it.first + r to it.second + c } // 델타값에 현재 위치 반영
                        .filter { it.first in 0..<m && it.second in 0..<n } // 각 인덱스가 유효하도록 필터링
                    ) {
                        if (visited[nr][nc] || arr[i][j] != arr[nr][nc]) continue
                        // 다음 위치가 방문했거나 시작한 위치의 팀과 다른 팀일 경우 건너 뜀
                        visited[nr][nc] = true // 방문 체크
                        q.add(Pair(nr, nc)) // 큐에 다음 위치 추가
                    }
                }
                score[if (arr[i][j]) 1 else 0] += s.pow(2).toInt() // n일때 n^2 의 파워를 가지므로
                // pow(2)를 통해 값 증가 후 올바른 위치에 값 추가
            }
        }
        with(System.out.bufferedWriter()) {
            write(score.joinToString(" ")) // 배열을 공백과 함께 문자열로 바꾸어 출력
            flush()
            close()
        }
        close()
    }
}
