package ru.stepanov.ktawesome.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Basic

fun main(args: Array<String>) {
//    basic()
    play()
}

fun basic() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

fun play() {
    val thread1 = Thread {
        GlobalScope.launch {
            var count = 0
            while (count++ < 10) {
                delay(1000L)
                println("корутина1 (поток: ${Thread.currentThread().name}): $count")
            }
        }
    }

    GlobalScope.launch {
        var count = 0
        while (count++ < 3) {
            delay(1000L)
            println("корутина2 (поток: ${Thread.currentThread().name}): $count")
        }
    }
    thread1.start()

    println("Начинаем считать вслух! (поток: ${Thread.currentThread().name})")
    Thread.sleep(4000L) // основной поток засыпает, но корутины в фоне продолжают работать
    thread1.join()
    println("жив ли поток, запустивший корутину1: ${thread1.isAlive}") //нет, но корутина продолжает работать
    Thread.sleep(4000L)
    println("Спасибо! Закончили! (поток: ${Thread.currentThread().name})")
}
