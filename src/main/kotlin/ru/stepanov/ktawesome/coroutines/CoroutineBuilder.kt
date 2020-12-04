package ru.stepanov.ktawesome.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class CoroutineBuilder

fun main() {
    GlobalScope.launch { }   // coroutine lifetime is limited
    GlobalScope.async { }    //                              only by the lifetime of all application

    /** что-то из пакета kotlinx-coroutines-android */
//    MainScope().launch { }
//    MainScope().async { }

//    CoroutineScope().launch {  }
//    CoroutineScope().async {  }

    thread {
        //todo НЕЛЬЗЯ: Suspend function 'delay' should be called only from a coroutine or another suspend function
        //delay(1000L)
    }
}