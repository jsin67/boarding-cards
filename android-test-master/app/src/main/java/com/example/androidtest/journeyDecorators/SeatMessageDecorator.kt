package com.example.androidtest.journeyDecorators

/**
 * Adds information regarding seat.
 */
class SeatMessageDecorator(val journey: Journey, val seatNo: String) : MessageDecorator(journey) {

    override fun getMessage(): String {
        return journey.getMessage() + ". Seat $seatNo"
    }
}