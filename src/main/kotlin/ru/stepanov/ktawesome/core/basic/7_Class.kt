package ru.stepanov.ktawesome.core.basic

//final by default
class Customer

data class Contact(
        val id: Int,
        var email: String) {
    fun getSomething(): String {
        return "returning_string"
    }

    constructor(id: Int) : this(id, "default@mail.com")
}

fun main() {
    val customer = Customer()

    val contact = Contact(1, "mary@gmail.com")
    val contact_2 = Contact(1)

    println(contact)
    println(contact_2)

    println(contact.id)
    println("Change email because VAR:")
    contact.email = "jane@gmail.com"
    println("                         " + contact)
}