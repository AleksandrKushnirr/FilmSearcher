package com.example.filmsearcher

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.filmsearcher.domain.entities.Film
import java.lang.Exception

class UpdateDBWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val api = App.instance.daggerComponent.getFilmApiService()
    private val dao = App.instance.daggerComponent.getFilmDao()

    override fun doWork(): Result {
        dao.deleteAllRows()

        for(i in 1..100) {
            try {
                val response = api.getFilmsWithRating((290..100000).random()).execute()
                if (response.isSuccessful) {
                    if (response.body()!!.data.countries.isNotEmpty() && response.body()!!.data.genres.isNotEmpty() &&
                        response.body()?.data?.description != null) {
                        dao.insert(Film(response.body()!!))
                    }
                }
            } catch (e: Exception) { Log.d("MyLog", e.printStackTrace().toString()) }
        }

        val notifyIntent = Intent(context, NotificationReceiver::class.java)
        notifyIntent.action = "updatedDatabase"
        context.sendBroadcast(notifyIntent)

        return Result.success()
    }
}