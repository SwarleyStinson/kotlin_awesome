package ru.stepanov.kotlin_awesome.ktawesome.core.functional

fun operation(): (Int) -> Int {
    return ::square
}

fun square(x: Int) = x * x

fun main() {
    val func = operation()
    println(func(2))
}