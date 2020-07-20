class QueueByList(list: MutableList<Any>) {
    var items: MutableList<Any> = list

    fun isEmpty(): Boolean = items.isEmpty()

    fun size(): Int = items.count()

    override fun toString(): String = items.toString()

    fun enqueue(element: Any) = items.add(element)

    fun dequeue(): Any?{
        return if (this.isEmpty()){
            null
        }else{
            items.removeAt(0)
        }
    }
    fun peek(): Any? = items[0]
}

fun main(args: Array<String>) {
    val exampleQueue = QueueByList(mutableListOf("Tu","Pom", "Pok"))
    // add some integer to the queue.
    println("Enqueue an integer to the list at front.")
    exampleQueue.enqueue(1)
    println("exampleQueue is: $exampleQueue")
    println("Dequeue an item from the list at front.")
    exampleQueue.dequeue()
    println("After exampleQueue is: $exampleQueue")
    println("The peek of exampleQueue is: ${exampleQueue.peek()}")
    println("The size of exampleQueue is: ${exampleQueue.size()}")
    println("The xampleQueue.toString() is: $exampleQueue")
}
