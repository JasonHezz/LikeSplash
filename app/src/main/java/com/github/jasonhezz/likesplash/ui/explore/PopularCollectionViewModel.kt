package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.ExploreCollection
import com.github.jasonhezz.likesplash.repository.MockRepository

/**
 * Created by JavaCoder on 2018/1/4.
 */
class PopularCollectionViewModel(val repository: MockRepository) : ViewModel() {
    val result: LiveData<List<ExploreCollection>> = repository.getListExploreCollection()
}