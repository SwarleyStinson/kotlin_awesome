package ru.stepanov.kotlin_awesome.ktawesome.core.basic

fun main() {
    operator fun Int.times(str: String) = str.repeat(this)
    println(2 * "There ")

    operator fun String.get(range: IntRange) = substring(range)  
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])
}