package com.example.filmsearcher.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.filmsearcher.R

class AddReminderFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_reminder, container, false)
        val buttonSave = view.findViewById<Button>(R.id.buttonSaveAdd)
        buttonSave.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace<RemindFragment>(R.id.container_frame)
            }
        }
        return view
    }

}