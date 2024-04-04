class BOJ12919 {
    fun main() {
        val a = readln() // A 입력
        val b = readln() // B 입력
        var c = 0 // 답을 저장할 변수
        fun dfs(str: String) {
            if (str == a) c = 1 // str이 A와 문자열이 동일한 경우 값 갱신
            if (str.length <= a.length || c > 0) return // 길이가 A와 같거나 짧아지는 경우 재귀 종료
            if (str.first() == 'B') dfs(str.substring(1).reversed()) // 첫글자가 B인 경우 B 제거 후 뒤집어 dfs
            if (str.last() == 'A') dfs(str.substring(0, str.lastIndex)) // 끝글자가 A 인경우 A 제거 후 dfs
        }
        dfs(b) // B 문자열로 DFS 실행
        print(c) // 정답 출력
    }
}
