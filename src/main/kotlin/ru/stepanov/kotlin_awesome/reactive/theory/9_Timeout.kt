package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration

fun main() {
    Mono.fromCallable {
        try {
            Thread.sleep(4_000)
            "external service"
        } catch (e: Exception) {
        }
    }
            .timeout(Duration.ofSeconds(2), Mono.just("fallback"))
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe { s -> println(s) }

    Thread.sleep(5_000)
}