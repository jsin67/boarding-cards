package com.example.androidtest.repo

import android.annotation.SuppressLint
import com.example.androidtest.journeyDecorators.*


class RepoImpl : Repo {


    @SuppressLint("CheckResult")
    override fun getBoardingCards(): List<Journey> {


        return arrayListOf(
            getFlightBoardingPassFromLondonToNewYork(),
            getBusBoardingPassFromBarcelonaToAirport(),
            getTrainBoardingFromMadridToBarcelona(),
            getFlightBoardingPassFromGironaToLondon()
        )
    }


    /**
     * Returns bus pass with seat information
     */
    private fun getBusBoardingPassFromBarcelonaToAirport(): Journey {
        val busBoardingPassToGironaAirport: Journey = Bus("Barcelona", "Girona Airport", "100")
        //add seat to bus
        return SeatMessageDecorator(busBoardingPassToGironaAirport, "No seat assignment")
    }


    /**
     * Returns flight pass with seat, gate and baggage information for newyork
     */
    private fun getFlightBoardingPassFromLondonToNewYork(): Journey{
        val flightBoardingPassToNewYork: Journey = Flight("London", "New York", "SK22")
        //add seat to flight and baggage
        val newYorkPassWithSeatNo: Journey = SeatMessageDecorator(flightBoardingPassToNewYork, "23")
        val newYourPassWithSeatAndGate: Journey = BaggageMessageDecorator(newYorkPassWithSeatNo, "Baggage will we automatically transferred from your last leg")
        return GateMessageDecorator(newYourPassWithSeatAndGate, "45B")
    }


    /**
     * Returns flight pass with seat, gate and baggage information for london
     */
    private fun getFlightBoardingPassFromGironaToLondon(): Journey {
        val flightBoardingPassToLondon: Journey = Flight("Girona Airport", "London", "SK455")
        //add seat to flight and baggage
        val seatInFlight: Journey = SeatMessageDecorator(flightBoardingPassToLondon, "23")
        val baggage: Journey = BaggageMessageDecorator(seatInFlight, "Your baggage is at ticket no 78")
        return GateMessageDecorator(baggage, "45B")
    }

    /**
     * Returns train pass for Barcelona with seat information
     */
    private fun getTrainBoardingFromMadridToBarcelona(): Journey {
        val trainBoardingPassToBarcelona: Journey = Train("Madrid", "Barcelona", "99")
        //add seat to train
        return SeatMessageDecorator(trainBoardingPassToBarcelona, "45B")
    }
}