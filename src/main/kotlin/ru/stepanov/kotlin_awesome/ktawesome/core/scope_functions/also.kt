package ru.stepanov.kotlin_awesome.ktawesome.core.scope_functions

fun writeCreationLog(p: Person) {
    println("A new person ${p.name} was created.")
}

/** Post_Init action */
fun main() {
    Person("Jake", 30, "Android developer")
            .also {
                writeCreationLog(it)
            }
}