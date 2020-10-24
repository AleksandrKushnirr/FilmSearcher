package com.example.filmsearcher.data.database

import androidx.room.*
import com.example.filmsearcher.domain.entities.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM Film WHERE is_liked = 0")
    fun getAllFilms(): MutableList<Film>

    @Query("SELECT * FROM Film WHERE is_liked = 1")
    fun getLikedFilms(): MutableList<Film>

    @Query("SELECT * FROM Film WHERE id == :id")
    fun getFilmById(id: Int): Film

    @Query("DELETE FROM Film WHERE is_liked == 0")
    fun deleteAllRows()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(film: Film)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(film: Film)

    @Delete
    fun delete(film: Film)

}