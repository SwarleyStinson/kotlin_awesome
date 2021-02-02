package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {

    Flux.range(1, 4)
            .flatMap { t ->
                if (t == 3) Flux.empty<Int>()
                else Flux.just(t)
            }
            .doOnNext { s -> println("do on next for [$s]") }
            .switchIfEmpty(Mono.just(44))
            .map { s -> "[$s]" }
            .subscribe { s -> println(s) }

    Flux.empty<Any>()
            .doOnNext { s -> println(s) }
            .switchIfEmpty { println("Empty") }
            .subscribe()

    getMonoEmpty()
            .doOnNext { s -> println(s) }
            .switchIfEmpty(Mono.just(33))
            .map { s -> "[$s]" }
            .subscribe { s -> println(s) }


}