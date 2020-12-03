package ru.stepanov.ktawesome.core.basic

fun printMessage(message: String): Unit {
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}

fun multiply(x: Int, y: Int) = x * y

fun main() {
    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix(prefix = "Log", message = "Hello")

    println(sum(1, 2))
    println("fun multiply(x: Int, y: Int) = x * y    => " + multiply(1, 2))

    System.out.println("Plain old Java code")
    println("Call JAVA-code from kotlin: " + JavaUtils.reverse("54321"))
}