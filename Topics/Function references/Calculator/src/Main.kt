val add: (Int, Int) -> Int = { x, y -> x + y }

val subtract: (Int, Int) -> Int = { x, y -> x - y }

val divide: (Int, Int) -> Int = { x, y -> x / y }

val multiply: (Int, Int) -> Int = { x, y -> x * y }

fun calculate(x: Int, y: Int, operator: (Int, Int) -> Int) {
    // write your code here
    val result = operator.invoke(x, y)
    print(result)
}

fun main() {
    val x: Int = readLine()!!.toInt()
    val y: Int = readLine()!!.toInt()
    when (readLine()!!) {
        // write your code here
        "add" -> calculate(x, y, add)
        "subtract" -> calculate(x, y, subtract)
        "multiply" -> calculate(x, y, multiply)
        "divide" -> calculate(x, y, divide)
    }
}