package ru.stepanov.kotlin_awesome.ktawesome.coroutines.b_Cancellation_and_Timeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    init {
        acquired++
    } // Acquire the resource

    fun close() {
        acquired--
    } // Release the resource
}

fun main() {
    runBlocking {
        repeat(100_000) {
//            launch { badMemoryLeek() }
            launch { gracefullyFinish() }
        }
    }

    /** Outside of runBlocking all coroutines have completed */
    println(acquired)
}

suspend fun badMemoryLeek() {
    val resource = withTimeout(60) {
        delay(50)
        Resource() // Acquire a resource and return it from withTimeout block
    }
    resource.close() // Release the resource
}

suspend fun gracefullyFinish() {
    var resource: Resource? = null // Not acquired yet
    try {
        withTimeout(60) {
            delay(50)
            resource = Resource()
        }
    } finally {
        resource?.close()
    }
}
