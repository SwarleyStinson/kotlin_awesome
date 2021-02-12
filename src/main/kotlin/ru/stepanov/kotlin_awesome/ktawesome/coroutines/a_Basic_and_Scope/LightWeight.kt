package ru.stepanov.kotlin_awesome.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Пусть 100_000 корутин напечатают по точке!")
    coroutineScope {
        repeat(100_000) {
            launch {
                delay(2_000L)
                print(".")
            }
        }
    }

    println("\nПусть 100_000 потоков напечатают по двоеточию!")
    for (i in 0..100_000) {
        if (i % 1_000 == 0) {
            println(Runtime.getRuntime().freeMemory())
        }
        Thread { Thread.sleep(60_000) }.start()
    }


}