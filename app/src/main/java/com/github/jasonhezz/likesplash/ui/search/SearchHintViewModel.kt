package com.github.jasonhezz.likesplash.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.SearchHints
import com.github.jasonhezz.likesplash.repository.SearchRepository

class SearchHintViewModel(val searchRepository : SearchRepository) : ViewModel() {

    fun autoComplete(query: String) : LiveData<SearchHints>{
        return searchRepository.autoComplete(query)
    }
}