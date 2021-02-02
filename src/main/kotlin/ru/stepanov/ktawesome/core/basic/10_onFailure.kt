package ru.stepanov.ktawesome.core.basic

fun main() {
    val bool: Boolean = runCatching {
        runCatching {
            throw RuntimeException("sdfsdf")
        }.onFailure {
            throw it
        }
    }.onFailure {

    }.isSuccess

    println(bool)
}