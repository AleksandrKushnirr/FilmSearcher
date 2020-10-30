package com.example.filmsearcher.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.filmsearcher.App
import com.example.filmsearcher.domain.entities.Filter
import com.example.filmsearcher.domain.entities.Reminder

class AddReminderFragmentViewModel : ViewModel() {

    private val repository = App.instance.daggerComponent.getRemindersRepository()

    fun onSaveButtonClicked(name: String, date: Long) = repository.insertReminder(Reminder(name = name, date = date))

}