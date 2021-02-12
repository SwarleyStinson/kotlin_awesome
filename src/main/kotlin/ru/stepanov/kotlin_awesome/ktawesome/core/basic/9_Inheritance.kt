package ru.stepanov.kotlin_awesome.ktawesome.core.basic

/** class FINAL by default
 * OPEN for allow inheritance */
open class Dog {

    /** methods also FINAL by default
     * OPEN for allow OVERRIDING*/
    open fun sayHello() {
        println("wow wow!")
    }
}

/** : instead of extends keyword */
class Yorkshire : Dog() {
    override fun sayHello() {
        println("wif wif!")
    }
}

fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()
}