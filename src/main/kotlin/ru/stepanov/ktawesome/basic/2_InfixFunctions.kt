package ru.stepanov.ktawesome.basic

fun main() {

    infix fun Int.times(str: String) = str.repeat(this)
    println(2 times "There ")

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia
    println("Sophia likes: " + sophia.likedPeople.stream()
            .map { Person::name }
            .forEach(::println))
}

data class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()

    infix fun likes(other: Person) {
        likedPeople.add(other)
    }
}