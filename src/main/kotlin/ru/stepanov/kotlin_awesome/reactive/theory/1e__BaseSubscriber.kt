package ru.stepanov.kotlin_awesome.reactive.theory

import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux


fun main() {
    val producer = Flux.range(1, 3)

    val cs = CustomSubscriber<Int>()
    producer.subscribe(cs)
}

internal class CustomSubscriber<T> : BaseSubscriber<T>() {
    override fun hookOnSubscribe(subscription: Subscription) {
        println("Subscribed")
        request(1)
    }

    public override fun hookOnNext(value: T) {
        println(value)
        request(1)
    }
}