package com.example.filmsearcher.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsearcher.R


class RemindFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_remind, container, false)

        /*val buttonSave = view.findViewById<Button>(R.id.buttonSaveAdd)
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val info = editTextInformation.text.toString()

            Toast.makeText(view.context, "Film added.", Toast.LENGTH_SHORT).show()
        }*/

        return view
    }

}
