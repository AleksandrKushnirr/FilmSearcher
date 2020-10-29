package com.example.filmsearcher.presentation.recycler

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmsearcher.App
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.presentation.activities.DetailsActivity
import com.example.filmsearcher.presentation.activities.MainActivity

class FilmsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView){

    private val mainInteractor = App.instance.daggerComponent.getInteractor()

    private val image = itemView.findViewById<ImageView>(R.id.imageCard)
    private val name = itemView.findViewById<TextView>(R.id.textCardName)
    private val details = itemView.findViewById<TextView>(R.id.textCardDef)
    private val likeButton = itemView.findViewById<ImageView>(R.id.imageLike)

    fun bind(item: Film, context: Context) {
        Glide.with(context)
            .load(item.imageUrl)
            .placeholder(R.drawable.icon_default)
            .error(R.drawable.image_error)
            .into(image)

        name.text = item.nameRu
        details.text = item.description

        if (item.isLiked) likeButton.setImageResource(R.drawable.icon_like)

        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val detailsActivity = Intent(context, DetailsActivity::class.java)
                detailsActivity.putExtra(MainActivity.ID, item.id)
                startActivity(context, detailsActivity, null)
            }
        }
        likeButton.setOnClickListener {
            if (item.isLiked) {
                likeButton.setImageResource(R.drawable.icon_like_started)
                Toast.makeText(context, "Film removed from liked list.", Toast.LENGTH_SHORT).show()
            } else {
                likeButton.setImageResource(R.drawable.icon_like)
                Toast.makeText(context, "You like this film.", Toast.LENGTH_SHORT).show()
            }
            mainInteractor.changeLike(item)
        }
    }
}