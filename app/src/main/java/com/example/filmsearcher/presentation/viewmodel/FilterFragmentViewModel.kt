package com.example.filmsearcher.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmsearcher.App
import com.example.filmsearcher.domain.entities.Filter

class FilterFragmentViewModel: ViewModel() {

    private val mainInteractor = App.instance.daggerComponent.getInteractor()
    private val filterRepository = App.instance.daggerComponent.getFilterRepository()

    private lateinit var filter: Filter

    private var mGenresLiveData: MutableLiveData<MutableList<String>> = MutableLiveData()
    private var mCountriesLiveData: MutableLiveData<MutableList<String>> = MutableLiveData()
    private var mMinYearLiveData: MutableLiveData<Int> = MutableLiveData()
    private var mMaxYearLiveData: MutableLiveData<Int> = MutableLiveData()
    private var mMinRatingKinoLiveData: MutableLiveData<Double> = MutableLiveData()
    private var mMaxRatingKinoLiveData: MutableLiveData<Double> = MutableLiveData()
    private var mMinRatingImdbLiveData: MutableLiveData<Double> = MutableLiveData()
    private var mMaxRatingImdbLiveData: MutableLiveData<Double> = MutableLiveData()
    private var mAgeLimitLiveData: MutableLiveData<MutableList<String>> = MutableLiveData()

    private var mIsMoreChecked: MutableLiveData<Boolean> = MutableLiveData()

    val genresLiveData: LiveData<MutableList<String>> = mGenresLiveData
    val countriesLiveData: LiveData<MutableList<String>> = mCountriesLiveData
    var minYearLiveData: LiveData<Int> = mMinYearLiveData
    var maxYearLiveData: LiveData<Int> = mMaxYearLiveData
    var minRatingKinoLiveData: LiveData<Double> = mMinRatingKinoLiveData
    var maxRatingKinoLiveData: LiveData<Double> = mMaxRatingKinoLiveData
    var minRatingImdbLiveData: LiveData<Double> = mMinRatingImdbLiveData
    var maxRatingImdbLiveData: LiveData<Double> = mMaxRatingImdbLiveData
    var ageLimitLiveData: LiveData<MutableList<String>> = mAgeLimitLiveData

    var isMoreChecked: LiveData<Boolean> = mIsMoreChecked


    fun onFilterFragmentCreated() {
        filter = mainInteractor.getFilter()
        mGenresLiveData.value = filter.genres
        mCountriesLiveData.value = filter.countries
        mMinYearLiveData.value = filter.minYear
        mMaxYearLiveData.value = filter.maxYear
        mMinRatingKinoLiveData.value = filter.minRatingKino
        mMaxRatingKinoLiveData.value = filter.maxRatingKino
        mMinRatingImdbLiveData.value = filter.minRatingImdb
        mMaxRatingImdbLiveData.value = filter.maxRatingImdb
        mAgeLimitLiveData.value = filter.ageLimit
    }

    fun onGenresChanged(genre: String, isChange: Boolean) {

        if (isChange && mGenresLiveData.value!!.contains(genre)) {
            filter.genres.removeAll(mGenresLiveData.value!!)
            filter.genres.add(genre)
        } else if (isChange) {
            filter.genres.add(genre)
        } else {
            filter.genres.remove(genre)
        }
        filterRepository.updateFilter(filter)

    }

    fun onCountriesChanged(country: String, isChange: Boolean) {

        if (isChange && mCountriesLiveData.value!!.contains(country)) {
            filter.countries.removeAll(mCountriesLiveData.value!!)
            filter.countries.add(country)
        } else if (isChange) {
            filter.countries.add(country)
        } else {
            filter.countries.remove(country)
        }
        filterRepository.updateFilter(filter)
    }

    fun onSliderYearChanged(values: MutableList<Float>) {
        filter.minYear = values[0].toInt()
        filter.maxYear = values[1].toInt()
        filterRepository.updateFilter(filter)
    }

    fun onSliderRatingKinoChanged(values: MutableList<Float>) {
        filter.minRatingKino = values[0].toDouble()
        filter.maxRatingKino = values[1].toDouble()
        filterRepository.updateFilter(filter)
    }

    fun onSliderRatingImdbChanged(values: MutableList<Float>) {
        filter.minRatingImdb = values[0].toDouble()
        filter.maxRatingImdb = values[1].toDouble()
        filterRepository.updateFilter(filter)
    }

    fun onAgeLimitChanged(age: String, isChange: Boolean) {
       if (isChange) filter.ageLimit.add(age) else filter.ageLimit.remove(age)
        filterRepository.updateFilter(filter)
    }

    fun onMoreClicked(isChange: Boolean) {
        mIsMoreChecked.value = isChange
    }
}