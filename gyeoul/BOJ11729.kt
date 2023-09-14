/**
 * BOJ 11729 구현로직
 * f = from / t = to / o = other
 *
 * 2개 기준 f = 1, / t = 3 / o = 2
 * 1->2, 1->3, 2->3
 *
 * 3개 기준 f, t, o를 각각 f, o, t로 생각하고
 * 2개 기준 로직을 실행한다
 * 이후 f->t
 * other에 빼둔 3개의 디스크를 다시 t로 옮기기 위해
 * f, t, o를 각각 2(o), 3(t), 1(f)로 생각하고
 * 2개 기존 로직을 실행하는 방식으로 구현하였다
 */
class BOJ11729 {
    fun main() {
        val n = readln().toInt()
        val sb = StringBuilder()
        var count = 0

        fun hanoi(from: Int, to: Int, other: Int, depth: Int) {
            if (depth == 0) return  // 2개 기준 f->o f->t o->t
            count++ // 1뎁스의 로직마다 카운트 증가
            hanoi(from, other, to, depth - 1) // from -> other (from -> to (from -> other ...)) 로 이어지는 재귀
            sb.append("$from $to\n") // 제일 밑 뎁스까지 내려간 후 순서 계산
            hanoi(other, to, from, depth - 1)
        }

        hanoi(1, 3, 2, n)
        print("$count\n$sb")
    }
}