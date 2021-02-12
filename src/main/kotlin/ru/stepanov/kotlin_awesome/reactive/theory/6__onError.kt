package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {

    /** onErrorReturn()  -  завершает выполнение цепочки */
    Flux.just(1, 2, 0, 4)
            .map { i -> "100 / " + i + " = " + 100 / i }
            .onErrorReturn(
                    { error ->
                        println("Some gonna wrong: ${error.message}")
                        true // use fallback or re-throw if false
                    },
                    "Divided by zero :("
            )
            .subscribe { s -> println(s) }

    /** onErrorResume() - продолжает выполнение цепочки */
    Flux.just("key1", "key2", "key3")
            .flatMap { req ->
                callExternalService(req)
                        .map { res -> "wrapped [$res]" }
//                        .onErrorMap { original -> java.lang.RuntimeException("re-throw error", original) }
//                        .onErrorResume { getFromCache(req) }
                        .onErrorResume { e ->
//                            getFromCache(req)
                            when (e.message) {
                                "Some gonna wrong..." -> Mono.error<String>(e) // RE-THROW
                                "Unknown req" -> println("some usefull action").let { Mono.just("bla bla") }
                                else -> getFromCache(req)
                            }
                        }
                        .map { s -> "$s + after error" }
                        .onErrorResume { err -> Mono.just("finish error handler") }
            }
            .subscribe { s -> println(s) }
}

fun callExternalService(req: String) =
        when (req) {
            "key2" -> Mono.error<String>(RuntimeException("Some gonna wrong..."))
            "key3" -> Mono.error<String>(RuntimeException("Unknown req"))
            else -> Mono.just("value from external for $req")
        }


fun getFromCache(k: String) = Mono.just("cashed value for $k")
