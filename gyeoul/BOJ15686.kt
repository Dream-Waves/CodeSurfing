import java.util.StringTokenizer
import kotlin.math.abs

class BOJ15686 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
        var st = StringTokenizer(br.readLine())
        // 기본 입출력

        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        // 도시 크기, 치킨 프랜차이즈 개수 입력

        val franchiseList = ArrayList<Pair<Int, Int>>()
        val homeList = ArrayList<Triple<Int, Int, ArrayList<Int>>>()
        // 각각의 좌표를 저장할 리스트, 각 집에서 각 치킨집까지의 거리도 저장

        repeat(n) { i ->
            st = StringTokenizer(br.readLine())
            repeat(n) { j ->
                when (st.nextToken().toInt()) {
                    1 -> homeList.add(Triple(i, j, arrayListOf()))
                    // 각 치킨집의 거리를 저장할 빈 ArrayList 추가
                    2 -> franchiseList.add(Pair(i, j))
                }
            }
        }
        br.close()
        // 각각의 좌표 저장

        val check = BooleanArray(franchiseList.size)
        val selected = IntArray(m)
        var min = Int.MAX_VALUE
        // calc()함수 계산시 사용될 변수

        homeList.forEach { (r, c, arr) ->
            franchiseList.forEach { (i, j) ->
                arr.add(abs(r - i) + abs(c - j))
                // 각 집에서 각 치킨집까지의 거리 계산 및 저장
            }
        }

        fun calc(count: Int) { // 프랜차이즈 m개를 선택할 수 있는 모든 경우의 수 계산
            if (count == m) {
                val selectedValue = homeList.sumOf {
                    it.third.filterIndexed { idx, _ -> idx in selected }.min()
                }
                // 각 집에서 선택하지 않은 치킨집을 제외한 나머지 치킨집과의 거리 중 최솟값 계산
                min = minOf(min, selectedValue)
                return
            }
            for (i in (selected.getOrNull(count - 1) ?: 0)..franchiseList.lastIndex) {
                if (check[i]) continue
                check[i] = true
                selected[count] = i
                calc(count + 1)
                check[i] = false
            }
        }

        calc(0)
        // 계산 실행

        bw.write("$min")
        bw.flush()
        bw.close()
    }
}
