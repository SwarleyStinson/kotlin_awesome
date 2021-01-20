package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    Flux.range(1, 4)
            .flatMap { t ->
                if (t == 3) Flux.empty<Int>()
                else Flux.just(t)
            }
            .switchIfEmpty { i ->
                println("switch if")
            }
            .map { s -> "[$s]" }
            .subscribe { s -> println(s) }

    Flux.empty<Any>()
            .doOnNext { s -> println(s) }
            .switchIfEmpty { println("Empty") }
            .subscribe()
}