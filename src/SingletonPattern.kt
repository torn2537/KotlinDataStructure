public class SingletonPattern private constructor(){
    init{
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = SingletonPattern()
    }

    companion object {
        val instance: SingletonPattern by lazy { Holder.INSTANCE }
    }

    var b: String? = null
}

fun main() {
    val first: SingletonPattern = SingletonPattern.instance
    first.b = "Hello Singleton Pattern"

    val second: SingletonPattern = SingletonPattern.instance
    println(first.b)
    println(second.b)
}
