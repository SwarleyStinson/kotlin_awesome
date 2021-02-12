package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux
import java.time.Duration

fun main() {
    val producer = Flux.range(1, 5)

    val subscribtion = producer
            .delayElements(Duration.ofMillis(1000))
            .subscribe({ i -> println(i) })

    println("Wait 2 seconds...").also { Thread.sleep(2_100) }
    subscribtion.dispose()
    println("Wait 2 seconds...").also { Thread.sleep(2_000) }
    println("Is subscription disposed: ${subscribtion.isDisposed}")
}