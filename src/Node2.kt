class Node2(
        var key: Int,
        var left: Node2? = null,
        var right: Node2? = null) {

    /**
     * Return a node with given value. If no such node exists, return null.
     * @param value
     */
    fun find(value: Int): Node2? = when {
        this.key > value -> left?.find(value)
        this.key < value -> right?.find(value)
        else -> this

    }

    /**
     * Insert a given value into the tree.
     * After insertion, the tree should contain a node with the given value.
     * If the tree already contains the given value, nothing is performed.
     * @param value
     */
    fun insert(value: Int) {
        when {
            value > key -> if(right == null) right = Node2(value) else right?.insert(value)
            value < key -> if(left == null) left = Node2(value) else left?.insert(value)
        }
    }

    /**
     * Delete the value from the given tree. If the tree does not contain the value, the tree remains unchanged.
     * @param value
     */
    fun delete(value: Int) {
        when {
            value > key -> scan(value, right, this)
            value < key -> scan(value, left, this)
            else -> removeNode(this, null)
        }
    }

    /**
     * Scan the tree in the search of the given value.
     * @param value
     * @param node sub-tree that potentially might contain the sought value
     * @param parent node's parent
     */
    private fun scan(value: Int, node: Node2?, parent: Node2?) {
        if (node == null) {
            println("value $value seems not present in the tree.")
            return
        }
        when {
            value > node.key -> scan(value, node.right, node)
            value < node.key -> scan(value, node.left, node)
            value == node.key -> removeNode(node, parent)
        }

    }

    /**
     * Remove the node.
     *
     * Removal process depends on how many children the node has.
     *
     * @param node node that is to be removed
     * @param parent parent of the node to be removed
     */
    private fun removeNode(node: Node2, parent: Node2?) {
        node.left?.let { leftChild ->
            run {
                node.right?.let {
                    removeTwoChildNode(node)
                } ?: removeSingleChildNode(node, leftChild)
            }
        } ?: run {
            node.right?.let { rightChild -> removeSingleChildNode(node, rightChild) } ?: removeNoChildNode(node, parent)
        }


    }

    /**
     * Remove the node without children.
     * @param node
     * @param parent
     */
    private fun removeNoChildNode(node: Node2, parent: Node2?) {
        parent?.let { p ->
            if (node == p.left) {
                p.left = null
            } else if (node == p.right) {
                p.right = null
            }
        } ?: throw IllegalStateException(
                "Can not remove the root node without child nodes")

    }

    /**
     * Remove a node that has two children.
     *
     * The process of elimination is to find the biggest key in the left sub-tree and replace the key of the
     * node that is to be deleted with that key.
     */
    private fun removeTwoChildNode(node: Node2) {
        val leftChild = node.left!!
        leftChild.right?.let {
            val maxParent = findParentOfMaxChild(leftChild)
            maxParent.right?.let {
                node.key = it.key
                maxParent.right = null
            } ?: throw IllegalStateException("Node with max child must have the right child!")

        } ?: run {
            node.key = leftChild.key
            node.left = leftChild.left
        }

    }

    /**
     * Return a node whose right child contains the biggest value in the given sub-tree.
     * Assume that the node n has a non-null right child.
     *
     * @param n
     */
    private fun findParentOfMaxChild(n: Node2): Node2 {
        return n.right?.let { r -> r.right?.let { findParentOfMaxChild(r) } ?: n }
                ?: throw IllegalArgumentException("Right child must be non-null")

    }

    /**
     * Remove a parent that has only one child.
     * Removal is effectively is just coping the data from the child parent to the parent parent.
     * @param parent Node to be deleted. Assume that it has just one child
     * @param child Assume it is a child of the parent
     */
    private fun removeSingleChildNode(parent: Node2, child: Node2) {
        parent.key = child.key
        parent.left = child.left
        parent.right = child.right
    }

    fun visit(): Array<Int> {
        val a = left?.visit() ?: emptyArray()
        val b = right?.visit() ?: emptyArray()
        return a + arrayOf(key) + b
    }
}

fun main() {
    val tree = Node2(4)
    val keys = arrayOf(8, 15, 21, 3, 7, 2, 5, 10, 2, 3, 4, 6, 11)
    for (key in keys) {
        tree.insert(key)
    }
    val node = tree.find(4)!!
    println("Node with value ${node.key} [left = ${node.left?.key}, right = ${node.right?.key}]")
    println("Delete node with key = 3")
    node.delete(3)
    print("Tree content after the node elimination: ")
    println(tree.visit().joinToString { it.toString() })
}
