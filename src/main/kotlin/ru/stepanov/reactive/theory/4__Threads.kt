package ru.stepanov.reactive.theory

import reactor.core.publisher.Mono

fun main() {

    val producer = Mono.just("hello from: ")

    /** выполнение в том потоке, где вызов subscribe() */
    producer
            .subscribe { s -> println(s + Thread.currentThread().name) }

    Thread {
        producer
                .subscribe { s -> println(s + Thread.currentThread().name) }
    }.also {
        it.start()
        it.join()
    }

}