package com.example.filmsearcher.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule (val appContext: Application) {

    @Provides
    @Singleton
    fun provideAppContext(): Application = appContext
}