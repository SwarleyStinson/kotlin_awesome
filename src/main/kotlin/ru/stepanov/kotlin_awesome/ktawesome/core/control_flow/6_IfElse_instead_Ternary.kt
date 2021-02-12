package ru.stepanov.kotlin_awesome.ktawesome.core.control_flow

fun main() {
    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }
    println(max(99, -42))

    fun maxShort(a: Int, b: Int) = if (a > b) a else b
    println(max(99, -42))
}