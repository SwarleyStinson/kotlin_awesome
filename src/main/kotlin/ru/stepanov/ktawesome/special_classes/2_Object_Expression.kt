package ru.stepanov.ktawesome.special_classes

fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {

    //expression means it can be write into var
    val dayRates = object {
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special

    print("Total price: $$total")

}

fun main() {
    rentPrice(10, 2, 1)
}