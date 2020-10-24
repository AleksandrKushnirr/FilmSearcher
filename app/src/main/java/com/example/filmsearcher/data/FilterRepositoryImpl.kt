package com.example.filmsearcher.data

import com.example.filmsearcher.App
import com.example.filmsearcher.domain.FilterRepository
import com.example.filmsearcher.domain.entities.Filter
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor() : FilterRepository {

    private val filterDao = App.instance.daggerComponent.getFilterDao()

    override fun getFilterFromDB(): Filter = filterDao.getFilter()
    override fun deleteAllFilters() = filterDao.deleteAllRows()
    override fun insertFilter(filter: Filter) = filterDao.insert(filter)
    override fun updateFilter(filter: Filter) = filterDao.update(filter)
    override fun deleteFilter(filter: Filter) = filterDao.delete(filter)
}