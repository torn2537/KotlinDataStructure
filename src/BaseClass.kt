//open class BaseClass(var name: String) {
//    fun printSomething() {
//        println("Hello $name")
//    }
//    var someValue = 10
//}
//
//class DerivedClass : BaseClass {
//    constructor() : super("John")
//    constructor(name: String): super(name){
//        println("Author name is $name")
//    }
//    fun baseFunction(){
//        printSomething()
//        println("A variable of the base class is $someValue")
//    }
//}
//
//fun main() {
//    val deriveClassWithoutArgument = DerivedClass()
//    deriveClassWithoutArgument.baseFunction()
//    val deriveClassWithArgument = DerivedClass("Misxy")
//    deriveClassWithArgument.baseFunction()
//}

open class BaseClass(val name: String){
    fun testBaseFunc(){
        println("Test a BaseClass function: $name")
    }
}
class Child(private val author: String):BaseClass(author){
    constructor(): this("author")

    fun testChildFunc(){
        println("Test a ChildClass function: $name")
    }
}

fun main() {
    val child = Child()
    child.testBaseFunc()
    child.testChildFunc()
}
