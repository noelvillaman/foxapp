package com.noelvillaman.software.foxapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import java.util.ArrayList

class FeedItemsAdapter internal constructor(
        viewModel: FeedItemsViewModel,
        lifecycleOwner: LifecycleOwner
    ) : RecyclerView.Adapter<FeedItemsAdapter.RepoViewHolder>() {

        private val data = ArrayList<FeedItems>()

        init {
            viewModel.feedItems.observe(lifecycleOwner, Observer { feedItem ->
                data.clear()
                if (feedItem != null) {
                    data.addAll(feedItem)
                }
                notifyDataSetChanged()
            })
            setHasStableIds(true)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.feed_list_items_view, parent, false)
            return RepoViewHolder(view)
        }

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
            holder.bind(data[position])
        }

        override fun getItemCount() = data.size


        override fun getItemId(position: Int): Long {
            return data[position].id!!
        }

        class RepoViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

            @BindView(R.id.tvtx_feedItem_title)
            lateinit var itemNameTextView: TextView
            @BindView(R.id.txtv_feedItem_desc)
            lateinit var itemDescriptionTextView: TextView


            private var feedItems: FeedItems

            init {
                ButterKnife.bind(this, itemView)
                feedItems = FeedItems()
                itemNameTextView = itemView.findViewById(R.id.tvtx_feedItem_title)
                itemDescriptionTextView = itemView.findViewById(R.id.txtv_feedItem_desc)
            }

            fun bind(feedItems1: FeedItems) {
                this.feedItems = feedItems1
                itemNameTextView.text = feedItems1.name
                itemDescriptionTextView.text = feedItems1.description
            }
        }
    }