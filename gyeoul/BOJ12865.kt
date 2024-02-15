class BOJ12865 {
    fun main() = with(System.`in`.bufferedReader()) {
        data class Item(val w: Int, val v: Int) // 아이템 저장 형태 선언

        val (n, k) = readLine().split(" ").map { it.toInt() } // N,K 입력
        val items = mutableListOf<Item>() // 아이템 저장 공간
        val bag = Array(n + 1) { IntArray(k + 1) } // dp를 실행할 공간
        // 무게별로 아이템을 넣었을 때 감당 가능한 최대 V 가치 저장

        repeat(n) {
            with(java.util.StringTokenizer(readLine())) { // this = StringTokenizer
                items.add(Item(this.nextToken().toInt(), this.nextToken().toInt())) // 아이템 입력
            }
        }

        for (w in 1..k) { // 무게 제한 순회(dp)
            items.forEachIndexed { i, t -> // 아이템 선택 순회
                bag[i + 1][w] = if (t.w > w) { // 현재 무게제한의 최대값 계산
                    // 이번에 선택한 물건이 가방에 들어갔을 때 무게를 버티는지
                    bag[i][w]
                    // 버티지 못하면 이전과 동일한 값
                } else {
                    maxOf(bag[i][w], bag[i][w - t.w] + t.v)
                    // 버틴다면 이전과 동일한 값, 지금 아이템을 (공간을 확보하고) 넣었을 때를 비교후 큰 값 삽입
                }
            }
        }

        with(System.out.bufferedWriter()) {
            write("${bag[n][k]}") // k의 무게를 버틸 수 있는 최대값 출력
            flush()
        }
    }
}
