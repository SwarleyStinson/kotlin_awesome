package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2

fun main() {
    val publisher =
            Mono.zip(
                    Mono.fromCallable { 12 }.subscribeOn(Schedulers.boundedElastic()),
                    Mono.fromCallable { 22 }.subscribeOn(Schedulers.boundedElastic())
            ).flatMap { (t1, t2) ->
                Mono.zip(
                        Mono.fromCallable { t1 + 38 }.subscribeOn(Schedulers.boundedElastic()),
                        Mono.fromCallable { t2 + 78 }.subscribeOn(Schedulers.boundedElastic())
                )
            }.map { (t1, t2) ->
                t1 + t2
            }.switchIfEmpty(Mono.fromCallable {
                println("зашли в switchIfEmpty")
                200
            })


    publisher.subscribe { s -> println(s) }

    println("[${Thread.currentThread().name}] поток пошел дальше")
    Thread.sleep(3_000)
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

