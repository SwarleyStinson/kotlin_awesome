package ru.stepanov.ktawesome.coroutines.b_Cancellation_and_Timeouts

import kotlinx.coroutines.*

fun main() = runBlocking {
//    basic()
    global()
}


fun basic() {
    runBlocking {
        val job =
                launch {
                    launch {
                        repeat(1000) { i ->
                            delay(500L)
                            println("children_job: I`m too")
                        }
                    }
                    repeat(1000) { i ->
                        println("job: I'm sleeping $i ...")
                        delay(500L)
                    }
                }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }
}

fun global() {
    runBlocking {
        runBlocking {
            GlobalScope.launch {
                repeat(1000) { i ->
                    println("global job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            println("джоба запущен")
        }
        delay(1_200L)
        GlobalScope.cancel()
        delay(1_200L)
        println("подождал еще для верности")
    }
}