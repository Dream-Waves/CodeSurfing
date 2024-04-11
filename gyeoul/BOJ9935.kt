class BOJ9935 {
    fun main() {
        val br = System.`in`.bufferedReader()
        val str = br.readLine() // 문자열 입력
        val del = br.readLine() // 폭발 문자열 입력
        val sb = StringBuilder() // 정답을 담을 스트링 빌더
        for (c in str) { // str을 한자씩
            sb.append(c) // 스트링 빌더에 담기
            if (sb.endsWith(del)) // 스트링 빌더 끝자리가 폭발 문자열과 일치하면
                sb.setLength(sb.length - del.length) // 폭발 문자열 자리수만큼 삭제
        }
        with(System.out.bufferedWriter()) {
            write(sb.toString().ifBlank { "FRULA" }) // 조건에 따라 버퍼에 작성
            flush() // 버퍼 출력
        }
    }
}
