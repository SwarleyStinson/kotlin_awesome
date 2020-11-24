package ru.nspk.tpp.transaction.sgb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
open class TransactionSgbApplication

fun main(args: Array<String>) {
    runApplication<TransactionSgbApplication>(*args)
}