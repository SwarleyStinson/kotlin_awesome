package ru.stepanov.kotlin_awesome.reactive.theory

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
                                    Thread.sleep(3_000)
                                    element * 5
                                }.subscribeOn(Schedulers.boundedElastic())
                        )
                    }
//                    .flatMap { tuple ->
//                        println("${tuple.t1} + ${tuple.t2}")
//                        Flux.just(tuple.t1 + tuple.t2)
//                    }
                    .flatMap { (extRes_1, extRes_2) ->
                        print("${extRes_1} + ${extRes_2} = ")
                        Flux.just(extRes_1 + extRes_2)
                    }
    publisher.subscribe { s -> println(s) }

    println("[${Thread.currentThread().name}] поток пошел дальше")
    Thread.sleep(4_000)
}


private fun external_1(): Mono<List<Int>> =
        Mono.fromCallable {
            Thread.sleep(2_000)
            listOf(1, 2)
        }.subscribeOn(Schedulers.boundedElastic())

private fun external_2(): Flux<Int> =
        Mono.fromCallable {
            Thread.sleep(2_000)
            listOf(12, 13)
        }.flatMapMany { list ->
            Flux.fromIterable(list)
        }.subscribeOn(Schedulers.boundedElastic())

