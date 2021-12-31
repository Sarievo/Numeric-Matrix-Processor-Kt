package processor

import java.lang.AssertionError
import java.util.*

val s = Scanner(System.`in`)
val errMsg = listOf("ERROR")


fun readMatrix(): Array<DoubleArray> {
    val r = s.nextInt()
    val c = s.nextInt()
    val matrix = Array(r) { DoubleArray(c) }
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            matrix[i][j] = s.next().toDouble()
        }
    }
    return matrix
}

fun Array<DoubleArray>.print() = run {
    for (i in this) {
        for (j in i) print(String.format("%.0f ", j)); println()
    }
}

operator fun Array<DoubleArray>.plus(other: Array<DoubleArray>): Array<DoubleArray> {
    assert(this.size == other.size && this[0].size == other[0].size)
    val raw = this.clone()
    for (i in raw.indices) {
        for (j in this[0].indices) {
            this[i][j] += other[i][j]
        }
    }
    return raw
}

fun main() {
    try {
        (readMatrix() + readMatrix()).print()
    } catch (e: AssertionError) {
        println(errMsg[0])
    }
}