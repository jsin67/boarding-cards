package com.example.androidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtest.journeyDecorators.Journey
import com.example.androidtest.repo.Repo
import com.example.androidtest.utils.BAD_DATA
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    var boardingPassLiveData: MutableLiveData<List<String>> = MutableLiveData()

    /**
     * Calls repo to get mix boarding passes
     */
    fun getMixBoardingCardsData() {
        val journeyCards: List<Journey> = repo.getBoardingCards()
        boardingPassLiveData.value = getRandomMessagesList(journeyCards)
    }

    /**
     * Calls repo to get cards and process them into correct order
     */
    fun getSortedBoardingCardData() {
        val journeyCards: List<Journey> = repo.getBoardingCards()
        boardingPassLiveData.value = processBoardingCards(journeyCards)
    }

    /**
     * Converts random cards into messages list
     * @param list : List of random cards
     * @return List of random messages
     */
    private fun getRandomMessagesList(list: List<Journey>): List<String>{
        val messages: ArrayList<String> = arrayListOf()
        list.forEach { messages.add(it.getMessage()) }
        return messages
    }

    /**
     * Converts random cards into correct order
     * @param list : List of random cards
     * @return List correct order
     */
    private fun processBoardingCards(list: List<Journey>): List<String> {
        val messages: ArrayList<String> = arrayListOf()
        var firstBoardingPass: Journey? = null;
        val sourceMap = HashMap<String, Journey>()
        val destinationMap = HashMap<String, Journey>()
        list.forEach { item ->

            sourceMap.put(item.source, item)
            destinationMap.put(item.destination, item)
        }


        //find an element which is not there in destination map, that will be the source
        for (journey in sourceMap.values.toList()) {
            if (destinationMap.containsKey(journey.source)) continue
            firstBoardingPass = journey
        }

        // if source is not found, return error
        if (firstBoardingPass == null) {
            messages.add(BAD_DATA)
        } else {
            //keep traveling the map to find the next node
            while (firstBoardingPass != null) {
                messages.add(firstBoardingPass.getMessage())
                firstBoardingPass = sourceMap.get(firstBoardingPass.destination)
            }
        }

        return messages
    }
}