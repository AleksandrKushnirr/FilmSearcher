package com.example.filmsearcher.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.R
import com.example.filmsearcher.domain.entities.Reminder
import com.example.filmsearcher.presentation.recycler.reminds.RemindsAdapter
import com.example.filmsearcher.presentation.viewmodel.RemindFragmentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RemindFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var remindersAdapter: RemindsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var viewModel: RemindFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_remind, container, false)

        val titleText = view.findViewById<TextView>(R.id.text_title)

        initRecycler(view)
        //remindersAdapter.setItems(mutableListOf(Reminder(0,"Мстители", 55545644), Reminder(0,"1+1", 5664545644)))
        viewModel = ViewModelProvider(this).get(RemindFragmentViewModel::class.java)
        viewModel.listOfRemindersLiveData.observe(viewLifecycleOwner, Observer {
            remindersAdapter.setItems(it)
            if (it.isNotEmpty()) titleText.visibility = View.GONE
        })

        viewModel.showReminders()

        val fab = view.findViewById<FloatingActionButton>(R.id.fab_add_reminder)
        fab.setOnClickListener {

            activity?.supportFragmentManager?.commit {
                replace<AddReminderFragment>(R.id.container_frame)
            }
            
        }

        return view
    }

    private fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recycler_remind)
        remindersAdapter = context?.let { RemindsAdapter(it) } !!
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = remindersAdapter
    }

}
