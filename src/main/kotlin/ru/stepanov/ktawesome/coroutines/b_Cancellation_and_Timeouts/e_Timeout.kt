package ru.stepanov.ktawesome.coroutines.b_Cancellation_and_Timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    try{
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    } catch (ex: Exception){
        println(ex.message)
    }
}