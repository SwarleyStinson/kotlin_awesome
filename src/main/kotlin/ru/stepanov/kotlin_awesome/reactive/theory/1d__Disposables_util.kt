package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.Disposables
import reactor.core.publisher.Flux
import java.time.Duration

fun main() {
    val producer = Flux.range(1, 5)

    val sub1 = producer
            .delayElements(Duration.ofMillis(1000))
            .subscribe({ i -> println("First: " + i) })

    val sub2 = producer.take(4).subscribe({i -> println("Second: " + i)})

    val replace = Disposables.swap().replace(sub1)
    //todo Disposables Util
}