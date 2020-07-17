class Node<T>(var value: T) {
    var next: Node<T>? = null
    var previous: Node<T>? = null
}

class LinkedList<T> {
    private var head: Node<T>? = null
    var isEmpty: Boolean = head == null
    fun first(): Node<T>? = head
    fun last(): Node<T>? {
        var node: Node<T>? = head
        if (node != null) {
            while (node?.next != null) {
                node = node.next
            }
            return node
        } else {
            return null
        }
    }

    fun count(): Int {
        var node: Node<T>? = head
        if (node != null) {
            var counter = 1
            while (node?.next != null) {
                node = node.next
                counter += 1
            }
            return counter
        } else {
            return 0
        }
    }

    fun getNodeAtIndex(index: Int): Node<T>? {
        if (index >= 0) {
            var node: Node<T>? = head
            var i: Int = index
            while (node != null) {
                if (i == 0) {
                    return node
                }
                i -= 1
                node = node.next
            }
        }
        return null
    }

    fun append(value: T) {
        val newNode = Node(value)
        val lastNode = this.last()
        if (lastNode != null) {
            newNode.previous = lastNode
            lastNode.next = newNode
        } else {
            head = newNode
        }
    }

    fun removeAll() {
        head = null
    }

    fun removeNode(node: Node<T>): T {
        val prev: Node<T>? = node.previous
        val next: Node<T>? = node.next
        if (prev != null) {
            prev.next = prev
        } else {
            head = next
        }
        next?.previous = prev
        node.previous = null
        node.next = null
        return node.value
    }

    fun removeLast(): T? {
        val lastNode: Node<T>? = this.last()
        if (lastNode != null) {
            return removeNode(lastNode)
        } else {
            return null
        }
    }

    override fun toString(): String {
        var s = "["
        var node = head
        while (node != null) {
            s += "${node.value}"
            node = node.next
            if (node != null) {
                s += ", "
            }
        }
        return "$s]"
    }
}

fun main() {
    val linkedList: LinkedList<Int> = LinkedList()
    linkedList.append(1)
    println(linkedList)
    linkedList.append(2)
    println(linkedList)
    linkedList.append(3)
    println(linkedList)
    linkedList.append(4)
    println(linkedList)
    println("first item: ${linkedList.first()?.value}")
    println("second item: ${linkedList.first()?.next?.value}")
    println("penultimate item: ${linkedList.last()?.previous?.value}")
    println("\n4th item: ${linkedList.getNodeAtIndex(3)?.value}")
    println("\nthe list has ${linkedList.count()} items")
}
