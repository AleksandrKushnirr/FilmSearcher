package com.example.filmsearcher

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.presentation.activities.MainActivity
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UpdateDBWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val api = App.instance.daggerComponent.getFilmApiService()
    private val dao = App.instance.daggerComponent.getFilmDao()

    override fun doWork(): Result {
        dao.deleteAllRows()

        for(i in 1..1000) {
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
        showNotification("Film Searcher", "Обновление успешно. Ждем тебя!", applicationContext)
        return Result.success()
    }

    private fun showNotification(
        title: String,
        message: String,
        context: Context
    ){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "channel id"

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, "Channel Name", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_update)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntent(Intent(context, MainActivity::class.java))
        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationBuilder.setContentIntent(resultPendingIntent)

        notificationManager.notify(createID(), notificationBuilder.build())

    }

    private fun createID(): Int = Integer.parseInt(
        SimpleDateFormat("ddHHmmss", Locale.ENGLISH).format(
            Date()
        ))
}