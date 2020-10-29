package com.example.filmsearcher.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearcher.App
import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.domain.entities.Filter
import com.example.filmsearcher.domain.entities.Reminder

class RemindFragmentViewModel: ViewModel() {

    //private val mainInteractor = App.instance.daggerComponent.getInteractor()
    //private val repository = App.instance.daggerComponent.getRepository()

    private val dao = App.instance.daggerComponent.getReminderDao()

    private var mListOfRemindersLiveData: MutableLiveData<List<Reminder>> = MutableLiveData()

    val listOfRemindersLiveData: LiveData<List<Reminder>> = mListOfRemindersLiveData

    fun showReminders() { mListOfRemindersLiveData.value = dao.getListReminders() }

}