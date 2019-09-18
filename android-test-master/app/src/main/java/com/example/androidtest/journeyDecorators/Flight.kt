package com.example.androidtest.journeyDecorators

/**
 * Contains basic information about flight journey
 */
class Flight(override val source: String, override val destination: String, val flightNo: String) : Journey {

    override fun getMessage(): String {
        return "From $source take flight $flightNo to $destination"
    }
}
