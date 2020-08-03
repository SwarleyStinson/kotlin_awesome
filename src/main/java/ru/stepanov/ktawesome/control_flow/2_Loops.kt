package ru.stepanov.ktawesome.control_flow

fun main() {
    val cakes = listOf("carrot", "cheese", "chocolate")

    for (cake in cakes) {                               // 1
        println("Yummy, it's a $cake cake!")
    }
    for (cake in 1..cakes.size) {                               // 1
        println(cake.toString() + " - " + cakes[cake - 1])
    }
}