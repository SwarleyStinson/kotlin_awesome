package ru.stepanov.kotlin_awesome.ktawesome.core.control_flow

fun main() {
    val clazz: Clazz? = null

    if (clazz?.param == 10) {
        println("true")
    } else {
        println("false")
    }
}

class Clazz {
    val param: Int = 10
}