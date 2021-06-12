package com.armanco.integral.utils

import kotlin.math.floor
import kotlin.math.sqrt

object XpLevel {
    fun xpToLevel(xp: Int): Int {
        return floor((1.0 + sqrt(1.0 + 4.0 * xp.toDouble() / 25.0)) / 2.0).toInt()
    }

    fun levelToMinXp(level: Int): Int {
        return level * (level - 1) * 25
    }

    fun levelToMaxXp(level: Int): Int {
        return levelToMinXp(level+1)
    }
}