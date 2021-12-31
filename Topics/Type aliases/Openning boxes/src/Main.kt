class BiggerBox() {
    class InnerBox<T>(var items: List<T>) {
        fun getSomethingFromBox(): List<T> {
            return items
        }
    }
}
typealias Box<T> = BiggerBox.InnerBox<T>
fun main() {
    var box = Box(listOf("lion", "rose"))
    //print the number of items
    print(box.getSomethingFromBox().size)
}