package ru.stepanov.reactive.practice

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.concurrent.ForkJoinPool

fun main() {
    Mono.just(listOf(1, 2, 3, 4, 5))
            .map {
                println(it.toString())
                it
            }
            .publishOn(Schedulers.elastic())
            .map {
                println(it.toString())
                it
            }.block()

    println()

    Mono.just(listOf(1, 2, 3, 4, 5))
            .map {
                println(it.toString())
                it
            }
            .subscribeOn(Schedulers.elastic())
            .map {
                println(it.toString())
                it
            }.block()

    println()

    Flux.fromIterable(listOf(1, 2, 3, 4, 5))
            .subscribeOn(Schedulers.fromExecutor(ForkJoinPool()))
            .map {
                println(it.toString())
                it
            }.blockLast()

    println()

    Flux.fromIterable(listOf(1, 2, 3, 4, 5))
            .parallel()
            .runOn(Schedulers.elastic())
            .flatMap {
                println(it.toString())
                Flux.just(it)
            }
            .sequential()
            .blockLast()
}