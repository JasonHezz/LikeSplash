package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.photo

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoPagedController(
    var callback: AdapterCallbacks? = null
) : PagingEpoxyController<Photo>() {

    var onAvatarClick: ((user: User?) -> Unit)? = null
    var onPhotoClick: ((photo: Photo) -> Unit)? = null

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                requestModelBuild()
            }
        }

    override fun buildModels(list: MutableList<Photo>) {
        list.forEach {
            photo {
                id(it.id)
                photo(it)
                avatarClickListener { model, parentView, clickedView, position ->
                    callback?.onAvatarClick(it.user)
                }
                photoClickListener { model, parentView, clickedView, position ->
                    callback?.onPhotoClick(it)
                }
            }
        }
        loadingModel.addIf(isLoading, this)
    }

    companion object {
        interface AdapterCallbacks {
            fun onAvatarClick(user: User?)
            fun onPhotoClick(it: Photo)
        }
    }
}