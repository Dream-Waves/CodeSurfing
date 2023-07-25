class BOJ17615 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val now = readLine().toCharArray()
        val count = intArrayOf(0, 0, 0, 0)

        var pointer = 0
        var flag = false
        while (pointer < n) {
            if (now[pointer] == 'R') {
                if (flag) count[0]++
            } else {
                flag = true
            }
            pointer++
        }
        pointer = 0
        flag = false
        while (pointer < n) {
            if (now[pointer] == 'B') {
                if (flag) count[1]++
            } else {
                flag = true
            }
            pointer++
        }
        pointer = n - 1
        flag = false
        while (pointer >= 0) {
            if (now[pointer] == 'R') {
                if (flag) count[2]++
            } else {
                flag = true
            }
            pointer--
        }
        pointer = n - 1
        flag = false
        while (pointer >= 0) {
            if (now[pointer] == 'B') {
                if (flag) count[3]++
            } else {
                flag = true
            }
            pointer--
        }
        print(count.min())
    }
}
