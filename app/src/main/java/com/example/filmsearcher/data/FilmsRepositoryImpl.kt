package com.example.filmsearcher.data

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.filmsearcher.App
import com.example.filmsearcher.UpdateDBWorker
import com.example.filmsearcher.domain.FilmsRepository
import com.example.filmsearcher.domain.entities.Film
import javax.inject.Inject


class FilmsRepositoryImpl @Inject constructor() : FilmsRepository {

    // database
    private val dao = App.instance.daggerComponent.getFilmDao()

    override fun getAllFilmsFromDB(): MutableList<Film> = dao.getAllFilms()
    override fun getLikedFilmsFromDB(): MutableList<Film> = dao.getLikedFilms()
    override fun getFilmByIdFromDB(id: Int): Film = dao.getFilmById(id)
    override fun deleteAllFilmsFromDB() = dao.deleteAllRows()
    override fun addFilmToDB(film: Film) = dao.insert(film)
    override fun updateFilmInDB(film: Film)= dao.update(film)
    override fun deleteFilmFromDB(film: Film) = dao.delete(film)

    // web
    override fun loadNewFilms(){
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val loadFilmsRequest = OneTimeWorkRequest.Builder(UpdateDBWorker::class.java)
            .setConstraints(constraints)
            .addTag("loadFilms")
            .build()
        WorkManager.getInstance().enqueue(loadFilmsRequest)
    }
}