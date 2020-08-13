package ru.stepanov.ktawesome.collections

fun main(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)                

    /** WOW */
    val evenOdd = numbers.partition { it % 2 == 0 }
    println(evenOdd)

    /** AMAZING */
    val (positives, negatives) = numbers.partition { it > 0 }
    println(positives)
    println(negatives)
}