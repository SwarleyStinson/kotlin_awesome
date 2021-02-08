package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2

fun main() {
    val publisher =
            Flux.range(1, 5)
                    .flatMap { element ->
                        Mono.zip(
                                /** имитируем внешний ресурс */
                                Mono.fromCallable {
                                    Thread.sleep(1_000)
                                    element * 9
                                }.subscribeOn(Schedulers.boundedElastic()),

                                Mono.fromCallable {
                                    Thread.sleep(2_000)
                                    element * 5
                                }
                                        .flatMap { Mono.empty<Int>() }
                                        .subscribeOn(Schedulers.boundedElastic())
                        )
                    }
                    .flatMap { (extRes_1, extRes_2) ->
                        println("${extRes_1} + ${extRes_2}")
                        Flux.just(extRes_1 + extRes_2)
                    }
                    .onErrorResume {
                        println("зашли в onErrorResume")
                        Mono.just(88)
                    }
                    .switchIfEmpty {
                        println("зашли в switchIfEmpty")
                        Mono.just(99)
                    }

    publisher.subscribe { s -> println(s) }

    println("[${Thread.currentThread().name}] поток пошел дальше")
    Thread.sleep(3_000)
}
