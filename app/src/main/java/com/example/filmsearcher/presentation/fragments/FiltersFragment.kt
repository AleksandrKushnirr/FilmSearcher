package com.example.filmsearcher.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.filmsearcher.R
import com.example.filmsearcher.presentation.viewmodel.FilterFragmentViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.slider.RangeSlider


class FiltersFragment: Fragment() {

    private lateinit var viewModel: FilterFragmentViewModel

    private lateinit var chipGroup: ChipGroup
    private lateinit var chipGroupCountries: ChipGroup
    private lateinit var sliderYear: RangeSlider
    private lateinit var sliderRatingKino: RangeSlider
    private lateinit var sliderRatingImdb: RangeSlider
    private lateinit var chipGroupAges: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FilterFragmentViewModel::class.java)
        viewModel.onFilterFragmentCreated()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filters, container, false)

        chipGroup = view.findViewById(R.id.chip_group)
        chipGroupCountries = view.findViewById(R.id.chipGroupCountry)
        chipGroupAges = view.findViewById(R.id.chipGroupAgeLimit)
        sliderYear = view.findViewById(R.id.sliderYear)
        sliderRatingKino = view.findViewById(R.id.sliderRating)
        sliderRatingImdb = view.findViewById(R.id.sliderRatingImdb)

        val buttonShow = view.findViewById<Button>(R.id.buttonShow)

        val listAllCountries = resources.getStringArray(R.array.countries_top).toMutableList()
        val listAllGenres = resources.getStringArray(R.array.genres).toMutableList()
        val listAgeLimit = resources.getStringArray(R.array.age_limit_list).toMutableList()


        viewModel.genresLiveData.observe(viewLifecycleOwner, Observer {
            var counter = 0
            for (genre in listAllGenres) {
                if (counter <= 9) {
                    val newChip = addNewChip(genre, chipGroup, listAllGenres, it)
                    newChip.setOnCheckedChangeListener { buttonView, isChecked -> viewModel.onGenresChanged((buttonView as Chip).text as String, isChecked) }

                } else {
                    val newChip = LayoutInflater.from(context).inflate(R.layout.chip_item, chipGroup, false) as Chip
                    newChip.text = "Еще"
                    chipGroup.addView(newChip)
                    newChip.setOnCheckedChangeListener { buttonView, isChecked ->
                        viewModel.onMoreClicked(isChecked)
                        newChip.visibility = View.GONE
                    }
                    break
                }
                counter++
            }
        })

        viewModel.isMoreChecked.observe(viewLifecycleOwner, Observer {
            if (it) {
                for ((counter, genre) in listAllGenres.withIndex()) {
                    if (counter > 9) {
                        val newChip = addNewChip(genre, chipGroup, listAllGenres, viewModel.genresLiveData.value!!)
                        newChip.setOnCheckedChangeListener { buttonView, isChecked -> viewModel.onGenresChanged((buttonView as Chip).text as String, isChecked) }
                    }
                }
            }
        })

        viewModel.countriesLiveData.observe(viewLifecycleOwner, Observer {
            for (country in listAllCountries) {
                val newChip = addNewChip(country, chipGroupCountries, listAllCountries, it)
                newChip.setOnCheckedChangeListener { buttonView, isChecked -> viewModel.onCountriesChanged((buttonView as Chip).text as String, isChecked) }
            }
        })

        val valuesYear = mutableListOf<Float>()
        viewModel.minYearLiveData.observe(viewLifecycleOwner, Observer { valuesYear.add(it.toFloat()) })
        viewModel.maxYearLiveData.observe(viewLifecycleOwner, Observer {
            valuesYear.add(it.toFloat())
            sliderYear.values = valuesYear
        })

        sliderYear.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            override fun onStopTrackingTouch(slider: RangeSlider) {
                viewModel.onSliderYearChanged(slider.values)
            }
        })

        val valuesRatingKino = mutableListOf<Float>()
        viewModel.minRatingKinoLiveData.observe(viewLifecycleOwner, Observer { valuesRatingKino.add(it.toFloat()) })
        viewModel.maxRatingKinoLiveData.observe(viewLifecycleOwner, Observer {
            valuesRatingKino.add(it.toFloat())
            sliderRatingKino.values = valuesRatingKino
        })

        sliderRatingKino.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            override fun onStopTrackingTouch(slider: RangeSlider) {
                viewModel.onSliderRatingKinoChanged(slider.values)
            }
        })

        val valuesRatingImdb = mutableListOf<Float>()
        viewModel.minRatingImdbLiveData.observe(viewLifecycleOwner, Observer { valuesRatingImdb.add(it.toFloat()) })
        viewModel.maxRatingImdbLiveData.observe(viewLifecycleOwner, Observer {
            valuesRatingImdb.add(it.toFloat())
            sliderRatingImdb.values = valuesRatingImdb
        })

        sliderRatingImdb.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }
            override fun onStopTrackingTouch(slider: RangeSlider) {
                viewModel.onSliderRatingImdbChanged(slider.values)
            }
        })

        viewModel.ageLimitLiveData.observe(viewLifecycleOwner, Observer {
            for (age in listAgeLimit) {
                val newChip = addNewChip(age, chipGroupAges, listAllGenres, it)
                newChip.setOnCheckedChangeListener { buttonView, isChecked -> viewModel.onAgeLimitChanged((buttonView as Chip).text as String, isChecked) }
            }
        })

        buttonShow.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace<AllFragment>(R.id.container_frame)
            }
        }

        return view
    }

    private fun addNewChip(
        name: String,
        chipGroup: ChipGroup,
        entireList: List<String>,
        currentList: MutableList<String>
    ): Chip {
        val newChip = LayoutInflater.from(context).inflate(R.layout.chip_item, chipGroup, false) as Chip
        newChip.text = name
        if (entireList != currentList && currentList.contains(name)) newChip.isChecked = true
        chipGroup.addView(newChip)
        return newChip
    }
}