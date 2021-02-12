package ru.stepanov.kotlin_awesome.reactive.theory

import reactor.core.publisher.Flux

fun main() {

    val producer = Flux
            .range(1, 5)
            .map { i -> if (i <= 4) i else throw RuntimeException("Some gonna wrong...") }

    producer.subscribe(
            { i -> println(i) },
            { error -> println("Error: ${error.message}") }, // only if error occured
            { println("Successfully completed!") },  // only if successful completion
            { subscription -> subscription.request(10) }
    )


}