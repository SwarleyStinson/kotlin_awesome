package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    Flux.range(1, 4)
            .map { i -> if (i <= 3) i else throw RuntimeException("external unavailable") }
            .doOnError { error -> println("occurred: ${error.message}") }
            .retry(1)
            .onErrorResume { ex -> Flux.just(12) } // some cache value
            .map { i -> "wrapped [$i]" }
            .subscribe { s -> println(s) }

}
