package ru.stepanov.ktawesome.coroutines.b_Cancellation_and_Timeouts

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/** Code is sequential by default */
fun main() {
    print("sequential: ").also { calculateSequential() }
    print("async: ").also { calculateAsync() }
//    print("async LAZY WITHOUT explicit start() invoking: ").also { calculateAsyncLazy(false) }
//    print("async LAZY WITH explicit start() invoking: ").also { calculateAsyncLazy(true) }
    Thread.sleep(4_000L)
}

fun calculateSequential() {
    runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            print("${one + two} ")
        }
        println("Completed in $time ms")
    }
}

fun calculateAsync() {
    GlobalScope.launch {
        val time = measureTimeMillis {
            println("calculateAsync - ${Thread.currentThread().name}")
            val one = async {
                println("one - ${Thread.currentThread().name}")
                doSomethingUsefulOne()
            }
            val two = async {
                println("two - ${Thread.currentThread().name}")
                doSomethingUsefulTwo()
            }
            print("${one.await() + two.await()} ")
        }
        println("Completed in $time ms")
    }
}

fun calculateAsyncLazy(startExplicit: Boolean) {
    runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
            if (startExplicit) {
                one.start()
                two.start()
            }
            print("${one.await() + two.await()} ")
        }
        println("Completed in $time ms")
    }
}

suspend fun doSomethingUsefulOne(): Int {
//    delay(1000L)
    Thread.sleep(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
//    delay(1000L)
    Thread.sleep(1000L)
    return 29
}