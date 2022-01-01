// change the code below
class SmartKitchen<T> where T : Logger, T : Cooker {
    fun finishCooking(device: T) {
        device.log()
    }
}

// don't change it!
interface Logger { fun log() }
interface Cooker { fun cook() }

class Microwave: Logger, Cooker {
    override fun log() = println("Microwave finished cooking!")
    override fun cook() = println("Microwave is  cooking")
}
class MultiCooker: Logger, Cooker {
    override fun log() = println("Multicooker finished cooking!")
    override fun cook() = println("Multicooker is  cooking")
}
