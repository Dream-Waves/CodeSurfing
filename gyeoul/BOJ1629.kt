class BOJ1629 {
    fun main() {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        var ans: Long = 1
        val arr = IntArray(33) // Int의 최대 크기인 2^32까지 사전 계산할 배열
        arr[0] = a % c //
        for (i in 1..32) {
            val priv = arr[i - 1].toLong() // i-1 칸을 Long 타입으로 변환하여
            arr[i] = ((priv * priv) % c).toInt()
        }
        val list = ArrayList<Boolean>()
        var p = b
        while (p > 0) {
            list.add(p % 2 == 1)
            p /= 2
        }
        for (i in list.indices) {
            if (list[i]) ans = ans * arr[i] % c // 2^n은 mod 계산후 0이 되므로 2^n+1의 값만을 계산
        }
        print(ans)
    }
}
