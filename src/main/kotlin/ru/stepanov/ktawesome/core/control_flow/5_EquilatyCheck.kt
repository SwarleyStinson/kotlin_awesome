package ru.stepanov.ktawesome.core.control_flow

fun main() {
    val authors = setOf("Shakespeare", "Hemingway", "Twain")
    val writers = setOf("Twain", "Shakespeare", "Hemingway")

    /** Structural comparison */
    println(authors == writers)

    /** Referentia comparison */
    println(authors === writers)
}