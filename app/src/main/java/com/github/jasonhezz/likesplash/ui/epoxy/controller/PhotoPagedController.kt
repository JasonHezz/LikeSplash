package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.view.View
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.PhotoModel_

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoPagedController(
        var callback: AdapterCallbacks? = null
) : PagedListEpoxyController<Photo>(EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    override fun buildItemModel(currentPosition: Int, item: Photo?): EpoxyModel<*> {
        return PhotoModel_()
                .id(item?.id)
                .photo(item)
                .avatarClickListener { model, parentView, clickedView, position ->
                    callback?.onAvatarClick(clickedView, item!!.user)
                }
                .photoClickListener { model, parentView, clickedView, position ->
                    callback?.onPhotoClick(item!!)
                }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
        loadingModel.addIf(isLoading, this)
    }

    interface AdapterCallbacks {
        fun onAvatarClick(view: View, user: User?)
        fun onPhotoClick(it: Photo)
    }

}