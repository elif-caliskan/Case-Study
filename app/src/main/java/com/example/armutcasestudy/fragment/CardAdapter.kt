package com.example.armutcasestudy.fragment

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.armutcasestudy.R
import com.squareup.picasso.Picasso
import java.lang.Math.max
import java.lang.Math.min

class CardAdapter(var dataSet: List<CardModel>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.card_name)
        val text: TextView = v.findViewById(R.id.card_description)
        val image: ImageView = v.findViewById(R.id.card_image)


        fun bind(card: CardModel, clickListener: OnItemClickListener) {
            name.text = card.name
            if(card.text!=null){
                text.text = Html.fromHtml(card.text)
            }

            if(card.img!=null){
                Picasso.get().load(card.img).into(image)
            }
            else{
                image.setImageResource(R.drawable.ic_img_placeholder)
            }

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
    override fun getItemCount() = min(dataSet.size, 10)

}

interface OnItemClickListener {
    fun onItemClicked(cardModel: CardModel)
}