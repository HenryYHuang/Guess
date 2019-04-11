package com.home.guess

import java.util.*

class SecretNumber {
    val secret = Random().nextInt(10) + 1
    var count = 0

    fun validate(number: Int) : Int{
        count ++
        return secret - number
    }
}