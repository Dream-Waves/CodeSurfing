class BOJ13335 {
    fun main() {
        val br = System.`in`.bufferedReader() // 버퍼 입력
        val (_, w, l) = br.readLine().split(" ").map { it.toInt() } // N,W,L 입력
        val trucks = ArrayDeque<Int>() // 트럭을 구현할 큐
        val bridge = ArrayDeque<Pair<Int, Int>>() // 다리를 구현할 큐
        trucks.addAll(br.readLine().split(" ").map { it.toInt() }) // 트럭 입력
        var weight = 0 // 현재 다리에 올라가 있는 트럭의 무게
        var time = 0 // 현재 시간
        while (bridge.isNotEmpty() || trucks.isNotEmpty()) { // 기다리는 트럭, 다리위에 트럭이 없을 때 까지
            time++ // 시간 증가
            if (bridge.isNotEmpty() && bridge.first().second < time) { // 다리에 올라간 트럭이 내려 올 때
                val truck = bridge.removeFirst() // 다리에서 트럭 제거
                weight -= truck.first // 무게 감소
            }
            if (trucks.isNotEmpty() && weight + trucks.first() <= l) { // 트럭이 더 올라갈 수 있으면
                val truck = trucks.removeFirst() // 트럭큐에서 트럭 제거
                bridge.add(Pair(truck, time + w - 1)) // 다리에서 트럭 추가
                weight += truck // 무게 증가
            } else if (bridge.isNotEmpty()) { // 만약 두가지 다 불가능하지만 다리에 트럭이 있는 경우
                time = bridge.first().second // 시간 증가
            }
        }
        with(System.out.bufferedWriter()) { // 출력 버퍼
            write("$time") // 버퍼 작성
            flush() // 버퍼 출력
        }
    }
}
