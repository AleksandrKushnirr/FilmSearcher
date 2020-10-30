package com.example.filmsearcher.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.filmsearcher.App
import com.example.filmsearcher.domain.entities.Filter
import com.example.filmsearcher.domain.entities.Reminder

class AddReminderFragmentViewModel : ViewModel() {

    private val interactor = App.instance.daggerComponent.getInteractor()
    private val repository = App.instance.daggerComponent.getRemindersRepository()

    fun onSaveButtonClicked(name: String, date: Long){
        val reminder = Reminder(name = name, date = date)
        repository.insertReminder(reminder)
        interactor.startAlarm(reminder)
    }

}