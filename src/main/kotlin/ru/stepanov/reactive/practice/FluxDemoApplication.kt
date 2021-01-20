package ru.stepanov.reactive.practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.*
import kotlin.random.Random

@SpringBootApplication
@RestController
open class FluxDemoApplication {

    @GetMapping("/stream", produces = [APPLICATION_STREAM_JSON_VALUE])
    fun getStream() = Flux.generate<Data> {
        it.next(Data())
    }
            .take(10)
            .delayElements(Duration.ofMillis(300))

    @GetMapping("/flux")
    fun getAllData() = Flux.generate<Data> {
        it.next(Data())
    }
            .take(10)
            .delayElements(Duration.ofMillis(300))

}

data class Data(
        val int: Int = Random.nextInt(),
        val string: String = UUID.randomUUID().toString()
)

fun main(args: Array<String>) {
    runApplication<FluxDemoApplication>(*args)

    Flux.empty<Int>()
            .doOnNext {
                println(it)
            }
            .switchIfEmpty {
                println("Empty")
            }
            .subscribe()

}
