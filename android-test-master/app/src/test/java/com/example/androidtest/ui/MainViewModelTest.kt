package com.example.androidtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidtest.repo.Repo
import com.example.androidtest.repo.RepoImpl
import org.junit.*

class MainViewModelTest {

    private val newYorkFlightMessage =
        "From London take flight SK22 to New York. Seat 23. Baggage will we automatically transferred from your last leg. Gate no. 45B"
    private val airportBusMessage = "Take bus 100 From Barcelona to Girona Airport. Seat No seat assignment"
    private val trainMessage = "Take train 99 From Madrid to Barcelona. Seat 45B"
    private val londonMessage =
        "From Girona Airport take flight SK455 to London. Seat 23. Your baggage is at ticket no 78. Gate no. 45B"

    private lateinit var repo: Repo
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repo = RepoImpl()
        viewModel = MainViewModel(repo)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun test_mix_boardingCards() {
        viewModel.getMixBoardingCardsData()
        val boardingCards = repo.getBoardingCards()
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.size, boardingCards.size)
        Assert.assertNotNull(boardingCards)
    }

    @Test
    fun test_process_order_cards() {
        viewModel.getMixBoardingCardsData()
        val boardingCards = repo.getBoardingCards()
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.size, boardingCards.size)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(0), newYorkFlightMessage)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(1), airportBusMessage)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(2), trainMessage)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(3), londonMessage)

    }


    @Test
    fun test_sorted_boardingCards() {
        viewModel.getSortedBoardingCardData()
        val boardingCards = repo.getBoardingCards()
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.size, boardingCards.size)
        Assert.assertNotNull(boardingCards)
    }

    @Test
    fun test_sorted_order_cards() {
        viewModel.getSortedBoardingCardData()
        val boardingCards = repo.getBoardingCards()
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.size, boardingCards.size)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(0), trainMessage)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(1), airportBusMessage)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(2), londonMessage)
        Assert.assertEquals(viewModel.boardingPassLiveData.value?.get(3), newYorkFlightMessage)

    }

}