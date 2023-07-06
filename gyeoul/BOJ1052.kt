import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class BOJ1052 {
    fun main() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        val p = br.readLine().split(" ").map { it.toInt() } // 입력받은 값을 Int형으로 변환하여 배열로 저장
        bw.write("${calc(p[0], p[1])} ")
        bw.flush()
    }

    fun calc(n: Int, k: Int): Int {
        if (n <= k) return 0 // n이 k보다 큰 경우에만 수행
        var next = n // 코틀린에서 인자로 받은 값을 수정할 수 없기 때문에 새 변수에 담음
        var d: Int // 2의 n승을 저장할 변수
        repeat(k - 1) {
            d = 1 // 초기화
            while (d < next) d *= 2
            next -= (d / 2)
        }
        d = 1 // 초기화
        while (d < next) d *= 2
        return d - next // 마지막에 2의 n승에 도달하기 위해 모자란 숫자를 반환
    }
}
