package ru.stepanov.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Scope

fun main() {

    runBlocking { //todo implicit .join()  -> дождеться завершения всех созданных корутин, и только после продолжит выполнение
        launch {
            delay(2_000L)
            println(", World! ${Thread.currentThread().name}")
        }
        print("Hello")
    }

    println("управление вернулось в ${Thread.currentThread().name} поток")
    Thread.sleep(2_000L)
    println("Coroutine.join() was implicitly called")

}