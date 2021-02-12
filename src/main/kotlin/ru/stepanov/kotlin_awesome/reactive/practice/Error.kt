package ru.stepanov.kotlin_awesome.reactive.practice

import reactor.core.publisher.Mono

fun main() {
    Mono.fromCallable {
        throw Exception()
        "fromCallable"
    }.onErrorReturn("Default value")
            .map { println(it) }
            .block()

    println()

    Mono.fromCallable {
        throw Exception()
        "fromCallable"
    }.onErrorResume {
        println("onErrorResume")
        Mono.fromCallable {
            println("new publisher")
            "new value"
        }
    }
            .map { println(it) }
            .block()

    println()

    Mono.just("mono error test")
            .flatMap {
                if (true) Mono.error<Exception>(Exception())
                else Mono.just("flatMap")

            }.onErrorResume {
                Mono.just("new publisher")
            }
            .map {
                println("$it")
                "qqq"
            }.block()

    println()

    Mono.fromCallable {
        throw Exception()
        "fromCallable"
    }.doOnError {
        println("doOnError")
        Mono.just("Just")
    }
            .map { println(it) }
            .block()
}