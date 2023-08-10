/*
1932번: 정수 삼각형
입력받은 삼각형을 다음 형태로 저장한다
0 0
0 7 0
0 3 8 0
0 8 1 0 0
0 2 7 4 4 0
0 4 5 2 6 5 0
0 0 0 0 0 0 0 0
각 줄의 첫번째와 마지막 숫자를 제외한 나머지 숫자의
도달할 수 있는 경로는 [i-1][j-1], [i-1][j]
즉 자신의 바로 위의 숫자와 그 왼쪽 숫자만이 도달할 수 있는 유일한 경로이다
두 값중 큰 값을 선택하여 현재 위치의 값을 더한 뒤 맨 마지막 줄에서
같은 계산을 시도하여 그중 가장 큰 값이 이 삼각형에서 선택할 수 있는
가장 큰 합이다.
*/
import java.util.StringTokenizer

class BOJ1932 {
    fun main() = with(System.`in`.bufferedReader()) {
        val bw = System.out.bufferedWriter()
        var st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val arr = ArrayList<IntArray>()
        for (i in 0..n + 1) {
            val now = IntArray(i + 2)
            if (i in 1..n) {
                st = StringTokenizer(readLine())
                for (j in 1..i) {
                    now[j] = st.nextToken().toInt()
                }
            }
            arr.add(now)
        }
        for (i in 1..n + 1) {
            for (j in 1..arr[i].size - 2) {
                arr[i][j] += Math.max(arr[i - 1][j - 1], arr[i - 1][j])
            }
        }
        bw.write("${arr[n + 1].max()}")
        bw.flush()
    }
}

