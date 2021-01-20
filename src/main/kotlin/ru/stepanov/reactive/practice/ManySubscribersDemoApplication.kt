package ru.stepanov.reactive.practice

import reactor.core.publisher.Mono
import java.lang.RuntimeException

fun main() {
    Mono.fromCallable { "test" }
            .flatMap {
                newSubscription()
                throw RuntimeException("hello")
                Mono
//                        .error<String>(RuntimeException("blabla"))
                        .just("afterSubscription")
            }
            .onErrorResume {
                Mono.fromCallable {
                    "in onErrorResume".let {
                        println(it)
                        it
                    }
                }
            }
            .doOnNext { s -> println("[$s]") }
            .block()
}

fun newSubscription() = Mono.error<Exception>(Exception()).subscribe()