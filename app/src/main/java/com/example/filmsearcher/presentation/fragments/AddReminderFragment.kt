package com.example.filmsearcher.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.filmsearcher.R
import com.example.filmsearcher.presentation.viewmodel.AddReminderFragmentViewModel
import java.util.*

class AddReminderFragment: Fragment() {

    private lateinit var viewModel: AddReminderFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_reminder, container, false)

        viewModel = ViewModelProvider(this).get(AddReminderFragmentViewModel::class.java)

        val nameInput = view.findViewById<TextView>(R.id.editTextName)
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val buttonSave = view.findViewById<Button>(R.id.buttonSaveAdd)

        var currentDate = calendarView.date

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            currentDate = dateStringToLong(year, month, dayOfMonth)
        }

        buttonSave.setOnClickListener {
            viewModel.onSaveButtonClicked(nameInput.text.toString(), currentDate)
            activity?.supportFragmentManager?.commit {
                replace<RemindFragment>(R.id.container_frame)
            }
        }

        return view
    }

    private fun dateStringToLong(year: Int, month: Int, dayOfMonth: Int): Long{
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.timeInMillis
    }

}