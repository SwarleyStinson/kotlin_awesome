//package ru.stepanov.ktawesome.core.basic
//
//fun main() {
//    var neverNull: String = "This can't be null"
//    neverNull = null
//
//    var nullable: String? = "You can keep a null here"
//    nullable = null
//
//    var inferredNonNull = "The compiler assumes non-null"
//    inferredNonNull = null
//
//    strLength(neverNull)
//    strLength(nullable)
//    strLengthNullable(nullable)
//}
//
//fun strLength(notNull: String): Int {
//    return notNull.length
//}
//
//fun strLengthNullable(nullable: String?): Int {
//    //compilator required explicit check
//    if (nullable != null) {
//        return nullable.length
//    }
//}