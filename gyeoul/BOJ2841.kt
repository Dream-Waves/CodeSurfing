class BOJ2841 {
    fun main() {
        val (n, p) = readln().split(" ").map { it.toInt() } // N, P 입력
        val arr = Array(7) { ArrayDeque<Int>(p) } //각 줄마다 손가락의 위치를 확인하기 위한 스택 저장
        var count = 0 // 손가락의 움직임 횟수를 파악하는 변수
        repeat(n) {
            val (line, fret) = readln().split(" ").map { it.toInt() } // 라인과 프랫 입력
            while (arr[line].isNotEmpty() && arr[line].last() > fret) {
                arr[line].removeLast() // 현재 라인의 입력받은 프랫 뒤에있는 손가락 모두 떼기
                count++
            }
            if (arr[line].isEmpty() || arr[line].last() != fret) { // 이전과 동일한 위치에 손가락이 위치한 경우 건너뜀
                arr[line].addLast(fret) // 아닐 경우 해당 라인의 손가락 위치를 저장
                count++
            }
        }
        print(count)
    }
}