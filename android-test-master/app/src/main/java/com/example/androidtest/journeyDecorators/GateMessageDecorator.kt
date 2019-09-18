package com.example.androidtest.journeyDecorators

/**
 * Add information regarding gate.
 */
class GateMessageDecorator(private val journey: Journey, private val gateNo: String) : MessageDecorator(journey) {
    override fun getMessage(): String {
        return journey.getMessage() + ". Gate no. $gateNo"
    }
}