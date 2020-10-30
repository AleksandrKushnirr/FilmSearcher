package com.example.filmsearcher.presentation.fragments

import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.filmsearcher.*
import com.example.filmsearcher.presentation.recycler.films.EndlessRecyclerOnScrollListener
import com.example.filmsearcher.presentation.recycler.films.FilmsAdapter
import com.example.filmsearcher.presentation.viewmodel.AllFragmentViewModel

class AllFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var filmsAdapter: FilmsAdapter
    private lateinit var viewModel: AllFragmentViewModel
    private val receiver = InternetConnectionReceiver()

    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_all, container, false)

        viewModel = ViewModelProvider(this).get(AllFragmentViewModel::class.java)

        initRecycler(view)
        return view
    }


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        viewModel.listOfFilmsLiveData.observe(viewLifecycleOwner, Observer {
            filmsAdapter.setItems(it)
            handler.postDelayed( {
                if (viewModel.listOfFilmsLiveData.value!!.size < 5) {
                    viewModel.showAllFilms()
                }
            }, 500)
        })

        viewModel.showAllFilms()

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeswipe)
        handler = Handler(Looper.getMainLooper())
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onSwipeRefreshed()
            handler.postDelayed( {
                viewModel.showAllFilms()
                swipeRefreshLayout.isRefreshing = false } , 2000)
        }


        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore() {
                viewModel.showAllFilms()
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewCards_v2)
        filmsAdapter = context?.let { FilmsAdapter(it) } !!
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = filmsAdapter
    }

    override fun onResume() {
        context?.registerReceiver(receiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
        super.onResume()
    }

    override fun onPause() {
        context?.unregisterReceiver(receiver)
        super.onPause()
    }
}
