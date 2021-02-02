package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

fun main() {
    val publisher =
            Flux.range(1, 2)
                    .zipWith(Flux.range(10, 20))
                    .flatMap<Int> { t ->
                        Flux.just(t.t1 + t.t2).subscribeOn(Schedulers.elastic())
                    }

    publisher.subscribe { s -> println(s) }

    println("[${Thread.currentThread().name}] поток пошел дальше")
    Thread.sleep(3_000)
}


private fun external_1(): Mono<List<Int>> =
        Mono.fromCallable {
            Thread.sleep(2_000)
            listOf(1, 2)
        }.subscribeOn(Schedulers.boundedElastic())

/** Mono<List> to Flux */
private fun external_2(): Flux<Int> =
        Mono.fromCallable {
            Thread.sleep(2_000)
            listOf(12, 13)
        }.flatMapMany { list ->
            Flux.fromIterable(list)
        }.subscribeOn(Schedulers.boundedElastic())

