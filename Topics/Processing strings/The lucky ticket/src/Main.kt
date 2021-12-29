fun main() {
    val myArray = readLine()!!.toCharArray()
    var n1 = 0
    var n2 = 0
    for (i in 0..2) {
        n1 += myArray[i].toString().toInt()
        n2 += myArray[i + 3].toString().toInt()
    }
    
    if (n1 == n2) {
        println("Lucky")
    } else {
        println("Regular")
    }
}
