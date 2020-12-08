package ru.stepanov.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Пусть 100_000 корутин напечатают по точке!")
    coroutineScope {
        repeat(100_000) {
            launch {
                delay(5000L)
                print(".")
            }
        }
    }

    println("\nПусть 100_000 потоков напечатают по двоеточию!")
    repeat(100_000) {
        Thread {
            Thread.sleep(1000)
            print(":")
        }.run()
    }


}