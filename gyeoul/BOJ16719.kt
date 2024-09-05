private fun rm(str: String): StringBuilder {
    var save = str.substring(1)
    for (i in str.indices) {
        val next = str.removeRange(i, i + 1)
        if (save > next) save = next
    }
    if (save.isEmpty()) return StringBuilder("$str\n")
    return rm(save).append("$str\n")
}

private fun BOJ16719() {
    val raw = System.`in`.bufferedReader().use { it.readLine() }
    System.out.bufferedWriter().use {
        it.write("${rm(raw)}")
        it.flush()
    }
}
