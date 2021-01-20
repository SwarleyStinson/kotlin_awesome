package ru.stepanov.reactive.theory

import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import java.time.Duration
import java.util.*

fun main() {
    val list = mutableListOf<Int>()

    Mono.empty<Int>()
    Flux.empty<Int>()

    val mono = Mono.just(5)
    val flux = Flux.just(3, 2, 1, 4)

    val flux1 = mono.flux()
    val monoFromFlux = flux.any { s -> s == 3 }
    val monoFromFlux2 = flux.elementAt(3)

    Flux.range(1, 5).subscribe { s -> println(s) }
    Flux.fromIterable(Arrays.asList(1, 2, 3)).subscribe { s -> println(s) }

    Flux
            .generate<String> { sink -> sink.next("Hello") }
            .delayElements(Duration.ofMillis(500))
            .take(3)
            .subscribe { s -> println(s) }
    Thread.sleep(3_000)

    /** generate() - one-by-one emission */
    val producer = Flux.generate({ 2345 }) { state: Int, sink: SynchronousSink<String> ->
        sink.next("Step: $state")
        if (state > 2350) {
            sink.complete()
        }
        state.inc()
    }
    producer.subscribe { s -> println(s) }

    Flux // also push (1 thread)
            .create<String> { sink ->
                producer.subscribe {
                    object : BaseSubscriber<String>() {
                        override fun hookOnNext(value: String) {
                            sink.next(value)
                        }

                        override fun hookOnComplete() {
                            sink.complete()
                        }
                    }
                }
                sink.onRequest { r ->
                    sink.next("DB return: " + producer.blockFirst())
                }
            }
            .subscribe { s -> println(s) }

    Thread.sleep(4_000)
}