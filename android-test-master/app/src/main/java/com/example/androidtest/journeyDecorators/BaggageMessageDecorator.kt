package com.example.androidtest.journeyDecorators

/**
 * Adds baggage information.
 */
class BaggageMessageDecorator(private val journey: Journey, private val baggageMessage: String) : MessageDecorator(journey) {

    override fun getMessage(): String {
        return journey.getMessage() + ". " +  baggageMessage
    }
}