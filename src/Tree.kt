class Tree<T>(private val value: T) {
    var parent: Tree<T>? = null
    private var children: MutableList<Tree<T>> = mutableListOf()

    fun addChild(node: Tree<T>){
        children.add(node)
        node.parent = this
    }

    override fun toString(): String {
        var s = "$value"
        if(children.isNotEmpty()){
            s += " {" + children.map { it.toString() } + "}"
        }
        return s
    }
}

fun main() {
    val milkTree: Tree<String> = Tree("Milk")
    val beverageNode: Tree<String> = Tree("Beverage")
    val curdNode: Tree<String> = Tree("Curd")
    milkTree.addChild(beverageNode)
    milkTree.addChild(curdNode)
    println(milkTree)
}
