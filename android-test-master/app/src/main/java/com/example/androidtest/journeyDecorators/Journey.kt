package com.example.androidtest.journeyDecorators

/**
 * Contains journey's basic information
 */
interface Journey {
    val source: String
    val destination: String
    fun getMessage(): String
}