import java.util.StringTokenizer

/*
예제

입력
첫째 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다. 각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다.
두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.

출력
각 테스트 케이스에 대해서 진영 주식회사가 선발할 수 있는 신입사원의 최대 인원수를 한 줄에 하나씩 출력한다.

예제 입력 1
2
5
3 2
1 4
4 1
2 3
5 5
7
3 6
7 3
4 2
1 4
5 7
2 5
6 1
예제 출력 1
4
3
 */

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val t = readLine().toInt()
    var st: StringTokenizer
    var answer: Int
    var arr: IntArray
    var max: Int
    repeat(t) {
        val n = readLine().toInt()
        arr = IntArray(n + 1)
        answer = 1
        repeat(n) {
//            StringTokenizer를 사용하는 쪽이 readLine과 split, map을 사용하는것보다 빠르다
//            val (score, rank) = readLine().split(" ").map { it.toInt() }
//            arr[rank] = score
            st = StringTokenizer(readLine())
            arr[st.nextToken().toInt()] = st.nextToken().toInt()
        }
//        1번 풀이
//        4%, 12% 틀렸습니다
        /*
        max = arr[arr.size - 1]
        for (i in arr.size - 1 downTo 1) {
            if (max > arr[i]) {
                max = max.coerceAtMost(arr[i]) // Math.min(arr[i],max)
                answer--
            } else {
                max = arr[i].coerceAtLeast(max) // Math.max(arr[i],max)
            }
        }
         */
//        1번 풀이 끝
//        2번 풀이 (정답)
        max = arr[1]
        for (i in 1 until arr.size) {
            if (max > arr[i]) {
                max = arr[i]
                answer++
            }
        }
//        2번 풀이 끝
        bw.write("$answer\n")
    }
    bw.flush()
}
