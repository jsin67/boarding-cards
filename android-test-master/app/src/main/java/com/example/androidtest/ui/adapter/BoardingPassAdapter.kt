package com.example.androidtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.ui.OnBoardingPassTappedListener

class BoardingPassAdapter(val listener: OnBoardingPassTappedListener) : RecyclerView.Adapter<BoardingPassAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtBoardingMessage: TextView = itemView.findViewById(R.id.tv_message)
    }

    private val cardsList = mutableListOf<String>()

    override fun getItemCount(): Int = cardsList.size

    fun setCardsData(list: List<String>?) {
        list?.let {
            cardsList.clear()
            cardsList.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtBoardingMessage.text = cardsList[position]
        holder.itemView.setOnClickListener { view -> listener.onItemTapped(cardsList[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return ViewHolder(v)
    }
}