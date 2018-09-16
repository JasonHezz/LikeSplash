package com.github.jasonhezz.likesplash.ui.photodetail

import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.repository.PhotoRepository

class PhotoDetailViewModel(
        val id: String,
        val repository: PhotoRepository
) : ViewModel() {

    val result = repository.getAPhoto(id)
}