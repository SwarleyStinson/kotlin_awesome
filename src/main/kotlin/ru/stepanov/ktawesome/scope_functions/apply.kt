package ru.stepanov.ktawesome.scope_functions

data class Person(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

/** just Builder */
fun main() {
    val stringDescription = Person().apply {
        name = "Jake"
        age = 30
        about = "Android developer"
    }.toString()
    println(stringDescription)
}