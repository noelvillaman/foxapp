package com.noelvillaman.software.foxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @BindView(R.id.feedItemsContainer)
    internal lateinit var listFeedItemsView: RecyclerView
    private var viewModel: FeedItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listFeedItemsView = findViewById(R.id.feedItemsContainer)
        viewModel = ViewModelProvider(this).get(FeedItemsViewModel::class.java)

        listFeedItemsView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        listFeedItemsView.adapter = FeedItemsAdapter(viewModel!!, this)
        listFeedItemsView.layoutManager = LinearLayoutManager(this)
        showListFeedItems()
    }

    private fun showListFeedItems(){
        val nameObserver = Observer<List<FeedItems>> { feedItem ->
            if (feedItem != null) {
                listFeedItemsView.visibility = View.VISIBLE
            }
        }
        viewModel!!.getItems().observe(this, nameObserver)
    }
}