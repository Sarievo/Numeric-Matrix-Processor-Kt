package processor

import java.util.*
import kotlin.math.pow

val s = Scanner(System.`in`)
val errMsg = listOf("The operation cannot be performed.")
val prompt = listOf("Enter size of first matrix: ", "Enter first matrix:\n", // 0, 1
                    "Enter size of second matrix: ", "Enter second matrix:\n", "The result is:\n", // 2, 3, 4
                    "Enter size of matrix: ", "Enter matrix:\n", "Enter constant: ", // 5, 6, 7
                    "This matrix doesn't have an inverse.")

object MatrixReader {
    private fun readMatrix(r: Int, c: Int): Array<DoubleArray> {
        val matrix = Array(r) { DoubleArray(c) }
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                matrix[i][j] = s.next().toDouble()
            }
        }
        return matrix
    }

    fun readSingle(): Array<DoubleArray> {
        print(prompt[5])
        val r = s.nextInt()
        val c = s.nextInt()
        print(prompt[6])
        return readMatrix(r, c)
    }

    fun readPair(): Pair<Array<DoubleArray>, Array<DoubleArray>> {
        print(prompt[0])
        var r = s.nextInt()
        var c = s.nextInt()
        print(prompt[1])
        val m1 = readMatrix(r, c)

        print(prompt[2])
        r = s.nextInt()
        c = s.nextInt()
        print(prompt[3])
        return Pair(m1, readMatrix(r, c))
    }
}

fun main() {
    while (true) {
        println("1. Add matrices")
        println("2. Multiply matrix by a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrices")
        println("5. Calculate a determinant")
        println("6. Inverse matrix")
        println("0. Exit")
        print("Your choice: ")
        try {
            when (s.nextInt()) {
                1 -> addByM()
                2 -> mulByC()
                3 -> mulByM()
                4 -> { transM(); break }
                5 -> calDet()
                6 -> { calInv(); break }
                0 -> break
            }
        } catch (e: AssertionError) {
            println(errMsg[0])
        }
    }
}

fun addByM() {
    val pair = MatrixReader.readPair()
    print(prompt[4])
    (pair.first + pair.second).print()
}

operator fun Array<DoubleArray>.plus(other: Array<DoubleArray>): Array<DoubleArray> {
    assert(this.size == other.size && this[0].size == other[0].size)
    val raw = this.clone()
    for (i in raw.indices) {
        for (j in raw[0].indices) {
            raw[i][j] += other[i][j]
        }
    }
    return raw
}

fun mulByC() {
    val m = MatrixReader.readSingle()
    print(prompt[7])
    val c = s.next().toDouble()
    print(prompt[4])
    (m * c).print()
}

operator fun Array<DoubleArray>.times(c: Double): Array<DoubleArray> {
    val raw = this.clone()
    for (i in raw.indices) {
        for (j in raw[0].indices) {
            raw[i][j] *= c
        }
    }
    return raw
}

fun mulByM() {
    val pair = MatrixReader.readPair()
    print(prompt[4])
    (pair.first * pair.second).print()
}

operator fun Array<DoubleArray>.times(other: Array<DoubleArray>): Array<DoubleArray> {
    assert(this[0].size == other.size)
    val raw = Array(this.size) { DoubleArray(other[0].size) }
    for (k in this.indices) {
        for (j in other[0].indices) {
            for (i in this[0].indices) raw[k][j] += this[k][i] * other[i][j]
    }}
    return raw
}

fun Array<DoubleArray>.print() = run {
    for (i in this) {
        for (j in i) print(String.format("%.2f ", j)); println()
    }
}

fun transM() {
    while (true) {
        println("1. Main diagonal")
        println("2. Side diagonal")
        println("3. Vertical line")
        println("4. Horizontal line")
        println("0. Exit")
        println("Your choice: ")
        when (s.nextInt()) {
            1 -> MatrixReader.readSingle().t().print()
            2 -> MatrixReader.readSingle().t(true).print()
            3 -> MatrixReader.readSingle().f().print()
            4 -> MatrixReader.readSingle().f(true).print()
            0 -> return
        }
    }
}

fun Array<DoubleArray>.t(isSide: Boolean = false): Array<DoubleArray> {
    return if (isSide) {
        this.t().f().f(true)
    } else {
        val matrix = this.flipped()
        for (i in 0 until this.size) {
            for (j in 0 until this[0].size) { matrix[j][i] = this[i][j] }
        }
        matrix
    }
}

fun Array<DoubleArray>.f(isVertical: Boolean = false): Array<DoubleArray> {
    return if (isVertical) {
        val matrix = this.flipped()
        for (i in 0 until this.size) {
            System.arraycopy(
                this[i], 0, matrix[matrix.size - 1 - i], 0, this[0].size
            )
        }
        matrix
    } else {
        val matrix = this.flipped()
        for (i in 0 until this.size) {
            for (j in 0 until this[0].size) {
                matrix[i][matrix[0].size - 1 - j] = this[i][j]
            } }
        matrix
    }
}

fun Array<DoubleArray>.flipped(): Array<DoubleArray> = Array(this[0].size) { DoubleArray(this.size) }

fun calDet() {
    val m = MatrixReader.readSingle()
    print(prompt[4])
    println(m.det())
}

// return the determinant by the corresponding matrix
fun Array<DoubleArray>.det(): Double {
    return if (this.size == 2) { // Trivial
        this[0][0] * this[1][1] - this[0][1] * this[1][0]
    } else {
        var sum = 0.0 // iterate *column times
        for (i in this.indices) sum += this[0][i] * this.getCofactor(0, i)
        sum
    }
}

// return the determinant by the corresponding indices of the corresponding matrix
fun Array<DoubleArray>.getCofactor(i1: Int, i2: Int): Double =
    (-1.0).pow(((i1 + i2) % 2).toDouble()) * this.getSubMatrix(i1, i2).det()

// return the subMatrix by the corresponding indices
fun Array<DoubleArray>.getSubMatrix(i1: Int, i2: Int): Array<DoubleArray> {
    val sub = Array(this.size - 1) { DoubleArray(this.size - 1) }
    for (i in this.indices) {
        for (j in this.indices) {
            if (i < i1) {
                if (j < i2)
                    sub[i][j] = this[i][j]
                else if (j > i2)
                    sub[i][j - 1] = this[i][j]
            } else if (i > i1) {
                if (j < i2)
                    sub[i - 1][j] = this[i][j]
                else if (j > i2)
                    sub[i - 1][j - 1] = this[i][j]
            }
        }
    }
    return sub
}

fun calInv() {
    val m = MatrixReader.readSingle().inv()
    if (m.isNullOrEmpty()) println(errMsg[8])
    else {
        print(prompt[4])
        m.print()
    }
}

fun Array<DoubleArray>.inv(): Array<DoubleArray>? {
    val det = this.det()
    if (det == 0.0) return null
    var m = Array(this.size) { DoubleArray(this.size) }
    for (i in this.indices) { // calculate the adjacent matrix
        for (j in this.indices) { m[i][j] = this.getCofactor(i, j) }
    }
    m = m.t() // main diagonal transpose m1
    for (i in this.indices) { // calculate the adjacent matrix
        for (j in this.indices) { m[i][j] = m[i][j] / det }
    }
    return m
}