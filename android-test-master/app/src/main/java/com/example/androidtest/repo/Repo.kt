package com.example.androidtest.repo

import com.example.androidtest.journeyDecorators.Journey

/**
 * This is single source of truth for boarding cards
 */
interface Repo {

    /**
     * Returns boarding cards list
     */
    fun getBoardingCards(): List<Journey>
}