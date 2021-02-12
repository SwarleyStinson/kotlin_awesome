package ru.stepanov.kotlin_awesome.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Scope

fun main() {

    runBlocking { //todo implicit .join()  ->
        /** todo дождеться завершения всех созданных корутин, и только после продолжит выполнение
         *                            только если они не GlobalScope !!!
         */
        launch {
            delay(2_000L)
            println(", World!")
        }
        print("Hello")
    }

    println("управление вернулось в ${Thread.currentThread().name} поток")
    Thread.sleep(2_000L)
    println("Coroutine.join() was implicitly called")

}