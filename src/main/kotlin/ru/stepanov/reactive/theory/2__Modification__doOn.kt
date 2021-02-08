package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    val producer = Flux
            .just("World", "exc", "Hello")
            .map { s -> if (s != "exc") s else Flux.error<String>(RuntimeException("bla")) }
//            .map { s -> if (s != "exc") s else throw RuntimeException("bla") }
            .doOnError { error -> println("error occured") }
            .map { s -> "wrapped [$s]" }

    producer.subscribe { s -> println(s) }

    println("\nafter text")

}