package com.noelvillaman.software.foxapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FeedItemsViewModel : ViewModel(){
    val feedItems = MutableLiveData<List<FeedItems>>()
    @Inject
    lateinit var mockClient: MockClient

    init {
        fetchListItems()
    }

    internal fun getItems(): LiveData<List<FeedItems>> {
        return feedItems
    }

    fun fetchListItems(){
        viewModelScope.launch(Dispatchers.IO) {
            feedItems.value = mockClient.getFeed()
        }
    }
}