package ru.stepanov.reactive.theory

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

/**  при EMPTY:  и Flux, и Mono зайдут в switchIfEmpty
 *              Flux - прервет pipeline для этого элемента!!!
 *              Mono - продолжит pipeline с дефолтным значением!!!
 * */
fun main() {

    Flux.range(1, 4)
            .flatMap { t ->
                if (t == 3) Flux.empty<Int>()
                else Flux.just(t)
            }
//            .switchIfEmpty { Flux.just(33) }
            .defaultIfEmpty(55)
            .doOnNext { s -> println("do on next for: $s") }
            .map { s -> "[$s]" }
            .subscribe { s -> println(s) }

    println("\nFlux:")
    Flux.empty<Any>()
            .doOnNext { s -> println("не зайду, ибо нет след.элемента") }
            .switchIfEmpty {
                println("захожу в switchIfEmpty, но по pipeline дальше НЕ ИДУ!")
                Flux.just(12)
            }
            .map { s -> "[$s]" }
            .map { s -> "выполняю операции: $s" }
            .subscribe { s -> println(s) }

    println("\nMono:")
    getMonoEmpty()
            .doOnNext { s -> println("не зайду, ибо нет след.элемента") }
//            .switchIfEmpty(Mono.just(33))
            .switchIfEmpty {
                println("захожу в switchIfEmpty и ИДУ по pipeline дальше!!!")
                Mono.just(44)
            }
            .map { s -> "[$s]" }
            .subscribe { s -> println(s) }
}