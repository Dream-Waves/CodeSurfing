class BOJ2531 {
    fun main() {
        val (n, d, k, c) = readln().split(" ").map { it.toInt() }
        val belt = IntArray(n) // 벨트 배열
        val arr = IntArray(d + 1) // 초밥 가짓수 (접시 수 저장)
        var ans = 0
        fun calcMax() { // 1개 이상 먹은 초밥의 가짓수를 계산하는 함수
            var now = arr.filter { it > 0 }.size // ĸ개 만큼 초밥을 먹었을때 1개 이상 먹은 초밥의 가짓수
            if (arr[c] < 1) now++ // 쿠폰으로 받은 초밥을 먹지 않았을 경우 먹기
            ans = Math.max(ans, now) // 초밥 가짓수의 최대값 갱신
        }
        repeat(n) {
            val now = readln().toInt()
            belt[it] = now // 벨트에 입력받은 초밥 올리기
            if (it < k) arr[now]++ // 처음부터 k번째까지 먹은 가짓수를 먼저 저장
        }
        calcMax() // 가짓수 계산
        for (i in k until n + k) { // k번째부터 벨트를 한바퀴 돌아 n+k-1만큼 까지 모든 경우의 수를 계산
            arr[belt[i % n]]++ // k+1번째의 접시를 먹고
            arr[belt[i - k]]-- // 처음 먹은 접시를 먹지 않은 경우의
            calcMax() // 가짓수 계산
        }
        print(ans) // 최대로 먹을 수 있는 가짓수를 출력
    }
}
