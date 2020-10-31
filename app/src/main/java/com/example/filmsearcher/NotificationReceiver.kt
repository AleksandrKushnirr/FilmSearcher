package com.example.filmsearcher

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.filmsearcher.presentation.activities.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "updatedDatabase"){
            showNotification("Film Searcher", "Обновление успешно. Ждем тебя!", context)
        }
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