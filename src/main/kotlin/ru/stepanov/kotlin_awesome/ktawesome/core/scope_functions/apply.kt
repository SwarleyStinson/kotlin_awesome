package ru.stepanov.kotlin_awesome.ktawesome.core.scope_functions

import java.lang.StringBuilder

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

    val param1 = "param1"
    val param2 = null

    var result  = StringBuilder("STARTING ")
    param1?.let{ result.append("AND param_1 ") }
    param2?.let{ result.append("AND param_2 ") }

    println(result)
}