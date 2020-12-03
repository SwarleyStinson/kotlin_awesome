package ru.stepanov.ktawesome.core.collections

import java.util.*
import kotlin.collections.ArrayList

val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)
val sudoUsers: List<Int> = systemUsers

fun addSudoer(newUser: Int) {
    systemUsers.add(newUser)
}

fun getSysSudoers(): List<Int> {
    return sudoUsers
}

fun main() {
    addSudoer(4)
    println("Tot sudoers: ${getSysSudoers().size}")
    getSysSudoers().forEach { i ->
        println("Some useful info on user $i")
    }
    val list = ArrayList<String>()
    list.add("Loopa")
    list.add("Poopa")
    println(list.size)

    val joiner = StringJoiner(", ")
    list.stream().forEach { s -> joiner.add(s) }
    println(joiner.toString())
}