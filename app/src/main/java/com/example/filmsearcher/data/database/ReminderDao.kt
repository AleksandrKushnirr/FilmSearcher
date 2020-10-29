package com.example.filmsearcher.data.database

import androidx.room.*
import com.example.filmsearcher.domain.entities.Filter
import com.example.filmsearcher.domain.entities.Reminder

@Dao
interface ReminderDao {

    @Query("SELECT * FROM Reminder")
    fun getListReminders(): List<Reminder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reminder: Reminder)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(reminder: Reminder)

    @Delete
    fun delete(reminder: Reminder)
}