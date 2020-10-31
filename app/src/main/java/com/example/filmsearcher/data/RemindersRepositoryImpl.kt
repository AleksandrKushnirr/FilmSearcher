package com.example.filmsearcher.data

import com.example.filmsearcher.App
import com.example.filmsearcher.domain.RemindersRepository
import com.example.filmsearcher.domain.entities.Reminder
import javax.inject.Inject

class RemindersRepositoryImpl @Inject constructor() : RemindersRepository {

    private val dao = App.instance.daggerComponent.getReminderDao()

    override fun getRemindersFromDB(): List<Reminder> = dao.getListReminders()
    override fun deleteByName(name: String) = dao.deleteReminderByName(name)
    override fun insertReminder(reminder: Reminder) = dao.insert(reminder)
    override fun updateReminder(reminder: Reminder) = dao.update(reminder)
    override fun deleteReminder(reminder: Reminder) = dao.delete(reminder)

}