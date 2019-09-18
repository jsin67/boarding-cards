package com.example.androidtest.journeyDecorators

/**
 * Contains journey information for Train
 */
class Train(override val source: String, override val destination: String, val trainNo: String) : Journey {

    override fun getMessage(): String {
        return "Take train $trainNo From $source to $destination"
    }
}