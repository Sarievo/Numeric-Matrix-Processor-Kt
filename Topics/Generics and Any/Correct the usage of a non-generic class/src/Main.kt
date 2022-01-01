class Holder<T>(var value: T) {

    fun set(newValue: T) {
        value = newValue
    }

    fun get(): T {
        return value
    }
}

fun main() {
    val holder: Holder<Int> = Holder(0)
    holder.set(256)

    // correct the line to make the code compile
    val value: Int = holder.get()

    // do not change
    println(value)
}
