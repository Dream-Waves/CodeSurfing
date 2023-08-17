import java.util.StringTokenizer

class BOJ2343 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        var st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val arr = IntArray(n)
        var arrMax = 0 // 한 블루레이에 담길 수 있는 최소값
        var arrSum = 0 // 한 블루레이에 담길 수 있는 최대값
        var arrNow: Int
        st = StringTokenizer(readLine())
        repeat(n) {
            arrNow = st.nextToken().toInt()
            arr[it] = arrNow
            arrMax = maxOf(arrMax, arrNow)
            arrSum += arr[it]
        }
        var left: Int = arrMax
        var right: Int = arrSum

        var answer: Int = Int.MAX_VALUE // 최소값과 비교하기 위해 Int 최댓값 삽입
        var count: Int
        var sum: Int
        while (left <= right) {
            sum = 0
            count = 1 // 최소 1개부터 담길 수 있다
            val mid = (left + right) / 2
            for (now in arr) {
                if (sum + now > mid) { // 중간값을 넘길 경우 다음 블루레이로
                    sum = 0
                    count++
                }
                sum += now
            }
            if (count <= m) {
                answer = minOf(answer, mid) // 현재 최소값과 이전에 저장되었던 값을 비교
                right = mid - 1 // 현재 answer보다 작은 값이 있는지 찾기 위해 범위 축소
            } else {
                left = mid + 1 // 조건 m의 개수를 넘어서기 때문에 최소값 증가후 재계산
            }
        }
        bw.write("$answer")
        bw.flush()
    }
}
