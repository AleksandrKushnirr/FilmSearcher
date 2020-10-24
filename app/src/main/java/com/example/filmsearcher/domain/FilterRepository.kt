package com.example.filmsearcher.domain

import com.example.filmsearcher.domain.entities.Filter

interface FilterRepository {

    fun getFilterFromDB(): Filter
    fun deleteAllFilters()
    fun insertFilter(filter: Filter)
    fun updateFilter(filter: Filter)
    fun deleteFilter(filter: Filter)

}