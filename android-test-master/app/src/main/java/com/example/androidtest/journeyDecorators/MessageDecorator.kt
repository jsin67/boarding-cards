package com.example.androidtest.journeyDecorators

/**
 * Base class for decorators
 */
abstract class MessageDecorator(private val journey: Journey): Journey {

    override val destination: String
        get() = journey.destination

    override val source: String
        get() = journey.source
}