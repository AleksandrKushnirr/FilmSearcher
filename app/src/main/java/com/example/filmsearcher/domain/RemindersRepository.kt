package com.example.filmsearcher.domain

import com.example.filmsearcher.domain.entities.Reminder

interface RemindersRepository {

    fun getRemindersFromDB(): List<Reminder>
    fun deleteByName(name: String)
    fun insertReminder(reminder: Reminder)
    fun updateReminder(reminder: Reminder)
    fun deleteReminder(reminder: Reminder)

}