package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    val producer = Flux.empty<Int>()
            .switchIfEmpty {
                println("зашли")
                Flux.error<Int>(RuntimeException("bla bla"))
            }
//            .flatMap { number ->
//                println("получен: $number")
//                Flux.just(number + 100)
//            }
//            .onErrorResume { Flux.just(100) }
//            .doOnNext{number -> println("получили $number")}
//            .doOnError{error -> println("словили ${error.message}")}
            .collectList()

    producer.block()

    println("after")

}