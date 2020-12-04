package ru.stepanov.ktawesome.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Blocking

fun main(){
    GlobalScope.launch { // no block the main thread, aka "Fire and Forget"
        delay(1000L)
        println("World!")
    }
    println("Hello,")

    runBlocking {     // blocks the main thread
        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
    }
}