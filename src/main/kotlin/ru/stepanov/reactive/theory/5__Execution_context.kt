package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {

    val s = Schedulers.newParallel("test-parallel", 4)

    /** publishOn() */
    val producer = Flux
            .range(1, 3)
            .map { i -> "'${10 + i} [" + Thread.currentThread().name + "]'" }
            .publishOn(s)
            .map { i -> "Value $i from thread: [" + Thread.currentThread().name + "]" }

    producer.subscribe { s -> println(s) }
    Thread {
        producer.subscribe { s -> println(s) }
    }.also {
        it.start()
        it.join()
    }

    /** subscribeOn() */
    val producer2 = Flux.range(1, 3)
            .map { i -> "'${10 + i} [" + Thread.currentThread().name + "]'" }
            .subscribeOn(s)
            .map { i -> "Value $i from thread: [" + Thread.currentThread().name + "]" }

    producer2.subscribe { s -> println(s) }
    Thread {
        producer2.subscribe { s -> println(s) }
    }.also {
        it.start()
        it.join()
    }

}