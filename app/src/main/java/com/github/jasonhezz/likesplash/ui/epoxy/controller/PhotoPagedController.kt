package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.photo
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoPagedController(
    var callback: AdapterCallbacks? = null
) : PagingEpoxyController<Photo>() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

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

    interface AdapterCallbacks {
        fun onAvatarClick(user: User?)
        fun onPhotoClick(it: Photo)
    }

}