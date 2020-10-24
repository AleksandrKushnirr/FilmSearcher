package com.example.filmsearcher.domain

import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.domain.entities.Filter
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val repository: FilmsRepository,
    private val filterRepository: FilterRepository
) {

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

}