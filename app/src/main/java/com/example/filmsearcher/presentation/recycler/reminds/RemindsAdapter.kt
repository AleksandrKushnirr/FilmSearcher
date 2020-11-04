package com.example.filmsearcher.presentation.recycler.reminds

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Reminder

class RemindsAdapter(
    private val context: Context
) : RecyclerView.Adapter<RemindsViewHolder>(){

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

}