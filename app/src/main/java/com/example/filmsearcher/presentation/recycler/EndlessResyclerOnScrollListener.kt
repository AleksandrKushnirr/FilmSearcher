package com.example.filmsearcher.presentation.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class EndlessRecyclerOnScrollListener(
    private val mLinearLayoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private val visibleThreshold = 3

    override fun onScrolled(
        recyclerView: RecyclerView,
        dx: Int,
        dy: Int
    ) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        if (totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && dy > 0) {
            onLoadMore()
        }
    }

    abstract fun onLoadMore()
}