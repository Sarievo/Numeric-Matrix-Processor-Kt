fun main() {
    val input = readLine()!!.split(Regex("\\s+"))
    val s = input[0]
    var n = input[1].toInt()
    if (n > s.length) {
        println(s)
    } else {
        n %= s.length
        val s1 = s.substring(0, n)
        val s2 = s.substring(n)
        println("$s2$s1")
    }
}