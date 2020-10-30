package com.example.filmsearcher.di

import com.example.filmsearcher.data.FilmsRepositoryImpl
import com.example.filmsearcher.data.FilterRepositoryImpl
import com.example.filmsearcher.data.RemindersRepositoryImpl
import com.example.filmsearcher.domain.FilmsRepository
import com.example.filmsearcher.domain.FilterRepository
import com.example.filmsearcher.domain.RemindersRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(): FilmsRepository = FilmsRepositoryImpl()

    @Provides
    fun provideFilterRepository(): FilterRepository = FilterRepositoryImpl()

    @Provides
    fun provideRemindersRepository(): RemindersRepository = RemindersRepositoryImpl()
}