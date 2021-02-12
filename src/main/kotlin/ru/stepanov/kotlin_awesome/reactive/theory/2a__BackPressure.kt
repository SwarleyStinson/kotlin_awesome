package ru.stepanov.kotlin_awesome.reactive.theory

import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

fun main() {

    /** 1)  BaseSubscriber  ->  override hookOnNext() + cancel() */
    Flux.range(1, 6)
            .doOnRequest { r -> println("request of $r") }
            .subscribe(object : BaseSubscriber<Int>() {
                override fun hookOnSubscribe(subscription: Subscription) {
                    request(1)
                }

                override fun hookOnNext(value: Int) {
                    /** чекать ресурсы, и отменять при превышении лимита */
                    println("Received value: $value")
                    cancel()
                }
            })


    /** 2)  limitRate()   or     limitRequest()  todo what difference take()?  */
    Flux.range(1, 9)
//            .limitRate(3) // todo
//            .limitRate(12, 10) // todo
            .limitRequest(5)
            .subscribe { s -> println(s) }

    Thread.sleep(2_000)
}