package ru.stepanov.ktawesome.core.scope_functions

fun writeCreationLog2(p: Person) {
    println("A new person ${p.name} was created.")
}

/** Post_Init action */
fun main() {
    Person("Jake", 30, "Android developer")
            .also {
                writeCreationLog2(it)
            }

    // TODO commit from master

    // todo add from feature commit 1
    // todo add from feature commit 2
}