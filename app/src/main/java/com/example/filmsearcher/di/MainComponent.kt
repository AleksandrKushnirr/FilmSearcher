package com.example.filmsearcher.di

import android.app.Application
import com.example.filmsearcher.data.FilmsRepositoryImpl
import com.example.filmsearcher.data.FilterRepositoryImpl
import com.example.filmsearcher.data.database.AppDatabase
import com.example.filmsearcher.data.database.FilmDao
import com.example.filmsearcher.data.database.FilterDao
import com.example.filmsearcher.data.database.ReminderDao
import com.example.filmsearcher.data.web.FilmsApiService
import com.example.filmsearcher.domain.MainInteractor
import com.example.filmsearcher.domain.RemindersRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, AppContextModule::class, RoomModule::class, RetrofitModule::class])
interface MainComponent {

    fun getInteractor(): MainInteractor
    fun getRepository(): FilmsRepositoryImpl
    fun getFilterRepository(): FilterRepositoryImpl
    fun getRemindersRepository(): RemindersRepository

    fun getAppContext(): Application
    fun getFilmDao(): FilmDao
    fun getFilterDao(): FilterDao
    fun getReminderDao(): ReminderDao
    fun getAppDatabase(): AppDatabase

    fun getFilmApiService(): FilmsApiService

}