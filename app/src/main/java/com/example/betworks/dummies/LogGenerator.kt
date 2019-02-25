package com.example.betworks.dummies

object LogGenerator {

    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    @JvmStatic
    fun generateLog(len: Int): String {
        return (1..len)
            .map {kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

}