package ru.stepanov.ktawesome.control_flow

fun main() {
    fun eatACake() = println("Eat a Cake")
    fun bakeACake() = println("Bake a Cake")

    fun main(args: Array<String>) {
        var cakesEaten = 0
        var cakesBaked = 0

        while (cakesEaten < 5) {
            eatACake()
            cakesEaten++
        }

        do {
            bakeACake()
            cakesBaked++
        } while (cakesBaked < cakesEaten)

    }
}