package com.home.guess

import java.util.*

class SecretNumber {
    var secret = Random().nextInt(10) + 1
    var count = 0

    fun validate(number: Int) : Int{
        count ++
        return secret - number
    }

    fun reStart() {
        secret = Random().nextInt(10) + 1
        count = 0
    }
}