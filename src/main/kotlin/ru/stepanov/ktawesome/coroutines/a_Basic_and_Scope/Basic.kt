package ru.stepanov.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.*

class Basic

fun main(args: Array<String>) {
//    basic()
//    play()
    println("before")
    runBlocking {
        withTimeout(3_000){
            while(true){
                print(".")
                Thread.sleep(1_000)
            }
        }
    }
    println("after")
}

fun basic() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!")
        delay(500L)
        cancel() // отменит корутину, потому что ругаться плохо
        println("BITCH")
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

fun play() {
    val thread1 = Thread {
        println("(поток: ${Thread.currentThread().name}): запускаю корутину1 !!")
        GlobalScope.launch {
            var count = 0
            while (count++ < 10) {
                delay(1000L)
                println("(поток: ${Thread.currentThread().name}): корутина1 - $count")
            }
        }
    }

    GlobalScope.launch {
        var count = 0
        while (count++ < 3) {
            delay(1000L)
            println("(поток: ${Thread.currentThread().name}): корутина2 - $count")
        }
    }
    thread1.start()

    println("(поток: ${Thread.currentThread().name}): Начинаем считать вслух! ")
    Thread.sleep(3000L) // основной поток засыпает, но корутины в фоне продолжают работать
    thread1.join()
    println("жив ли ${thread1.name}: ${thread1.isAlive} - ${thread1.state}") //нет, но корутина продолжает работать
    Thread.sleep(3000L)
    println("(поток: ${Thread.currentThread().name}): Спасибо! Закончили!")
}
