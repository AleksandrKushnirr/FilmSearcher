package com.example.filmsearcher.presentation.recycler.reminders

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.App
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Reminder
import java.text.SimpleDateFormat
import java.util.*

class RemindsAdapter(
    private val context: Context
) : RecyclerView.Adapter<RemindsAdapter.RemindsViewHolder>(){

    private var items: MutableList<Reminder> = mutableListOf()

    fun setItems(reminders: List<Reminder>) {
        items.clear()
        items.addAll(reminders)

        notifyDataSetChanged()
    }


    fun deleteItem(position: Int){
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RemindsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_remind, parent, false)
        return RemindsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RemindsViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    inner class RemindsViewHolder(
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
                deleteItem(adapterPosition)
            }
        }
    }

}