package ru.stepanov.ktawesome.coroutines.b_Cancellation_and_Timeouts

import kotlinx.coroutines.*

/**
 * нет точек, для отмены корутины !!
 *                                 напр., delay()
 *
 * 2 approach to make coroutine code cancellable:
 *                          1) periodically invoke a suspending function that checks for cancellation
 *                          2) explicit check a cancelation status
 * */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0

        /** Good practice */
        while (isActive) {
        /** Bad practice. computation loop, just wastes CPU */
//        while (i < 5) {

            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")


    withContext(NonCancellable) {

    }
}