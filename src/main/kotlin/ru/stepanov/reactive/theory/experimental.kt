package ru.stepanov.reactive.theory

import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

fun main() {

    val producer = Mono.fromCallable {
        Thread.sleep(2_000)
        println("я выполняюсь")
        "from external"
    }
            .subscribeOn(Schedulers.boundedElastic())
            .then(Mono.just("from then"))


    producer.subscribe { s -> println(s) }
    Thread.sleep(3_000)

}