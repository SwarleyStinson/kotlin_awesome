package ru.stepanov.ktawesome.core.basic

open class Tiger(val name: String, val origin: String) {
    fun sayHello() {
        println("A tiger $name from $origin says: grrhhh!")
    }
}

class SiberianTiger(name: String) : Tiger(name, "Ussuriysk")

fun main() {
    val timur: Tiger = Tiger("no_name","any beautiful forest")
    timur.sayHello()

    val amur: Tiger = SiberianTiger("Amur")
    amur.sayHello()
}