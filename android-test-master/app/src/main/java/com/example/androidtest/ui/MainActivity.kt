package com.example.androidtest.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.di.Injectable
import com.example.androidtest.ui.adapter.BoardingPassAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable, OnBoardingPassTappedListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val adapter = BoardingPassAdapter(this)

    var isSorted: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)

        setupView()
        observeCardData()
        showProgressing()
        viewModel.getMixBoardingCardsData()
    }

    private fun observeCardData() {
        viewModel.boardingPassLiveData.observe(this, Observer { boardingCards ->
            adapter.setCardsData(boardingCards)
        })
    }

    /**
     * Sets up views
     */
    private fun setupView() {
        recycler_view.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(it.context, RecyclerView.VERTICAL))
        }

        btn_sort.setOnClickListener {
            showProgressing()
            if (isSorted) {
                isSorted = false
                viewModel.getMixBoardingCardsData()
                findViewById<Button>(R.id.btn_sort).text = getString(R.string.sort_cards)
                findViewById<TextView>(R.id.tv_sort_lable).text = getString(R.string.mix_cards_label)
            } else {
                isSorted = true
                viewModel.getSortedBoardingCardData()
                findViewById<Button>(R.id.btn_sort).text = getString(R.string.mix_cards)
                findViewById<TextView>(R.id.tv_sort_lable).text = getString(R.string.sort_cards_label)
            }

        }
    }

    /**
     * Show progress
     */
    private fun showProgressing() {
        Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show()
    }

    override fun onItemTapped(information: String) {
        Toast.makeText(this, information, Toast.LENGTH_SHORT).show()
    }
}
