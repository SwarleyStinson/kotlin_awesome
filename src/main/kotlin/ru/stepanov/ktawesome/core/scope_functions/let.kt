package ru.stepanov.ktawesome.core.scope_functions

fun printUpper(s: String) {
    print(s.toUpperCase())
}

fun main() {
    val empty = "test".let {
        printUpper(it)
        it.isEmpty()
    }
    println(" is empty: $empty")


    fun printNonNull(str: String?) {
        println("Printing \"$str\":")

        str?.let {
            print("\t")
            printUpper(it)
            println()
        }
    }
    printNonNull(null)
    printNonNull("my string")
}