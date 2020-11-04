package com.example.filmsearcher.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import com.example.filmsearcher.App
import com.example.filmsearcher.NotificationReceiver
import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.domain.entities.Filter
import com.example.filmsearcher.domain.entities.Reminder
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val repository: FilmsRepository,
    private val filterRepository: FilterRepository,
    private val remindersRepository: RemindersRepository
) {

    private val context = App.instance.applicationContext

    fun getFilms(filter: Filter): List<Film> {

        val resultSet = mutableSetOf<Film>()
        val topCountries = listOf<String>("США", "Россия", "СССР", "Франция", "Италия",
                                                        "Испания", "Великобритания", "Германия", "Япония", "Украина")

        outerLoop@ for (film in repository.getAllFilmsFromDB()) {
            try {
                // genre filter
                for (genre in film.genres) {
                    val currentGenre = genre.substring(0, 1).toUpperCase(Locale.ROOT) + genre.substring(1)
                    if (filter.genres.contains(currentGenre)) {
                        // country filter
                        for (country in film.countries) {
                            if (filter.countries.contains(country) || (filter.countries.contains("Другие") && !topCountries.contains(country))) {
                                // years filter
                                if (film.year >= filter.minYear && film.year <= filter.maxYear ) {
                                    // rating kinopoisk filter
                                    if (film.rating >= filter.minRatingKino && film.rating <= filter.maxRatingKino) {
                                        // rating Imdb filter
                                        if (film.ratingImdb >= filter.minRatingImdb && film.ratingImdb <= filter.maxRatingImdb) {
                                            // age limit filter
                                            if (filter.ageLimit.contains(film.ageLimit.toString() + "+")) {
                                                resultSet.add(film)
                                                continue@outerLoop
                                            }
                                        }
                                    }
                                }
                                continue@outerLoop
                            }
                        }
                        continue@outerLoop
                    }
                }
            }catch (e: Exception) {
                continue@outerLoop
            }

        }
        return resultSet.toMutableList()
    }

    fun getLikedFilms(): MutableList<Film> = repository.getLikedFilmsFromDB()

    fun changeLike(film: Film) {
        film.isLiked = !film.isLiked
        repository.updateFilmInDB(film)
    }

    fun getFilter(): Filter {
        var filter: Filter? = filterRepository.getFilterFromDB()

        if (filter == null) {
            filter = Filter()
            filterRepository.insertFilter(filter)
        }
        return filter
    }

    fun startAlarm(reminder: Reminder){
        val alarmManager: AlarmManager = App.instance.applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, reminder.date, createPendingIntent(reminder.name))
    }

    private fun createPendingIntent(action: String): PendingIntent{
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    fun stopAlarm(reminder: Reminder){
        val alarmManager: AlarmManager = App.instance.applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(createPendingIntent(reminder.name))
        remindersRepository.deleteByName(reminder.name)
    }

}