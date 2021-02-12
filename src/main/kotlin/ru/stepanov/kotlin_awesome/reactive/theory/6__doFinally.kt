package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    Flux.just(1, 2, 0, 4)
            .doOnSubscribe { sub -> println("начинаем засекать") }
            .doFinally { type -> println("прошло N секунд") }
            .map { s -> "wrapped [$s]" }
            .subscribe { s -> println(s) }

}
