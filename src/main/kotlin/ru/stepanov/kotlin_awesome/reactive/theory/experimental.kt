package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    val producer =
            Flux.just("Hello, World!")
                    .flatMap {
                        printText("первый - $it")
                        printText("второй - $it")
                    }

    producer.subscribe { s -> println(s) }
}

fun printText(text: String): Mono<String> =
        Mono.just(text).doOnNext {
            println("зашли в метод со значением - $text")
        }