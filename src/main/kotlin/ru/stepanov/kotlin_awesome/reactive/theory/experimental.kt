package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {

    val producer =
            Flux.empty<String>()
                    .flatMap {
                        println("зашли во flatMap")
                        Flux.just("stroka")
                    }
                    .switchIfEmpty(Flux.error<String>(RuntimeException("bla")))
                    .collectList()
                    .switchIfEmpty(Mono.error<List<String>>(RuntimeException("bla")))
                    .onErrorResume { Mono.just(listOf("после ошибки")) }

    producer.subscribe { s -> println(s) }
    Thread.sleep(2_000)
}