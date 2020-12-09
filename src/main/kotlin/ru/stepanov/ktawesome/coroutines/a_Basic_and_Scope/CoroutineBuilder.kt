package ru.stepanov.ktawesome.coroutines.a_Basic_and_Scope

import kotlinx.coroutines.*
import kotlin.concurrent.thread

class CoroutineBuilder

suspend fun main() {

    /**  GlobalScope
    coroutine lifetime is limited
    only by the lifetime of all application
     */
    val job: Job = GlobalScope.launch { }
    job.isActive
    job.cancel()

    val deferred: Deferred<Unit> = GlobalScope.async { /* light-weight future. Promise to provide a result later */ }
    deferred.await()
    deferred.cancel()

    /** CoroutineScope
    coroutine lifetime is limited
    by parent coroutine
     */
    CoroutineScope(NonCancellable).launch { }
    CoroutineScope(NonCancellable).launch { }
    CoroutineScope(NonCancellable).async { }

    runBlocking {
        launch {}
        withTimeout(1_000L) {}
        withTimeoutOrNull(2_000L) {}
        withContext(NonCancellable) {}
        coroutineScope { }
    }


    /** что-то из пакета kotlinx-coroutines-android */
//    MainScope().launch { }
//    MainScope().async { }


    thread {
        //todo НЕЛЬЗЯ: Suspend function 'delay' should be called only from a coroutine or another suspend function
        //delay(1000L)
    }
}