/* 2374 - 같은 수로 만들기
 * 메모리: 19388KB 시간: 176ms
 * 
 * n(1 ≤ n ≤ 1,000)개의 자연수 A[1], A[2], A[3], …, A[n]이 있다.
 * 이 자연수에 Add(i)라는 연산을 하면, A[i]가 1만큼 증가한다.
 * 이때, A[i]만 증가하는 것이 아니고, A[i]의 좌우로 인접한 같은 수의 그룹이 한번에 1씩 증가한다.
 * A[1]과 A[n]은 인접해 있지 않다.
 * 
 * 예를 들어 수가 {1, 1, 1, 1, 3, 3, 1} 이었다고 해 보자.
 * Add(2)를 하면 A[2]의 좌우로 인접한 같은 수가 1씩 증가하니까 {2, 2, 2, 2, 3, 3, 1}이 된다.
 * 여기서 Add(4)를 하면 {3, 3, 3, 3, 3, 3, 1}이 되고, 여기서 Add(1)을 하면 {4, 4, 4, 4, 4, 4, 1}이 된다.
 * 
 * 이와 같이 Add라는 연산을 사용하여 A[1] = A[2] = A[3] = … = A[n]이 되도록 하려 한다.
 * 이때, 최소 회수로 Add연산을 사용하는 방법을 찾는 것이 문제이다.
 * 
 * 입력
 * 첫째 줄에 정수 n이 주어진다.
 * 다음 n개의 줄에는 차례로 A[1], A[2], …, A[n]이 주어진다.
 * 모든 입력은 1,000,000,000을 넘지 않는다.
 * 
 * 출력
 * 첫째 줄에 최소의 Add연산 사용 회수를 출력한다. 이 값은 1025을 넘지 않는다.
 */

fun main() {
    val arr = LongArray(readln().toInt()) { readln().toLong() } // 바로 배열로 변환
    var ans = 0L
    val max = arr.max() // 최대값 선계산
    while (arr.any { it != max }) { // 모든 항목이 최대값이 될때까지
        val min = arr.min() // 현재 가장 작은값
        val idx = arr.indexOf(min) // 가장 작은값의 첫번째 인덱스
        val l = idx // 해당값 좌측 인덱스
        var r = idx // 해당값 우측 인덱스 (초기값)
        while (r < arr.size - 1 && arr[r + 1] == min) r++ // 우측 인덱스 탐색
        val gap = minOf( // 좌우측 갭 비교후 적은 값으로 선언
            if (l > 0) arr[l - 1] - min else Long.MAX_VALUE, // 좌측 갭 계산
            if (r < arr.size - 1) arr[r + 1] - min else Long.MAX_VALUE // 우측 갭 계산
        )
        for (i in l..r) { // 최소값 그룹 인덱스 순회
            arr[i] += gap
        }
        ans += gap
    }
    System.out.bufferedWriter().use {
        it.write("$ans")
        it.flush()
    }
}
