package com.example.filmsearcher.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.R
import com.example.filmsearcher.presentation.recycler.FilmsAdapter
import com.example.filmsearcher.presentation.viewmodel.AllFragmentViewModel

class LikedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var filmsAdapter: FilmsAdapter
    private lateinit var viewModel: AllFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liked, container, false)
        initRecycler(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this).get(AllFragmentViewModel::class.java)
        viewModel.listOfLikedFilmsLiveData.observe(viewLifecycleOwner, Observer {
            filmsAdapter.setItems(it)
        })

        viewModel.showLikedFilms()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewLikedCards)
        filmsAdapter = context?.let { FilmsAdapter(it) } !!
        recyclerView.adapter = filmsAdapter
    }

}