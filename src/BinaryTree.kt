class Node3(var value: Int) {
    var left: Node3? = null
    var right: Node3? = null
}

class BinaryTree {
    private var root: Node3? = null

    private fun addElement(currentNode: Node3?, value: Int): Node3 {
        return if (currentNode == null) {
            Node3(value)
        } else {
            when {
                value < currentNode.value -> currentNode.left = addElement(currentNode.left, value)
                value > currentNode.value -> currentNode.right = addElement(currentNode.right, value)
            }
            currentNode
        }
    }

    fun add(value: Int) {
        root = addElement(root, value)
    }

    private fun containNodeRecursive(currentNode: Node3?, value: Int): Boolean {
        if (currentNode == null) return false
        else if (currentNode.value == value) return true

        return if (value < currentNode.value) containNodeRecursive(currentNode.left, value)
        else containNodeRecursive(currentNode.right, value)
    }

    fun containsNode(value: Int): Boolean {
        return containNodeRecursive(root, value)
    }

    private fun isNull(currentNode: Node3?): Boolean =  currentNode == null


    private fun deleteRecursive(currentNode: Node3?, value: Int): Node3? {
        if (isNull(currentNode)) {
            return null
        }
        if (currentNode!!.value == value) {
            return when {
                //Case 1: no children
                currentNode.left == null && currentNode.right == null -> null
                // Case 2: only 1 child
                currentNode.right == null -> currentNode.left
                currentNode.left == null -> currentNode.right
                // Case 3: 2 children
                else -> {
                    currentNode.value = findSmallestValue(currentNode)
                    currentNode.right = deleteRecursive(currentNode.right, value)
                    currentNode
                }
            }
        }
        return if (value < currentNode.value) {
            currentNode.left = deleteRecursive(currentNode.left, value)
            currentNode
        } else {
            currentNode.right = deleteRecursive(currentNode.right, value)
            currentNode
        }
    }

    fun delete(value: Int) {
        root = deleteRecursive(root, value)
    }

    private fun findSmallestValue(root: Node3): Int = if (root.left == null) root.value else findSmallestValue(root.left!!)

    private fun visit(node: Node3) = print(" ${node.value} ")


    fun traverseDFSInOrder() = traverseInOrder(root)

    fun traverseDFSPreOrder() = traversePreOrder(root)

    fun traverseDFSPostOrder() = traversePostOrder(root)

    fun traverseBFS() = traverseLevelOrder()

    private fun traversePostOrder(node: Node3?) {
        if (node != null) {
            traversePostOrder(node.left)
            traversePostOrder(node.right)
            visit(node)
        }
    }

    private fun traversePreOrder(node: Node3?) {
        if (node != null) {
            visit(node)
            traversePreOrder(node.left)
            traversePreOrder(node.right)
        }
    }

    private fun traverseInOrder(node: Node3?) {
        if (node != null) {
            traverseInOrder(node.left)
            visit(node)
            traverseInOrder(node.right)
        }
    }

    private fun traverseLevelOrder(){
        if(root == null) return
        val queue = QueueByList(mutableListOf(root!!))
        while (!queue.isEmpty()){
            val node: Node3 = queue.dequeue() as Node3
            print(" ${node.value} ")
            if(node.left != null){
                queue.enqueue(node.left!!)
            }
            if(node.right != null){
                queue.enqueue(node.right!!)
            }
        }

    }

}

fun main() {
    val bt = BinaryTree()
    bt.add(6)
    bt.add(4)
    bt.add(8)
    bt.add(3)
    bt.add(5)
    bt.add(7)
    bt.add(9)
    val num = 9
    println("The  Binary tree contains $num is: ${bt.containsNode(num)}")
    //bt.delete(9)
    //println("After deleted $num")
    println("The  Binary tree contains $num is: ${bt.containsNode(num)}")
    println("DFS (InOrder) of the tree: ${bt.traverseDFSInOrder()}")
    println("DFS (PreOrder) of the tree: ${bt.traverseDFSPreOrder()}")
    println("DFS (PostOrder) of the tree: ${bt.traverseDFSPostOrder()}")
    println("BFS of the tree: ${bt.traverseBFS()}")

}
