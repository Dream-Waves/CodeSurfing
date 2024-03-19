class BOJ1124 {
    fun main() {
        val max = 100_000 // 최대값 상수
        val br = System.`in`.bufferedReader() // 입력버퍼
        val (a, b) = br.readLine().split(" ").map { it.toInt() } // A,B 입력
        br.close() // 입력버퍼 닫기
        val arr = BooleanArray(max + 1) { it >= 2 } // 에라토스테네스의 체로 활용할 배열
        var ans = 0 // 정답을 저장할 변수
        for (i in 2..max) for (j in i * 2..max step i) arr[j] = false // 에라토스테네스의 체 실행
        for (i in a..b) {
            var num = i // i 를 소인수 분해하기 위해 mutable 변수에 재할당
            var div = 2 // 소인수로 사용할 변수
            var count = 0 // 소수의 개수
            while (num > 1) { // 소인수 분해
                if (num % div == 0) { // div로 나누어지면
                    num /= div // num을 div로 나누어 저장
                    count++ // 소수 증가
                } else {
                    div++ // div의 값 증가
                }
            }
            if (arr[count]) ans++ // count가 소수인지 검사 후 맞다면 ans 1 증가
        }
        with(System.out.bufferedWriter()) { // 출력 버퍼 사용
            write("$ans") // ans를 버퍼에 작성
            flush() // 버퍼 출럭
            close() // 출력버퍼 닫기
        }
    }
}
