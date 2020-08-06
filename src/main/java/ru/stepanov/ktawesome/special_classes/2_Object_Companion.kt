package ru.stepanov.ktawesome.special_classes

class CryptoUtil {
    /** используется как обычный static-метод в Java
     * */
    companion object SHA256 {
        fun sign(nTimes: Int) {
            for (i in 1..nTimes) {
                when (i) {
                    2, 5, 6 -> println("SHA256 ")
                    else -> println(i)
                }
            }
        }
    }
}

fun main() {
    CryptoUtil.sign(8)
}