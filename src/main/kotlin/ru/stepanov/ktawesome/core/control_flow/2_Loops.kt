package ru.stepanov.ktawesome.core.control_flow

fun main() {
    val cakes = listOf("carrot", "cheese", "chocolate")

    for (cake in cakes) {
        println("Yummy, it's a $cake cake!")
    }

    for (cake in 1..cakes.size) {
        println(cake.toString() + " - " + cakes[cake - 1])
    }

    val list = ArrayList<String>()
    list.add("Hello")
    list.add(" ")
    list.add("World")
    list.add("!")
    for(s in list){
        print(s)
    }
}