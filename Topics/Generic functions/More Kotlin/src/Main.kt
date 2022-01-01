// implement your function here
fun <T> countItem(list: List<T>, item: T): Int {
    return list.count { it == item }
}
