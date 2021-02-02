package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    val mono = Mono.just(55)
    val mono2 = Mono.just(11)

    mono.zipWith(mono2)
            .subscribe { s -> println(s) }
    mono.zipWith(mono2) { m1, m2 -> 34 + m1 + m2 }
            .subscribe { s -> println(s) }

    mono.zipWhen { t ->
        if (t > 50) Mono.just(33)
        else Mono.just(11)
    }.subscribe { s -> println(s) }

    println("---")
    Mono.zipDelayError(mono, mono2)
            .subscribe { s -> println(s) }
    Mono.zipDelayError(mono, mono2, getMonoError())
            .onErrorResume { t -> Mono.zip(mono, mono2, Mono.just(44)) }
            .subscribe { s -> println(s) }

    println("---")
    getMonoEmpty().switchIfEmpty(Mono.just(11))
            .subscribe { s -> println(s) }


    println("***")
    mono.thenMany<Int> {
        Flux.just(1, 2, 3)
                .doOnNext { s -> println(s) }
                .subscribe()
    }.subscribe()
    mono.then()
            .subscribe { s -> println(s) }


    println("-*-")
    mono2.repeat(2).subscribe { s -> println(s) }
}

fun getMonoError(): Mono<Int> {
    return Mono.error<Int>(RuntimeException())
}

fun getDelayedMono(): Mono<Int> = Mono.fromCallable<Int> {
    Thread.sleep(2_000)
    77
}

fun getMonoEmpty(): Mono<Int> = Mono.fromCallable<Int> {
    null
}
