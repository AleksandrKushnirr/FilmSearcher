package com.example.filmsearcher.presentation.recycler.reminds

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.App
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Reminder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RemindsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView){

    private val name = itemView.findViewById<TextView>(R.id.textCardName)
    private val date = itemView.findViewById<TextView>(R.id.textCardDate)
    private val deleteButton = itemView.findViewById<ImageView>(R.id.icon_delete)
    private val mainCardView = itemView.findViewById<CardView>(R.id.main_card)

    private val interactor = App.instance.daggerComponent.getInteractor()

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(item: Reminder, context: Context) {

        name.text = item.name
        date.text = "${date.text}: ${SimpleDateFormat("dd.MM.yyyy").format(Date(item.date))}"

        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                deleteButton.visibility = View.GONE
                mainCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorBackgroundLight))
            }
        }
        itemView.setOnLongClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                deleteButton.visibility = View.VISIBLE
                mainCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.divider))
            }
            true
        }
        deleteButton.setOnClickListener {
            interactor.stopAlarm(item)
            

        }
    }
}