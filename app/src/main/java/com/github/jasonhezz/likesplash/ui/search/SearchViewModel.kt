package com.github.jasonhezz.likesplash.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.repository.SearchRepository

class SearchViewModel(val searchRepository: SearchRepository) : ViewModel() {
    var query = MutableLiveData<String>()
    var hints = Transformations.switchMap(query) {
        searchRepository.autoComplete(it)
    }!!
}