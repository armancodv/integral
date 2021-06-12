package com.armanco.integral.utils

import org.junit.Assert.*
import org.junit.Test

class XpLevelTest {

    private val maxLevel = 1000
    private val maxXp = 1000000

    @Test
    fun checkInverse() {
        for(l in 1..maxLevel) {
            val xp = XpLevel.levelToMinXp(l)
            val level = XpLevel.xpToLevel(xp)
            assertEquals(l, level)
        }
    }

    @Test
    fun checkLevelPlusOne() {
        for(l in 1..maxLevel) {
            val xp = XpLevel.levelToMinXp(l)
            val levelPlusOne = XpLevel.xpToLevel(xp+1)
            assertEquals(l, levelPlusOne)
        }
    }

    @Test
    fun checkLevelMinusOne() {
        for(l in 1..maxLevel) {
            val xp = XpLevel.levelToMinXp(l)
            val levelMinusOne = XpLevel.xpToLevel(xp-1)
            assertEquals(l - 1, levelMinusOne)
        }
    }

    @Test
    fun xpZeroLevelShouldBeOne() {
        val level = XpLevel.xpToLevel(0)
        assertEquals(1, level)
    }

    @Test
    fun minXpOfLevelIsLessThanCurrentLevel() {
        for(xp in 0..maxXp) {
            val level = XpLevel.xpToLevel(xp)
            val minXp = XpLevel.levelToMinXp(level)
            assert(xp >= minXp)
        }
    }

    @Test
    fun maxXpOfLevelIsGreaterThanCurrentLevel() {
        for(xp in 0..maxXp) {
            val level = XpLevel.xpToLevel(xp)
            val maxXp = XpLevel.levelToMinXp(level+1)
            assert(xp < maxXp)
        }
    }

}