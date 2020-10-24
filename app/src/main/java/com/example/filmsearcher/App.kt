package com.example.filmsearcher

import android.app.Application
import com.example.filmsearcher.di.*

class App : Application() {

    companion object{
        lateinit var instance: App
        const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    }

    lateinit var daggerComponent: MainComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        daggerComponent = DaggerMainComponent
            .builder()
            .appContextModule(AppContextModule(instance))
            .roomModule(RoomModule(instance))
            .retrofitModule(RetrofitModule())
            .build()
    }
}

