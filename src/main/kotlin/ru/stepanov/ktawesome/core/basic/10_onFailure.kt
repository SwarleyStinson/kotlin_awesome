package ru.stepanov.ktawesome.core.basic

fun main() {
    val bool: Boolean = runCatching {
        throw RuntimeException("sdfsdf")
    }.isSuccess

    println(bool)
}