package com.github.jasonhezz.likesplash.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.repository.SearchRepository

class SearchViewModel(val searchRepository: SearchRepository) : ViewModel() {
    var query = MutableLiveData<String>()
    var hints = Transformations.switchMap(query) {
        searchRepository.autoComplete(it)
    }!!
}