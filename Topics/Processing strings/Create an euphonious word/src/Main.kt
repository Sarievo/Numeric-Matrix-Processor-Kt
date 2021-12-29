fun main() {
    val input = readLine()!!
    val result = checkEuphonious(input)
    println(result)
}

fun checkEuphonious(input: String): Int {
    val vowels =  hashSetOf<Char>('a', 'e', 'i', 'o', 'u', 'y')
    var consecutiveVowels = 0
    var consecutiveConsonants = 0

    var groupVowels = 0
    var groupConsonants = 0

    for (c in input) {
        if (vowels.contains(c)) {
            consecutiveVowels++
            consecutiveConsonants = 0
            if (consecutiveVowels > 2) {
                groupVowels += 1
                consecutiveVowels = 1
            }
        } else {
            consecutiveConsonants++
            consecutiveVowels = 0
            if (consecutiveConsonants > 2) {
                groupConsonants += 1
                consecutiveConsonants = 1
            }
        }
    }

    return groupVowels + groupConsonants
}