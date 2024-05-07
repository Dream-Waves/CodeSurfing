class PRO92341 {
    data class Record(
        val time: Int,
        val car: Int,
        val type: Boolean,
    )

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val (dm, df, cm, cf) = fees // 기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원)
        val recordQueue = ArrayDeque<Record>() // 입출차 기록
        val recordMap = java.util.concurrent.ConcurrentHashMap<Int, Record>() // 차량별 입출차 기록
        val timeMap = java.util.concurrent.ConcurrentHashMap<Int, Int>() // 시간의 총량을 계산할 Map

        recordQueue.addAll(records.map { i ->
            val (time, car, type) = i.split(" ") // 시간, 차량번호, 내역 분리
            val (h, m) = time.split(":").map { it.toInt() } // 시간:분 분리
            Record(m + h * 60, car.toInt(), type == "IN") // 시간과 차량번호, 입출차 타입 기록
        })

        fun runCalc(now: Record) = if (now.type) { // 입차 기록
            recordMap[now.car] = now // 차량 입차 기록
        } else {
            val priv = recordMap.remove(now.car) // 차량 출차(삭제)
            timeMap[now.car] = timeMap.getOrDefault(now.car, 0) + (now.time - (priv?.time ?: 0))
            // 입차 기록과 출차 기록의 시간을 비교해 기존깂에 추가
        }

        while (recordQueue.isNotEmpty()) {
            runCalc(recordQueue.removeFirst()) // 기록을 순차적으로 뽑아 함수 실행
        }

        for (r in recordMap.values) {
            runCalc(Record(23 * 60 + 59, r.car, false)) // 나기지 않은 차량에 대해 출차 함수 실행
        }
//        println(recordMap.toString())
//        println(timeMap.toString())

        return timeMap.toList().sortedBy { it.first }.map {
            // 시간 총량 기록을 리스트로 변환하고 차량번호 순서로 정렬
            if (it.second <= dm) df else { // 기본시간보다 작다면 기본요금
                kotlin.math.ceil((it.second - dm).toDouble() / cm).toInt() * cf + df
                // 아니라면 기본 시간을 제외한 나머지 시간에 대해 단위요금 적용
            }
        }.toIntArray() // 배열로 변환 후 반환
    }

    fun main() { // 테스트용 main
        val test = listOf(
            intArrayOf(180, 5000, 10, 600) to arrayOf(
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"
            ), intArrayOf(120, 0, 60, 591) to arrayOf(
                "16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"
            ), intArrayOf(1, 461, 1, 10) to arrayOf(
                "00:00 1234 IN"
            )
        )
        val pro = PRO92341()
        test.forEach {
            println(pro.solution(it.first, it.second).contentToString())
        }
    }
}
