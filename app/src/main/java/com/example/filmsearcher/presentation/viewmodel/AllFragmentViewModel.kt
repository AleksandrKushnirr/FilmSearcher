package com.example.filmsearcher.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearcher.App
import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.domain.entities.Filter

class AllFragmentViewModel : ViewModel() {

    private val mainInteractor = App.instance.daggerComponent.getInteractor()
    private val repository = App.instance.daggerComponent.getRepository()

    private lateinit var filter: Filter

    private var mListOfFilmsLiveData: MutableLiveData<List<Film>> = MutableLiveData()
    private var mListOfLikedFilmsLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    val listOfFilmsLiveData: LiveData<List<Film>> = mListOfFilmsLiveData
    val listOfLikedFilmsLiveData: LiveData<List<Film>> = mListOfLikedFilmsLiveData

    fun showLikedFilms() { mListOfLikedFilmsLiveData.value = mainInteractor.getLikedFilms() }

    fun showAllFilms() {
        filter = mainInteractor.getFilter()
        mListOfFilmsLiveData.value = mainInteractor.getFilms(filter)
    }

    fun onSwipeRefreshed() {
        repository.loadNewFilms()
    }

}