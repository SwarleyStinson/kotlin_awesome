package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
//    val producer = getMonoEmpty()
//    val producer = getMonoError()
    val producer = Flux.just(5)

    producer
            .filter { i -> i > 10 }
            .flatMap<Int> { number ->
                println(number)
                Mono.just(55)
            }
            .onErrorResume {
                println("зашли в onErrorResume")
                Mono.just(66)
            }
            .switchIfEmpty(Mono.just(77))
            .subscribe { s -> println("результат: " + s) }


}