package com.example.androidtest.journeyDecorators

/**
 * Contains basic information of bus journey
 */
class Bus(override val source: String, override val destination: String, val busNo: String) : Journey {

    override fun getMessage(): String {
        return "Take bus $busNo From $source to $destination"
    }
}