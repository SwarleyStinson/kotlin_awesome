package ru.stepanov.ktawesome.collections

fun main() {
    data class Person(val name: String, val city: String, val phone: String)

    val people = listOf(
            Person("John", "Boston", "1-888"),
            Person("Sarah", "Munich", "49-777"),
            Person("Petr", "SpB", "7-456"),
            Person("Tunchik", "SpB", "7-123"))

    val phoneBook = people.associateBy { it.phone }
    println(phoneBook)

    val cityBook = people.associateBy(Person::phone, Person::city)
    println(cityBook)

    val peopleCities = people.groupBy(Person::city, Person::name)
    println(peopleCities)
}