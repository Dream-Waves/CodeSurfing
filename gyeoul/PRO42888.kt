class PRO42888 {
    fun solution(record: Array<String>): Array<String> {
        val map = HashMap<String, String>() // 닉네임 변경을 추적할 Map
        val list = mutableListOf<Pair<String, String>>() // uid와 메시지를 기록할 List
        for (r in record) {
            val v = r.split(" ") // 하나의 레코드를 액션과 uid, 닉네임으로 분할
            when (v[0]) { // 액션을 기준으로
                "Enter" -> {
                    map[v[1]] = v[2] // map에 닉네임 생성
                    list.add(Pair(v[1], "님이 들어왔습니다.")) // uid와 메시지 기록
                }

                "Leave" -> list.add(Pair(v[1], "님이 나갔습니다.")) // uid, 메시지 기록
                "Change" -> map[v[1]] = v[2] // 닉네임 업데이트
            }
        }
        return list.map { (uid, str) -> "${map[uid]}$str" }.toTypedArray()
        // 각 메시지를 순회하며 map에 기록된 최종 닉네임을 메시지에 결합 후 String 배열로 변경해 반환
    }
}