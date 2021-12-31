fun main() {
    val input = readLine()!!.trim()
    val length = input.length
    println(
        if (length % 2 == 0) {
            input.substring(0, length / 2 - 1)
        } else {
            input.substring(0, length / 2)
        } + input.substring(length / 2 + 1)
    )
}