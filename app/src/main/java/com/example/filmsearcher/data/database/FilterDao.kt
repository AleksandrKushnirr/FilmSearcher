package com.example.filmsearcher.data.database

import androidx.room.*
import com.example.filmsearcher.domain.entities.Filter

@Dao
interface FilterDao {

    @Query("SELECT * FROM Filter")
    fun getFilter(): Filter

    @Query("SELECT * FROM Filter")
    fun getListFilter(): List<Filter>

    @Query("DELETE FROM Filter WHERE id == 0")
    fun deleteAllRows()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(filter: Filter)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(filter: Filter)

    @Delete
    fun delete(filter: Filter)
}