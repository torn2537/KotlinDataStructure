class Graph(var numOfNodes: Int, var directed: Boolean, var weighted: Boolean) {
    var matrix: Array<Array<Int>> = Array(numOfNodes) { Array(numOfNodes) { 0 } }
    var isSetMatrix: Array<Array<Boolean>> = Array(numOfNodes) { Array(numOfNodes) { false } }

    fun addEdge(source: Int, destination: Int) {
        var valueToAdd = 1
        if (weighted) {
            valueToAdd = 0
        }
        matrix[source][destination] = valueToAdd
        isSetMatrix[source][destination] = true

        if (!directed) {
            matrix[destination][source] = valueToAdd
            isSetMatrix[destination][source] = true
        }
    }

    fun addEdge(source: Int, destination: Int, weight: Int) {
        var valueToAdd: Int = weight
        if (!weighted) {
            valueToAdd = 1
        }
        matrix[source][destination] = valueToAdd
        isSetMatrix[source][destination] = true
        if (!directed) {
            matrix[destination][source] = valueToAdd
            isSetMatrix[destination][source] = true
        }
    }

    fun printMatrix() {
        for (i in matrix.indices) {
            for (j in matrix.indices) {
                if (isSetMatrix[i][j]) {
                    print(isSetMatrix[i][j].toString().padEnd(15))
                } else {
                    print("/".padEnd(15))
                }
            }
            println()
        }
    }

    fun printEdge() {
        for (i in matrix.indices) {
            print("Node $i is connected to: ")
            for (j in matrix.indices) {
                if (isSetMatrix[i][j]) {
                    print("$j ")
                }
            }
            println()
        }
    }

    fun hasEdge(source: Int, destination: Int): Boolean {
        return isSetMatrix[source][destination]
    }

    fun getEdgeValue(source: Int, destination: Int): Int? {
        if (!weighted || isSetMatrix[source][destination]) {
            return null
        }
        return matrix[source][destination]
    }
}


fun main() {
    val graph = Graph(5, directed = false, weighted = true)
    graph.addEdge(0, 2, 19)
    graph.addEdge(0, 3, -2)
    graph.addEdge(0, 3, -2)
    graph.addEdge(1, 3) // The default weight is 0 if weighted == true
    graph.addEdge(1, 4)
    graph.addEdge(2, 3)
    graph.addEdge(3, 4)
    graph.printMatrix()
    graph.printEdge()
    println("Has edge?: ${graph.hasEdge(3, 4)}")

    val items: IntArray = intArrayOf(1, 2, 3, 4, 5)
    val joinedToString = items.fold("Elements:", { acc, i -> "$acc $i" })

    println("joinedToString: $joinedToString")
    val product = items.fold(1, Int::times)
    println("product: $product")

}

