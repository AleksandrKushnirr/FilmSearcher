package com.example.filmsearcher.presentation.recycler.reminds

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Reminder

class RemindsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView){

    private val name = itemView.findViewById<TextView>(R.id.textCardName)
    private val date = itemView.findViewById<TextView>(R.id.textCardDate)

    fun bind(item: Reminder, context: Context) {

        name.text = item.name
        date.text = item.date.toString()

        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                Toast.makeText(context, item.name, Toast.LENGTH_LONG).show()
            }
        }
    }
}