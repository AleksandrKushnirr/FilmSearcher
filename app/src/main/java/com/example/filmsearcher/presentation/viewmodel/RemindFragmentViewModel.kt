package com.example.filmsearcher.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearcher.App
import com.example.filmsearcher.domain.entities.Reminder

class RemindFragmentViewModel: ViewModel() {

    private val repository = App.instance.daggerComponent.getRemindersRepository()

    private var mListOfRemindersLiveData: MutableLiveData<List<Reminder>> = MutableLiveData()

    val listOfRemindersLiveData: LiveData<List<Reminder>> = mListOfRemindersLiveData

    fun showReminders() { mListOfRemindersLiveData.value = repository.getRemindersFromDB() }

}