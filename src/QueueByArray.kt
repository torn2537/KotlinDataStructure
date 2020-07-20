class QueueByArray {
    private val capacity = 6
    private var queueArr: IntArray = IntArray(capacity)
    private var front = 0
    private var rear = -1
    private var currentSize = 0


    fun enqueue(item: Int) {
        if (isQueueFull()) {
            println("Queue is full, increase capacity...")
            increaseCapacity()
            println("Increased capacity successfully")
        }
        rear++
        if (rear >= queueArr.size && currentSize != queueArr.size) {
            rear = 0
        }
        queueArr[rear] = item
        currentSize++
        println("Adding: $item")
    }

    fun dequeue() {
        if (isQueueEmpty()) {
            println("Underflow ! Unable to remove element from Queue")
        } else {
            front++
            if (front > queueArr.size - 1) {
                println("removed: ${queueArr[front - 1]}")
                front = 0
            } else {
                println("removed: ${queueArr[front - 1]}")
            }
            currentSize--
        }
    }
    fun peek():Int? {
        return if (isQueueEmpty()) {
            println("Underflow ! Unable to remove element from Queue")
            null
        } else {
            return queueArr[front]
        }
    }

    fun size():Int = currentSize

    private fun isQueueFull(): Boolean = currentSize == queueArr.size

    fun isQueueEmpty(): Boolean = currentSize == 0

    private fun increaseCapacity() {

        //create new array with double size as the current one.
        val newCapacity = queueArr.size * 2
        val newArr = intArrayOf(newCapacity)
        //copy elements to new array, copy from rear to front
        var tmpFront = front
        var index = -1
        while (true) {
            newArr[index++] = queueArr[tmpFront]
            tmpFront++
            if (tmpFront == queueArr.size) {
                tmpFront = 0
            }
            if (currentSize == index + 1) {
                break
            }
        }
        //make new array as queue
        queueArr = newArr
        println("New array capacity: ${queueArr.size}")
        //reset front & rear values
        front = 0
        rear = index
    }
}

fun main() {
    val queue = QueueByArray()
    queue.enqueue(4)
    queue.enqueue(56)
    queue.enqueue(67)
    println(queue.size())
    queue.dequeue()
    println(queue.isQueueEmpty())
}
