package com.example.filmsearcher.domain

import com.example.filmsearcher.domain.entities.Film

interface FilmsRepository {

    // database
    fun getAllFilmsFromDB(): MutableList<Film>
    fun getLikedFilmsFromDB(): MutableList<Film>
    fun getFilmByIdFromDB(id: Int): Film
    fun deleteAllFilmsFromDB()
    fun addFilmToDB(film: Film)
    fun updateFilmInDB(film: Film)
    fun deleteFilmFromDB(film: Film)

    // web
    suspend fun loadNewFilms()

}