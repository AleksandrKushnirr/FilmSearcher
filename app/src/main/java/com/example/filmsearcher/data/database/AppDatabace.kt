package com.example.filmsearcher.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.filmsearcher.domain.entities.Film
import com.example.filmsearcher.domain.entities.Filter
import com.google.gson.Gson


@Database(entities = [Film::class, Filter::class], version = 20)
@TypeConverters(MyConverters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun filmDao(): FilmDao
    abstract fun filterDao(): FilterDao
}

class MyConverters {
    @TypeConverter
    fun listToJson(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }

}
