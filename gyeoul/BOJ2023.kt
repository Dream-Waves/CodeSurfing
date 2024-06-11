import kotlin.math.pow
import kotlin.math.sqrt

class BOJ2023 {
    private fun checkNumber(n: Int): Boolean { // 소수 판별 함수
        if (n < 2) return false // 0,1 리턴
        for (i in 2..(sqrt(n.toDouble()).toInt())) {
            if (n % i == 0) {
                return false // 소수가 아닌 수는 리턴
            }
        }
        return true // n = 소수
    }


    fun main() {
        val n = readln().toInt()
        val bw = System.out.bufferedWriter()
        val m = 10.0.pow(n).toInt() // 최대 값
        for (i in m / 10..<m) {
            tailrec fun check(k: Int, len: Int = m / 10) { // 꼬리재귀 최적화
                if (len <= 0) bw.write("$i\n") // 끝자리까지 모두 검사했다면 출력
                else if (checkNumber(k / len)) check(k, len / 10) // 소수인경우 다음 자리수를 포함해 계산
            }
            check(i) // 재귀호출
        }
        bw.let { it.flush();it.close(); }
    }
}
