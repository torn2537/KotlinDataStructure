class Stacklist {
    val element: MutableList<Any> = mutableListOf()
    fun isEmpty() = element.isEmpty()
    fun size() = element.size
    fun push(item: Any) = element.add(item)
    fun pop(): Any? {
        val item = element.lastOrNull()
        if(!isEmpty()){
            element.removeAt(element.size - 1)
        }
        return item
    }
    fun peek(): Any? = element.lastOrNull()
    override fun toString(): String = element.toString()
}

fun main(args: Array<String>) {
    val stackList2 = Stacklist()
    stackList2.push(1)
    stackList2.push(2)
    stackList2.push(3)
    stackList2.push(4)
    println("This stackList is empty: ${stackList2.isEmpty()}")
    println("This stackList size is: ${stackList2.size()} element(s)")
    println("The pop element is: ${stackList2.pop()}")
}
