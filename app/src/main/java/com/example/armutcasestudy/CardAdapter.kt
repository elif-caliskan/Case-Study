package com.example.armutcasestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(var dataSet: List<CardModel>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.card_name)
        val text: TextView = v.findViewById(R.id.card_description)
        val image: ImageView = v.findViewById(R.id.card_image)


        fun bind(card: CardModel, clickListener: OnItemClickListener) {
            name.text = card.name
            text.text = card.text

            itemView.setOnClickListener {
                clickListener.onItemClicked(card)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_item, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val event = dataSet[position]
        viewHolder.bind(event, itemClickListener)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

interface OnItemClickListener {
    fun onItemClicked(cardModel: CardModel)
}