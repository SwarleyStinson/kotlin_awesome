package ru.stepanov.ktawesome.special_classes

object DoAuth {
    private var a: Int = 0
    var b: Int = 1

    fun login(): Int {
        a = 12
        return a
    }

    fun takeParams(username: String, password: String) {
        println("input Auth parameters = $username:$password")
    }

    override fun toString(): String {
        return "{a:${this.a}, b:${this.b}}"
    }
}

fun main() {
    DoAuth.takeParams("foo", "qwerty")

    println("DoAuth = $DoAuth")
    val result = DoAuth.login()
    println("b = ${DoAuth.b}")
    println("result = $result")
}