package ru.stepanov.kotlin_awesome.ktawesome.core.collections

val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)
val sudoUsers: List<Int> = systemUsers

fun addSudoer(newUser: Int) {
    systemUsers.add(newUser)
}

fun getSysSudoers(): List<Int> {
    return sudoUsers
}

fun main() {
//    addSudoer(4)
//    println("Tot sudoers: ${getSysSudoers().size}")
//    getSysSudoers().forEach { i ->
//        println("Some useful info on user $i")
//    }
//    val list = ArrayList<String>()
//    list.add("Loopa")
//    list.add("Poopa")
//    println(list.size)
//
//    val joiner = StringJoiner(", ")
//    list.stream().forEach { s -> joiner.add(s) }
//    println(joiner.toString())

    val startFree = Runtime.getRuntime().freeMemory()
    val startUsage = (startFree / Runtime.getRuntime().totalMemory()) * 100
    val mutlist = mutableListOf<List<Int>>()
    val mutlist2 = mutableListOf<Int>()
    for (i in 0..1_000_000) {
//        mutlist.add(emptyList())
//        mutlist.add(listOf(1))
        mutlist2.add(1)
    }

    val endFree = Runtime.getRuntime().freeMemory()
    val endUsage = (endFree / Runtime.getRuntime().totalMemory()) * 100
    println(startFree)
    println(endFree)
    println(startUsage)
    println(endUsage)
    println(startFree - endFree)
}