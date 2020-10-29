package com.example.filmsearcher.di

import android.app.Application
import androidx.room.Room
import com.example.filmsearcher.data.database.AppDatabase
import com.example.filmsearcher.data.database.FilmDao
import com.example.filmsearcher.data.database.FilterDao
import com.example.filmsearcher.data.database.ReminderDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule (mAppContext: Application) {

    private val appDatabase = Room
        .databaseBuilder(mAppContext, AppDatabase::class.java, "main_database")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase = appDatabase

    @Singleton
    @Provides
    fun providesFilmDao(appDatabase: AppDatabase): FilmDao = appDatabase.filmDao()

    @Singleton
    @Provides
    fun providesFilterDao(appDatabase: AppDatabase): FilterDao = appDatabase.filterDao()

    @Singleton
    @Provides
    fun providesReminderDao(appDatabase: AppDatabase): ReminderDao = appDatabase.reminderDao()

}