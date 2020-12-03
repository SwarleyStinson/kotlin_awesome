package ru.stepanov.ktawesome.core.basic

fun main() {
    var a: String = "initial_value"
    var a2 = "second_variable"
    println(a)
    println(a2)

    val b: Int = 1
    val c = 3

    val d: Int
    if (someCondition()) {
        d = 1
    } else {
        d = 2
    }
    println(d)
}

fun someCondition(): Boolean {
    return true
}