private const val MAX_LINE = 80
private val hr = "-".repeat(MAX_LINE)

private fun BOJ6581() {
    System.out.bufferedWriter().use { bw -> // bufferedWriter 사용
        System.`in`.bufferedReader() // bufferedReader 사용
            .use { it.readText() } // 모든 텍스트를 가져온 뒤 bufferedReader 종료
            .htmlParse() // 기져온 텍스트를 htmlParse 함수에 넘김
            .forEach { bw.write("$it\n") } // 각 줄을 출력버퍼에 작성
        bw.flush() // 정답 출력
    }
}

private fun String.htmlParse(): List<String> = parse(this) // 스트링에서 str.htmlParse() 를 사용할 수 있도록 활성화

private fun parse(text: String): List<String> {
    val lineBuffer = StringBuilder() // 각 줄을 관리하는 버퍼
    val screen = mutableListOf<String>() // 화면에 표시될 각 줄을 리스트로 저장

    fun clearBuffer() { // 라인버퍼 -> 스크린 출력 함수
        screen += "$lineBuffer" // lineBuffer 를 스크린에 추가
        lineBuffer.clear() // StringBuilder.setLength(0)
    }

    text.split(Regex("\\s+")) // 공백을 기준으로 단어 분할
        .filter { it.isNotEmpty() } // 값이 있는것만 필터링 << 이거 한줄이 없어서 엄청 헤멤
        .forEach { // 각 단어들을 기준으로 작업
            when (it) {
                "<br>" -> { // br 태그
                    clearBuffer()
                }

                "<hr>" -> { // hr 태그
                    if (lineBuffer.isNotEmpty()) clearBuffer() // 기존에 입력한게 있다면 다음줄부터 선을 긋는다
                    screen.add(hr)
                }

                else -> {
                    if (lineBuffer.length + it.length + 1 > MAX_LINE) clearBuffer()
                    // 현재 단어가 현재 줄에 들어갈 수 없다면 새 줄 생성
                    if (lineBuffer.isNotEmpty()) lineBuffer.append(" ")
                    // 현재 줄이 비어있지 않다면 구분을 위해 공백 추가
                    lineBuffer.append(it) // 현재 단어를 현재 줄에 작성
                }
            }
        }
    if (lineBuffer.isNotEmpty()) clearBuffer() // 남아있는 줄을 출력
    return screen
}
