fun main() {
    var ans = 0
    val list = System.`in`.bufferedReader().use { br ->
        val n = br.readLine().toInt()
        List(n) { // 반환할 배열
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            x to y // [x, y],[x, y],... 형태로 리스트 생성
        }
    }

    var (f, l) = list.first() // 초기값 설정
    list.forEach { (x, y) -> // 리스트 순회
        if (x > l) { // 현재 시작점이 이전 범위와 이어지지 않으면
            ans += l - f // 지금까지 저장된 범위를 계산
            f = x // 시작점 초기화
        }
        l = maxOf(l, y) // 끝점 갱신
    }
    ans += l - f // 마지막으로 저장된 범위 계산

    System.out.bufferedWriter().use { bw ->
        bw.write("$ans") // 정답 출력
        bw.flush()
    }
}