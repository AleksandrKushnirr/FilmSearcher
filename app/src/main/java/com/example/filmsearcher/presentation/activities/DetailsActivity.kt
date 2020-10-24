package com.example.filmsearcher.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.filmsearcher.R
import com.example.filmsearcher.data.FilmsRepositoryImpl
import com.example.filmsearcher.domain.entities.Film
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    private val repository = FilmsRepositoryImpl()
    private lateinit var currentFilm: Film

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar_collaps)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val filmId = intent.getIntExtra(MainActivity.ID, 0)
        currentFilm = repository.getFilmByIdFromDB(filmId)

        description.text = currentFilm.description
        genresTitle.text = "${genresTitle.text} ${currentFilm.genres.toString().substring(1).replace("]", "")}"
        countriesTitle.text = "${countriesTitle.text} ${currentFilm.countries.toString().substring(1).replace("]", "")}"
        yearTitle.text = "${yearTitle.text} ${currentFilm.year}"
        ratingKino.text = "${ratingKino.text} ${currentFilm.rating}"
        ratingImdb.text = "${ratingImdb.text} ${currentFilm.ratingImdb}"
        ages.text = "${ages.text} ${currentFilm.ageLimit}+"

        Glide.with(this)
                .load(currentFilm.imageUrl)
                .placeholder(R.drawable.icon_default)
                .error(R.drawable.image_error)
                .into(image_collaps)

        toolbar_collaps.title = ""

        val sizeAnimation = AnimationUtils.loadAnimation(this, R.anim.image_details_sige_anim)
        image_collaps.startAnimation(sizeAnimation)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onClickFAB(v: View?) {
        if (currentFilm.isLiked) {
            currentFilm.isLiked = false
            Toast.makeText(this, resources.getText(R.string.film_removed_from_liked_list), Toast.LENGTH_SHORT).show()
        }else{
            currentFilm.isLiked = true
            Toast.makeText(this, resources.getText(R.string.you_like_this_film), Toast.LENGTH_SHORT).show()
        }
        repository.updateFilmInDB(currentFilm)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.invoke_friends -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.kinopoisk.ru/film/${currentFilm.kinopoiskId}/")
                shareIntent.type = ("text/plain")
                if (shareIntent.resolveActivity(packageManager) != null) startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}