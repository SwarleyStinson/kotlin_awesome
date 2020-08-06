package ru.stepanov.ktawesome.scope_functions

fun writeCreationLog(p: Person) {
    println("A new person ${p.name} was created.")
}

fun main() {
    Person("Jake", 30, "Android developer")
            .also {
                writeCreationLog(it)
            }
}