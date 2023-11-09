class BOJ15565 {
    fun main() {
        val list = ArrayList<Int>() // 라이언 인형의 위치를 저장할 리스트
        val (_, k) = readln().split(" ").map { it.toInt() }
        readln().split(" ").mapIndexed { idx, num ->
            //인형의 순서를 입력받아 인덱스를 리스트에 저장
            if (num.toInt() == 1) list.add(idx)
        }
        if (list.size < k) print(-1) // 리스트에 담긴 라이언 인형보다 요구하는 인형이 많을경우 -1
        else {
            var ans = Int.MAX_VALUE // 답을 저장할 변수
            var l = 0 // 왼쪽 포인터
            var r = k - 1 // 오른쪽 포인터
            while (r < list.size) ans = ans.coerceAtMost(list[r++] - list[l++] + 1)
            //포인터를 한칸씩 옆으로 옮기며 k개를 포함하는 집합의 최대, 최소 인덱스의 차이를 계산
            print(ans) // 정답 출력
        }
    }
}