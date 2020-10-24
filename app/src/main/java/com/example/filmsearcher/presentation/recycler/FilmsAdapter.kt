package com.example.filmsearcher.presentation.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Film


class FilmsAdapter(
    private val context: Context
) : RecyclerView.Adapter<FilmsViewHolder>(){

    private var items: MutableList<Film> = mutableListOf()

    fun setItems(films: List<Film>) {
        items.clear()
        items.addAll(films)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_def, parent, false)
        return FilmsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(items[position], context)
    }


}