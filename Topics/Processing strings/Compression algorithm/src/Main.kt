fun main() {
    val str = readLine()!!

    var ch = str[0]
    var counter = 1
    var result = ch.toString()

    for (i in 1 until str.length) {
        if (str[i] == ch) {
            counter++
        } else {
            result += counter
            ch = str[i]
            counter = 1
            result += ch
        }
    }
    result += counter
    print(result)
}