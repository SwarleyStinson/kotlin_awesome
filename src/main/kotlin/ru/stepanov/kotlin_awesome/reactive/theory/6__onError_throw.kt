package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    /** onErrorReturn()  -  завершает выполнение цепочки */
    Flux.just(1, 2, 0, 4)
            .doOnNext { i -> throw RuntimeException("vsdvdsv") }
            .map { i -> "100 / " + i + " = " + 100 / i }
            .onErrorReturn(
                    { error ->
                        println("Some gonna wrong: ${error.message}")
                        true // use fallback or re-throw if false
                    },
                    "Divided by zero :("
            )
            .subscribe { s -> println(s) }

}
