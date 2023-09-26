import kotlin.math.max

class BOJ2156 {
    fun main() {
        val n = readln().toInt()
        val arr = IntArray(n)
        val calc = IntArray(n)
        repeat(n) {
            arr[it] = readln().toInt()
        }
        for (i in calc.indices) {
            calc[i] = when (i) {
                0 -> arr[0]
                // 한잔만 있는 경우
                1 -> arr[1] + arr[0]
                // 두잔이 있는 경우
                2 -> max(calc[1], arr[2] + max(calc[0], arr[1]))
                // 세잔부터는 연속으로 마실 수 없으므로 가장 큰 두잔을 고른다
                3 -> arr[i] + max(calc[1], calc[0] + arr[2])
                // 4번째잔은 3개의 잔을 선택해서 마시는것이 가장 많이 마실수 있으므로
                // 1,3,4 혹은 1,2,4중 최대한 많은 양을 선택한다
                else -> arr[i] + max(calc[i - 2], arr[i - 1] + max(calc[i - 3], calc[i - 4]))
                // 5번째 잔 부터는 i-1잔을 마셨는지 여부에 따라 계산한다
                // i-1잔을 마시지 않았을 경우 i-2의 잔까지 마셨을 경우의 가장 많은 양과
                // i-1잔을 마셨을 경우 i-1 이전의 두잔이 연속으로 양이 적을 경우를 대비하여
                // i-3잔, i-4잔까지 마신 양, 이렇게 세가지 선택지 중 가장 큰 값을 선택한다
            }
        }
        print(calc.max())
    }
}