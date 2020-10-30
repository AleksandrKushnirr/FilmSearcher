package com.example.filmsearcher.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Reminder (
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    var name: String,
    var date: Long
)