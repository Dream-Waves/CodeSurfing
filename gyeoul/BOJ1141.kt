class BOJ1141 {
    fun main() {
        val n = readln().toInt()
        val arr = mutableListOf<String>() // 단어를 저장할 리스트
        val skip = BooleanArray(n) // 접두사일 경우 건너뛰기 위한 배열
        repeat(n) {
            arr.add(readln()) // 단어 입력받기
        }
        arr.sortByDescending { it.length } // 문자열의 길이로 정렬
        var result = 0 // 정답 카운트 변수
        for (i in arr.indices) {
            if (skip[i]) continue // 현재 단어가 다른 단어의 접두사일 경우 건너뜀
            for (j in i until arr.size) { // 현재 단어보다 짧은 단어와 현재 단어를 검사
                if (arr[i].startsWith(arr[j])) {
                    skip[j] = true // 현재 단어의 접두사일 경우 건너뛸 목록에 추가
                }
            }
            result++ // 한 단어의 검사가 모두 끝나면 정답 1 증가
        }
        println(result)
    }
}