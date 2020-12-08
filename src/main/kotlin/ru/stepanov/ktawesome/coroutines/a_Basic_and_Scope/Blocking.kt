package ru.stepanov.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Blocking

fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(2000L)
        println("World!")
    }

    println("Hello,")
    delay(1000L)
    job.join()
}