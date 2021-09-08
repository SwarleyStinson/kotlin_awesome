package ru.stepanov.kotlin_awesome.task

class Square {
    fun run() {
        val array = arrayOf(1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2)

        if (array.sum() % 4 != 0) throw RuntimeException()

        val side = array.sum() / 4

    }

    fun getSide(side: Int) {

    }
}