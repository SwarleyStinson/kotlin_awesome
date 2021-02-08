package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

fun main() {

    val producer =
            Flux.empty<Int>()
                    .switchIfEmpty {
                        println("зашли в switchIfEmpty")
                        Flux.error<Int>(RuntimeException("bla bla"))
                    }
                    .collectList()

    producer
            .timeout(Duration.ofSeconds(5))
            .onErrorResume {
                println("поток был заблокирован")
                Mono.just(listOf())
            }
            .block()

    println("after")

}