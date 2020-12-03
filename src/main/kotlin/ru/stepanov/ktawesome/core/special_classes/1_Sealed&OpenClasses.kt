package ru.stepanov.ktawesome.core.special_classes

/** SEALED - разрешает наследование в рамках файла!!!
 * */
sealed class Mammal(val name: String)

class Cat(val catName: String) : Mammal(catName)
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal): String {
    when (mammal) {
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"
        is Cat -> return "Hello ${mammal.name}"
    }
}

fun main() {
    println(greetMammal(Cat("Snowy")))
}

// 1_ Defines a sealed class.
// 2_ Defines subclasses. Note that all subclasses must be in the same file.
// 3_ Uses an instance of the sealed class as an argument in a when expression.
// 4_ A smartcast is performed, casting Mammal to Human.
// 5_ A smartcast is performed, casting Mammal to Cat.
// 6_ The else-case is not necessary here since all possible subclasses of the sealed class are covered.
//    With a non-sealed superclass else would be required.