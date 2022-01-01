class Box<T : Animal> {
    var listOfT = mutableListOf<T>()
    fun add(t: T) {
        listOfT.add(t)
    }
}

// Don't change the class below
open class Animal
class Cat: Animal()
