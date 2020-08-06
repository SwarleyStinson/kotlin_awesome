package ru.stepanov.ktawesome.scope_functions

/** The difference is that inside run the object is accessed by this.
 *  This is useful when you want to call the object's methods rather than pass it as an argument.
 */
fun main() {
    fun getNullableLength(ns: String?) {
        println("for \"$ns\":")
        val run = ns?.run {                                                  // 1
            println("\tis empty? " + isEmpty())                    // 2
            println("\tlength = $length")
            length                                                 // 3
        }
        println("run: $run \n***********")
    }

    getNullableLength(null)
    getNullableLength("")
    getNullableLength("some string with Kotlin")
}